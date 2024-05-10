package 算法.动态规划.题集.跳跃游戏;

public class _55跳跃游戏I {
    /*
    给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
    数组中的每个元素代表你在该位置可以跳跃的最大长度。
    判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     */

    /**
     <h1>动态规划</h1>
     dp[i]:从索引i位置能跳到的最远距离<br>
     状态转移方程:dp[i]=max(dp[i-1],i+nums[i])<br>
     i+nums[i] 表示直接从索引i起跳的最远位置, dp[i-1] 为上一个最远到达位置<br>
     如果索引i无法到达(i>dp[i-1]),则返回false
     */
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i > dp[i - 1]) {
                return false;//索引i无法到达
            }
            dp[i] = Math.max(dp[i - 1], i + nums[i]);
            if (dp[i] >= nums.length - 1) {
                return true;//可以到达最后
            }
        }
        return true;
    }

    /**
     滚动变量降维
     */
    public boolean canJump2(int[] nums) {
        int dp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > dp) {
                return false;//无法到达索引i
            }
            dp = Math.max(dp, i + nums[i]);
            if (dp >= nums.length - 1) {
                return true;//可以到达最后
            }
        }
        return true;
    }

    /**
     <h1>贪心</h1>
     flag:最近的 能到达目的地 的 下标<br>
     判断一个点能否到达目的地，只需要看它的最大步数是否不小于到flag的距离就行了<br>
     不小于就更新flag，小于就继续遍历。<br>
     最后只需要判断flag是否在第一个位置就行了。
     */
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int flag = n - 1;//flag:最近的 能到达目的地 的 下标
        for (int i = n - 2; i >= 0; i--) {//倒序判断前面的索引能否到达目的地
            //判断索引i位置能否到达flag
            if (nums[i] >= flag - i) {
                //能到达,更新 最近的 能到达目的地 的 下标 为i
                flag = i;
            }
        }
        return flag == 0;
    }
}
