package 算法.动态规划.题集.背包问题;

public class _279完全平方数 {
    /*
    返回和为n的完全平方数的最少数量 。
    12=4+4+4 -> 3
    13=9+4 ->2
     */
    public int numSquares1(int n) {
        /*动态规划_贪心 (完全背包问题)
        dp[i][j]:最大为j时尝试装i的最少个数
        行数(物品种数):row=(int)Math.floor(Math.sqrt(n))+1;
        列数(背包最大容量):n;
        物品权重:w=(int)Math.pow(i,2); 每个物品的权重都是它自身的平方
        降维
        初始化 dp[i]=i; 只装1
        状态转移方程:dp[j]=Math.min(1+dp[j-w],dp[j]); 其中1+dp[j-w]为装入当前物品1个,加上剩余容量的最少个数
        */
        int row = (int) Math.sqrt(n) + 1;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) dp[i] = i;
        for (int i = 1; i <= row; i++) {
            int w = i * i;
            for (int j = w; j <= n; j++) {
                dp[j] = Math.min(1 + dp[j - w], dp[j]);
            }
        }
        return dp[n];
    }

    public int numSquares2(int n) {
        /*
        dp[i]最大容量为i时的最少个数
        dp[i]=MIN(dp[i-j*j]) 其中1<=j*j<=i,寻找可放入j中,哪一种选择会使个数最少
        */
        int[] dp = new int[n + 1];
        // dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            int mn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                mn = Math.min(mn, dp[i - j * j]);
            }
            dp[i] = mn + 1;//还要放入j,所以+1
        }
        return dp[n];
    }
}
