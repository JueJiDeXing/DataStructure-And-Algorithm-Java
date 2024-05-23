package 算法.图论.最短路径.模版.重写;

import java.util.*;

public class SPFA {
    private static List<int[]>[] graph;

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

        int[] dist = spfa(1);
        System.out.println(Arrays.toString(dist));
    }

    public static int[] spfa(int start) {
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean[] visited = new boolean[graph.length];
        visited[start] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visited[cur] = false;
            for (int[] edge : graph[cur]) {
                int next = edge[0], weight = edge[1];
                if (dist[next] > dist[cur] + weight) {
                    dist[next] = dist[cur] + weight;
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
        }
        return dist;
    }
}
