package 算法.动态规划.概率dp;

import java.util.Scanner;

public class 蓝桥杯_爬树的甲壳虫 {
    /*
    甲壳虫从树根处爬到树顶, 树根高度为0,树顶高度为n
    在高度为i-1处,要爬到高度为i处时,需要花费1点时间, 并且有P[i]的概率掉到树根
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        solve2();
    }

    /**
     <h1>自顶向下 - 系数解</h1>
     <pre>
     令 dp[i]: 从i爬到n的期望时间
     初态: dp[n] = 0
     转移方程: dp[i] = P[i] * dp[0] + (1-P[i]) * dp[i+1] + 1
     但是转移方程中依赖了需要求解的dp[0]

     考虑系数:
     dp[n-1] = P[n-1] * dp[0] + 1
     dp[n-1]是关于dp[0]的一次函数
     dp[n-2] = P[n-2] * dp[0] + (1-P[n-2]) * dp[n-1] + 1
     = { P[n-2]   + (1-P[n-2]) *  P[n-1] } dp[0]  + 2 - P[n-2]
     将dp[n-1]代入后, dp[n-2]也是关于dp[0]的一次函数
     ...
     令 dp[i] = A[i] * dp[0] + B[i]
     则:
     dp[i] = P[i] * dp[0] + (1-P[i]) * (A[i+1]dp[0]+B[i+1]) + 1
     = { P[i] + (1-P[i])*A[i+1] } dp[0] + (1-P[i]) * B[i+1] + 1
     所以:
     A[n-1] = P[n-1], B[n-1] = 1
     A[i] = P[i] + (1-P[i]) * A[i+1], B[i] = (1-P[i]) * B[i+1] + 1
     dp[0] = - B[0] / (A[0] - 1)
     </pre>
     */
    private static void solve1() {
        int n = sc.nextInt();
        long[] P = new long[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            P[i] = x * inv(y) % MOD;
        }
        long A = P[n - 1], B = 1;
        for (int i = n - 2; i >= 0; i--) {
            A = P[i] + (1 - P[i] + MOD) * A % MOD;
            B = ((1 - P[i] + MOD) * B + 1) % MOD;
        }
        System.out.println((MOD - B * inv(A - 1) % MOD) % MOD);
    }

    /**
     <h1>自底向上 - 递推解</h1>
     令 dp[i]: 从0爬到i的期望时间
     首先, 要爬到i, 必然爬到了i-1, 期望时间为dp[i-1]
     然后需要花费1点时间爬到i, 期望时间+1
     有P[i]的概率掉到树底需要重新爬,  期望时间为P[i] * dp[i]
     <p>
     则: dp[i] = dp[i-1] + 1 + P[i] * dp[i]
     dp[i] = (dp[i-1] + 1) / (1-P[i])
     */
    private static void solve2() {
        int n = sc.nextInt();
        long[] P = new long[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            P[i] = x * inv(y) % MOD;
        }
        long dp = 0;
        for (int i = 0; i < n; i++) {
            dp = (dp + 1) * inv(1 - P[i] + MOD) % MOD;
        }
        System.out.println(dp);
    }

    static long MOD = 998244353;

    static long pow(long x, long n) {
        long ans = 1;
        while (n > 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }

    static long inv(long x) {
        return pow(x, MOD - 2);
    }

}
