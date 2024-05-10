package 算法.进阶数据结构算法.树.最近公共祖先;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 最近公共祖先模版 {
    private final int[] depth;//节点深度
    private final int[][] pa;//pa[x][y]:xy的最近公共祖先

    /**
     传入一颗树,构建任意两个节点最近公共祖先矩阵pa

     @param edges 树
     */
    public 最近公共祖先模版(int[][] edges) {
        int n = edges.length + 1;// 节点个数
        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        //建图
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) { // 节点编号从 0 开始
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        depth = new int[n];
        pa = new int[n][m];
        dfs(g, 0, -1);

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }

    private void dfs(List<Integer>[] g, int x, int fa) {
        pa[x][0] = fa;
        for (int y : g[x]) {
            if (y == fa) continue;
            depth[y] = depth[x] + 1;
            dfs(g, y, x);
        }
    }

    public int getKthAncestor(int node, int k) {
        for (; k > 0; k &= k - 1)
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        return node;
    }

    public int getLCA(int x, int y) {
        if (depth[x] > depth[y]) {
            return getLCA(y, x);
        }
        // 使 y 和 x 在同一深度
        y = getKthAncestor(y, depth[y] - depth[x]);
        if (y == x)
            return x;
        for (int i = pa[x].length - 1; i >= 0; i--) {
            int px = pa[x][i], py = pa[y][i];
            if (px != py) {
                x = px;
                y = py;
            }
        }
        return pa[x][0];
    }
}
