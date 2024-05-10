package 算法.动态规划.题集.股票买卖;

/**
 无手续费,含1天冷冻期,卖出后隔天才能买入
 */
public class _309_IV {
    /*
    给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。

    设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

    卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    /**
     <h1>动态规划_贪心</h1>
     买入时的比较对象变为前两天卖出的结果
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 1) return 0;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = -prices[0];
        buy[1] = -Math.min(prices[0], prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return Math.max(sell[len - 1], sell[len - 2]);
    }
}
