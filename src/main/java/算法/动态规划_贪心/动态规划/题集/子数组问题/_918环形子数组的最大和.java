package 算法.动态规划_贪心.动态规划.题集.子数组问题;

public class _918环形子数组的最大和 {
    /*
    给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
    环形数组 意味着数组的末端将会与开头相连呈环状。
    形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
    子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。
    形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
     */

    /**
     解有两种情况:<br>
     case 1 -> 解在数组中连续<br>
     case 2 -> 解在数组的两头<br>
     case1: 该问题就是最大子数组和<br>
     case2: 进行取反操作, 求全部和sum ,然后减去最小子数组和min, 即为最大子数组和<br>
     结果取 case1 和 case2 的最大值<br>
     <p>
     特殊情况: 全为负数, 由于需要非空子数组, 应当返回最大的负数,
     所以使max的初始值为数组第一项,这样,如果max<0,那么求出来的max就是最大负数
     */
    public int maxSubarraySumCircular(int[] nums) {
        int preMax = 0, preMin = 0, sum = 0;
        int min = 0, max = nums[0];
        for (int num : nums) {
            preMax = Math.max(preMax + num, num);//前缀最大值
            preMin = Math.min(preMin + num, num);//前缀最小值
            max = Math.max(preMax, max);
            min = Math.min(preMin, min);
            sum += num;//全部和
        }
        if (max < 0) return max;//全是负数,返回最大的一个负数
        return Math.max(max, sum - min);
    }
}
