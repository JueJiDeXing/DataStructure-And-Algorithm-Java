package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 已AC
 */
public class B灌水 {
    /*
    0/1矩阵 (30行40列)
    将(0,0)位置的0变为2, 相邻的0也会变为2,求最终2的个数
     */
    // bfs求与(0,0)的连通的0的个数

    static int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = new int[30][40];
        for (int i = 0; i < 30; i++) {
            char[] s = sc.next().toCharArray();
            for (int j = 0; j < 40; j++) {
                matrix[i][j] = s[j] - '0';
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] isVisit = new boolean[30][40];
        isVisit[0][0] = true;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            int x = p[0], y = p[1];
            for (int[] d : direction) {
                int nx = x + d[0], ny = y + d[1];
                if (!isValid(nx, ny) || isVisit[nx][ny] || matrix[nx][ny] != 0) continue;
                isVisit[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }
        int count = 0;
        for (boolean[] vis : isVisit) {
            for (boolean b : vis) {
                if (b) count++;
            }
        }
        System.out.println(count);
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < 30 && 0 <= y && y < 40;
    }


}

