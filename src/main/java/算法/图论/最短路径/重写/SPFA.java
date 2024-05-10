package 算法.图论.最短路径.重写;

import java.util.*;

public class SPFA {
    static List<int[]>[] graph;
    static long[] distance;
    static boolean[] isVisit;
    static PriorityQueue<Integer> queue = new PriorityQueue<>(
            (Comparator.comparingInt(o -> Math.toIntExact(distance[o]))));//未访问顶点

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        graph = new ArrayList[n + 1];//编号从1开始
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
    }
}
