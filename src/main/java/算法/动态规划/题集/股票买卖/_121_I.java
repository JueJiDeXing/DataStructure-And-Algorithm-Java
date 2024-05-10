package 算法.动态规划.题集.股票买卖;

/**
 最多买卖一次,求最大利润
 */
public class _121_I {
/*
    给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
    你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。
    设计一个算法来计算你所能获取的最大利润。
    返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */

    /**
     <h1>动态规划_贪心</h1>
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        int minOfPre = prices[0];//前缀最小值
        for (int price : prices) {
            res = Math.max(res, price - minOfPre);//尝试卖出
            minOfPre = Math.min(minOfPre, price);//更新最小值
        }
        return res;
    }

    /**
     <h1>双指针</h1>
     */
    public int maxProfit2(int[] prices) {
        int i = 0;
        int j = 1;
        int res = 0;
        while (j < prices.length) {
            if (prices[j] > prices[i]) {//涨
                res = Math.max(res, prices[j] - prices[i]);
            } else {//跌
                i = j;//更新最小值
            }
            j++;
        }
        return res;
    }
}
