package 算法.图论.图的深搜和广搜;

import java.util.*;

public class _1466重新规划路线 {
    /*
    n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。
    因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。
    去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
    路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
    今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
    请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。
    返回需要变更方向的最小路线数。
    题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
     */

    /**
     <h1>深度优先</h1>
     */
    public int minReorder(int n, int[][] connections) {
        List<int[]>[] e = new List[n];
        for (int i = 0; i < n; i++) {
            e[i] = new ArrayList<int[]>();
        }
        for (int[] edge : connections) {
            e[edge[0]].add(new int[]{edge[1], 1});
            e[edge[1]].add(new int[]{edge[0], 0});
        }
        return dfs(0, -1, e);
    }

    public int dfs(int x, int parent, List<int[]>[] e) {
        int res = 0;
        for (int[] edge : e[x]) {
            if (edge[0] == parent) {
                continue;
            }
            res += edge[1] + dfs(edge[0], x, e);
        }
        return res;
    }

    /**
     <h1>广度优先</h1>
     */
    public int minReorder2(int n, int[][] connections) {
        //邻接表建图
        List<int[]>[] graph = new List[n]; //int[][]nei==graph[i]为节点i的邻居;nei[k]==[j,direction],k∈[0,1],只有两个邻居,j为邻居索引,direction为连接方向,1为i->j,0为j->i
        Arrays.setAll(graph, e -> new ArrayList<int[]>());
        for (int[] edge : connections) {
            graph[edge[0]].add(new int[]{edge[1], 1});
            graph[edge[1]].add(new int[]{edge[0], 0});
        }
        //设置源
        int count = 0;
        boolean[] isSource = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        isSource[0] = true;
        //广度优先搜索
        while (!queue.isEmpty()) {
            int v = queue.poll();//每次抛出一个源
            for (int[] edge : graph[v]) {//搜索它的两个邻居
                if (isSource[edge[0]]) continue;//如果邻居是源,则说明是已经遍历的节点,跳过
                if (edge[1] == 1) {  //如果指向的邻居不是源,将指向反转
                    //反转
                    count++;
                    edge[1] = 0;
                    for (int[] _edge : graph[edge[0]]) {
                        if (_edge[0] == v) _edge[1] = 1;
                    }
                }//else edge[1] == 0 此时邻居指向自己(源),不用反转
                //将邻居设为新的源入队
                isSource[edge[0]] = true;
                queue.offer(edge[0]);
            }
        }
        return count;
    }

}
