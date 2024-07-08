package 算法.图论.最短路径.模版.重写;

import java.io.*;
import java.util.*;

public class SPFA {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String S() throws IOException {
        String res = bf.readLine();
        while (res.isEmpty()) res = bf.readLine();
        return res;
    }

    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        int n = I(), m = I(), start = I();
        graph = new ArrayList[n + 1];//编号从1开始
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
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
        System.out.println(dist[n]);
    }
}
