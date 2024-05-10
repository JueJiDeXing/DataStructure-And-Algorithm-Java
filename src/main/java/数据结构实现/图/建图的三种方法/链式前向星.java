package 数据结构实现.图.建图的三种方法;

public class 链式前向星 {
    int[] head;
    int[] next;
    int[] to;
    int[] weight;
    int count = 1;

    public void creat(int n, int[][] edges) {
        head = new int[n + 1];//顶点对应的头边
        next = new int[edges.length + 1];//边的下一条边
        to = new int[edges.length + 1];//边的终点
        weight = new int[edges.length + 1];//边权
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);//有向
            /*
            addEdge(edge[0], edge[1], edge[2]);无向图,两方向都要加
            addEdge(edge[1], edge[0], edge[2]);
             */
        }
    }

    public void addEdge(int start, int end, int w) {
        next[count] = head[start];//第count条边的下一条边为start点的头边
        head[start] = count;//start点的头边变为count号边
        to[count] = end;
        weight[count] = w;
        count++;
    }

    public void travel(int n) {
        for (int i = 1; i < n; i++) {
            System.out.print(i + "邻居、边权:");
            for (int ei = head[i]; ei > 0; ei = next[ei]) {
                System.out.print("(" + to[ei] + "," + weight[ei] + ")");
            }
            System.out.println();
        }
    }
}
