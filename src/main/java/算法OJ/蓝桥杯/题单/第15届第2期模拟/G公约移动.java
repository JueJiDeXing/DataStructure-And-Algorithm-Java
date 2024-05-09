package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

/**
 已AC
 */
public class G公约移动 {
    /*
    n行m列方格图,每格一个正整数
    如果gcd(a,b)>1,且a与b是邻居(上下左右),则ab连通
    求与(r,c)连通的方格数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int(), m = Int();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Int();
            }
        }
        //bfs
        int r = Int() - 1, c = Int() - 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c, -1});
        boolean[][] isVisit = new boolean[n][m];
        isVisit[r][c] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0], y = p[1];
            for (int[] d : direction) {
                int nx = x + d[0], ny = y + d[1];
                if (!isValid(nx, ny, n, m)) continue;
                if (isVisit[nx][ny]) continue;
                if (gcd(matrix[x][y], matrix[nx][ny]) > 1) {//gcd大于1才能移动
                    isVisit[nx][ny] = true;
                    count++;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        System.out.println(count);
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static boolean isValid(int x, int y, int n, int m) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
