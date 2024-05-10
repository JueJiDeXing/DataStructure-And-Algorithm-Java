package 算法.图论.最短路径;

import java.util.*;
import java.util.stream.Collectors;

class Edge3 {
    public int from;//起点距离
    public int to;//终点距离
    public Vertex3 linked;
    List<Vertex3> vertices;
    int start;
    public int weight;//边权
    int end;


    @Override
    public String toString() {
        return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
    }


}

class Vertex3 {
    public List<Edge3> edge3s;//与顶点连接的边
    String name;
    int distance = Integer.MAX_VALUE;//和起点的距离

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex3 vertex1 = (Vertex3) o;
        return Objects.equals(name, vertex1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + "(" + distance + ")";
    }
}

public class FloydWarshall多源最短路径 {
    public static void main(String[] args) {
        FloydWarshall多源最短路径 test = new FloydWarshall多源最短路径();
        int[][] graph = new int[][]{
                {0, 0, 0, 0},
                {0, 0, -1, 2},
                {0, -1, 0, -1},
                {0, 2, -1, 0},
        };
        int[][][] ints = test.FloydWarshall2(graph);
        int[][] distance = ints[0];
        int[][] prev = ints[1];
        System.out.println(Arrays.deepToString(distance));
        System.out.println(Arrays.deepToString(prev));
        BellManFord单源最短路径.printPath(prev[0], 0, 2);

    }

    /**
     <h1>Floyd-Warshall 多源最短路径算法</h1>
     1.创建邻接矩阵<br>
     2.初始化,主对角线标0,有连接标上边权,无连接标为无穷<br>
     3.进行n轮循环,循环内Vj借助Vi到达其他顶点<br>
     枚举借助点-枚举起点和终点

     @param graph 图,邻接表
     */
    public static void FloydWarshall(List<Vertex3> graph) {
        int size = graph.size();
        int[][] distance = new int[size][size];//邻接矩阵
        Vertex3[][] prev = new Vertex3[size][size];//记录最短路径的上级顶点
        //初始化
        for (int i = 0; i < size; i++) {
            Vertex3 v = graph.get(i);
            Map<Vertex3, Integer> map = v.edge3s.stream().collect(Collectors.toMap(e -> e.linked, e -> e.weight));
            for (int j = 0; j < size; j++) {
                Vertex3 u = graph.get(j);
                if (v == u) {
                    distance[i][j] = 0;//主对角线标0,有连接标上边权,无连接标为无穷
                } else {
                    distance[i][j] = map.getOrDefault(u, Integer.MAX_VALUE);
                    prev[i][j] = map.get(u) != null ? v : null;//记录路径上级节点
                }
            }
        }
        print(distance);
        /*借路示例:v2借v1到其他顶点
            v2->v1
          dist[1][0]  + dist[0][0] v1->v1
          dist[1][0]  + dist[0][1] v1->v2
          dist[1][0]  + dist[0][2] v1->v3
          dist[1][0]  + dist[0][3] v1->v4
         */
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                if (distance[i][k] == Integer.MAX_VALUE) continue;
                for (int j = 0; j < size; j++) {
                    if (distance[k][j] == Integer.MAX_VALUE) continue;
                    int newDistance = distance[i][k] + distance[k][j];
                    if (newDistance < distance[i][j]) {//如果借k到j比i直接到j更短则更新
                        distance[i][j] = newDistance;
                        prev[i][j] = prev[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < size; i++) {
            //对角线小于0,说明有负环 (对角线小于0 -> 节点借其他顶点到达自身路径比自身到达自身更短)
            if (distance[i][i] < 0) {
                throw new RuntimeException("存在负环,无解");
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                printPath(prev, graph, i, j);
            }
        }

    }

    /**
     打印带无穷大的二维数组
     */
    public static void print(int[][] arr) {
        for (int[] row : arr) {
            System.out.println(Arrays.stream(row).boxed()
                    .map(x -> x == Integer.MAX_VALUE ? "∞" : String.valueOf(x))
                    .map(x -> String.format("%2s", x))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    public static void print(Vertex3[][] prev) {
        System.out.println("-------------------");
        for (Vertex3[] row : prev) {
            System.out.println(Arrays.stream(row)
                    .map(x -> x == null ? "null" : x.name)
                    .map(x -> String.format("%5s", x))
                    .collect(Collectors.joining(",", "[", "]")));
        }
    }

    /**
     打印最短路径

     @param prev  最短路径表
     @param graph 图
     @param i     起点
     @param j     终点
     */
    public static void printPath(Vertex3[][] prev, List<Vertex3> graph, int i, int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.print("[" + graph.get(i).name + "," + graph.get(j).name + "]");
        stack.push(graph.get(j).name);
        while (i != j) {
            Vertex3 p = prev[i][j];
            if (p == null) {
                stack.push("null");
                break;
            }
            stack.push(p.name);
            j = graph.indexOf(p);
        }
        System.out.println(stack);
    }

    /**
     <h1>Floyd-Warshall 多源最短路径算法</h1>
     简化版,直接输入邻接矩阵<br>
     1.初始化,主对角线标0,有连接标上边权,无连接标为无穷<br>
     2.进行n轮循环,循环内Vj借助Vi到达其他顶点<br>
     枚举借助点-枚举起点和终点

     @param graph 图
     */
    public int[][][] FloydWarshall2(int[][] graph) {
        int n = graph.length;
        int[][] distance = graph.clone();
        int[][] prev = new int[n][n];//记录最短路径的上级
        //初始化
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (distance[i][j] == 0 && i != j) {
                    distance[i][j] = Integer.MAX_VALUE;
                }
                prev[i][j] = distance[i][j] == 0 ? -1 : i;
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (distance[i][k] == Integer.MAX_VALUE) continue;
                for (int j = 0; j < n; j++) {
                    if (distance[k][j] == Integer.MAX_VALUE) continue;
                    int newDistance = distance[i][k] + distance[k][j];
                    if (newDistance < distance[i][j]) {//如果借k到j比i直接到j更短则更新
                        distance[i][j] = newDistance;
                        prev[i][j] = prev[k][j];
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            //对角线小于0,说明有负环 (对角线小于0 -> 节点借其他顶点到达自身路径比自身到达自身更短)
            if (distance[i][i] < 0) {
                throw new RuntimeException("存在负环,无解");
            }
        }
        return new int[][][]{distance, prev};
    }

    /**
     <h1>Floyd的单源递归写法</h1>
     假设要从i走到j,那么取i~j之间的最大编号的点k
     此时有两种选择
     1. k不在最短路径上,此时问题转变为i到j上最短路径编号<=k-1
     2. k在最短路径上,此时问题分解为两个 i到k上最短路径变号<=k-1 和 k到j上最短路径编号<=k-1

     @param i 起点
     @param j 终点
     @param k 终点
     @param w 图(邻接矩阵)
     @return 返回i到j的最短路径长度
     */
    public int dfs(int i, int j, int k, int[][] w) {//@cache
        if (k < 0) return w[i][j];
        return Math.min(dfs(k - 1, i, j, w), dfs(k - 1, i, k, w) + dfs(k - 1, k, j, w));
    }

}



