package 算法.动态规划.题集.子序列问题;

import java.util.HashMap;
import java.util.Map;
/**
 难度:中等
 */
public class _873最长的斐波那契子序列长度 {
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> indices = new HashMap<Integer, Integer>();// 严格递增数列, 值->索引
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indices.put(arr[i], i);
        }
        // dp[i][j]: 在arr[0,j]上, 以arr[i]和arr[j]结尾, 最长斐波那契序列长度
        // dp[i][j] = dp[k][i] + 1   如果 arr[k] + arr[i] = arr[j],    k < i <j
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[i] < 2 * arr[j]; j--) {
                int k = indices.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }
}
