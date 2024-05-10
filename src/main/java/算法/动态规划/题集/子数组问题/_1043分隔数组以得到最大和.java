package 算法.动态规划.题集.子数组问题;

/**
 第 136 场周赛 Q3
 难度分:1916
 */
public class _1043分隔数组以得到最大和 {
    /*
    给你一个整数数组 arr，请你将该数组分隔为长度 最多 为 k 的一些（连续）子数组。
    分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。

    返回将数组分隔变换后能够得到的元素最大和。
    本题所用到的测试用例会确保答案是一个 32 位整数。
    */

    /**
     dp[i]:将数组arr[0,i]分隔为长度最多为k的子数组的最大和
     ans=dp[n-1]
     dp[i]=MAX( dp[j]+sum(j~i) )
     */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + arr[i];//单独一组
            // arr[i]划分到前面
            int max = arr[i];//枚举j,令j~i划分为一组
            for (int j = i - 1; j >= Math.max(0, i - k + 1); j--) {
                max = Math.max(max, arr[j]);
                int sum = max * (i - j + 1);//改组所有值会变为组内最大值
                dp[i + 1] = Math.max(dp[i + 1], dp[j] + sum);
            }
        }
        return dp[n];
    }
}
