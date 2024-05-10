package 算法.进阶数据结构算法.树.最近公共祖先;

import java.util.*;

public class _2846边权重均等查询 {
    /*
    现有一棵由n个节点组成的无向树，节点按从0到n - 1编号。
    给你一个整数n和一个长度为n - 1的二维整数数组edges，
    其中edges[i] = [ui, vi, wi]表示树中存在一条位于节点ui和节点vi之间、权重为wi的边。

    另给你一个长度为m的二维整数数组queries，其中queries[i] = [ai, bi]。
    对于每条查询，请你找出使从ai到bi路径上每条边的权重相等所需的最小操作次数。
    在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。

    注意：

    查询之间相互独立的，这意味着每条新的查询时，树都会回到初始状态。
    从ai到bi的路径是一个由不同节点组成的序列，从节点ai开始，到节点bi结束，且序列中相邻的两个节点在树中共享一条边。
    返回一个长度为m的数组answer，其中answer[i]是第i条查询的答案。

    1 <= n  <= 1e4
    1 <= wi <= 26
     */
}

/**
 <h1>力扣官方题解-tarjan</h1>
 选择任意一个节点为根, 令 freq[node][w] 表示从根节点到node路径上边权位w的边的个数<br>
 那么从a到b的路径上,边权为w的边数有 t = freq[a][w] + freq[b][w] - 2 * freq[lca(a, b)][w]<br>
 lca(a, b) 表示a和b的最近公共祖先, 可由二进制提升算法或tarjan算法得到<br>
 <p></p>
 为了让节点 ai 到节点 bi 路径上每条边的权重都相等, 贪心地将路径上所有的边都更改为边数量最多的权重即可<br>
 即从节点 ai 到节点 bi 路径上每条边的权重都相等, 所需的最小操作次数 resi 的计算如下:<br>
 resi = &sum { j=1 -> W } tj - max(t) 其中W=26表示权重的最大值<br>
 */
class Solution1 {
    static final int W = 26;

    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int m = queries.length;
        //邻接表建图,存储邻居节点和相应边权
        Map<Integer, Integer>[] neighbors = new Map[n];
        for (int i = 0; i < n; i++) neighbors[i] = new HashMap<>();

        for (int[] edge : edges) {
            neighbors[edge[0]].put(edge[1], edge[2]);
            neighbors[edge[1]].put(edge[0], edge[2]);
        }

        List<int[]>[] queryArr = new List[n];
        for (int i = 0; i < n; i++) queryArr[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            queryArr[queries[i][0]].add(new int[]{queries[i][1], i});
            queryArr[queries[i][1]].add(new int[]{queries[i][0], i});
        }
        //求 count 和 最近公共祖先表
        int[][] count = new int[n][W + 1];//count[node][w]表示从根到node路径上边权为w的边的个数
        boolean[] visited = new boolean[n];
        int[] uf = new int[n];
        int[] lca = new int[m];
        tarjan(0, -1, neighbors, queryArr, count, visited, uf, lca);

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int totalCount = 0, maxCount = 0;
            for (int j = 1; j <= W; j++) {
                int t = count[queries[i][0]][j] + count[queries[i][1]][j] - 2 * count[lca[i]][j];
                maxCount = Math.max(maxCount, t);
                totalCount += t;
            }
            res[i] = totalCount - maxCount;
        }
        return res;
    }

    public void tarjan(int node, int parent, Map<Integer, Integer>[] neighbors, List<int[]>[] queryArr, int[][] count, boolean[] visited, int[] uf, int[] lca) {
        if (parent != -1) {
            System.arraycopy(count[parent], 0, count[node], 0, W + 1);
            count[node][neighbors[node].get(parent)]++;
        }
        uf[node] = node;
        for (int child : neighbors[node].keySet()) {
            if (child == parent) continue;

            tarjan(child, node, neighbors, queryArr, count, visited, uf, lca);
            uf[child] = node;
        }
        for (int[] pair : queryArr[node]) {
            int node1 = pair[0], index = pair[1];
            if (node != node1 && !visited[node1]) continue;

            lca[index] = find(uf, node1);
        }
        visited[node] = true;
    }

    public int find(int[] uf, int i) {
        if (uf[i] == i) return i;

        uf[i] = find(uf, uf[i]);
        return uf[i];
    }
}

/**
 <h1>灵茶山艾府-LCA二进制倍增法</h1>
 */
class Solution2 {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1], w = e[2] - 1;
            g[x].add(new int[]{y, w});
            g[y].add(new int[]{x, w});
        }

        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        var pa = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(pa[i], -1);
        }
        var cnt = new int[n][m][26];
        var depth = new int[n];
        dfs(0, -1, g, pa, cnt, depth);

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                if (p != -1) {
                    int pp = pa[p][i];
                    pa[x][i + 1] = pp;
                    for (int j = 0; j < 26; j++) {
                        cnt[x][i + 1][j] = cnt[x][i][j] + cnt[p][i][j];
                    }
                }
            }
        }

        var ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int x = queries[qi][0], y = queries[qi][1];
            int pathLen = depth[x] + depth[y];
            var cw = new int[26];
            if (depth[x] > depth[y]) {
                int temp = x;
                x = y;
                y = temp;
            }

            // 让 y 和 x 在同一深度
            for (int k = depth[y] - depth[x]; k > 0; k &= k - 1) {
                int i = Integer.numberOfTrailingZeros(k);
                int p = pa[y][i];
                for (int j = 0; j < 26; ++j) {
                    cw[j] += cnt[y][i][j];
                }
                y = p;
            }

            if (y != x) {
                for (int i = m - 1; i >= 0; i--) {
                    int px = pa[x][i];
                    int py = pa[y][i];
                    if (px != py) {
                        for (int j = 0; j < 26; j++) {
                            cw[j] += cnt[x][i][j] + cnt[y][i][j];
                        }
                        x = px;
                        y = py; // x 和 y 同时上跳 2^i 步
                    }
                }
                for (int j = 0; j < 26; j++) {
                    cw[j] += cnt[x][0][j] + cnt[y][0][j];
                }
                x = pa[x][0];
            }

            int lca = x;
            pathLen -= depth[lca] * 2;
            int maxCw = 0;
            for (int i = 0; i < 26; i++) {
                maxCw = Math.max(maxCw, cw[i]);
            }
            ans[qi] = pathLen - maxCw;
        }
        return ans;
    }

    private void dfs(int x, int fa, List<int[]>[] g, int[][] pa, int[][][] cnt, int[] depth) {
        pa[x][0] = fa;
        for (var e : g[x]) {
            int y = e[0], w = e[1];
            if (y != fa) {
                cnt[y][0][w] = 1;
                depth[y] = depth[x] + 1;
                dfs(y, x, g, pa, cnt, depth);
            }
        }
    }
}
