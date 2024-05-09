package 算法.动态规划_贪心.动态规划.其他;

import java.util.Arrays;

public class _62不同路径 {
    /*
    矩形
    从左上角走到右下角,只能向右/向下走,问一共有多少条路径
     */
    public static void main(String[] args) {
        int unique1 = uniquePaths1(7, 3);
        int unique2 = uniquePaths2(7, 3);
    }

    public static int uniquePaths1(int col, int row) {
        int[][] dp = new int[row][col];
        Arrays.fill(dp[0],1);
        for (int i = 0; i < row; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int uniquePaths2(int col, int row) {
        int[] dp = new int[col];//降维
        Arrays.fill(dp, 1);
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[j] += dp[j - 1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[col - 1];
    }
}
