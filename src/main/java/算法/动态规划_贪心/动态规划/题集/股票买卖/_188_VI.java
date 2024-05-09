package 算法.动态规划_贪心.动态规划.题集.股票买卖;

import java.util.Arrays;

/**
 最多买卖K次
 */
public class _188_VI {
    /*
    给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
    也就是说，你最多可以买 k 次，卖 k 次。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    public int maxProfit(int k, int[] prices) {
        int[] Buy = new int[k];
        int[] Sell = new int[k];
        Arrays.fill(Buy, Integer.MIN_VALUE);
        Arrays.fill(Sell, 0);
        for (int price : prices) {
            for (int i = 0; i < k; i++) {
                Buy[i] = Math.max(Buy[i], (i == 0 ? 0 : Sell[i - 1]) - price);
                Sell[i] = Math.max(Sell[i], Buy[i] + price);
            }
        }
        return Sell[k - 1];
    }

}
