package 算法.进阶数据结构算法.ST表;

public class 动态规划到ST表 {

/*
给定一个数组, 需要查询Q次区间最大值
 */

    /**
     <h1>动态规划 - 线性dp</h1>
     令dp[i][j]: nums[i~j]的最大值
     <p>
     有dp[i][j] = max{ dp[i][j-1],nums[j] }
     */
    public void dp1(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], nums[j]);
            }
        }
    }

    /**
     <h1>动态规划 - 区间dp</h1>
     令dp[i][j]: nums[i~j]的最大值
     <p>
     有 dp[i][j] = max{ dp[i][X], dp[X][j] }
     */
    public void dp2(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);// X任意取, 不影响max
            }
        }
    }

    /**
     <h1>动态规划 - 区间dp - 状态优化</h1>
     我们发现: 一个长度可以用2的幂进行组合, 例如长度为6的, 可以用两个长度为4的区间覆盖
     令dp[i][j]: nums[i~j]的最大值
     <p>
     有 dp[i][j] = max{ dp[i][X], dp[X][j] }
     <p>
     查询nums[l~r]时, 取 nums[l ~ l+2^mi) 和 nums(r-2^mi,r]的最大值
     */
    public void dp3(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];

        for (int len = 2; len <= n; len *= 2) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(dp[i][i + (len / 2) - 1], dp[j - len / 2 + 1][j]);
            }
        }
    }

    /**
     <h1>动态规划 - 区间dp - 空间优化</h1>
     经过dp3的更改, dp数组中只有2的幂的列被使用, 其余位置没有使用, 将其压缩
     <p>
     将第二个维度用幂表示即可
     */
    public void dp4(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = nums[i];

        for (int mi = 1; (1 << mi) <= n; mi++) {
            int len = 1 << mi;
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][mi] = Math.max(dp[i][mi - 1], dp[i + len / 2 - 1][mi - 1]);// X任意取, 不影响max
            }
        }
        // 这就是st表
    }


}
