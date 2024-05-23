package 算法.算法基础.搜索.dfs;

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
     <h1>顶点贡献法:dfs+动态规划</h1>
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
     <p>
     {@link 算法.图论.图的深搜和广搜._2646最小化旅行的价格总和}
     */
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        return 0;
    }


}


