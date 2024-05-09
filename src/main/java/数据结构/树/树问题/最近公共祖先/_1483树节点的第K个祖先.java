package 数据结构.树.树问题.最近公共祖先;

public class _1483树节点的第K个祖先 {
    /*
    给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。
    树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。
    树的根节点是编号为 0 的节点。

    树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。

    实现 TreeAncestor 类：

    TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。

    getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。
    如果不存在这样的祖先节点，返回 -1 。
     */
    static class TreeAncestor {
        private final int[][] pa;

        public TreeAncestor(int n, int[] parent) {
            int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
            pa = new int[n][m];
            for (int i = 0; i < n; i++)pa[i][0] = parent[i];
            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = pa[x][i];
                    pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
            for (int i = 0; i < m; i++) {
                if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                    node = pa[node][i];
                    if (node < 0) break;
                }
            }
            return node;
        }

        // 另一种写法，不断去掉 k 的最低位的 1
        public int getKthAncestor2(int node, int k) {
            for (; k > 0 && node != -1; k &= k - 1)
                node = pa[node][Integer.numberOfTrailingZeros(k)];
            return node;
        }
    }
}
