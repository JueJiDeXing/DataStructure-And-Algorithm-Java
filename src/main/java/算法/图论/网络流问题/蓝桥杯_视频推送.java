package 算法.图论.网络流问题;

import java.io.*;
import java.util.*;

public class 蓝桥杯_视频推送 {
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

    static int N = 105, M = 1005, k = 1;
    static int[] head = new int[N];
    static int[] d = new int[N];
    static int n, m, S, T;

    static class Edge {
        int v;
        long flow;
        int next;

        public Edge(int v, long flow, int next) {
            this.v = v;
            this.flow = flow;
            this.next = next;
        }
    }

    static Edge[] e = new Edge[2 * M];

    static void add(int u, int v, int f) {
        e[++k] = new Edge(v, f, head[u]);
        head[u] = k;
        e[++k] = new Edge(u, 0, head[v]);
        head[v] = k;
    }

    static boolean bfs() {
        Arrays.fill(d, 0);
        Queue<Integer> que = new LinkedList<>();
        que.offer(S);
        d[S] = 1;
        while (!que.isEmpty()) {
            int x = que.poll();
            for (int i = head[x]; i > 0; i = e[i].next) {
                if (d[e[i].v] == 0 && e[i].flow != 0) {
                    que.offer(e[i].v);
                    d[e[i].v] = d[x] + 1;
                }
            }
            if (d[T] != 0) return true;
        }
        return false;
    }

    static long dfs(int u, long f) {
        if (u == T) return f;
        long res = f;
        for (int i = head[u]; i > 0 && res > 0; i = e[i].next) {
            if (d[e[i].v] == d[u] + 1 && e[i].flow != 0) {
                long temp = dfs(e[i].v, Math.min(res, e[i].flow));
                if (temp == 0) d[e[i].v] = 0;
                res -= temp;
                e[i].flow -= temp;
                e[i ^ 1].flow += temp;
            }
        }
        return f - res;
    }

    static long Dinic() {
        long ans = 0, temp;
        while (bfs()) {
            while ((temp = dfs(S, Long.MAX_VALUE)) != 0) {
                ans += temp;
            }
            System.out.println(ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        n = nextInt();
        m = nextInt();
        S = nextInt();
        T = nextInt();
        //链式前向星建图
        for (int i = 0; i < m; i++) {
            int u = nextInt(), v = nextInt(), f = nextInt();
            add(u, v, f);
        }
        System.out.println(Dinic());
    }
}
