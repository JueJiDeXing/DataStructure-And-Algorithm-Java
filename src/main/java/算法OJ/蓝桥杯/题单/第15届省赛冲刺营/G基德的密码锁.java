package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.Arrays;
import java.util.Scanner;
/**
 已AC
 */
public class G基德的密码锁 {
    static int m, k;
    static int MOD = 998244353;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        long[][] dp = new long[n + 1][m + 1];//dp[i][j]:前i位,最后一位是j的方案数
        Arrays.fill(dp[1], 1);//第1位,每位一种方案
        dp[1][0] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {//dp[i-1]转化为前缀和数组
                dp[i - 1][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % MOD;
            }
            for (int j = 1; j <= m; j++) {
                //dp[i][j]=sum{dp[i-1][0~j-k]}+sum{dp[i-1][j+k~m]}
                if (j - k >= 0) dp[i][j] = (dp[i][j] + dp[i - 1][j - k]) % MOD;
                if (k == 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][m] - dp[i - 1][Math.min(m, j + k)] + MOD) % MOD;
                } else {
                    dp[i][j] = (dp[i][j] + dp[i - 1][m] - dp[i - 1][Math.min(m, j + k - 1)] + MOD) % MOD;
                }
            }
        }
        long sum = 0;
        for (int i = 1; i <= m; i++) sum = (sum + dp[n][i]) % MOD;
        System.out.println(sum);
    }
}
