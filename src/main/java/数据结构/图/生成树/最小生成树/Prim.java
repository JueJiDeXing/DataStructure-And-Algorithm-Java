package 数据结构.图.生成树.最小生成树;

import 数据结构.图.Edge;
import 数据结构.图.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prim {
    public static void main(String[] args) {
        Prim test = new Prim();
        /*
                   2                              2
              v1------ v3                     v1 ---- v3
            1/  6\  4/  2\                  1/          2\
           v0     v2     v4      -->       v0     v2     v4
            5\    4\   /2                          4\   /2
               ----- v5                               v5

         */
        int[][] graph2 = new int[][]{{0, 1, 0, 0, 0, 5}, {1, 0, 6, 2, 0, 0}, {0, 6, 0, 4, 0, 4}, {0, 2, 4, 0, 2, 0}, {0, 0, 0, 2, 0, 2}, {5, 0, 4, 0, 2, 0}};
        List<int[]> prev2 = test.prim2(graph2);
        for (int[] p : prev2) {
            System.out.print("(" + p[0] + "," + p[1] + ") ");
        }
    }

    /**
     <h1>Prim 最小生成树算法</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     与Dijkstra算法仅在第4步不同,距离不累加,而是表示距离上一个节点的距离
     */
    public void prim(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);//未访问顶点
        source.distance = 0;//起点临时距离设为0
        while (!list.isEmpty()) {
            Vertex current = chooseMinDistanceVertex(list);//选择最小的临时距离的未访问点
            updateNeighboursDistance(current);//更新它的邻居的距离
            list.remove(current);//遍历后移除节点,标记为已未访问
            current.visited = true;
        }
        for (Vertex vertex : graph) {
            System.out.println(vertex.name + " <- " + (vertex.prev != null ? vertex.prev.name : "null"));
        }
    }

    /**
     寻找最小distance节点

     @param list 未访问节点
     @return 未访问节点中最小distance的节点
     */
    private Vertex chooseMinDistanceVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < min.distance) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     更新邻居距离

     @param current 当前节点
     */
    private void updateNeighboursDistance(Vertex current) {
        for (Edge edge : current.edges) {
            Vertex n = edge.linked;
            if (!n.visited) { // 未访问节点
                int newDistance = edge.weight;
                if (newDistance < n.distance) {
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                }
            }
        }
    }


    /**
     <h1>Prim 最小生成树算法-邻接矩阵格式输入</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     与Dijkstra算法仅在第4步不同,距离不累加,而是表示距离上一个节点的距离

     @param graph 无向带权图,邻接矩阵
     */
    public List<int[]> prim2(int[][] graph) {
        int n = graph.length;
        boolean[] isVisited = new boolean[n];
        int numOfVisit = 0;
        List<int[]> path = new ArrayList<>();//记录路径,[a,b]表示选中边a-b
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;//起点临时距离设为0
        int prev = -1;//上次选择的节点
        while (numOfVisit < n) {
            int current = chooseMinDistanceVertex(distance, isVisited);//选择最小的临时距离的未访问点
            updateNeighboursDistance(graph, distance, isVisited, current);//更新它的邻居的距离
            if (numOfVisit != 0) {//第一个不记录,因为第一个是 起点-起点
                path.add(new int[]{prev, current});//记录路径
            }
            prev = current;
            isVisited[current] = true;//设为已访问
            numOfVisit++;
        }
        return path;
    }

    private int chooseMinDistanceVertex(int[] distance, boolean[] isVisited) {
        int n = distance.length;
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < n; i++) {
            if (distance[i] == 0 || isVisited[i]) continue;
            if (distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private void updateNeighboursDistance(int[][] graph, int[] distance, boolean[] isVisited, int v) {
        int n = graph.length;
        int[] edges = graph[v];
        for (int i = 0; i < n; i++) {//遍历顶点v的所有邻居
            if (edges[i] == 0 || isVisited[i]) continue; // list.contains(n)
            //更新最短路径
            if (edges[i] < distance[i]) {//距离不累加,直接表示为与上一个顶点的距离
                distance[i] = edges[i];
            }
        }
    }
}
