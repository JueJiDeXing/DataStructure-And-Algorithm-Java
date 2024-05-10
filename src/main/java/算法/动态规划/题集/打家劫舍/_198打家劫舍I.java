package 算法.动态规划.题集.打家劫舍;

public class _198打家劫舍I {
    //与LCR089为同一题
    /*
    你是一个专业的小偷，计划偷窃沿街的房屋。
    每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
    如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

    给定一个代表每个房屋存放金额的非负整数数组，
    计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     */

    /**
     限制: 不能连续偷,偷的数量任意,最高金额<br>
     定义: dp[i]偷[0,i]能偷到的最高金额<br>
     如果 i+1 不偷, 则 dp[i+1] = dp[i] ; 如果 i+1 偷 (i一定不偷), 则 dp[i+1] = nums[i+1]+dp[i-1]<br>
     所以dp[i+1] = max( dp[i], dp[i-1]+nums[i+1] )<br>
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];//只有1家,偷第1家
        dp[1] = Math.max(nums[0], nums[1]);//只有两家,偷钱多的一家
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }

    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        int first = nums[0];//只有1家,偷第1家
        int second = Math.max(nums[0], nums[1]);//只有两家,偷钱多的一家
        for (int i = 2; i < len; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
