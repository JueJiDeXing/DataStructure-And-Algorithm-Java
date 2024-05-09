package 数据结构.图.最短路径;

import java.util.*;

// TODO 写法优化

public class Dijkstra单源最短路径 {
    static class Edge1 implements Comparable<Edge1> {
        public Vertex1 linked;//终点
        public int to;//终点距离
        public int weight;//边权

        @Override
        public int compareTo(Edge1 o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static class Vertex1 {
        String name;//id
        List<Edge1> edge1s;//与顶点连接的边
        boolean visited = false;//是否被访问过
        int distance = Integer.MAX_VALUE;//和起点的距离
        Vertex1 prev = null;//上级(最短路径)

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex1 vertex1 = (Vertex1) o;
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

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 1, 0, 2, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] ints = dijkstra3(graph, 0);
        int[] distance = ints[0];
        int[] prev = ints[1];
        System.out.println(Arrays.toString(distance));
        System.out.println(Arrays.toString(prev));
        BellManFord单源最短路径.printPath(prev, 0, 2);
    }

    /**
     <h1>Dijkstra 单源最短路径算法</h1>
     // 使用自定义结构(Vertex,Edge)表示
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     缺陷:不能处理负权重

     @param graph  图,邻接表
     @param source 起点
     */
    public static void dijkstra1(List<Vertex1> graph, Vertex1 source) {
        ArrayList<Vertex1> list = new ArrayList<>(graph);//未访问顶点
        source.distance = 0;//起点距离设为0
        while (!list.isEmpty()) {
            Vertex1 current = chooseMinDistanceVertex1(list);//每次选择最小临时距离的未访问点作为当前顶点
            updateNeighboursDistance1(current);//遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)
            list.remove(current);//当前顶点处理完所有邻居后当前顶点设为已访问
            current.visited = true;
        }
        for (Vertex1 vertex1 : graph) {
            System.out.println(vertex1.name + " " + vertex1.distance + " " + (vertex1.prev != null ? vertex1.prev.name : "null"));
        }
    }

    /**
     更新邻居距离

     @param current 当前节点
     */
    private static void updateNeighboursDistance1(Vertex1 current) {
        for (Edge1 edge1 : current.edge1s) {
            Vertex1 n = edge1.linked;
            if (!n.visited) { // list.contains(n)
                int newDistance = current.distance + edge1.weight;
                if (newDistance < n.distance) {
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                }
            }
        }
    }

    /**
     寻找最小distance节点

     @param list 未访问节点
     @return 未访问节点中最小distance的节点
     */
    private static Vertex1 chooseMinDistanceVertex1(ArrayList<Vertex1> list) {
        Vertex1 min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < min.distance) {
                min = list.get(i);
            }
        }
        return min;
    }

    /**
     <h1>优化:优先级队列</h1>
     使用了优先级队列进行优化,当"每次选择最小临时距离的未访问点作为当前顶点"时,可以从优先级队列中直接抛出

     @param graph  图
     @param source 起点
     */
    public static void dijkstra2(List<Vertex1> graph, Vertex1 source) {
        PriorityQueue<Vertex1> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));//未访问顶点
        source.distance = 0;
        for (Vertex1 vertex1 : graph) queue.offer(vertex1);

        while (!queue.isEmpty()) {
            Vertex1 current = queue.poll();
            updateNeighboursDistance2(current, queue);
            current.visited = true;
        }
        for (Vertex1 vertex1 : graph) {
            System.out.println(vertex1.name + " " + vertex1.distance + " " + (vertex1.prev != null ? vertex1.prev.name : "null"));
        }
    }

    /**
     更新邻居距离
     */
    private static void updateNeighboursDistance2(Vertex1 current, PriorityQueue<Vertex1> queue) {
        for (Edge1 edge1 : current.edge1s) {
            Vertex1 n = edge1.linked;
            if (!n.visited) { // list.contains(n)
                int newDistance = current.distance + edge1.weight;
                if (newDistance < n.distance) {//更新最短长度
                    n.distance = newDistance;
                    n.prev = current;//记录最短路径时的上级
                    queue.remove(n);//需要重新加入队列,保持队列功能
                    queue.offer(n);
                }
            }
        }
    }

    /**
     <h1>Dijkstra 单源最短路径算法</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     缺陷:不能处理负权重

     @param graph  图,邻接矩阵
     @param source 起点
     */
    public static int[][] dijkstra3(int[][] graph, int source) {
        int n = graph.length;//n个顶点

        boolean[] isVisited = new boolean[n];//顶点是否访问
        int numOfVisit = 0;

        int[] distance = new int[n];//最短路径
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        int[] prev = new int[n];//路径上级
        Arrays.fill(prev, -1);

        while (numOfVisit < n) {
            int current = chooseMinDistanceVertex3(distance, isVisited);//每次选择最小临时距离的未访问点作为当前顶点
            updateNeighboursDistance3(graph, distance, isVisited, prev, current);//遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)
            isVisited[current] = true;//当前顶点处理完所有邻居后当前顶点设为已访问
            numOfVisit++;
        }
        return new int[][]{distance, prev};
    }

    private static int chooseMinDistanceVertex3(int[] distance, boolean[] isVisited) {
        int minIndex = 0;
        for (int i = 0; i < distance.length; i++) {
            if (isVisited[i]) continue;
            if (distance[i] < distance[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void updateNeighboursDistance3(int[][] graph, int[] distance, boolean[] isVisited, int[] prev, int v) {
        int[] edges = graph[v];
        for (int i = 0; i < graph.length; i++) {//遍历顶点v的所有邻居
            if (edges[i] == 0 || isVisited[i]) continue; // edges[i]==0表示i不是v的邻居
            //更新最短路径
            int newDistance = distance[v] + edges[i];
            if (newDistance < distance[i]) {
                distance[i] = newDistance;
                prev[i] = v;//记录最短路径时的上级
            }
        }
    }

    /**
     <h1>Dijkstra 单源最短路径算法</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     缺陷:不能处理负权重

     @param graph  图,邻接表
     @param source 起点
     */
    public static int[][] dijkstra4(List<Integer>[] graph, int source) {
        int n = graph.length;//n个顶点
        boolean[] isVisited = new boolean[n];//顶点是否访问
        int numOfVisit = 0;
        int[] distance = new int[n];//最短路径
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        int[] prev = new int[n];//路径上级
        Arrays.fill(prev, -1);

        while (numOfVisit < n) {
            int current = chooseMinDistanceVertex4(distance, isVisited);//每次选择最小临时距离的未访问点作为当前顶点
            updateNeighboursDistance4(graph, distance, isVisited, prev, current);//遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)
            //当前顶点处理完所有邻居后当前顶点设为已访问
            isVisited[current] = true;
            numOfVisit++;
        }
        return new int[][]{distance, prev};
    }

    private static int chooseMinDistanceVertex4(int[] distance, boolean[] isVisited) {
        int minIndex = 0;
        for (int i = 0; i < distance.length; i++) {
            if (isVisited[i]) continue;
            if (distance[i] < distance[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }


    private static void updateNeighboursDistance4(List<Integer>[] graph, int[] distance, boolean[] isVisited, int[] prev, int v) {
        int n = graph.length;
        List<Integer> edges = graph[v];
        for (int i = 0; i < n; i++) {//遍历顶点v的所有邻居
            if (edges.get(i) == 0 || isVisited[i]) continue; // list.contains(n)
            //更新最短路径
            int newDistance = distance[v] + edges.get(i);
            if (newDistance < distance[i]) {
                distance[i] = newDistance;
                prev[i] = v;//记录最短路径时的上级
            }
        }
    }
}
