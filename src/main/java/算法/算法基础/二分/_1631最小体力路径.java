package 算法.算法基础.二分;

import 数据结构实现.并查集.普通并查集.DisjointSet;

import java.util.*;

/**
 难度:中等
 */
public class _1631最小体力路径 {

    /*
    你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，
    其中 heights[row][col] 表示格子 (row, col) 的高度。

    一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
    你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。

    一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
    请你返回从左上角走到右下角的最小 体力消耗值 。
     */

    /**
     <h1>二分+广度搜索</h1>
     1 <= heights[i][j] <= 10^6<br>
     二分枚举体力x,判定在x体力下,能否从左上角走到右下角<br>
     判断体力x能否走到右下角使用bfs,
     从(0,0)开始,如果下一位置的高度差小于x,则能走,并标记visit,
     最后无路可走后检查右下角是否visit即可
     */
    public int minimumEffortPath(int[][] heights) {
        int left = 0, right = 1000000;//heights[i][j] <= 10^6
        int m = heights.length, n = heights[0].length;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (can(heights, mid, m, n)) {
                ans = mid;//leftMost,ans记录候选位置
                right = mid - 1;//继续向左查找
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     判断在体力h下能否从左上角走到右下角
     */
    private boolean can(int[][] heights, int h, int m, int n) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});//从(0,0)开始
        boolean[][] isVisited = new boolean[m][n];
        isVisited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            for (int i = 0; i < 4; ++i) {//查看相邻四格
                int nx = x + directions[i][0];
                int ny = y + directions[i][1];
                if (!isValidIndex(heights, nx, ny) || isVisited[nx][ny]) {//走过不重复走
                    continue;
                }
                if (Math.abs(heights[x][y] - heights[nx][ny]) <= h) {
                    //体力消耗值不超过h
                    if (nx == m - 1 && ny == n - 1) return true;//是否为走到右下角
                    isVisited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return false;//右下角没走到
    }

    private static boolean isValidIndex(int[][] heights, int nextX, int nextY) {
        return nextX >= 0 && nextX < heights.length && nextY >= 0 && nextY < heights[0].length;
    }


    /**
     <h1>Kruskal最小生成树</h1>
     将矩阵视为图,每个元素与相邻元素构成一条无向边,高度差为边的边权<br>
     每次选取最小边权的边加入并查集中<br>
     当加入一条边后,左上角与右下角连通,则当前选取边权为最小体力消耗值
     */
    public int minimumEffortPath2(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        //建立边权队列
        Queue<int[]> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o[2])));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pos = i * n + j;
                if (j != n - 1) {
                    int pos2 = i * n + j + 1;
                    int[] edge = new int[]{pos, pos2, Math.abs(heights[i][j] - heights[i][j + 1])};
                    queue.offer(edge);
                }
                if (i != m - 1) {
                    int pos2 = (i + 1) * n + j;
                    int[] edge = new int[]{pos, pos2, Math.abs(heights[i][j] - heights[i + 1][j])};
                    queue.offer(edge);
                }
            }
        }
        //选取边连通
        int size = m * n;
        DisjointSet set = new DisjointSet(size);
        int[] edge = new int[3];
        while (!set.isConnect(0, size - 1)) {
            edge = queue.poll();
            set.union(edge[0], edge[1]);
        }
        return edge[2];
    }

    /**
     <h1>Dijkstra最短路径</h1>
     1.将所有顶点标记为未访问<br>
     2.设置临时距离:起点设为0,其余设为无穷大<br>
     3.每次选择最小临时距离的未访问点作为当前顶点<br>
     4.遍历当前顶点邻居,更新邻居的距离值 min(邻居距离,当前顶点距离+边权)
     // 体力消耗:修改为 邻居距离=min(邻居距离,max(当前顶点距离,高度差))<br>
     5.当前顶点处理完所有邻居后当前顶点设为已访问<br>
     */
    public int minimumEffortPath3(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));//[x,y,distance]
        pq.offer(new int[]{0, 0, 0});
        //设置临时距离
        int[] distance = new int[m * n];//从(0,0)到(i,j)的最小体力  二维 <--> 一维
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;//起点设为0
        boolean[] isVisited = new boolean[m * n];
        //
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();//选择临时距离最小的未访问点
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (isVisited[id]) continue;
            if (x == m - 1 && y == n - 1) return distance[m * n - 1];//选择了右下角,此后右下角的距离值不会再修改,返回最小体力
            isVisited[id] = true;
            //搜索四个方向
            for (int i = 0; i < 4; ++i) {
                int nx = x + directions[i][0], ny = y + directions[i][1];
                if (!isValidIndex(heights, nx, ny)) continue;
                //更新邻居的距离值
                int h = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));//h:(x,y)的初始体力为d,从(x,y)走到相邻格(nx,ny)的最小体力
                if (h < distance[nx * n + ny]) {//花费体力更小,更新邻居距离
                    distance[nx * n + ny] = h;
                    pq.offer(new int[]{nx, ny, distance[nx * n + ny]});
                }
            }
        }
        return -1;//never
    }
}
