package 算法.动态规划.题集.打家劫舍;

public class _213打家劫舍II {
    //与LCR090为同一题
    /*
    你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
    这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
    同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

    给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     */

    /**
     限制: 不能连续偷,环形数组首尾相邻,最高金额
     考虑首尾,首如果偷,尾就不能偷,那么偷的范围是[0,len-2]; 如果偷尾,则范围是[1,len-1]
     求着两个子问题的最大值即可
     robRange求 从nums的[a,b]偷得的最大值 ,方法与 打家劫舍I 相同
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        } else if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //偷[0,len-2]和[1,len-1]
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
