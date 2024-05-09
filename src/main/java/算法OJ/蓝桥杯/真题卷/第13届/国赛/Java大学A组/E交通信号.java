package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.util.Scanner;
import java.util.*;

/**
 测试通过:8/10  2个TL
 */
public class E交通信号 {
    /*
    n个节点,m条边
    每条边有红绿黄三种状态,初始都为绿灯,且状态按 绿,黄,红,黄,绿,黄,红...切换
    绿灯正向通行,红灯反向通行,黄灯不允许通行
    通过一条边的时间等于黄灯耗时,(在某条路上行进时不受灯的状态影响)
    //注意:红灯是反向通行而非禁止通行
    输入:
    第一行 n:n个节点; m:m条边; s,t:从节点s到节点t
    第k行  u,v:从节点u到节点v; g,r,d:绿,红,黄的持续时间
    输出:节点s到节点t的最短时间
     */
    public static void main(String[] args) {
        main_enter();
    }

/**
 测试通过:8/10  2个TL
 */
private static void main_enter() {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
    //建图
    List<Vertex> graph = new ArrayList<>();
    for (int i = 0; i <= n; i++) {
        graph.add(new Vertex(i));
    }
    for (int i = 0; i < m; i++) {//m条边
        int u = sc.nextInt(), v = sc.nextInt(),
                g = sc.nextInt(), r = sc.nextInt(), d = sc.nextInt();
        graph.get(u).edgeList.add(new Edge(v, g, r, d, true));
        graph.get(v).edgeList.add(new Edge(u, g, r, d, false));
    }
    //初始化
    Vertex sVertex = graph.get(s), tVertex = graph.get(t);
    sVertex.dist = 0;//起点距离
    //dijkstra
    PriorityQueue<Vertex> q = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));//存储[节点,时间]
    for (Vertex vertex : graph) q.offer(vertex);
    while (!q.isEmpty()) {//队列不为空
        Vertex now = q.poll();//取最近距离的未访问节点
        if (now == tVertex) break;
        now.isVisit = true;//设为已访问
        update(now, graph, q);//更新邻居距离
    }
    System.out.println(tVertex.dist);
}

private static void update(Vertex now, List<Vertex> graph, PriorityQueue<Vertex> q) {
    long time = now.dist;//当前时间
    for (Edge edge : now.edgeList) {//更新邻居距离
        Vertex to = graph.get(edge.to);
        if (to.isVisit) continue;//已访问跳过
        long tt = time % (edge.g + edge.r + edge.d * 2L);
        long waitCurrLED;
        long newDist = time + edge.d;
        if (tt < edge.g) {//绿灯
            waitCurrLED = edge.g - tt;
            if (!edge.flag) {//等红灯
                newDist += waitCurrLED;
            }
        } else if (tt < edge.g + edge.d) {//黄
            waitCurrLED = edge.g + edge.d - tt;
            if (edge.flag) {//等绿灯
                newDist += waitCurrLED + edge.r + edge.d;
            } else {//等红灯
                newDist += waitCurrLED;
            }
        } else if (tt < edge.g + edge.d + edge.r) {//红
            waitCurrLED = edge.g + edge.d + edge.r - tt;
            if (edge.flag) {//等绿灯
                newDist += waitCurrLED + edge.d;
            }
        } else {//黄
            waitCurrLED = edge.g + 2L * edge.d + edge.r - tt;
            if (edge.flag) {//等绿灯
                newDist += waitCurrLED;
            } else {//等红灯
                newDist += waitCurrLED + edge.g + edge.d;
            }
        }
        to.dist = Math.min(to.dist, newDist);
        q.remove(to);
        q.offer(to);
    }
}

static class Edge {
    int to, g, r, d;
    boolean flag;

    public Edge(int to, int g, int r, int d, boolean flag) {
        this.to = to;
        this.g = g;
        this.r = r;
        this.d = d;
        this.flag = flag;
    }
}

static class Vertex {
    int id;
    boolean isVisit = false;
    long dist = Long.MAX_VALUE;
    List<Edge> edgeList = new ArrayList<>();

    public Vertex(int id) {
        this.id = id;
    }

}

    /**
     测试通过:3/10  3个TL 4个WA
     */
    private static void main_enter2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), s = sc.nextInt(), t = sc.nextInt();
        //建图
        List<int[]>[] graph = new ArrayList[n + 1];//graph[u]=[v,g,r,d,flag],开n+1是因为节点编号从1开始
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (int i = 0; i < m; i++) {//m条边
            int u = sc.nextInt(), v = sc.nextInt(),
                    g = sc.nextInt(), r = sc.nextInt(), d = sc.nextInt();
            graph[u].add(new int[]{v, g, r, d, 1});
            graph[v].add(new int[]{u, g, r, d, -1});
        }
        //初始化
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);
        distance[s] = 0;
        boolean[] isVisited = new boolean[n + 1];
        int numOfVisit = 0;
        //dijkstra
        while (numOfVisit < n) {//节点未访问完
            int current = chooseMinDistanceVertex(distance, isVisited);//每次选择最小临时距离的未访问点作为当前顶点
            updateNeighboursDistance(graph, distance, isVisited, current);//遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)
            //当前顶点处理完所有邻居后当前顶点设为已访问
            isVisited[current] = true;
            if (isVisited[t]) break;
            numOfVisit++;
        }
        System.out.println(distance[t]);
    }

    private static void updateNeighboursDistance(List<int[]>[] graph, long[] distance, boolean[] isVisited, int current) {
        List<int[]> edges = graph[current];
        long time = distance[current];
        for (int[] edge : edges) {
            int to = edge[0], g = edge[1], r = edge[2], d = edge[3];
            boolean flag = edge[4] == 1;//1表示current->to为绿灯通行,-1则为红灯通行
            if (isVisited[to]) continue;//已访问跳过
            long tt = time % (g + r + d * 2L);
            long waitCurrLED;
            long newDist = time + d;
            if (tt < g) {//绿灯
                waitCurrLED = g - tt;
                if (!flag) {//等红灯
                    newDist += waitCurrLED;
                }
            } else if (tt < g + d) {//黄
                waitCurrLED = g + d - tt;
                if (flag) {//等绿灯
                    newDist += waitCurrLED + r + d;
                } else {//等红灯
                    newDist += waitCurrLED;
                }
            } else if (tt < g + d + r) {//红
                waitCurrLED = g + d + r - tt;
                if (flag) {//等绿灯
                    newDist += waitCurrLED + d;
                }
            } else {//黄
                waitCurrLED = g + 2L * d + r - tt;
                if (flag) {//等绿灯
                    newDist += waitCurrLED;
                } else {//等红灯
                    newDist += waitCurrLED + g + d;
                }
            }
            distance[to] = Math.min(distance[to], newDist);
        }
    }

    private static int chooseMinDistanceVertex(long[] distance, boolean[] isVisited) {
        int minIdx = 1;
        for (int i = 2; i < distance.length; i++) {
            if (!isVisited[i] && distance[i] < distance[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx;
    }

}
