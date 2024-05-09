package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class E迷宫 {
    /*
    n*n的迷宫,左上角(1,1),右下角(n,n)为终点,起点对每个位置概率相等(终点也是)
    另外有m个双向传送门,传送门连接两个位置(x1,y1)<->(x2,y2)
    可以上下左右移动,也可以通过传送门移动,这些操作的步数都为1
    求起点到终点的最短路径期望

    输入:
    第一行 n:迷宫大小n*n; m:传送门个数
    第k行 x1,y1,x2,y2
    答案保留两位小数
    n,m<=2000
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static final int M = 1000000;

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        sc.nextLine();
        //节点->传送门->其他节点(们)
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        map.clear();
        for (int i = 0; i < m; i++) {
            int x1 = sc.nextInt() - 1, y1 = sc.nextInt() - 1, x2 = sc.nextInt() - 1, y2 = sc.nextInt() - 1;
            map.computeIfAbsent(x1 * M + y1, k -> new ArrayList<>()).add(x2 * M + y2);//压缩存储
            map.computeIfAbsent(x2 * M + y2, k -> new ArrayList<>()).add(x1 * M + y1);
        }
        sc.close();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{n - 1, n - 1});
        boolean[][] isVisited = new boolean[n][n];
        int[][] distance = new int[n][n];
        for (int[] dis : distance) Arrays.fill(dis, Integer.MAX_VALUE);
        distance[n - 1][n - 1] = 0;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];
            if (isVisited[x][y]) continue;
            isVisited[x][y] = true;
            for (int[] d : dir) {
                int nextX = x + d[0], nextY = y + d[1];
                if (!isValid(nextX, nextY, n) || isVisited[nextX][nextY]) continue;
                distance[nextX][nextY] = Math.min(distance[nextX][nextY], distance[x][y] + 1);
                queue.offer(new int[]{nextX, nextY});
            }
            if (map.containsKey(x * M + y)) {
                for (int next : map.get(x * M + y)) {
                    int nextX = next / M, nextY = next % M;
                    if (!isValid(nextX, nextY, n) || isVisited[nextX][nextY]) continue;
                    distance[nextX][nextY] = Math.min(distance[nextX][nextY], distance[x][y] + 1);
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
        for (int[] d : distance) System.out.println(Arrays.toString(d));
        double ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += distance[i][j];
            }
        }
        System.out.printf("%.2f", ans / n / n);
    }


    private static final int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    private static boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }


}
