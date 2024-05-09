package 算法.滑动窗口;

/**
 第 126 场周赛 Q3
 难度分:1656
 */
public class _1004最大连续1的个数III {
    /*
    给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0, right = 0, zero = 0;//[left,right]上0的数量
        int ans = 0;
        for (; right < nums.length; right++) {
            if (nums[right] == 0) {
                zero++;
                while (zero > k) {//抛出left多出的0
                    if (nums[left] == 0) zero--;
                    left++;
                }
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;

    }
}
