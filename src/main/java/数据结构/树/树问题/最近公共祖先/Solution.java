package 数据结构.树.树问题.最近公共祖先;

import java.util.*;

public class Solution {
    static List<Integer>[] son;//节点邻居,表示为树
    static int[][] fa;//节点i的祖先节点(记录2^k的祖先)
    static int[] depth;//节点i的深度
    static int n;
    static int[] log2;


    public int[] lcaQuestion(int[][] edges, int[][] query) {
        n = edges.length + 1;//n个节点
        initLog2(n);
        son = new ArrayList[n + 1];//节点从编号1开始
        Arrays.setAll(son, k -> new ArrayList<>());
        for (int[] edge : edges) {
            son[edge[0]].add(edge[1]);
            son[edge[1]].add(edge[0]);
        }
        fa = new int[n + 1][20];// log(2,1e5) = 5 * log(2,10) ~ 15
        depth = new int[n + 1];
        dfs(1, 0);//dfs 求 深度信息depth 和 祖先信息fa
        int Q = query.length;
        int[] ans = new int[Q];
        for (int i = 0; i < Q; i++) {
            int[] q = query[i];
            ans[i] = lca(q[0], q[1]);
        }
        return ans;
    }

    void dfs(int u, int parent) {
        depth[u] = depth[parent] + 1;
        fa[u][0] = parent;//向上2^0步就是父节点
        for (int i = 1; (1 << i) <= n; i++) {  // fa[i,2^k] = fa[fa[2^(k-1)],k-1]
            fa[u][i] = fa[fa[u][i - 1]][i - 1];//向上走2^i步等于走两个2^(i-1)
        }
        for (int v : son[u]) {
            if (v == parent) continue;
            dfs(v, u);
        }
    }

    int lca(int u, int v) {
        //首先检查两个节点的深度,深的节点向上跳转到同一深度
        if (depth[u] > depth[v]) {
            int t = u;
            u = v;
            v = t;
        }
        while (depth[u] != depth[v]) {
            v = fa[v][log2[depth[v] - depth[u]]];// 跳 2^floor(log2(diff)) 步
        }
        if (u == v) return u;
        //跳到同一高度,两个节点同时上跳,直到他们的父节点相同
        for (int k = log2[depth[u]]; k >= 0; k--) {//跳2^k步
            if (fa[u][k] != fa[v][k]) {//如果相同表示跳到太大了
                u = fa[u][k];
                v = fa[v][k];
            }
        }
        return fa[u][0];
    }

    void initLog2(int n) {
        log2 = new int[n + 1];
        // log2[1]=0
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }
    }
}
