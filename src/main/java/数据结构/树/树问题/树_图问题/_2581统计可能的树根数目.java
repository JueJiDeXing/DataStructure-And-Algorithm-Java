package 数据结构.树.树问题.树_图问题;

import java.util.*;

/**
 第 99 场双周赛 Q4
 难度分:2228
 */
public class _2581统计可能的树根数目 {
    /*
    Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。
    树用一个长度为 n - 1 的二维整数数组 edges 表示，其中 edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。

    Alice 想要 Bob 找到这棵树的根。她允许 Bob 对这棵树进行若干次 猜测 。每一次猜测，Bob 做如下事情：

    选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
    Bob 猜测树中 u 是 v 的 父节点 。
    Bob 的猜测用二维整数数组 guesses 表示，其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。

    Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。

    给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。如果没有这样的树，则返回 0。
     */


    public int rootCount(int[][] edges, int[][] guesses, int k) {
        // 建图
        g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        // guesses 转成哈希表
        for (int[] e : guesses) {
            s.add((long) e[0] << 32 | e[1]); // 两个 4 字节 int 压缩成一个 8 字节 long
        }

        dfs(0, -1);//先统计以0为根时猜测正确的次数cnt0
        reroot(0, -1, cnt0, k);//换根dp,计算答案
        return ans;
    }

    private List<Integer>[] g;
    private final Set<Long> s = new HashSet<>();
    private int ans, cnt0;

    private void dfs(int x, int fa) {
        for (int y : g[x]) {
            if (y == fa) continue;
            if (s.contains((long) x << 32 | y)) { // 以 0 为根时，猜对了
                cnt0++;
            }
            dfs(y, x);
        }
    }

    private void reroot(int x, int fa, int cnt, int k) {
        if (cnt >= k) ans++;  // 此时 cnt 就是以 x 为根时的猜对次数

        for (int y : g[x]) {
            if (y == fa) continue;
            int c = cnt;
            if (s.contains((long) x << 32 | y)) c--; // 原来是对的，现在错了
            if (s.contains((long) y << 32 | x)) c++; // 原来是错的，现在对了
            reroot(y, x, c, k);
        }
    }

}
