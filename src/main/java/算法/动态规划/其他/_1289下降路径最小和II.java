package 算法.动态规划.其他;

public class _1289下降路径最小和II {
    /*
    给你一个 n x n 整数矩阵 grid ，请你返回 非零偏移下降路径 数字和的最小值。
    非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
     */
    int n;

    public int minFallingPathSum(int[][] grid) {
        n = grid.length;
        int[] minOfDp = getMin(grid[0]);
        int[] dp = grid[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[j] = grid[i][j] + minOfDp[j];
            }
            minOfDp = getMin(dp);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, minOfDp[i]);
        }
        return min;
    }

    public int[] getMin(int[] arr) {
        if (n == 1) return arr;
        int[] pre = new int[n];
        pre[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            pre[i] = Math.min(pre[i - 1], arr[i - 1]);
        }
        int[] suf = new int[n];
        suf[n - 1] = Integer.MAX_VALUE;
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = Math.min(suf[i + 1], arr[i + 1]);
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(pre[i], suf[i]);
        }
        return res;
    }
}
