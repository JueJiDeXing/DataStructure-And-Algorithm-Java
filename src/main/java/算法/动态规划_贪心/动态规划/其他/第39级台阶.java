package 算法.动态规划_贪心.动态规划.其他;

import java.util.Arrays;

public class 第39级台阶 {
    /*
    每次走1个或2个台阶
    走偶数步到第39级台阶
     */

    /**
     <ul>
     <li>设计数据结构:<br>
     dp[i][0]走到第i层台阶,且为偶数的方案<br>
     dp[i][1]走到第i层台阶,且为奇数的方案<br></li>
     <li>初始化:<br>
     dp[0][0]=0;dp[0][1]=0<br>
     dp[1][0]=0;dp[1][1]=1<br>
     dp[2][0]=1;dp[2][1]=1</li>
     <li>状态转移方程:<br>
     dp[i][0]=dp[i-1][1]+dp[i-2][1]<br>
     dp[i][1]=dp[i-1][0]+dp[i-2][0]</li>
     </ul>
     */
    public static void main(String[] args) {
        int[][] dp = new int[40][2];
        dp[1][1] = 1;//走1步(1次,奇数)
        dp[2][0] = 1;//走1步,走1步(2次,偶数)
        dp[2][1] = 1;//走2步(1次,奇数)
        for (int i = 3; i < 40; i++) {
            dp[i][0] = dp[i - 1][1] + dp[i - 2][1];
            dp[i][1] = dp[i - 1][0] + dp[i - 2][0];
        }
        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[39][0]);
    }
}
