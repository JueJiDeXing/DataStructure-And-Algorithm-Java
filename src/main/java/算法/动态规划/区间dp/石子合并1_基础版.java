package 算法.动态规划.区间dp;

import 算法.动态规划.四边形表达式优化dp.石子合并_四边形不等式优化;

public class 石子合并1_基础版 {
    /*
    n堆石子排成1排,每次可以合并相邻两堆,代价为两堆个数之和
    求合并为1堆的总代价最小值
     */

    /**
     令dp[i][j]为合并[i,j]的代价<br>
     假设合并[i,j]的最后一步是合并[i,k]和[k+1,j]<br>
     合并的代价一定为sum[i,j]<br>
     则dp[i][j]=dp[i,k]+dp[k+1,j]+sum[i,j]<br>
     sum[i,j]可以使用前缀和预处理<br>
     */
    public int merge(int[] stones) {
        int n = stones.length;
        int[] pre = new int[n + 1];
        for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] + stones[i - 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {//区间dp,合并过程自底向上,区间从小到大,所以要先枚举区间长度
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = 0x3f3f3f3f;
                for (int k = i; k < j; k++) {//枚举最优转移
                    dp[i][j] = Math.min(dp[i][k] + dp[k + 1][j] + pre[j] - pre[i - 1], dp[i][j]);
                }
            }
        }
        return dp[1][n];
    }

    /**
     <h1>四边形不等式优化</h1>
     {@link 石子合并_四边形不等式优化}
     */
    public int merge2(int[] stones) {
        return 0;
    }
}
