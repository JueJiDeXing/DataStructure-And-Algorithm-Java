package 算法.进阶数据结构算法.ST表;

public class ST {
    /*
    预处理为O(nlogn), 查询为O(1)
    不支持修改
     */
    int[][] dp;

    int log2(int n) {
        return Integer.bitCount(Integer.highestOneBit(n) - 1);
    }

    public ST(int[] nums) {
        int n = nums.length;
        int mx = log2(n);
        dp = new int[n][mx + 1];// dp[i][mi]: nums[i ~ i + (1<<mi) ) 的最大值
        for (int i = 0; i < n; i++) dp[i][0] = nums[i];

        for (int mi = 1; mi <= mx; mi++) {
            int len = 1 << mi, halfLen = 1 << (mi - 1);
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][mi] = Math.max(dp[i][mi - 1], dp[i + halfLen][mi - 1]);
            }
        }
    }

    int query(int i, int j) {
        int len = j - i + 1;
        int mi = log2(len);
        return Math.max(dp[i][mi], dp[j - (1 << mi) + 1][mi]);
    }
}
