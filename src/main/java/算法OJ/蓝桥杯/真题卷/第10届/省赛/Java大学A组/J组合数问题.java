package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;

import java.util.Scanner;
/**
 已AC
 */
public class J组合数问题 {
    /*
    求满足C(i,j)%k==0的数对(i,j)的个数
    其中 1 <= i <= n , 0 <= j <= m
    k是质数
     */

    static int Mod = 10_0000_0007;

    /**
     <h2>卢卡斯定理</h2>
     卢卡斯定理: C(n,m) % p = C( n/p, m/p ) * C( n%p, m%p ) % p<br>
     <p>
     C(i,j)可以拆解为多个C(a,b)相乘<br>
     例如:<br>
     C(99,47) % 5<br>
     = C(9,19) * C(4,2) % 5<br>
     = C(3,1) * (C4,4) * C(4,2) % 5<br>
     这个操作和求k进制数是一样的, 344是99的5进制数,142是47的5进制数<br>
     所以C(i,j) % k 的拆分结果是 Mul { C(i[k][t],j[k][t]) } % k<br>
     其中i[k][t]表示i在k进制下的第t位数字<br>
     <p>
     因为C(i,j)%k==0,所以它的拆分中需要至少存在一项C(a,b)%k==0<br>
     C(a,b) = 0  ==>  a < b<br>
     即在k进制下,i需要至少一位数字小于j的对应位数字<br>
     <h2>数位dp</h2>
     本题问题转变为,有多少组(i,j)在k进制下i有至少一位数小于j的对应位数字<br>
     正难则反:<br>
     求出a>=b的全部方案,减去a每一位都大于等于b每一位的方案数<br>
     即可得到a至少有一位小于b对应位的方案数, 即C(a, b) % k = 0的方案数<br>
     数位枚举a和b,对于每一位的取值可以有三种情况:<br>
     cal(x,y):   a取值[0,x] 和 b取值[0,y] 情况下 a >= b 的种类数  (x + 2) * (x + 1) / 2<br>
     cal_1(x,y): a取定值x 和 b取值[0,y] 情况下 a >= b 的种类数  Math.min(x, y) + 1<br>
     cal_2(x,y): a取值[0,x] 和 b取定值y 情况下 a >= b 的种类数  x < y ? 0 : x - y + 1<br>
     而每一位的取值和前面的位是否取到 n 和 m 的上界有关<br>
     <p>
     考虑如下4个状态:<br>
     dp[i][0] a和b前 i 位都未达到n和m的上界<br>
     dp[i][1] a前 i 位达到n上界, b未达到m上界<br>
     dp[i][2] a前 i 位未达到n上界, b达到m上界<br>
     dp[i][3] 前 i 位a和b都达到n和m上界<br>
     <p>
     在前面都到达上界时,下一位的取值是[0,n[i]-1]<br>
     在前面没有都到达上界时,k进制数下任意取,[0,k-1]<br>
     (1) dp[i][0]<br>
     由dp[i - 1][0]转移:<br>
     由于前面未到上界, 因此第i位a和b的取值范围为[0, k - 1]<br>
     由dp[i - 1][1]转移:<br>
     由于前i-1位a已达n上界, 因此a的第i位必须取小于n对应位的数<br>
     而前面b未达到上界, 因此b的第i位可以取值[0, k - 1]。<br>
     由dp[i - 1][2]转移:<br>
     a的第i位可取[0, k - 1], b的第i位必须要取小于m对应位的数。<br>
     由dp[i - 1][3]转移:<br>
     由于前面都达到上界, 因此a和b都必须取小于对应位的数。<br>
     (2) dp[i][1]<br>
     a要到达上界,那么前面都要到达上界,只能由dp[i - 1][1]和dp[i - 1][3]转移<br>
     由dp[i - 1][1]转移:<br>
     前面a都到达上界,这位也到达上界,a只能取n[i],b未到达上界,取值范围[0,k-1]<br>
     由dp[i - 1][3]转移:<br>
     a只能取n[i],b前面到达上界,这位不能到达上界,取值范围为[0,m[i]-1]<br>
     (3) dp[i][2]<br>
     与(2)同理,由dp[i - 1][2]和dp[i - 1][3]转移<br>
     (4) dp[i][3]<br>
     a要在该位到达上界,前面取的都需要是上界,b同理<br>
     所以ab前面都取的是上界,所以只能由dp[i - 1][3]转移<br>
     <p>
     */
    public static void main(String[] args) {
        long T = sc.nextLong();
        k = sc.nextLong();
        for (int i = 0; i < T; i++) {
            System.out.println(solve());
        }
    }

    static Scanner sc = new Scanner(System.in);//坑点:不要用StreamTokenizer,大数double转long有精度损失
    static long k;

    private static long solve() {
        long n = sc.nextLong(), m = sc.nextLong();
        if (m > n) m = n;
        long ans = cal(n, m);
        Arr changeN = changeK(n), changeM = changeK(m);
        int len = changeN.len;
        int[] n_k = changeN.res, m_k = changeM.res;
        long[][] dp = new long[len + 1][4];
        dp[len][3] = 1;//都没放数时为1
        for (int i = len - 1; i >= 0; i--) { //高位到低位
            int ni = n_k[i], mi = m_k[i];
            //dp[i][0]:变为都未到达上界
            dp[i][0] = dp[i + 1][0] * cal(k - 1, k - 1) % Mod// 都未到达上界
                    + dp[i + 1][1] * cal(ni - 1, k - 1) % Mod// a上界->非上界
                    + dp[i + 1][2] * cal(k - 1, mi - 1) % Mod // b上界->非上界
                    + dp[i + 1][3] * cal(ni - 1, mi - 1) % Mod; //都上界
            //dp[i][1]:变为a到达上界(a上界->上界),b未到达上界(b非上界->非上界/上界)
            dp[i][1] = dp[i + 1][1] * cal_1(ni, k - 1) //a定值,b未到达上界
                    + dp[i + 1][3] * cal_1(ni, mi - 1); //a定值,b到达上界
            //dp[i][2]:变为b到达上界,a未到达上界...
            dp[i][2] = dp[i + 1][2] * cal_2(k - 1, mi) //b定值,a未到达上界
                    + dp[i + 1][3] * cal_2(ni - 1, mi); //b定值,a到达上界
            //dp[i][3]:变为ab都到达上界
            dp[i][3] = (dp[i + 1][3] == 1 && ni >= mi) ? 1 : 0;

            for (int j = 0; j < 4; j++) {//模Mod,放最后集体处理
                dp[i][j] %= Mod;
            }
        }
        ans -= dp[0][0] + dp[0][1] + dp[0][2] + dp[0][3];
        return (ans % Mod + Mod) % Mod;
    }


    static Arr changeK(long n) {
        int[] res = new int[100]; //1e18,转二进制的话由大概60位
        int len = 0;
        while (n > 0) {
            res[len++] = ((int) (n % k));
            n /= k;
        }
        return new Arr(res, len);
    }

    static class Arr {
        int[] res;
        int len;

        public Arr(int[] res, int len) {
            this.res = res;
            this.len = len;
        }
    }

    /**
     a>=b
     a取值[0, x], b取值[0, y]
     (1) x <= y:
     a取k, b取[0,k]
     总可能数为 1 + 2 + 3 + ... + (x + 1) = (x + 2) * (x + 1) / 2
     (2) x > y:
     在a取值[y+1,x]上时, b在[0,y]全部能取 => (x - y) * (y + 1)
     a取值[0,y]上,等价与情况(1)  => (y + 2) * (y + 1) / 2
     */
    static long cal(long x, long y) {
        if (x < 0 || y < 0) return 0;
        if (x <= y) {
            x %= Mod;
            return (x + 2) * (x + 1) / 2 % Mod;
        }
        x %= Mod;
        y %= Mod;
        return ((y + 2) * (y + 1) / 2 % Mod + (x - y) * (y + 1) % Mod) % Mod;
    }

    static long cal_1(long x, long y) {
        return Math.min(x, y) + 1;
    }

    static long cal_2(long x, long y) {
        return Math.max(0, x - y + 1);
    }
}

