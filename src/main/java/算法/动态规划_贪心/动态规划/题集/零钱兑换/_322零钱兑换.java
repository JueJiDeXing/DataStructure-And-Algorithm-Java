package 算法.动态规划_贪心.动态规划.题集.零钱兑换;import java.util.*;

public class _322零钱兑换 {
    public static void main(String[] args) {
        System.out.println(change2(new int[]{1, 2, 5}, 5));
    }
    /*
    给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
    计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
    你可以认为每种硬币的数量是无限的。
     */

    /**
     <h1>动态规划_贪心</h1>
     最少硬币个数=min(1＋剩余容量下最少个数,当前个数)
     dp[i] = min(1 + dp[i - coin], dp[i])<br>
     */
    public static int change(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int j = 1; j < amount + 1; j++) {
            if (j >= coins[0]) {
                dp[0][j] = dp[0][j - coins[0]] + 1;
            } else {
                dp[0][j] = amount + 1;//最大值
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (j >= coins[i]) {//能够装下硬币
                    //最少硬币个数=min(1＋剩余容量下最少个数,当前个数)
                    dp[i][j] = Math.min(1 + dp[i][j - coins[i]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        int res = dp[coins.length - 1][amount];
        return res > amount ? -1 : res;//如果是设置的最大值,则说明无法凑成,返回-1
    }

    /**
     <h1>动态规划_贪心:降维</h1>
     */
    public static int change2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {//i需要大于coin表示装得下,所以从coin开始
                //能够装下硬币
                //最少硬币个数=min(1＋剩余容量下最少个数,当前个数)
                dp[i] = Math.min(1 + dp[i - coin], dp[i]);
                //不能则不变
            }
        }
        int res = dp[amount];
        return res > amount ? -1 : res;
        /*示例
            0   1   2   3   4       5       6
        1   0   1   11  111 1111    11111   111111
        2   0   1   2   21  22      221     222
        5   0   1   2   21  22      5       51
         */
    }
}
