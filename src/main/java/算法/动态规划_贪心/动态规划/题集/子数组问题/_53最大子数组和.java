package 算法.动态规划_贪心.动态规划.题集.子数组问题;

public class _53最大子数组和 {
    /*
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    子数组 是数组中的一个连续部分。
     */
    public int maxSubArray(int[] nums) {
        int pre=0;//连续子数组和
        int max=Integer.MIN_VALUE;
        for(int n:nums){
            pre=Math.max(pre+n,n);//如果pre为负,会从新项开始求,否则继续加这一项
            max=Math.max(max,pre);//最大和
        }
        return max;
    }
}
