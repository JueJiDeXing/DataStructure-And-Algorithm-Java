package 数据结构实现.图.建图的三种方法;

public class 邻接矩阵 {
    int[][] graph1;
    int[][] graph2;

    public void creatGraph(int n, int[][] edges) {
        graph1 = new int[n][n];//如果边号从1开始,大小为n+1
        graph2 = new int[n][n];
        for (int[] edge : edges) {
            graph1[edge[0]][edge[1]] = edge[2];//有向,边权edge[2]

            graph2[edge[0]][edge[1]] = 1;//无向
            graph2[edge[1]][edge[0]] = 1;

        }
    }

    public void travel(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph1[i][j]+" ");
            }
            System.out.println();
        }
    }
}
