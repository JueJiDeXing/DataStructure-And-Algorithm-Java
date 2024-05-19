package 算法.图论.最短路径.例题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class 牛客_直线与圆 {
    /*
     从L1到L2的最短距离
     在L1、L2和圆上行走不计算距离
     */

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    public static void main(String[] args) throws Exception {
        int n = I(), A = I(), B = I(), C1 = I(), C2 = I();
        double[][] graph = new double[n + 2][n + 2];
        for (double[] g : graph) Arrays.fill(g, Double.MAX_VALUE);
        // 0号为L1, 1号为L2, 2~n+1号为圆
        graph[0][1] = graph[1][0] = d1(A, B, C1, C2);
        int[][] circle = new int[n + 2][3];
        for (int i = 2; i <= n + 1; i++) {
            int x = I(), y = I(), r = I();
            circle[i] = new int[]{x, y, r};
            graph[0][i] = graph[i][0] = d2(A, B, C1, circle[i]);
            graph[1][i] = graph[i][1] = d2(A, B, C2, circle[i]);
            for (int j = 2; j < i; j++) {
                graph[i][j] = graph[j][i] = d3(circle[i], circle[j]);
            }
        }
        //求0->1的最短路
        double[] dist = new double[n + 2];
        Arrays.fill(dist, Double.MAX_VALUE / 2);
        dist[0] = 0;
        Queue<V> queue = new PriorityQueue<>(Comparator.comparingDouble(a -> a.dist));
        queue.add(new V(0, 0));
        while (!queue.isEmpty()) {
            V cur = queue.poll();
            if (cur.dist > dist[cur.v]) continue;
            for (int i = 0; i < n + 2; i++) {
                if (i == cur.v || graph[cur.v][i] == Double.MAX_VALUE) continue;
                if (dist[i] > dist[cur.v] + graph[cur.v][i]) {
                    dist[i] = dist[cur.v] + graph[cur.v][i];
                    queue.add(new V(i, dist[i]));
                }
            }
        }
        System.out.println(dist[1]);
    }

    static class V {
        int v;
        double dist;

        public V(int v, double dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    /**
     直线距离
     */
    static double d1(int A, int B, int C1, int C2) {
        return Math.abs(C1 - C2) / Math.sqrt(A * A + B * B);
    }

    /**
     直线与圆距离
     */
    static double d2(int A, int B, int C, int[] c) {
        return d2(A, B, C, c[0], c[1], c[2]);
    }

    /**
     直线与圆距离
     */
    static double d2(int A, int B, int C, int x, int y, int r) {
        double d = Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
        return Math.max(d - r, 0);
    }

    /**
     两圆距离
     */
    static double d3(int[] c1, int[] c2) {
        return d3(c1[0], c1[1], c1[2], c2[0], c2[1], c2[2]);
    }

    /**
     两圆距离
     */
    static double d3(int x1, int y1, int r1, int x2, int y2, int r2) {
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return Math.max(d - r1 - r2, 0);
    }

}
