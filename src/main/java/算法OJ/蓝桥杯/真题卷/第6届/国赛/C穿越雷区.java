package 算法OJ.蓝桥杯.真题卷.第6届.国赛;

import java.util.*;
/**
 已AC
 */
public class C穿越雷区 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] posA = new int[2], posB = new int[2];
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                char ch = s[j].charAt(0);
                if (ch == 'A') {
                    posA = new int[]{i, j};
                } else if (ch == 'B') {
                    posB = new int[]{i, j};
                } else {
                    map[i][j] = ch == '+' ? 1 : -1;
                }
            }
        }
        boolean[][] isVisited = new boolean[n][n];
        isVisited[posA[0]][posA[1]] = true;
        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(posA[0], posA[1]));
        while (!queue.isEmpty()) {
            Path p = queue.poll();
            if (p.x == posB[0] && p.y == posB[1]) {
                System.out.println(p.step);
                return;
            }
            for (int[] d : direction) {
                int nx = p.x + d[0], ny = p.y + d[1];
                if (!isValid(nx, ny, n)) continue;
                if (isVisited[nx][ny] || p.status * map[nx][ny] == 1) continue;
                isVisited[nx][ny] = true;
                queue.offer(new Path(nx, ny, map[nx][ny], p.step + 1));
            }
        }
        System.out.println(-1);
    }

    static int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    static boolean isValid(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static class Path {
        int x, y;
        int status = 0;//1表示带正,-1表示带负
        int step = 0;

        public Path(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Path(int x, int y, int status, int step) {
            this.x = x;
            this.y = y;
            this.status = status;
            this.step = step;
        }
    }
}
