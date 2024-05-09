package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;
import java.io.*;

/**
 已AC
 */
public class J魔法阵 {
    static int inf = (int) 1e9;
    static int[][] dp = new int[1001][11]; // d[i][j]表示从起点到i，且消除最后的j条连续边伤害的最小伤害。

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        // 获取对应输入N、K、M
        int n = sc.nextInt(), k = sc.nextInt(), m = sc.nextInt();
        // 根据结点数n传入n个数据类型为node的动态存储空间
        List<node>[] g = new List[n];
        Arrays.setAll(g, kk -> new ArrayList<>());
        while (m-- > 0) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            g[u].add(new node(v, w));
            g[v].add(new node(u, w));
        }
        // 运用Dijkstra、Dp算法进行求解
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= k; j++)
                dp[i][j] = inf;
        dp[0][0] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()) {
            int x = q.poll();
            for (node o : g[x]) {//遍历x的邻居节点
                int y = o.to, w = o.w;
                boolean f = false; // 是否进行过操作
                if (dp[y][0] > dp[x][0] + w) {// 到y的不使用魔法的更短路径
                    dp[y][0] = dp[x][0] + w;
                    f = true;
                }
                for (int j = 1; j <= k; j++) {//到y使消除连续j条边,消除 x->y 和 x的最后j-1条
                    if (dp[y][j] > dp[x][j - 1]) {
                        dp[y][j] = dp[x][j - 1];
                        f = true;
                    }
                }
                if (dp[y][k] > dp[x][k] + w) {//y消除最后k条不如x消除最后k条,重置d[y][k]
                    dp[y][k] = dp[x][k] + w;
                    f = true;
                }
                // 如果进行过操作将下一结点下标y入队(防止过多重复节点入队)
                if (f) q.add(y);
            }
        }
        // 比对不使用魔法的最小伤害和使用魔法的最小伤害，取最小值将并最终答案输出到标准输出流（控制台）
        System.out.println(Math.min(dp[n - 1][0], dp[n - 1][k]));

    }

    static class node {
        int to, w;

        public node(int a, int b) {
            to = a;
            w = b;
        }
    }

}
