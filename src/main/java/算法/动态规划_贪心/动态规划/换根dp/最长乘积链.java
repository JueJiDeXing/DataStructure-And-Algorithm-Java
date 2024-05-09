package 算法.动态规划_贪心.动态规划.换根dp;

import java.util.*;

public class 最长乘积链 {
    /*
    任意选择一个节点,以该节点为起点,选择两条无重复边的路径,值为两条路径的长度之积,如果选择的点只有一条路径,则乘积为0
     */
}

/**
 树的直径做法
 */
class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // 建图
        graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }
        //寻找树的直径
        parent = new Path[n + 1];// 路径节点上级节点的id,以及他们的边权
        Arrays.setAll(parent, k -> new Path(0, 0));
        Path x = find(1);//以任意节点寻找最远的节点x
        Path y = find(x.node);//以x节点寻找最远的节点y,则xy就是直径
        // 枚举直径上的节点作为分隔
        long total = y.length, sum = 0;
        long max = 0;// 记录最大乘积
        int end = y.node, start = x.node;// x->y, 从y开始跳转上级节点,枚举分隔点
        while (end != start) {
            sum += parent[end].length;// sum第一段路径长度
            max = Math.max(max, sum * (total - sum));//total - sum第二段路径长度
            end = parent[end].node;//跳转到上级节点
        }
        System.out.println(max);
    }

    static int n;
    static List<int[]>[] graph;
    static Path[] parent;

    static Path find(int u) {// bfs求距离u最远的节点
        Queue<Path> queue = new PriorityQueue<>(((o1, o2) -> o2.length > o1.length ? 1 : -1));//按距离从大到小排序
        queue.offer(new Path(u, 0));
        boolean[] isVisited = new boolean[n + 1];
        isVisited[u] = true;
        Path ans = new Path(0, 0);

        while (!queue.isEmpty()) {
            Path poll = queue.poll();
            int cur = poll.node;
            long curW = poll.length;
            if (curW > ans.length) {//求最远节点
                ans.node = cur;
                ans.length = curW;
            }
            for (int[] next : graph[cur]) {
                if (isVisited[next[0]]) continue;
                queue.offer(new Path(next[0], curW + next[1]));
                isVisited[next[0]] = true;
                parent[next[0]].node = cur;// 记录一下上级节点及他们的边权
                parent[next[0]].length = next[1];
            }
        }
        return ans;
    }

    static class Path {
        int node;//节点id
        long length;//边权 或 累加距离

        public Path(int node, long length) {
            this.node = node;
            this.length = length;
        }
    }
}

/**
 换根dp做法
 */
class Solution2 {
    static final int N = (int) 1e5 + 5;
    static List<Edge>[] graph = new ArrayList[N];
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        // 建图
        graph = new List[n + 1];
        Arrays.setAll(graph, k -> new ArrayList<>());
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }
        dfs(1, -1);
        reroot(1, -1);
        long ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(dp[i][0] * Math.max(dp[i][1], up[i]), ans);
        System.out.println(ans);
    }

    static long[][] dp = new long[N][2];//dp[i][0,1]:节点i向下的最大长度和次大长度
    static int[][] parent = new int[N][2];//parent[i][0,1]:节点i向下的最大长度父节点 和 次大长度父节点

    private static void dfs(int x, int fa) {
        for (Edge e : graph[x]) {
            int u = e.to, val = e.weight;
            if (u == fa) continue;
            dfs(u, x);
            long distance = dp[u][0] + val;
            if (distance >= dp[x][0]) {// u比最大长度长
                dp[x][1] = dp[x][0];// 最大变次大
                dp[x][0] = distance;//次大变u
                parent[x][1] = parent[x][0];//更新父节点
                parent[x][0] = u;
            } else if (distance > dp[x][1]) {//u比次大长度长
                dp[x][1] = distance;//次大边u
                parent[x][1] = u;//更新父节点
            }
        }
    }

    static long[] up = new long[N];//up[i]:节点i向上的最大长度
    private static void reroot(int x, int fa) {
        for (Edge v : graph[x]) {
            int u = v.to, val = v.weight;
            if (u == fa) continue;
            // i的向上长度 = x的向上长度
            if (u == parent[x][0]) up[u] = Math.max(up[x], dp[x][1]);// u是最大长度的父节点
            else up[u] = Math.max(up[x], dp[x][0]);
            up[u] += val;
            reroot(u, x);
        }
    }


    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}


