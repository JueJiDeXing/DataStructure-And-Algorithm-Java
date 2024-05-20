package 算法.图论.最短路径.例题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;
/**
 已AC
 */
public class 牛客_三点旅行 {

    /*
     给定图,求 max{ 三个点(起点->中转->终点)的最短路径长度 }
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) {
            solve();
        }
    }

    static void solve() throws Exception {
        int n = I(), m = I();
        List<int[]>[] graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int a = I(), b = I(), c = I();
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
        int ans = -1;
        for (int c = 1; c <= n; c++) {//枚举中转点
            int[] dist = dijkstra(graph, c);// 求中转点到其他点的最短路长度
            //找两个最长的
            Arrays.sort(dist);
            List<Integer> l = new ArrayList<>();
            for (int i = n; i >= 1; i--) {
                if (dist[i] != Integer.MAX_VALUE / 2 && i != c) {
                    l.add(dist[i]);
                    if (l.size() == 2) break;
                }
            }
            if (l.size() == 2) ans = Math.max(ans, l.get(0) + l.get(1));
        }
        System.out.println(ans);
    }

    static int[] dijkstra(List<int[]>[] graph, int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[start] = 0;
        Queue<V> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));
        queue.add(new V(start, 0));
        while (!queue.isEmpty()) {
            V cur = queue.poll();
            if (cur.w > dist[cur.v]) continue;
            for (int[] e : graph[cur.v]) {
                if (dist[e[0]] > cur.w + e[1]) {
                    dist[e[0]] = cur.w + e[1];
                    queue.add(new V(e[0], dist[e[0]]));
                }
            }
        }
        return dist;
    }

    static class V {
        int v;
        int w;

        public V(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
