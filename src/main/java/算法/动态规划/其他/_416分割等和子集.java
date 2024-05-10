package 算法.动态规划.其他;

public class _416分割等和子集 {
    /*
    给你一个 只包含正整数 的 非空 动态数组 nums 。
    请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2) return false;
        int sum = 0, max = 0;
        for (int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }
        int target = sum / 2;
        if (sum % 2 == 1 || max > target) return false;

        //dp[i][j]:前i个元素,能否凑出j
        boolean[] dp = new boolean[target + 1];//压缩为一维
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {//j<num时,无法选num,dp[j]为上一次的dp[j]不变
                //j>=num时,可以选num,dp[j]为上一次dp[j]与dp[j-num]的或
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}
