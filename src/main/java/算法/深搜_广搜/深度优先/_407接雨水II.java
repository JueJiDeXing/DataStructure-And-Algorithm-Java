package 算法.深搜_广搜.深度优先;

import java.util.*;

public class _407接雨水II {
    /*
    给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度
    请计算图中形状最多能接多少体积的雨水。
     */

    /**
     短板效应:
     从最外层的最小值开始,向内更低的高度的块进行填充,填充量就是该块能接的雨水量
     之后,填充的块又作为外层
     */
    public static int trapRainWater(int[][] heightMap) {
        int n = heightMap.length, m = heightMap[0].length;
        if (m <= 2 || n <= 2) {//都是最外层,接不了水
            return 0;
        }
        boolean[][] visit = new boolean[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));//[块坐标,块高度]按照高度排序
        //将最外层入队
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        //每次选择高度最小的外层块,向内填充并统计接的雨水量
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};//方向
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int pos = curr[0], height = curr[1];
            for (int k = 0; k < 4; k++) {//四个方向
                int nx = pos / n + dirs[k], ny = pos % n + dirs[k + 1];
                if (!isValid(nx, n, ny, m) || visit[nx][ny]) continue;//越界或已填充
                //填充
                if (height > heightMap[nx][ny]) {//heightMap[nx][ny]填充至height
                    res += height - heightMap[nx][ny];
                }
                visit[nx][ny] = true;
                pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], height)});//(nx,ny)又作为最外层块
            }
        }
        return res;
    }

    private static boolean isValid(int x, int n, int y, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
