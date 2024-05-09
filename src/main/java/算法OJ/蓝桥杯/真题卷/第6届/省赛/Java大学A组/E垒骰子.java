package 算法OJ.蓝桥杯.真题卷.第6届.省赛.Java大学A组;

import java.io.*;
import java.util.Arrays;

/**
 已AC
 */
public class E垒骰子 {
    static int MOD = 10_0000_0007;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int[] op = new int[]{-1, 4, 5, 6, 1, 2, 3};//对面

    public static void main(String[] args) {
        int n = Int(), m = Int();
        solve2(n, m);
    }

    private static void solve1(int n, int m) {
        boolean[][] isEx = new boolean[7][7];//是否冲突
        for (int i = 0; i < m; i++) {
            int a = Int(), b = Int();
            isEx[a][b] = true;
            isEx[b][a] = true;
        }
        int[][] dp = new int[n + 1][7];//dp[i][j]:垒了i个骰子,最后一个上面是数字j
        //dp[i][j] = sum{ dp[i-1][k] | k与j的对面不冲突}
        Arrays.fill(dp[1], 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 6; j++) {
                for (int k = 1; k <= 6; k++) {
                    if (isEx[k][op[j]]) continue;
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }
        int sum = 0;
        for (int i : dp[n]) sum += i;
        System.out.println(sum * (int) Math.pow(4, n));//垒完后,每个骰子都可以水平旋转,四个面,4^n
    }

    /**
     dp[i][j]:垒了i个骰子,最后一个上面是数字j
     dp[i][j] = sum{ dp[i-1][k] | k与j的对面不冲突}
     dp[i][j] = a1*dp[i-1][1] + a2*dp[i-1][2]+... 其中如果k与j的对面冲突,则a为0,否则a为1
     令系数A={a1,a2...a6}  F[i]=sum{ dp[i-1][k] }
     有F[i]=A*F[i-1] -> 矩阵乘法
     则F[i]=A^(n-1) F[1] -> 矩阵快速幂
     F[1]={1,1,1,1,1,1}
     */
    private static void solve2(int n, int m) {
        long[][] isEx = new long[6][6];//是否冲突
        for (long[] e : isEx) Arrays.fill(e, 1);
        for (int i = 0; i < m; i++) {
            int a = Int(), b = Int();
            isEx[a - 1][op[b] - 1] = 0;
            isEx[b - 1][op[a] - 1] = 0;
        }
        isEx = pow(isEx, n - 1);
        long sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                sum = (sum + isEx[i][j]) % MOD;
            }
        }
        System.out.println(sum * pow(4, n) % MOD);//垒完后,每个骰子都可以水平旋转,四个面,4^n
    }

    private static long pow(long x, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            n >>= 1;
        }
        return res;
    }

    private static long[][] pow(long[][] A, int n) {
        long[][] res = new long[6][6];
        for (int i = 0; i < 6; i++) res[i][i] = 1;//单位矩阵
        while (n > 0) {
            if ((n & 1) == 1) res = mul(res, A);
            A = mul(A, A);
            n >>= 1;
        }
        return res;
    }

    static long[][] mul(long[][] A, long[][] B) {
        long[][] t = new long[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    t[i][j] = (t[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return t;
    }
}
