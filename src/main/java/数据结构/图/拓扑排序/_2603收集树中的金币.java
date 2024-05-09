package 数据结构.图.拓扑排序;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2603收集树中的金币 {
    /*
    给你一个 n 个节点的无向无根树，节点编号从 0 到 n - 1 。
    给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ， 其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间有一条边。
    再给你一个长度为 n 的数组 coins ，其中 coins[i] 可能为 0 也可能为 1 ，1 表示节点 i 处有一个金币。

    一开始，你需要选择树中任意一个节点出发。你可以执行下述操作任意次：

    收集距离当前节点距离为 2 以内的所有金币，或者
    移动到树中一个相邻节点。
    你需要收集树中所有的金币，并且回到出发节点，请你返回最少经过的边数。

    如果你多次经过一条边，每一次经过都会给答案加一。
     */

    /**
     去掉所有没用的节点(不需要走到的节点),剩余的节点都是要走到的<br>
     假设有n个剩余节点,e条剩余边,因为走后需要返回起点,所以每条边都会经过两次,ans=e*2<br>
     <br>
     没用节点:<br>
     1. 首先去掉没有硬币的叶子节点(没有金币的子树),因为没有硬币需要收集<br>
     2. 在1的基础上去掉2轮叶子节点(删除叶子节点和它的父节点),因为收集距离为2<br>
     剩下的节点就是要走的节点,每条边经过两次,ans=e*2<br>
     */
    public int collectTheCoins(int[] coins, int[][] edges) {
        int n = coins.length;
        List<Integer>[] g = new ArrayList[n];//图
        Arrays.setAll(g, e -> new ArrayList<>());
        var deg = new int[n];//度表
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x); // 建图
            deg[x]++;
            deg[y]++; // 统计每个节点的度数（邻居个数）
        }

        int leftEdges = n - 1; // 剩余边数
        // 1. 去掉没有金币的子树
        var q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; i++) {
            if (deg[i] == 1 && coins[i] == 0) { // 没有金币的叶子
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            leftEdges--; // 删除节点到其父节点的边
            for (int y : g[q.poll()]) {
                if (--deg[y] == 1 && coins[y] == 0) { // 没有金币的叶子
                    q.add(y);
                }
            }
        }

        // 2. 删除
        for (int i = 0; i < n; i++) {
            if (deg[i] == 1 && coins[i] == 1) { // 有金币的叶子（判断 coins[i] 是避免把没有金币的叶子也算进来）
                q.add(i);
            }
        }
        leftEdges -= q.size(); // 删除所有叶子到其父节点的边
        for (int x : q) { // 遍历所有叶子
            for (int y : g[x]) {
                if (--deg[y] == 1) { // y 现在是叶子了
                    leftEdges--; // 删除 y 到其父节点的边
                }
            }
        }
        return Math.max(leftEdges * 2, 0);
    }
}
