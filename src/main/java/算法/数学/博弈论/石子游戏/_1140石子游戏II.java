package 算法.数学.博弈论.石子游戏;

public class _1140石子游戏II {
    /*
    爱丽丝和鲍勃继续他们的石子游戏。
    许多堆石子 排成一行，每堆都有正整数颗石子 piles[i]。
    游戏以谁手中的石子最多来决出胜负。

    爱丽丝和鲍勃轮流进行，爱丽丝先开始。最初，M = 1。

    在每个玩家的回合中，该玩家可以拿走剩下的 前 X 堆的所有石子，其中 1 <= X <= 2M。
    然后，令 M = max(M, X)。

    游戏一直持续到所有石子都被拿走。

    假设爱丽丝和鲍勃都发挥出最佳水平，返回爱丽丝可以得到的最大数量的石头。
     */

    /**
     <h1>动态规划</h1>
     令dp[i][M]表示从i开始,且参数为M,可以拿的最大数量<br>
     枚举此次拿的数量x, 则 dp[i][M] = sum(i,i+x) - dp[i+x][max(M,x)]<br>
     要拿最大数量, 那么 dp[i][M] = sum(i,i+x) - MIN(dp[i+x][max(M,x)]) x∈[1,2M]<br>
     ans=dp[0][1]<br>
     <p>
     遍历i,M,然后枚举x,其中i需要倒序遍历,因为dp[i]需要使用dp[i+x]<br>
     M的遍历上界为i/2-1, 因为M最大时的情况是: <br>
     每次拿满2M个, i = 2+4+...+2M = 2M-2, 所以 i >= 2M-2, M <= i/2-1 //等边数列求和Sn=a1(1-q^n)/(1-q)=(an-a1)/(q-1)<br>
     如果i+2*M>=n说明此时可以全部拿走剩余石子, 则 dp[i][M] = sum(i,n-1)<br>

     */
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n + 1];
        int suffSum = 0;
        for (int i = n - 1; i >= 0; --i) {//倒序遍历,因为dp[i]需要使用dp[i+x]
            suffSum += piles[i];
            for (int M = 1; M <= i / 2 + 1; M++) {
                if (i + M * 2 >= n) {//可以全部拿走
                    dp[i][M] = suffSum;
                    continue;
                }
                //枚举x,找MIN(dp[i+x][max(M,x)]
                int mn = Integer.MAX_VALUE;
                for (int x = 1; x <= M * 2; ++x)
                    mn = Math.min(mn, dp[i + x][Math.max(M, x)]);
                dp[i][M] = suffSum - mn; // sum(i,i+x) - MIN(dp[i+x][max(M,x)])
            }
        }
        return dp[0][1];
    }
}
