package 算法.动态规划.题集.股票买卖;

/**
 可以买卖多次,同一时间只能持有一股,求最大利润
 */
public class _122_II {
    /*
    给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
    在每一天，你可以决定是否购买和/或出售股票。
    你在任何时候 最多 只能持有 一股 股票。
    你也可以先购买，然后在 同一天 出售。
    返回 你能获得的 最大 利润 。
     */

    /**
     <h1>贪心算法</h1>
     只要后一天大于前一天就可以买卖<br>
     例:[1,2,3,4]<br>
     res = (2-1) + (3-2) + (4-3) = 3<br>
     证明:<br>
     如果任取贪心算法下的两段相邻买卖 [a1,a2]  [b1,b2]<br>
     1. a2 = b1,则两段买卖等价b2-a1的一段买卖,利润相同(允许同一天买入和卖出)<br>
     2. a2 > b1,则a2至b1为跌,b2-a1的一段买卖利润低于两段买卖<br>
     3. a2 < b1,则a2至b1为涨,那么中间必然有另一段买卖,取出的两段买卖不是相邻的<br>
     综上:贪心算法的任意两段相邻买卖的利润大于等于合并后的利润
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     <h1>动态规划</h1>
     <p>
     dp[i][0]表示第i天持有现金,未购入股票<br>
     dp[i][1]表示第i天持有股票,未卖出<br>
     <p>
     初始化:dp[0][0]=0 , dp[0][1]=-prices[0]<br>
     状态转移方程:<br>
     dp[i][0]来源于 第i-1天未持有股票仍不购买股票,或第i-1天持有股票将其卖出<br>
     dp[i][1]来源于 第i-1天已经持有股票,或第i-1天未持有股票购入股票<br>
     dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);<br>
     dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);<br>
     <p>
     最后一天一定持有现金,手中没有股票,所以返回dp[最后一天][0]<br>
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        //滚动变量
        if (len < 2) {
            return 0;
        }
        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < len; i++) {
            //int preCash = cash;
            //int preHold = hold;
            //cash = Math.max(preCash, preHold + prices[i]);
            //hold = Math.max(preHold, preCash - prices[i]);

            cash = Math.max(cash, hold + prices[i]);//与上面四行等效
            hold = Math.max(hold, cash - prices[i]);

        }
        return cash;
        /*等效原因
        int preCash = cash;
        int preHold = hold;
        cash = Math.max(preCash, preHold + prices[i]);
        hold = Math.max(preHold, preCash - prices[i]);

        首先preHold可以直接替换,因为它是最后才改变的
        int preCash = cash;
        cash = Math.max(cash, hold + prices[i]);//这里的cash也可以替换
        hold = Math.max(hold, preCash - prices[i]);

        对 cash = Math.max(cash, hold + prices[i]) 分类讨论:
        1. cash = cash 则 后面的cash没有改变,cash=preCash,preCash可以替换
        2. cash = hold + prices[i] 则
            如果不使用cash替换:preCash < hold + prices[i] =>  preCash - prices[i] < hold => hold不变
            如果使用cash替换:当前cash = hold + prices[i] =>  cash - prices[i] = hold => hold不变
           所以当 hold + prices[i] > preCash时,max(hold, xxx - prices[i])一定返回hold,可以使用cash替换


         */
    }
}
