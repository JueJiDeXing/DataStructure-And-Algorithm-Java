package 算法.图论.最短路径.模版;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

class Edge2 implements Comparable<Edge2> {
    public int from;//起点距离
    public int to;//终点距离
    public Vertex2 linked;//终点

    public Edge2(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    List<Vertex2> vertices;
    int start;
    public int weight;//边权
    int end;


    @Override
    public String toString() {
        return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
    }

    @Override
    public int compareTo(Edge2 o) {
        return Integer.compare(this.weight, o.weight);
    }
}

class Vertex2 {
    String name;
    public List<Edge2> edge2s;//与顶点连接的边
    int distance = Integer.MAX_VALUE;//和起点的距离
    Vertex2 prev = null;//上级(最短路径)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex2 vertex1 = (Vertex2) o;
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

public class BellManFord单源最短路径 {
    public static void main(String[] args) {
        BellManFord单源最短路径 test = new BellManFord单源最短路径();
        int[][] graph = new int[][]{
                {0, 1, 0, 2, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int source = 0;
        int[][] ints = test.bellManFord2(graph, source);
        int[] minLength = ints[0];
        System.out.println(Arrays.toString(minLength));
        int[] path = ints[1];
        System.out.println(Arrays.toString(path));
        printPath(path, source, 3);
    }

    /**
     <h1>BellManFord 单源最短路径算法</h1>
     使用封装结构(Vertex和Edge)仅为演示<br>
     1.设置临时距离:起点设为0,其余设为无穷大<br>
     2.对每条边进行标号(随机)<br>
     3.每轮对边的两端进行比较,如果 起点端+边权< 终点端 ,则更新终点端 ; 共需要进行n-1轮,其中n为顶点个数

     @param graph  图,邻接表
     @param source 起点
     */
    public void bellManFord(List<Vertex2> graph, Vertex2 source) {
        source.distance = 0;
        int n = graph.size();
        for (int i = 0; i < n; i++) { //进行n-1轮更新,第n轮检测负环
            for (Vertex2 s : graph) {
                if (s.distance == Integer.MAX_VALUE) continue;
                for (Edge2 edge : s.edge2s) {
                    Vertex2 e = edge.linked;// s -- edge --> e
                    if (s.distance + edge.weight < e.distance) {
                        if (i == n - 1) {//如果第n轮还能找到更短的说明有负环
                            throw new RuntimeException("存在负环,无解");
                        }
                        e.distance = s.distance + edge.weight;
                        e.prev = s;
                    }
                }
            }
        }
        for (Vertex2 vertex : graph) {
            System.out.println(vertex.name + " " + vertex.distance + " " + (vertex.prev != null ? vertex.prev.name : "null"));
        }
    }

    /**
     <h1>邻接矩阵表示法</h1>
     1.设置临时距离:起点设为0,其余设为无穷大<br>
     2.对每条边进行标号(随机)<br>
     3.每轮对边的两端进行比较,如果 起点端+边权< 终点端 ,则更新终点端 ; 共需要进行n-1轮,其中n为顶点个数

     @param graph  邻接矩阵
     @param source 起点
     */
    public int[][] bellManFord2(int[][] graph, int source) {
        int n = graph.length;//n个顶点

        int[] minDistance = new int[n];//距离
        Arrays.fill(minDistance, Integer.MAX_VALUE);
        minDistance[source] = 0;//起点置0,其余点设置为无穷

        int[] prev = new int[n];//记录路径源
        Arrays.fill(prev, -1);

        for (int i = 0; i < n; i++) {//进行n-1轮循环更新最短路径,第n轮循环用于判断是否存在负环
            //枚举 起点-边-终点
            for (int start = 0; start < n; start++) {
                if (minDistance[start] == Integer.MAX_VALUE) continue;
                int[] edges = graph[start];
                for (int end = 0; end < n; end++) {
                    if (edges[end] == 0) continue;//邻接矩阵0表示两点不能连接
                    if (minDistance[start] + edges[end] < minDistance[end]) {
                        if (i == n - 1) {//如果第n轮还能找到更短的说明有负环
                            throw new RuntimeException("存在负环,无解");
                        }
                        minDistance[end] = minDistance[start] + edges[end];
                        prev[end] = start;
                    }
                }
            }
        }
        return new int[][]{minDistance, prev};
    }

    /**
     打印最短路径

     @param path bellManFord方法中输出的最短路径数组,存储了每个点的最短路径上级
     @param from 起点
     @param to   终点
     */
    public static void printPath(int[] path, int from, int to) {
        int i = to;
        int[] p = new int[path.length];
        int k = 0;
        while (i != from) {
            p[k++] = i;
            i = path[i];
            if (i == -1) {
                System.out.printf("(%d -> %d)没有可到达的路径", from, to);
                return;
            }
        }
        while (k >= 0) {
            System.out.print(p[k]);
            if (k != 0) System.out.print("->");
            k--;
        }
    }
}
