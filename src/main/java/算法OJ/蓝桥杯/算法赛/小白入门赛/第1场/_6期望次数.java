package 算法OJ.蓝桥杯.算法赛.小白入门赛.第1场;

import java.util.Scanner;

public class _6期望次数 {
    /*
    长度为N的序列p, 记S=sum(p)
    初始有正整数x=1
    当x>=M时停止
    当x<M时需要操作
    操作会有 p1/S 变为 x*1 ,有 p2/S 变为 x*2 ... 有pn/S 变为 x*n
    求期望操作次数
     */

    /**
     dp[i] = 1 + sum{ p[j] * dp[i*j] | j∈[1,n]} / S
     dp[i] = ( S + sum{ p[j] * dp[i*j] | j∈[2,n]} ) / (S-p1)
     dp[m]=0
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long S = 0;
        int[] p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextInt();
            S += p[i];
        }
        long q = pow(S - p[1], MOD - 2, MOD);
        long[] dp = new long[m + 1];
        for (int i = m - 1; i > 0; i--) {
            dp[i] = S;// dp[i] = ( S + sum{ p[j] * dp[i*j] | j∈[2,n]} ) / (S-p1)
            for (int j = 2; j <= n; j++) {
                if (i * j > m) break;
                dp[i] = (dp[i] + p[j] * dp[i * j]) % MOD;
            }
            dp[i] = dp[i] * q % MOD;
        }
        System.out.println(dp[1]);
    }

    static int MOD = 998244353;

    static long pow(long x, int n, int MOD) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = res * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return res;
    }
}
