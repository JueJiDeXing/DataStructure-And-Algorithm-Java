package 数据结构.图.图的深搜和广搜;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2646最小化旅行的价格总和 {
    /*
    现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。
    给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，
    其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。

    每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。

    给定路径的 价格总和 是该路径上所有节点的价格之和。

    另给你一个二维整数数组 trips ，
    其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。

    在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。

    返回执行所有旅行的最小价格总和。
     */

    /**
     <h1>顶点贡献法:深度优先+动态规划</h1>
     <ul>
     <li>
     <b>顶点贡献:</b><br>
     顶点包含endi的数量即顶点的贡献
     </li>
     <li>
     <b>深度优先搜索:</b><br>
     假设一个顶点包含endi的数量为k,说明这个顶点需要经过k次,所需的价格为price[node]*k<br>
     使用深度优先搜索,对每个trip从trip.start开始,到trip.end路径上的所有节点都加1<br>
     </li>
     <li>
     <b>动态规划:</b><br>
     dp[i][0]表示节点i价格不减半,dp[i][1]表示节点i价格减半<br>
     状态转移方程:<br>
     dp[parent][0] += sum( min(dp[node_i][0],dp[node_i][1]) ) // parent价格不减半,那么node_i可选减半
     dp[parent][1] += sum( dp[node_i][0] ) // parent价格减半,那么node_i不能减半
     </li>
     </ul>

     @param n     n个节点
     @param edges 节点的连接关系
     @param price 节点的价格数组
     @param trips trip[i]=[starti,endi]为第i次旅行从strati到endi
     @return 旅行的最小价格
     */
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        //邻接表建图
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        //深度搜索,计算每个顶点包含endi的数量
        int[] count = new int[n];
        for (int[] trip : trips) {
            dfs(trip[0], -1, trip[1], graph, count);
        }
        //树型dp,对于一个node,它有减半和不减半两种操作(如果上一次减半,这次不能减半)
        int[] pair = dp(0, -1, price, graph, count);
        return Math.min(pair[0], pair[1]);
    }

    public boolean dfs(int node, int parent, int end, List<Integer>[] graph, int[] count) {
        if (node == end) {
            count[node]++;
            return true;
        }
        for (int child : graph[node]) {
            if (child == parent) {
                continue;
            }
            if (dfs(child, node, end, graph, count)) {
                count[node]++;
                return true;
            }
        }
        return false;
    }

    /**
     @param node   当前节点
     @param parent 当前节点的父节点
     @param price  节点的价格数组
     @param graph  图(邻接表)
     @param count  节点贡献数组,存储了每个节点经过的次数
     @return 返回pair=[p1,p2]其中 p1=以node为根,node不减半的最小价格 , p2=以node为根,node减半的最小价格
     */
    public int[] dp(int node, int parent, int[] price, List<Integer>[] graph, int[] count) {
        int p = price[node] * count[node];
        int[] res = {p, p / 2};
        for (int child : graph[node]) {
            if (child == parent) {
                continue;
            }
            int[] pair = dp(child, node, price, graph, count);
            res[0] += Math.min(pair[0], pair[1]); // node 没有减半，因此可以取子树的两种情况的最小值
            res[1] += pair[0]; // node 减半，只能取子树没有减半的情况
        }
        return res;
    }
}


