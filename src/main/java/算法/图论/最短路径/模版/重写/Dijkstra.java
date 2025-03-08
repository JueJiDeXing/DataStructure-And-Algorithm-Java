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

    static int I() throws Exception {
        st.nextToken();
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
/*
10 19 0
0 1 4
0 2 1
0 3 3
1 4 9
1 5 8
2 3 1
2 4 6
2 6 8
2 5 7
3 5 4
3 6 7
4 7 5
4 8 6
5 8 6
5 7 8
6 7 6
6 8 5
7 9 7
8 9 3
 */
    public static void main(String[] args) throws Exception {
        // 输入n个点(编号0~9),m条边, 起点
        int n = I(), m = I(), s = I();
        List<int[]>[] graph = new ArrayList[n];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = I(), v = I(), w = I();
            graph[u].add(new int[]{v, w});
            //graph[v].add(new int[]{u, w});//无向图
        }
        // 求s到其他点的最短路长度
        long[] dist = new long[n  ];
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
        // 输出起点到每个点的最短距离
        for (int u = 0; u <  n; u++) {
            System.out.print(dist[u] + " ");
        }
    }
}
