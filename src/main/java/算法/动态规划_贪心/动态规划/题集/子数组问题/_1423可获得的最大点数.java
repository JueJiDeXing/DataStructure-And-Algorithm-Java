package 算法.动态规划_贪心.动态规划.题集.子数组问题;

public class _1423可获得的最大点数 {
    /*
    几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
    每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
    你的点数就是你拿到手中的所有卡牌的点数之和。
    给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     */
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length - k;
        int sum = 0;
        for (int p : cardPoints) {
            sum += p;
        }
        if (n == 0) return sum;
        return sum - new 长度为K的连续子数组最小和().minSum(cardPoints, n);
    }
}
