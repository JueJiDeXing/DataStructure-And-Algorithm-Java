package 算法.图论.图的深搜和广搜;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2477到达首都的最少油耗 {
    /*
    给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，
    编号从 0 到 n - 1 ，且恰好有 n - 1 条路。

    0 是首都。

    给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
    每个城市里有一个代表，他们都要去首都参加一个会议。
    每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。

    城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
    请你返回到达首都最少需要多少升汽油。
     */

    /**
     <h1>边贡献法-dfs</h1>
     总体思路: 计算每条边的贡献,统计贡献总和<br>
     <p>
     对于一颗树,所有节点都要经过边 才能到达根节点, 那么 统计每条边有节点经过的数量, 即可得到油耗<br>
     对于一条边,经过它的节点数 = 它所连接的子树的节点数<br>
     <p>
     另外:<br>
     当 seats>1 时, 说明低节点是可以搭乘高节点顺风车的<br>
     设某条边有节点经过的数量为n <br>
     那么, 计算一条边贡献的油耗时, 则有 k = ceil(n/seats)辆车<br>
     所以这条边油耗贡献值为ceil(n/seats)
     */
    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;//节点数=边数+1
        //邻接表建图
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] e : roads) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        //深度搜索
        dfs(0, -1, g, seats);
        return ans;
    }

    private long ans;

    /**
     统计以fa为根节点的子树x的大小 (节点x顶边经过的城市代表数量)

     @param x     要统计数量的子树的根节点
     @param fa    子树的父节点(当x为根节点时,fa==-1,表示没有根节点)
     @param g     图(邻接表)
     @param seats 一辆车的最大乘坐人数
     @return 子树x的节点数
     */
    private int dfs(int x, int fa, List<Integer>[] g, int seats) {
        int size = 1;
        for (int y : g[x]) {// 遍历x的子节点
            if (y != fa) { // 不能递归父节点
                size += dfs(y, x, g, seats); // 统计子树大小
            }
        }
        if (x > 0) { // x 不是根节点(根节点为0,表示首都,已到达终点没有顶边了)
            ans += (size - 1) / seats + 1; // ceil(size/seats)
        }
        return size;
    }
}
