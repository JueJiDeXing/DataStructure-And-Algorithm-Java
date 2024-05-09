package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class G补给 {
    /*
    n个点,编号1~n
    总部位于1号节点,单次行走的距离不超过D
    现在需要从总部出发,遍历到所有的点,最后回到总部,点可以重复经过
    求最少行走距离
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }


    static double dis(int[][] point, int i, int j) {
        int x1 = point[i][0], y1 = point[i][1], x2 = point[j][0], y2 = point[j][1];
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    static int INF = 0x3f3f3f3f;
    static int Change_Float_Int = 10000;

    public static void main(String[] args) {
        //输入
        int n = Int(), D = Int();
        int[][] point = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x = Int(), y = Int();
            point[i] = new int[]{x, y};
        }
        //建图
        double[][] graph = new double[n][n]; //n很小,直接邻接矩阵建图
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                graph[i][j] = graph[j][i] = dis(point, i, j);
                if (graph[i][j] > D) graph[i][j] = graph[j][i] = INF;
            }
        }
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
         * dp[s][i] = min { dp[s-{i}][j] + dis(i,j) }
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
                    dp[s][i] = Math.min(dp[s][i], dp[s ^ (1 << i)][j] + (int) (Change_Float_Int * graph[j][i]));
                }
            }
        }
        int ans = INF;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, (int) (graph[0][i] * Change_Float_Int) + dp[tot - 1][i]);//0 -> i  + dp[i]
        }
        System.out.printf("%.2f", (double) ans / Change_Float_Int);
    }
}

