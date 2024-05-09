package 数据结构.图.网络流问题;

import java.io.*;
import java.util.*;

public class Temp {
    /*
    相关性定义:
    如果视频A到视频B有k条不同的路径,则A与B的相关性f(A,B)为 k条路径的每一条的最小相关边之和
    例:
    A ──10──> B ──5──>D
     │                ↑
     └──4──> C───3────┘
     A到D有两条路径, ABD和ACD
     f(A,D) = min{ f(A,B),f(B,D) } + min{ f(A,C),f(C,D) } = 8

     给出N个视频,和M条边,和起点S与终点T
     求f(S,T)
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static class Node {
        int v;
        long flow;

        public Node(int v, long flow) {
            this.v = v;
            this.flow = flow;
        }
    }

    static int N = 105;
    static int[] d = new int[N + 1];
    static int n, m, S, T;
    static List<Node>[] g = new ArrayList[N + 1];

    static boolean bfs() {
        Arrays.fill(d, 0);
        Queue<Integer> que = new LinkedList<>();
        que.offer(S);
        d[S] = 1;
        while (!que.isEmpty()) {
            int x = que.poll();
            for (Node next : g[x]) {
                if (d[next.v] == 0 && next.flow != 0) {
                    que.offer(next.v);
                    d[next.v] = d[x] + 1;
                }
            }
            if (d[T] != 0) return true;
        }
        return false;
    }

    static long dfs(int u, long f) {
        if (u == T) return f;
        long res = f;
        List<Node> nexts = g[u];
        for (int i = 0; i < nexts.size() && res > 0; i++) {
            Node next = nexts.get(i);
            if (d[next.v] != d[u] + 1 || next.flow == 0) continue;
            long temp = dfs(next.v, Math.min(res, next.flow));
            if (temp == 0) d[next.v] = 0;
            res -= temp;

            next.flow -= temp;
            for (Node next1 : g[next.v]) {
                if (next1.v == u) {
                    next1.flow += temp;
                }
            }

        }
        return f - res;
    }

    static long Dinic() {
        long ans = 0;
        while (bfs()) {
            while (true) {
                long res = dfs(S, Long.MAX_VALUE/2);
                if (res == 0) break;
                ans += res;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        n = nextInt();
        m = nextInt();
        S = nextInt();
        T = nextInt();
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int u = nextInt(), v = nextInt(), f = nextInt();
            g[u].add(new Node(v, f));
            g[v].add(new Node(u, f));
        }
        System.out.println(Dinic());
    }
}
