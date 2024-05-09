package 算法.博弈论.石子游戏;

public class _1690石子游戏VII {
    /*
    石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。

    有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，
    并获得与该行中剩余石头值之 和 相等的得分。
    当没有石头可移除时，得分较高者获胜。

    鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。
    爱丽丝的目标是最大限度地 扩大得分的差值 。

    给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，
    如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。
    //先手必胜求分差
     */

    /**
     <h1>动态规划</h1>

     令 dp[i][j] 表示 从[i,j]开始游戏的分差<br>
     如果先手取了i, 那么 dp[i][j] = sum(i+1,j) - dp[i+1][j]<br>
     如果先手取了j, 那么 dp[i][j] = sum(i,j-1) - dp[i][j-1]<br>
     所以dp[i][j] = max( sum(i+1,j)-dp[i+1][j] , sum(i,j-1)-dp[i][j-1] )<br>
     最后 ans = dp[0][n-1]<br>
     <p>
     对于sum(i,j),只需要用一维前缀和存储即可, sum(i,j) = sum(j)-sum(i-1)
     */
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        if (n == 1) return 0;
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1];
        for (int i = 0; i < n; i++) sum[i + 1] = sum[i] + stones[i];

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                //dp[i][j] = max( sum(i+1,j)-dp[i+1][j] , sum(i,j-1)-dp[i][j-1] )
                //sum(i,j)=sum[j]-sum[i-1]
                dp[i][j] = Math.max(sum[j + 1] - sum[i + 1] - dp[i + 1][j],
                        sum[j] - sum[i] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}
