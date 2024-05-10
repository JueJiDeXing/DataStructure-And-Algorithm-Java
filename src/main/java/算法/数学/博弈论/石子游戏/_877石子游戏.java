package 算法.数学.博弈论.石子游戏;

public class _877石子游戏 {
    /*
    Alice 和 Bob 用几堆石子在做游戏。
    一共有偶数堆石子，排成一行；每堆都有正整数颗石子，数目为 piles[i] 。

    游戏以谁手中的石子最多来决出胜负。
    石子的 总数 是 奇数 ，所以没有平局。

    Alice 和 Bob 轮流进行，Alice 先开始 。
    每回合，玩家从行的 开始 或 结束 处取走整堆石头。
    这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。

    假设 Alice 和 Bob 都发挥出最佳水平，
    当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。
     */

    /**
     对于固定的输入piles,它的 奇偶序列的和 sum(2i+1) 和 sum(2i) 是固定的,且一定有一个大,一个小<br>
     那么如果先手能够决定选择奇序列或者偶序列,那么他就是必胜的<br>
     [s1,s2,...s2n-1,s2n]<br>
     如果a选择了s1,那么b只能选择s2或s2n,选择之后a一定还能选到s3,总之a一定能够选择奇序列<br>
     如果a选择s2n,那么b只能选择s1或者s2n-1,选择之后a一定还能选到s2n-1,总之a一定能够选择偶序列<br>
     所以a作为先手能够决定是选择奇序列还是偶序列,他只要在游戏开始前计算奇偶序列的和,然后选择大的一个就能必胜<br>
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }

    /**
     <h1>区间dp</h1>
     令dp[i][j]表示 以[i,j]进行游戏, 先手与后手的分差<br>
     假设先手取了i, 那么 dp[i][j] = piles[i] - dp[i+1][j]<br>
     假设先手取了j, 那么 dp[i][j] = piles[j] - dp[i][j-1]<br>
     所以 dp[i][j] = max( piles[i]- dp[i+1][j], piles[j] - dp[i][j-1] )<br>
     */
    public boolean stoneGame2(int[] ps) {
        int n = ps.length;
        int[][] dp = new int[n + 2][n + 2];
        for (int len = 1; len <= n; len++) { // 枚举区间长度
            for (int i = 1; i + len - 1 <= n; i++) { // 枚举左端点
                int j = i + len - 1; // 计算右端点
                dp[i][j] = Math.max(ps[i - 1] - dp[i + 1][j], ps[j - 1] - dp[i][j - 1]);
            }
        }
        return dp[1][n] > 0;
    }
}
