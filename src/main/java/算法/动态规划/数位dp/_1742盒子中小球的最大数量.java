package 算法.动态规划.数位dp;

import java.util.Arrays;

public class _1742盒子中小球的最大数量 {
/*
你在一家生产小球的玩具厂工作，有 n 个小球，编号从 lowLimit 开始，到 highLimit 结束
（包括 lowLimit 和 highLimit ，即 n == highLimit - lowLimit + 1）。

另有无限数量的盒子，编号从 1 到 infinity 。

你的工作是将每个小球放入盒子中，其中盒子的编号应当等于小球编号上每位数字的和。
例如，编号 321 的小球应当放入编号 3 + 2 + 1 = 6 的盒子，而编号 10 的小球应当放入编号 1 + 0 = 1 的盒子。

给你两个整数 lowLimit 和 highLimit ，返回放有最多小球的盒子中的小球数量。
如果有多个盒子都满足放有最多小球，只需返回其中任一盒子的小球数量。
 */

    int sum(int x) {
        int r = 0;
        while (x > 0) {
            r += x % 10;
            x /= 10;
        }
        return r;
    }

    /**
     <h1>暴力枚举</h1>
     */
    public int countBalls1(int lowLimit, int highLimit) {
        int[] box = new int[50];
        int max = 0;
        for (int x = lowLimit; x <= highLimit; x++) {
            int r = sum(x);
            max = Math.max(max, ++box[r]);
        }
        return max;
    }


    /**
     <h1>前缀 + dp</h1>
     令dp[i][j]表示[1,i]上,数位和为j的个数
     则ans = max{ dp[l-1][j] - dp[h][j] }
     转移方程: dp[i][j] = dp[i-1][j] + ( sum[i]==j ? 1 : 0)
     */
    public int countBalls2(int lowLimit, int highLimit) {
        int maxSum = 46;
        int[][] s = new int[100_001][maxSum];
        for (int i = 1; i < s.length; i++) {
            System.arraycopy(s[i - 1], 0, s[i], 0, s[i].length);
            int r = sum(i);
            s[i][r]++;
        }
        int ans = 0;
        for (int j = 1; j < maxSum; j++) {
            ans = Math.max(ans, s[highLimit][j] - s[lowLimit - 1][j]);
        }
        return ans;
    }

    /**
     <h1>数位dp</h1>
     令dfs(i,j)表示,对[i,n)进行填写, 数位和为j的方案数
     则 ans = max{ dfs(0,j) }
     */
    public int countBalls3(int lowLimit, int highLimit) {
        // 转字符串
        highS = (highLimit + "").toCharArray();
        int n = highS.length;
        String lowS = String.valueOf(lowLimit);
        lowS = "0".repeat(n - lowS.length()) + lowS; // 补前导零, 和 highS 对齐
        this.lowS = lowS.toCharArray();
        // 初始化memo记忆化
        int m = highS[0] - '0' + (n - 1) * 9; // 数位和的上界
        memo = new int[n][m + 1];
        for (int[] row : memo) Arrays.fill(row, -1); // -1 表示没有计算过
        // 求max{ dp[0][j] }
        int ans = 0;
        for (int j = 1; j <= m; j++) {
            ans = Math.max(ans, dfs(0, j, true, true));
        }
        return ans;
    }

    char[] lowS, highS;
    int[][] memo;

    /**
     对[i,n)进行填写, 数位和为j的方案数
     */
    int dfs(int i, int j, boolean limitLow, boolean limitHigh) {
        if (i == highS.length) { // 填完了
            return j == 0 ? 1 : 0;
        }
        if (!limitLow && !limitHigh && memo[i][j] != -1) { // 之前计算过
            return memo[i][j];
        }
        int lo = limitLow ? lowS[i] - '0' : 0;
        int hi = limitHigh ? highS[i] - '0' : 9;
        int res = 0;
        for (int d = lo; d <= Math.min(hi, j); d++) { // 枚举当前数位填 d，但不能超过 j
            res += dfs(i + 1, j - d, limitLow && d == lo, limitHigh && d == hi);
        }
        if (!limitLow && !limitHigh) memo[i][j] = res; // 记忆化
        return res;
    }
}
