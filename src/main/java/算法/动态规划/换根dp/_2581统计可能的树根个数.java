package 算法.动态规划.换根dp;

import java.util.*;

public class _2581统计可能的树根个数 {
    /*
    Alice 有一棵 n 个节点的树，节点编号为 0 到 n - 1 。
    树用一个长度为 n - 1 的二维整数数组 edges 表示
    其中 edges[i] = [ai, bi] ，表示树中节点 ai 和 bi 之间有一条边。

    Alice 想要 Bob 找到这棵树的根。
    她允许 Bob 对这棵树进行若干次 猜测 。每一次猜测，Bob 做如下事情：

    选择两个 不相等 的整数 u 和 v ，且树中必须存在边 [u, v] 。
    Bob 猜测树中 u 是 v 的 父节点 。
    Bob 的猜测用二维整数数组 guesses 表示
    其中 guesses[j] = [uj, vj] 表示 Bob 猜 uj 是 vj 的父节点。

    Alice 非常懒，她不想逐个回答 Bob 的猜测，只告诉 Bob 这些猜测里面 至少 有 k 个猜测的结果为 true 。

    给你二维整数数组 edges ，Bob 的所有猜测和整数 k ，请你返回可能成为树根的 节点数目 。
    如果没有这样的树，则返回 0。
     */

    /**
     首先计算以0为根的猜测正确次数<br>
     假设以x为根时猜测正确次数为cnt<br>
     y是x的邻居节点,那么从x为根换到y为根,仅有x和y的父子关系发生改变<br>
     所以:<br>
     如果猜测中有(x,y),那么换到y为根,猜测就变成错误,cnt-1<br>
     如果猜测中有(y,x),那么换到y为根,猜测就变成正确,cnt+1<br>

     @param edges   边 u<-->v
     @param guesses 猜测 u->v
     @param k       至少有k个猜测是对的
     @return 可能为根节点的数量
     */
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        this.k = k;
        // 建图
        g = new ArrayList[edges.length + 1];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        // guesses 转成哈希表
        for (int[] e : guesses) {
            s.add((long) e[0] << 32 | e[1]); // 两个 4 字节 int 压缩成一个 8 字节 long
        }
        //dfs求cnt0:以0为根时的猜对次数
        dfs(0, -1);
        //换根dp
        reroot(0, -1, cnt0);
        return ans;
    }

    List<Integer>[] g;
    Set<Long> s = new HashSet<>();
    int k, ans, cnt0;

    private void dfs(int x, int fa) {
        for (int y : g[x]) {
            if (y != fa) {
                if (s.contains((long) x << 32 | y)) { // 以 0 为根时，猜对了
                    cnt0++;
                }
                dfs(y, x);
            }
        }
    }

    private void reroot(int x, int fa, int cnt) {
        if (cnt >= k) ans++; //  cnt: 以 x 为根时的猜对次数

        for (int y : g[x]) {// 根从x到y,只有x和y的父子关系改变了
            if (y != fa) {
                int c = cnt;
                if (s.contains((long) x << 32 | y)) c--; // 原来是对的，现在错了
                if (s.contains((long) y << 32 | x)) c++; // 原来是错的，现在对了(不能用else if,因为它可能两个都猜了)
                reroot(y, x, c);
            }
        }
    }

}
