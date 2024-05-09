package 算法.动态规划_贪心.动态规划.区间dp;

import java.util.*;

public class 石子合并2_k堆 {
    private int[][][] memo;
    private int[] preSum;
    private int k;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) > 0) return -1;// 无法合并成一堆

        preSum = new int[n + 1];
        for (int i = 0; i < n; i++) preSum[i + 1] = preSum[i] + stones[i]; // 前缀和
        this.k = k;
        memo = new int[n][n][k + 1];
        for (int[][] mem : memo) {
            for (int[] me : mem) {
                Arrays.fill(me, -1); // -1 表示还没有计算过
            }
        }
        return dfs(0, n - 1, 1);
    }

    private int dfs(int i, int j, int p) {
        if (memo[i][j][p] != -1) return memo[i][j][p];
        if (p == 1) // 合并成一堆
            return memo[i][j][p] = i == j ? 0 : dfs(i, j, k) + preSum[j + 1] - preSum[i];
        int res = Integer.MAX_VALUE;
        for (int m = i; m < j; m += k - 1) // 枚举哪些石头堆合并成第一堆
            res = Math.min(res, dfs(i, m, 1) + dfs(m + 1, j, p - 1));
        return memo[i][j][p] = res;
    }


}
