package 算法.动态规划_贪心.动态规划.题集.子数组问题;

public class _1749任意子数组和的绝对值的最大值 {
    /*
    给你一个整数数组 nums 。
    一个子数组 [numsl, numsl+1, ..., numsr-1, numsr] 的 和的绝对值 为 abs(numsl + numsl+1 + ... + numsr-1 + numsr) 。
    请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
    abs(x) 定义如下：
    如果 x 是负整数，那么 abs(x) = -x 。
    如果 x 是非负整数，那么 abs(x) = x 。
     */
    public int maxAbsoluteSum(int[] nums) {
        // int max=0,min=0,res=0;
        // for(int n:nums){
        //     max=Math.max(max+n,n);
        //     min=Math.min(min+n,n);
        //     res=Math.max(res,Math.max(max,-min));
        // }
        // return res;
        int preSum = 0, max = 0, min = 0;
        for (int n : nums) {
            preSum += n;
            if (preSum > max) {
                max = preSum;
            } else if (preSum < min) {
                min = preSum;
            }
        }
        return max - min;
    }
}
