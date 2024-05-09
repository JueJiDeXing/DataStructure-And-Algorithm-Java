package 数据结构.图.图的深搜和广搜;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 第 364 场周赛 Q4
 难度分:2428
 */
public class _2867统计树中的合法路径数目 {
    /*
    给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。

    请你返回树中的 合法路径数目 。

    如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。

    注意：

    路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
    路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。

     */

    /**
     <h1>素数筛+深搜+计数原理</h1>
     枚举质数节点x,假设与x有若干个连通块,其中y1,y2,...yk是不含质数的连通块
     那么任取ym和yn中的两个节点,它们是有效路径
     根据乘法原理,路径数为y1*y2+(y1+y2)*y3+...+Sum(yi)*yk
     然后从x出发到yi中的节点也是合法路径,还需要加上y1+y2+..+yk
     <p>
     为避免反复 DFS 同一个非质数连通块，
     可以把每个非质数所处的连通块的大小记录下来（类似记忆化搜索）。如果之前计算过，就无需再次 DFS。

     为处理质数,需要预先筛出素数表(合数表)np
     */
    public long countPaths(int n, int[][] edges) {
        //建图,邻接表
        List<Integer>[] g = new ArrayList[n + 1];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }
        long ans = 0;
        int[] size = new int[n + 1];
        var nodes = new ArrayList<Integer>();
        for (int x = 1; x <= n; x++) {//枚举质数x
            if (np[x]) continue;
            int sum = 0;
            for (int y : g[x]) { // 遍历不含质数的连通块y
                if (!np[y]) continue;//路径上只能有1个质数,所以y路走不通
                if (size[y] == 0) { // 尚未计算过
                    nodes.clear();
                    dfs(y, -1, g, nodes); // 遍历 y 所在连通块，在不经过质数的前提下，统计有多少个非质数
                    for (int z : nodes) {
                        size[z] = nodes.size();
                    }
                }
                // 这 size[y] 个非质数与之前遍历到的 sum 个非质数，两两之间的路径只包含质数 x
                ans += (long) size[y] * sum;
                sum += size[y];
            }
            ans += sum; // 从 x 出发的路径
        }
        return ans;
    }

    private final static int MX = (int) 1e5;
    private final static boolean[] np = new boolean[MX + 1]; // 合数表 质数=false 非质数=true

    static {
        //素数筛
        np[1] = true;
        for (int i = 2; i * i <= MX; i++) {
            if (!np[i]) {
                for (int j = i * i; j <= MX; j += i) {
                    np[j] = true;
                }
            }
        }
    }

    private void dfs(int x, int fa, List<Integer>[] g, List<Integer> nodes) {
        nodes.add(x);
        for (int y : g[x]) {
            if (y != fa && np[y]) {
                dfs(y, x, g, nodes);
            }
        }
    }
}
