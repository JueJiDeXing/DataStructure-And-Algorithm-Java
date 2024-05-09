package 算法.动态规划_贪心.动态规划.题集.子数组问题;

public class _238除自身以外数组的乘积 {
    /*
    给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
    题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
    请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        //求前后缀数组
        int[] pre = new int[len + 1];
        int[] suff = new int[len + 1];
        pre[0] = 1;
        suff[len] = 1;
        for (int i = 1; i <= len; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        for (int i = len - 1; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i];
        }
        //前缀乘以后缀
        for (int i = 0; i < len; i++) {
            nums[i] = pre[i] * suff[i + 1];
        }
        return nums;
    }
}
