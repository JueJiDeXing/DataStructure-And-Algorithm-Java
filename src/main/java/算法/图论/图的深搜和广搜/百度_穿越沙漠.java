package 算法.图论.图的深搜和广搜;

import java.util.*;

public class 百度_穿越沙漠 {
    /*
    给定n个点,m条边的无向图(不存在重边和自环)
    q个询问,每次询问u到v是否存在长度奇偶性等于p==q的路径

     */
    static int n, m;
    static List<List<Integer>> adj;
    static int[] parent;
    static int[] parity;
    static boolean[] hasCycle;
    static int[] component;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        // 建图
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        // 预处理连通性和奇偶性
        parent = new int[n + 1];
        parity = new int[n + 1];
        hasCycle = new boolean[n + 1];
        component = new int[n + 1];
        Arrays.fill(parent, -1);
        Arrays.fill(parity, -1);
        Arrays.fill(component, -1);

        int compId = 0;// 连通子图id
        for (int i = 1; i <= n; i++) {
            if (parent[i] == -1) {//未访问
                bfs(i, compId++);
            }
        }
        // 处理询问
        int Q = sc.nextInt();
        while (Q-- > 0) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();

            if (component[u] != component[v]) {// 不在同一个子图
                System.out.println("no");
                continue;
            }

            int comp = component[u];
            if (hasCycle[comp]) {// 子图有环路
                System.out.println("yes");
            } else {
                if ((parity[u] == parity[v]) == (p == q)) {// 奇子图|偶子图
                    System.out.println("yes");
                } else {
                    System.out.println("no");
                }
            }
        }
    }

    static void bfs(int start, int compId) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        parent[start] = start;
        parity[start] = 0;
        component[start] = compId;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (parent[v] == -1) {
                    parent[v] = u;
                    parity[v] = parity[u] ^ 1;
                    component[v] = compId;
                    queue.add(v);
                } else if (v != parent[u]) {
                    hasCycle[compId] = true;
                }
            }
        }
    }

}
