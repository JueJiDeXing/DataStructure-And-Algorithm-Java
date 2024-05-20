package 算法.数学.组合数学.容斥原理;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ICPC_互质对的数位和 {
    /*
    求 sum{ sum{ F(j) | j=1->i } | i=1->n }
    其中F(j)表示j的数位和
     */

    /**
     贡献法, 计算i的贡献, 即F[i] * 数量
     数量 = i后面与i互质的数的个数 = (n-i) - i后面不与i互质的数个数
     <p>
     i后面不与i互质的数个数 可以 枚举i的质因子集
     假设枚举出的质因子集相乘为ret, 则 i~n上有 (n-i) / ret 个ret的倍数, 即有 (n-i) / ret 个数与i不互质
     但是枚举出来的是有重叠的, 需要使用容斥原理, 奇数个因子则计入"不互质", 偶数个因子则从"不互质"中减去
     */
    public static void main(String[] args) throws Exception {
        long n = L();
        init(n);
        //对于每一个i, 找[i+1,n]里有多少个个数和它互质
        long ans = 1;//f(1)
        for (int i = 1; i < n; i++) {

            int m = p[i].size();// i的因数个数
            long tmp = 0;  // 与i不互质的数的个数
            for (int j = 1; j < (1 << m); j++) {// 枚举因数集
                long ret = 1;  //当前枚举状态的所有质因数的乘积
                for (int k = 0; k < m; k++) {
                    if ((j & (1 << k)) != 0) ret *= p[i].get(k);
                }
                long now = (n - i) / ret;  //有多少个数是ret的倍数

                int op = (Integer.bitCount(j) % 2 == 1) ? 1 : -1; //容斥,奇加偶减
                tmp += op * now;
            }
            ans += (n - i - tmp) * f[i];
        }
        System.out.println(ans);
    }

    static Scanner sc = new Scanner(System.in);

    static long L() {
        return sc.nextLong();
    }

    static int N = (int) (1e5 + 7);
    static boolean[] vis = new boolean[N];
    static long[] f = new long[N];  //数位和

    static List<Integer> prime = new ArrayList<>();  //质数表
    static List<Integer>[] p = new List[N];   // p[i]里存着i的所有质因子


    static int F(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }
        return res;
    }


    static void init(long n) {
        Arrays.setAll(p, i -> new ArrayList<>());
        //筛出所有质数
        for (int i = 2; i < N; i++) {
            if (!vis[i]) {
                prime.add(i);
            }
            for (int j = 0; j < prime.size(); j++) {
                if (i * prime.get(j) >= N) break;
                vis[i * prime.get(j)] = true;
                if (i % prime.get(j) == 0) break;
            }
        }
        //筛出每个数所有的质因数
        for (int i = 2; i <= n; i++) {
            int x = i;
            for (int j = 0; j < prime.size(); j++) {
                int v = prime.get(j);
                if (v * v > x) break;
                if (x % v == 0) {
                    p[i].add(v);
                    while (x % v == 0) x /= v;
                }
            }
            if (x != 1) p[i].add(x);
        }
        for (int i = 1; i <= n; ++i) f[i] = F(i);  //得到每个数的数位和
    }
}
