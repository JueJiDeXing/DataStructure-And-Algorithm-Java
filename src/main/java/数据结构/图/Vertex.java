package 数据结构.图;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 顶点类
 */
@Data
public class Vertex {
    //以下属性自行选择添加
    public String name;
    public List<Edge> edges;//与顶点连接的边
    public boolean visited = false;//是否被访问过
    public int inDegree;//入度
    public int outDegree;//出度
    public int status;//0-未访问 1-访问中 2-已访问
    public int distance = Integer.MAX_VALUE;//和起点的距离
    public Vertex prev = null;//上级(最短路径)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + "(" + distance + ")";
    }

    public Vertex(String name) {
        this.name = name;
    }

    public Vertex(String name, List<Edge> edges) {
        this.name = name;
        this.edges = edges;
    }
}
