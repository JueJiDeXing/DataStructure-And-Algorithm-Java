package 算法.动态规划.题集.股票买卖;

/**
 最多买卖两次
 */
public class _123_V {
    /*
    给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     */

    /**
     <h1>动态规划_贪心</h1>
     第一次买卖:<br>
     买入不依赖于卖出,买入 = 当日价格<br>
     卖出 = 昨天第一次买 + 当日价格<br>
     第二次买卖:<br>
     买入依赖于第一次的卖出,买入 = 昨天第一次卖 - 当日价格<br>
     卖出 = 昨天第二次买 + 当日价格
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        int[] firstBuy = new int[len], firstSell = new int[len];
        int[] secondBuy = new int[len], secondSell = new int[len];
        secondBuy[0] = firstBuy[0] = -prices[0];
        secondSell[0] = firstSell[0] = 0;
        for (int i = 1; i < len; i++) {
            firstBuy[i] = Math.max(firstBuy[i - 1], -prices[i]);
            firstSell[i] = Math.max(firstSell[i - 1], firstBuy[i - 1] + prices[i]);
            secondBuy[i] = Math.max(secondBuy[i - 1], firstSell[i - 1] - prices[i]);
            secondSell[i] = Math.max(secondSell[i - 1], secondBuy[i - 1] + prices[i]);
        }
        return secondSell[len - 1];
    }

    //降维
    public int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE, firstSell = 0;
        int secondBuy = Integer.MIN_VALUE, secondSell = 0;
        for (int price : prices) {
            firstBuy = Math.max(firstBuy, -price);
            firstSell = Math.max(firstSell, firstBuy + price);

            secondBuy = Math.max(secondBuy, firstSell - price);
            secondSell = Math.max(secondSell, secondBuy + price);
        }
        return secondSell;
    }
}
