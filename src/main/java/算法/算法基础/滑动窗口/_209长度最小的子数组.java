package 算法.算法基础.滑动窗口;

/**
 难度:中等
 */
public class _209长度最小的子数组 {
    /*
    给定一个含有 n 个正整数的数组和一个正整数 target 。

    找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
    如果不存在符合条件的子数组，返回 0 。
     */

    /**
     <h1>滑动窗口-双指针</h1>
     用指针left和right维护一个滑动窗口,表示nums[left~right]
     当窗口和sum小于target时,right++
     当窗口和大于等于target时,将前端抛出至最短的满足条件的子数组,然后更新最小长度
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, sum = 0;//滑动窗口,范围[left,right],sum为窗口和
        int ans = 100001;
        for (; right < nums.length; right++) {
            sum += nums[right];
            if (sum < target) continue;

            //抛出到最短
            while (sum - nums[left] >= target) {
                sum -= nums[left];
                left++;
            }
            //更新最短长度
            ans = Math.min(ans, right - left + 1);
        }
        if (sum >= target) {
            ans = Math.min(ans, right - left + 1);
        }
        return ans == 100001 ? 0 : ans;
    }
}
