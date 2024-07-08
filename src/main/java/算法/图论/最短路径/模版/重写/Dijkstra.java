package 算法.图论.最短路径.模版.重写;

import 算法OJ.洛谷.普及_提高down.P4779_模板_单源最短路径_标准版;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 {@link P4779_模板_单源最短路径_标准版}
 */
public class Dijkstra {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static class V {
        int v;
        long weight;

        public V(int v, long weight) {
            this.v = v;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        int n = nextInt(), m = nextInt(), s = nextInt();
        List<int[]>[] graph = new ArrayList[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = nextInt(), v = nextInt(), w = nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});//无向图
        }
        // 求s到其他点的最短路长度
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE / 2);
        dist[s] = 0;
        Queue<V> q = new PriorityQueue<>(Comparator.comparingLong(a -> a.weight));//按距离排序
        q.add(new V(s, 0));
        while (!q.isEmpty()) {
            V cur = q.poll();//选择最近的点
            int v = cur.v;
            if (dist[v] < cur.weight) continue;// 最短距离<入队时距离 => 该点已访问
            //更新邻居节点
            for (int[] next : graph[v]) {
                if (dist[next[0]] > dist[v] + next[1]) {
                    dist[next[0]] = dist[v] + next[1];
                    q.add(new V(next[0], dist[next[0]]));
                }
            }
        }
        for (int u = 1; u <= n; u++) {
            System.out.print(dist[u] + " ");
        }
    }
}
