package 算法.动态规划_贪心.动态规划.题集.子数组问题.最长递增序列;

/**
 难度:简单
 */
public class _374最长连续递增序列 {
    /*
    给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。

    连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，
    如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
    那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
     */
    public int findLengthOfLCIS(int[] nums) {
        int dp = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp++;
            } else {
                dp = 1;//重新开始计数
            }
            max = Math.max(max, dp);
        }
        return max;
    }
}
