package 算法.动态规划_贪心.动态规划.其他;

public class _1155掷骰子等于目标和的方法数 {
    /*
    这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。

    给定三个整数 n ,  k 和 target ，
    返回可能的方式(从总共 k^n 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。

    答案可能很大，你需要对 10^9 + 7 取模 。
     */
    int MOD = 1000000007;

    /**
     dp[i][j]表示i个骰子,凑成j的种数
     dp[i][j]=SUM(dp[i-1][j-t])
     */
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                for (int t = 1; t <= j && t <= k; t++) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - t]) % MOD;
                }
            }
        }
        return dp[n][target];
    }
}
