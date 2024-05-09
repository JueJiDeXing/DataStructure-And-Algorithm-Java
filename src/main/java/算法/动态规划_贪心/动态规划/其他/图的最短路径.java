package 算法.动态规划_贪心.动态规划.其他;

import 数据结构.图.Edge;

import java.util.Arrays;
import java.util.List;

public class 图的最短路径 {
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(6, 5, 9),
                new Edge(4, 5, 6),
                new Edge(1, 6, 14),
                new Edge(3, 6, 2),
                new Edge(3, 4, 11),
                new Edge(2, 4, 15),
                new Edge(1, 3, 9),
                new Edge(1, 2, 7)
        );
        minPath(edges);
    }

    public static void minPath(List<Edge> edges) {
        int[] dp = new int[edges.size() - 1];//dp[i]表示到顶点i的最短路径长
        //初始化
        dp[1] = 0;//编号从1开始
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        System.out.println(Arrays.toString(dp));
        for (int i = 0; i < edges.size(); i++) {
            boolean changeFlag = false;
            for (Edge e : edges) {
                if (dp[e.from] != Integer.MAX_VALUE && dp[e.to] > dp[e.from] + e.getWeight()) {
                    dp[e.to] = dp[e.from] + e.getWeight();
                    changeFlag = true;
                }
            }
            if (!changeFlag) {
                break;
            }
            System.out.println(Arrays.toString(dp));
        }
    }
}

