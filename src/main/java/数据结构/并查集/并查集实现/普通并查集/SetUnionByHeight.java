package 数据结构.并查集.并查集实现.普通并查集;

import java.util.Arrays;

/**
 数组链,根据树高度合并集合
 */
public class SetUnionByHeight {

    int[] fa;
    /**
     以 i 为根结点的子树的高度（引入了路径压缩以后该定义并不准确）
     */
    int[] rank;

    public SetUnionByHeight(int n) {
        fa = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
        Arrays.fill(rank, 1);
    }

    public void union(int x, int y) {
        int rx = find(x), ry = find(y);
        if (rx == ry) return;
        if (rank[rx] == rank[ry]) {
            fa[rx] = ry;
            // 此时以 ry 为根结点的树的高度仅加了 1
            rank[ry]++;
        } else if (rank[rx] < rank[ry]) {
            fa[rx] = ry;
            // 此时以 ry 为根结点的树的高度不变
        } else {
            // 同理，此时以 rx 为根结点的树的高度不变
            fa[ry] = rx;
        }
    }

    public int find(int x) {
        return x == fa[x] ? x : (fa[x] = find(fa[x]));
    }

    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }
}
