package 算法.图论.tarjan算法;

import java.util.*;

/**
 第 154 场周赛 Q4
 难度分:2085
 */
public class _1192查找集群内的关键连接 {
    /*
    力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
    它们之间以 服务器到服务器 的形式相互连接组成了一个内部集群，连接是无向的。

    用  connections 表示集群网络，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。
    任何服务器都可以直接或者间接地通过网络到达任何其他服务器。

    关键连接 是在该集群中的重要连接，假如我们将它移除，便会导致某些服务器无法访问其他服务器。

    请你以任意顺序返回该集群内的所有 关键连接 。
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 常规生成邻接表
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (List<Integer> e : connections) {
            int u = e.get(0), v = e.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        List<List<Integer>> ans = new ArrayList<>();
        int[] time = new int[n]; // dfs过程中，初次访问某个节点时的时间戳
        int[] low = new int[n]; // 每个节点的最小追溯值
        dfs(0, 0, graph, time, low, ans); // 从顶点 0 开始dfs，并认为其 前驱节点为 0
        return ans;
    }

    int num = 0;//节点序

    private void dfs(int u, int pa, List<Integer>[] graph, int[] time, int[] low, List<List<Integer>> ans) {
        low[u] = time[u] = ++num;
        for (int v : graph[u]) {
            if (time[v] == 0) { // 节点v未访问
                dfs(v, u, graph, time, low, ans);
                low[u] = Math.min(low[u], low[v]);//?
                if (low[v] > time[u]) { //发现桥边，添加到结果集
                    ans.add(Arrays.asList(u, v));
                }
            } else if (time[v] < time[u] && v != pa) {
                // v 已经在 u 之前被访问过了。
                // 因为是无向图，u 可以反向访问到其 【dfs搜索树】上的父节点，此时需要跳过。
                low[u] = Math.min(low[u], time[v]);//?
            }
        }
    }
}
