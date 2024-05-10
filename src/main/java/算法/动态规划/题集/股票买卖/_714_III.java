package 算法.动态规划.题集.股票买卖;

/**
 含手续费,不允许同一天买入卖出
 */
public class _714_III {
    /*
    给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；
    整数 fee 代表了交易股票的手续费用。

    你可以无限次地完成交易，但是你每笔交易都需要付手续费。
    如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
    返回获得利润的最大值。

    注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     */

    /**
     <h1>动态规划</h1>
     dp[i][0]表示第i天持有股票,dp[i][1]表示第i天不持有股票<br>
     状态转移方程:<br>
     <ul>
     <li>第i天选择持有股票:<br>
     1. 前一天有股票 不卖<br>
     2. 前一天没有股票 买入<br>
     dp[i][0]=max(dp[i-1][0],dp[i][1]-prices[i])</li>
     <li>第i天选择不持有股票:<br>
     1. 前一天有股票 卖出<br>
     2. 前一天没有股票 不买<br>
     dp[i][1]=max(dp[i-1][0]+prices[i]-fee,dp[i-1][1])</li>
     </ul>
     */
    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }
        return dp[len - 1][1];
    }

    //从二维数组的等价拆分
    public int maxProfit2(int[] prices, int fee) {
        int len = prices.length;
        int[] buy = new int[len], sell = new int[len];
        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);
        }
        return sell[len - 1];//最后必然是卖出的
    }

    //降维
    public int maxProfit3(int[] prices, int fee) {
        int len = prices.length;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < len; i++) {
            //int last_buy = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, buy + prices[i] - fee);
        }
        return sell;//最后必然是卖出的
    }
}
