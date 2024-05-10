package 算法.图论.生成树.最小生成树;

import 数据结构实现.并查集.普通并查集.DisjointSet;

import java.util.*;

public class Kruskal {
    static class Edge {
        public int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
         /*
                   2                              2
              v1------ v3                     v1 ---- v3
            1/  6\  4/  2\                  1/          2\
           v0     v2     v4                v0     v2     v4
            5\    4\   /2                          4\   /2
               ----- v5                               v5

         */
        int[][] graph2 = new int[][]{{0, 1, 0, 0, 0, 5}, {1, 0, 6, 2, 0, 0}, {0, 6, 0, 4, 0, 4}, {0, 2, 4, 0, 2, 0}, {0, 0, 0, 2, 0, 2}, {5, 0, 4, 0, 2, 0}};
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        int n = graph2.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (graph2[i][j] != 0) queue.offer(new Edge(i, j, graph2[i][j]));
            }
        }
        List<Edge> prev2 =  kruskal(n, queue);
        for (Edge p : prev2) System.out.print("(" + p.start + "," + p.end + ") ");
    }

    /**
     <h1>Kruskal 最小生成树算法</h1>
     1.将所有顶点设为"不连通" (使用并查集表达连通状态)<br>
     2.将所有边按边权升序<br>
     3.每次选择最小权边,如果两端是不连通的,则将两端连通<br>

     @param queue 使用优先级队列存储边,每次获取权重最小的边
     */
    public static List<Edge> kruskal(int size, Queue<Edge> queue) {
        List<Edge> list = new ArrayList<>();
        DisjointSet set = new DisjointSet(size);//使用并查集进行连通操作
        while (list.size() < size - 1) {//n个顶点要连接n-1条边
            Edge poll = queue.poll();//获取权重最小的边
            int i = set.find(poll.start);//查找集合的老大
            int j = set.find(poll.end);
            if (i != j) {//在一个集合里的所有元素的老大都是相等的,如果ij不等,说明start和end在两个集合中
                //没有连通
                set.union(i, j);//将i,j集合设为连通
                list.add(poll);//加这条边
            }
        }
        return list;
    }
}
