package 数据结构.树.树问题.树_图问题;

import java.util.*;

/**
 难度:中等
 */
public class _310最小高度树 {
    /*
    树是一个无向图，其中任何两个顶点只通过一条路径连接。
    换句话说，任何一个没有简单环路的连通图都是一棵树。

    给你一棵包含 n 个节点的树，标记为 0 到 n - 1 。
    给定数字 n 和一个有 n - 1 条无向边的 edges 列表（每一个边都是一对标签），
    其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条无向边。

    可选择树中任何一个节点作为根。当选择节点 x 作为根节点时，设结果树的高度为 h 。
    在所有可能的树中，具有最小高度的树（即，min(h)）被称为 最小高度树 。

    请你找到所有的 最小高度树 并按 任意顺序 返回它们的根节点标签列表。

    树的 高度 是指根节点和叶子节点之间最长向下路径上边的数量。
     */

    /**
     假设图中有节点x和y,xy的路径长度是最长的,记为maxDis
     那么这个图的最小高度树的高度 等于 maxDis/2 ,且 根在这条最长路径的中点(如果为偶数,则取中间两个之一)
     <p>
     那么只需要找到一个图中最长路径, 记录路径上的节点, 取中点即可(如果为偶数则两个都是答案)
     <p>
     找最长路径(bfs):
     可以从任意节点p出发,找到p的最远节点x
     再从x出发,找到x的最远节点y
     则xy就是最长的路径的端点
     在寻找的图中额外记录上级节点parent[]
     则可通过节点y,向上级返回补全路径
     最后返回路径中点
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        //建图
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, k -> new ArrayList<Integer>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        /* 找到与节点 0 最远的节点 x */
        int x = findLongestNode(0, parent, graph);
        /* 找到与节点 x 最远的节点 y */
        int y = findLongestNode(x, parent, graph);
        /* 求出节点 x 到节点 y 的路径 */
        List<Integer> path = new ArrayList<>();
        parent[x] = -1;
        while (y != -1) {
            path.add(y);
            y = parent[y];
        }
        int m = path.size();
        if (m % 2 == 0) {
            ans.add(path.get(m / 2 - 1));
        }
        ans.add(path.get(m / 2));
        return ans;
    }

    public int findLongestNode(int u, int[] parent, List<Integer>[] adj) {
        int n = adj.length;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[n];
        queue.offer(u);
        visit[u] = true;
        int node = -1;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            node = curr;
            for (int v : adj[curr]) {
                if (visit[v]) continue;
                visit[v] = true;
                parent[v] = curr;
                queue.offer(v);
            }
        }
        return node;
    }

    /**
     将最外层节点层层删除,最后留下的就是最长路径的中点
     所以可以使用拓扑序
     */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if (n == 1) {
            ans.add(0);
            return ans;
        }
        int[] degree = new int[n];
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, k -> new ArrayList<Integer>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
            degree[edge[0]]++;
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {//最外层节点
            if (degree[i] == 1) queue.offer(i);
        }
        int remainNodes = n;
        while (remainNodes > 2) {
            //删除当前层
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                int curr = queue.poll();
                for (int v : graph[curr]) {
                    degree[v]--;
                    if (degree[v] == 1) queue.offer(v);
                }
            }
            remainNodes -= sz;
        }
        while (!queue.isEmpty()) {
            ans.add(queue.poll());
        }
        return ans;
    }
}
