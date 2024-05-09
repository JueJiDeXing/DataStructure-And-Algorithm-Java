package 算法OJ.洛谷.DMOJ;

import java.io.*;
import java.util.*;

public class DWITE_CubeWorld {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int[][] heightMap = new int[21][21];
    static boolean[][] visit = new boolean[21][21];

    public static void main(String[] args) {
        for (int __ = 0; __ < 5; __++) {
            int n = Int(), m = Int();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    heightMap[i][j] = Int();
                    visit[i][j] = false;
                }
            }
            if (m <= 2 || n <= 2) {//都是最外层,接不了水
                System.out.println(0);
                return;
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));//[块坐标,块高度]按照高度排序
            //将最外层入队
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 || i == n - 1 || j == 0 || j == m - 1) {
                        pq.offer(new int[]{heightMap[i][j], i, j});
                        visit[i][j] = true;
                    }
                }
            }
            //每次选择高度最小的外层块,向内填充并统计接的雨水量
            int res = 0;
            int[] dirs = {-1, 0, 1, 0, -1};//方向
            while (!pq.isEmpty()) {
                int[] curr = pq.poll();
                int height = curr[0], x = curr[1], y = curr[2];
                for (int k = 0; k < 4; k++) {//四个方向
                    int nx = x + dirs[k], ny = y + dirs[k + 1];
                    if (!isValid(nx, ny, n, m) || visit[nx][ny]) continue;//越界或已填充
                    //填充
                    if (height > heightMap[nx][ny]) {//heightMap[nx][ny]填充至height
                        res += height - heightMap[nx][ny];
                    }
                    visit[nx][ny] = true;
                    pq.offer(new int[]{Math.max(heightMap[nx][ny], height), nx, ny});//(nx,ny)又作为最外层块
                }
            }
            System.out.println(res);
        }
    }

    /**
     短板效应:
     从最外层的最小值开始,向内更低的高度的块进行填充,填充量就是该块能接的雨水量
     之后,填充的块又作为外层
     */
    private static boolean isValid(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
