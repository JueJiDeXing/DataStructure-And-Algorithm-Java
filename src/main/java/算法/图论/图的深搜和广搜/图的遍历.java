package 算法.图论.图的深搜和广搜;

import 数据结构实现.图.Edge;
import 数据结构实现.图.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class 图的遍历 {
    public static void main(String[] args) {
        /*
          v2---- v4
         /     /   \
       v1----v3     v5
         \     \   /
           ---- v6
         */
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        v1.edges = Arrays.asList(new Edge(v2, 7), new Edge(v3, 9), new Edge(v6, 14));
        v2.edges = Arrays.asList(new Edge(v4, 15));
        v3.edges = Arrays.asList(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = Arrays.asList(new Edge(v5, 6));
        v5.edges = Arrays.asList();
        v6.edges = Arrays.asList(new Edge(v5, 9));
        List<Vertex> graph = new ArrayList<>();
        graph.add(v1);
        graph.add(v2);
        graph.add(v3);
        graph.add(v4);
        graph.add(v5);
        graph.add(v6);

    }

    /**
     深度优先

     @param vertex 起点
     */
    public void dfs(Vertex vertex) {
        vertex.visited = true;
        System.out.println(vertex.name);
        for (Edge edge : vertex.edges) {
            if (!edge.linked.visited) {
                edge.linked.visited = true;
                dfs(edge.linked);
            }
        }
    }

    /**
     深度优先--非递归版

     @param vertex 起点
     */
    public static void dfs2(Vertex vertex) {
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(vertex);
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop();
            pop.visited = true;
            System.out.println(pop.name);
            for (Edge edge : pop.edges) {
                if (!edge.linked.visited) {
                    stack.push(edge.linked);
                }
            }
        }
    }

    /**
     广度优先

     @param vertex 起点
     */
    public void bfs(Vertex vertex) {
        vertex.visited = true;
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.offer(vertex);
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            System.out.println(poll.name);
            for (Edge edge : poll.edges) {
                if (!edge.linked.visited) {
                    edge.linked.visited = true;
                    queue.offer(vertex);
                }
            }
        }
    }

    /**
     拓扑遍历<br>
     每次找入度为0的顶点,将其排序后删掉<br>
     如果出现环,环的部分无法遍历出来

     @param graph 图
     */
    public List<String> ToPoLogicalSort(List<Vertex> graph) {
        for (Vertex vertex : graph) {
            for (Edge edge : vertex.edges) {
                edge.linked.inDegree++;
            }
        }
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0) {
                queue.offer(vertex);
            }
        }
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            result.add(poll.name);
            for (Edge edge : poll.edges) {
                edge.linked.inDegree--;
                if (edge.linked.inDegree == 0) {
                    queue.offer(edge.linked);
                }
            }
        }
        if (result.size() != graph.size()) {
            throw new RuntimeException("图中存在环");
        }
        return result;
    }

    /**
     拓扑遍历--DFS<br>

     @param graph 图
     */
    public static void ToPoLogicalSort2(List<Vertex> graph) {
        LinkedList<String> stack = new LinkedList<>();
        for (Vertex vertex : graph) {
            dfs(vertex, stack);
        }
        System.out.println(stack);

    }

    /**
     深度优先

     @param vertex 起点
     */
    public static void dfs(Vertex vertex, LinkedList<String> stack) {
        if (vertex.status == 2) {//已访问
            return;
        }
        if (vertex.status == 1) {//访问中的节点再次进行被操作,说明有环
            throw new RuntimeException("图中存在环");
        }
        vertex.status = 1;
        for (Edge edge : vertex.edges) {
            dfs(edge.linked, stack);//操作连接的点
        }
        vertex.status = 2;
        stack.push(vertex.name);
    }
}




