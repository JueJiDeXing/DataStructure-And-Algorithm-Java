package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class I异或三角 {
/*
T次询问
每次询问求[1,n]上的三元组数目,三元组需满足 a^b^c==0 且 能构成三角形

每个数位,分配2个1,1个0 (或者全为0)
 */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            System.out.println(solve(n) * 3);//三元组位置轮换
        }
    }


    static long solve(int n) {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int cnt = 0;
        while (n > 0) {
            cnt += 1;
            num[cnt] = n & 1;
            n >>= 1;
        }
        return dfs(cnt, true, 0);
    }

    /**
     设 n >= a >= b >= c >= 1 <br>
     abc每个数位都有2个1,1个0 <br>
     1. a>b, 所以(ai,bi)=(1,0)必须出现在(aj,bj)=(0,1)前面 <br>
     a  ? 0 1 <br>
     b  ? 1 0   -> a的?处必然需要填1,而剩下的1无论分给b还是c都会导致a>b&&a>c不成立 <br>
     c  ? 1 1 <br>
     <br>
     2. a>c, 所以(ai,bi)=(1,1)必须出现在(aj,bj)=(0,1)前面 <br>
     a  ? 0 1 <br>
     b  ? 1 1   -> 同理: a的?处必然需要填1,而剩下的1无论分给b还是c都会导致a>b&&a>c不成立 <br>
     c  ? 1 0 <br>
     <br>
     3. a = b^c < b+c,所以必然存在状态(ai,bi)=(0,1) <br>
     因为b^c是不进位加法,0^0=0+0,0^1=0+1,唯有(bi,ci)=(1,1)时 bi^ci=0 < bi+ci <br>
     <br>
     所以当枚举到a的最后,(0,1),(1,0),(1,1)都存在时,则为有效情况 <br>
     // 因为(0,1)必然出现,又有(0,1)出现时,前面一定有(1,0)和(1,1) <br>

     @param i      当前a所在的二进制位数
     @param limit  前面所选择的数是否全部为上限数
     @param states 是否出现过上述三种状态 1->(0,1),2->(1,0),4->(1,1)
     @return
     */
    static long dfs(int i, boolean limit, int states) {

        if (i == 0) return states == 7 ? 1 : 0;
        int idx = limit ? 1 : 0;
        if (dp[i][idx][states] != -1) return dp[i][idx][states];

        int up = limit ? num[i] : 1;
        long res = 0;
        for (int d = 0; d <= up; d++) {
            if (d == 0) { //如果该位置上a为0
                //1. (0, 0, 0)
                res += dfs(i - 1, limit && d == up, states);
                //2. (0, 1)
                if (states >= 6) //选(0, 1) 时，(1, 1),(1, 0)必须要出现
                    res += dfs(i - 1, limit && d == up, states | 1);// 110 | 001 = 111
            } else {// d == 1
                //3. (1, 0)
                res += dfs(i - 1, limit && d == up, states | 2); //x0x | 010 = x1x
                //4. (1, 1)
                res += dfs(i - 1, limit && d == up, states | 4); //0xx | 100 = 1xx
            }
        }
        return dp[i][idx][states] = res;
    }


    static long[][][] dp = new long[32][2][8];
    static int[] num = new int[32];


    static long slove(int n) {
        //初始化memo为-1
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int m = 0; m < 2; m++) {
                            memo[i][j][k][l][m] = -1;
                        }
                    }
                }
            }
        }
        //取n的二进制到num数组
        int len = 0;
        while (n > 0) {
            num[++len] = n & 1;
            n >>= 1;
        }
        return dfs(len, true, true, false, false);

    }

    static long[][][][][] memo = new long[32][2][2][2][2];

    /**
     @param len    剩余的枚举位数
     @param limit1
     @param limit2
     @param ok1
     @param ok2
     @return
     */
    static long dfs(int len, boolean limit1, boolean limit2, boolean ok1, boolean ok2) {
        if (len == 0) return ok1 && ok2 ? 1 : 0;
        int f1 = limit1 ? 1 : 0, f2 = limit2 ? 1 : 0;
        int f3 = ok1 ? 1 : 0, f4 = ok2 ? 1 : 0;
        if (memo[len][f1][f2][f3][f4] != -1) return memo[len][f1][f2][f3][f4];
        int up1 = limit1 ? num[len] : 1;
        long res = 0;
        for (int i = 0; i <= up1; i++) {
            int up2 = limit2 ? i : 1;
            for (int j = 0; j <= up2; j++) {
                if (!ok1 && i == 0 && j != 0) continue;
                res += dfs(len - 1, limit1 && i == up1, limit2 && j == up2,
                        ok1 || (i == j && j == 1), ok2 || (i != j && j == 1));
            }
        }
        return memo[len][f1][f2][f3][f4] = res;
    }
}
