package 算法.动态规划.题集.子数组问题;

public class _152乘积最大的子数组 {
    /*
    给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组并返回该子数组所对应的乘积。
    该子数组中至少包含一个数字，
    测试用例的答案是一个 32-位 整数。
    子数组 是数组的连续子序列。
     */

    /**
     <h1>解1</h1>
     同时维护最大乘积和最小乘积<br>
     如果碰到负数,则需要最小乘积尽可能的小,以达到最大乘积
     */
    public int maxProduct1(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int num = nums[i];
            int mx = maxF * num, mn = minF * num;
            maxF = Math.max(mx, Math.max(num, mn));
            minF = Math.min(mn, Math.min(num, mx));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    /**
     <h1>解2</h1>
     考虑奇数个负数时的情况，这时最大乘积要么是去掉第一个负数及左边的数要么是去掉最后一个负数及右边的数<br>
     所以遍历两遍,从左至右计算去掉最后一个负数的(及右侧正数)的积,从右至左计算去掉最前一个负数(及左侧正数)的积<br>
     如果遇到0,那么将当前积重置为1,相当于求一个子数组的最大积,由与res已经记录前一个子数组的最大积,不需要做过多操作
     */
    public int maxProduct2(int[] nums) {
        int res = nums[0];  // 注意这里必须是nums[0],防止只有一个负数的情况
        //从左向右
        int product = 1;
        for (int num : nums) {
            product = product * num;
            // 是正数会更大，是负数，res不会更新
            res = Math.max(res, product);
            // 当遇到0时，看成由0分隔的子数组，求子数组的最大乘积，所以每到一个新的子数组时，就要重置为1
            if (product == 0) {
                product = 1;
            }
        }
        //从右向左
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            product = product * nums[i];
            res = Math.max(res, product);
            if (product == 0) {
                product = 1;
            }
        }
        return res;
    }
}
