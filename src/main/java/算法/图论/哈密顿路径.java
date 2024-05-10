package 算法.图论;

import 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组.G补给;

import java.util.*;

public class 哈密顿路径 {
    /*
    给定一张无向图,求从起点出发,经过图上所有点,回到起点的最短路径长度
     */

    /**
     {@link G补给}
     */
    public int hamiltonPath(int[][] graph) {
        int n = graph.length;
        //floyd求多源最短路径
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }
        //dp求解
        /*
         * dp[s][i]:从i出发,经过集合s的所有点,最后到达i的最短路径长度
         * dp[s][i] = min { dp[s-{i}][j] + dis(i,j) }  // i 和 j 均在 集合s 中  &&  i!=j
         * ans = min{ dp[s-{0}][i] + dis(0,i) }
         */
        int tot = 1 << n;//全集合(第i个位置是i号节点选和不选)
        int[][] dp = new int[tot][n];
        for (int[] d : dp) Arrays.fill(d, INF);
        dp[1][0] = 0;//从0号点出发,经过{0},到达0号点,长度为0

        for (int s = 0; s < tot; s++) {//枚举集合
            for (int i = 0; i < n; i++) {//枚举集合内的点
                if ((s & (1 << i)) == 0) continue;//i不在集合内

                for (int j = 0; j < n; j++) {//枚举转移
                    if (i == j || (s & (1 << j)) == 0) continue;//j在集合内,但j不等于i
                    dp[s][i] = Math.min(dp[s][i], dp[s ^ (1 << i)][j] + graph[j][i]);
                }
            }
        }
        int ans = INF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, graph[0][i] + dp[tot - 1][i]);//0 -> i  + dp[i]
        }
        return ans;
    }

    static int INF = 0x3f3f3f3f;
}
