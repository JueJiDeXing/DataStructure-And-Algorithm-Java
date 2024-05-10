package 数据结构实现.图;

import lombok.Data;

import java.util.List;

/**
 边
 */
@Data
public class Edge implements Comparable<Edge> {
    //以下属性自行选择添加
    public Vertex linked;//终点
    public int from;//起点距离
    public int to;//终点距离
    public List<Vertex> vertices;
    public int start;
    public int weight;//边权
    public int end;


    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }


    public Edge(List<Vertex> vertices, int start, int end, int weight) {
        this.weight = weight;
        this.vertices = vertices;
        this.start = start;
        this.end = end;
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }

    public Edge(Vertex linked) {
        this.linked = linked;
    }

    @Override
    public String toString() {
        return vertices.get(start).name + "<->" + vertices.get(end).name + "(" + weight + ")";
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }
}
