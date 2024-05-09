package 算法.动态规划_贪心.动态规划.题集.鸡蛋掉落;

import java.util.*;

/**
 第 97 场周赛 Q4
 难度分:2377
 */
public class _887鸡蛋掉落 {
    /*
    给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。

    已知存在楼层 f ，满足 0 <= f <= n ，
    任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。

    每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。
    如果鸡蛋碎了，你就不能再次使用它。
    如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。

    请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     */


    /**
     令dp[k][n]表示k个鸡蛋n层楼需要的最小操作次数
     当在x层扔鸡蛋:
     如果鸡蛋碎了,dp[k][n]=dp[k-1][x]+1
     如果鸡蛋没碎,dp[k][n]=dp[k][n-x]+1
     所以dp[k][n]= min( max( dp[k-1][x], dp[k][n-x] ) ) +1
     <br>
     f(x)=dp[k-1][x]随x单增,g(x)=dp[k][n-x]随x单减
     那么h(x)=max(dp[k-1][x],dp[k][n-x])最小时应当取f(x)和g(x)的交点处的x
     由于x是离散的,所以找的是:
     最大的满足f(x)<=g(x)的x0 和 最小的满足f(x)>=g(x)的x1
     然后取二者的最小h(x)即可
     其中x0与x1相差1,x的范围为dp[k][n]的[1,n]的整数
     */
    public int superEggDrop(int k, int n) {
        return dp(k, n);
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int dp(int k, int n) {
        if (n == 0) return 0;
        if (k == 1) return n;
        if (memo.containsKey(n * 100 + k)) return memo.get(n * 100 + k);

        int lo = 1, hi = n;
        while (lo + 1 < hi) {
            int x = (lo + hi) / 2;
            int t1 = dp(k - 1, x - 1);
            int t2 = dp(k, n - x);
            if (t1 < t2) {
                lo = x;
            } else if (t1 > t2) {
                hi = x;
            } else {
                lo = hi = x;
            }
        }
        int ans = 1 + Math.min(Math.max(dp(k - 1, lo - 1), dp(k, n - lo)),
                Math.max(dp(k - 1, hi - 1), dp(k, n - hi)));
        memo.put(n * 100 + k, ans);
        return ans;
    }

    /**
     令 dp[k][m]=n 表示当前有 k 个鸡蛋,可以尝试扔 m 次鸡蛋,这个状态下,最坏情况下最多能确切测试一栋 n 层的楼
     1、无论你在哪层楼扔鸡蛋，鸡蛋只可能摔碎或者没摔碎，碎了的话就测楼下，没碎的话就测楼上。
     2、无论你上楼还是下楼，总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）。
     dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1
     如果可以测出dp[k][m]层的楼,那么在某一层扔出鸡蛋后(记1次操作),问题分解为两个子问题
     1.鸡蛋没碎,测楼上,楼上的层数为dp[k][m-1]
     2.鸡蛋碎了,那么测楼下,楼下的层数为dp[k-1][m-1]
     */
    public int superEggDrop2(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;//TODO 二分优化
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        return m;

    }
}
