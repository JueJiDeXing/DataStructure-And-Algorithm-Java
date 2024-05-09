package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 已AC
 */
public class B走迷宫 {
    /*
    n*n的迷宫,小明从(2,2)走到(n-3,n-3),每秒移动1个单位
    为什么是(2,2),因为初始小明大小为5*5
    给出整数k,小明在k时刻大小变为3*3,在2k时刻变为1*1
    求小明的最少多长时间到终点

    9 5
    + + + + + + + + +
    + + 6 6 6 6 6 + +
    + 6 0 1 2 3 4 6 +
    + + 6 6 6 6 6 + +
    + + + 11 + + + + +
    * * * 12 * * * * *
    + + + 13 14 15 16 + +
    + + + + + + + + +
    + + + + + + + + +
     */

    static int n, k;
    static char[][] map;
    static int[][] dir_four = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] dir_eight = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static int[][] dir_sixteen = {{-2, -2}, {-2, -1}, {-2, 0}, {-2, 1}, {-2, 2},
            {2, -2}, {2, -1}, {2, 0}, {2, 1}, {2, 2},
            {-1, -2}, {-1, 2}, {0, -2}, {0, 2}, {1, -2}, {1, 2}
    };

    /**
     bfs 多记录一个时间,如果下一个位置能直接在time状态下走,则直接走,time+1
     如果不能直接走,检查变为下一个或下下个状态能不能走,时间上为等待,如果下一个状态是3*3,那么时间就是k+1
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }
        Queue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[2] - o2[2]));//[x,y,time]
        queue.offer(new int[]{2, 2, 0});
        boolean[][] visited = new boolean[n][n];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], time = cur[2];
            if (x == n - 3 && y == n - 3) {
                System.out.println(time);
                return;
            }
            if (visited[x][y]) continue;
            visited[x][y] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dir_four[i][0], ny = y + dir_four[i][1];
                if (!isValid(nx, ny) || map[nx][ny] == '*' || visited[nx][ny]) continue;
                int check = check(nx, ny, time);
                if (check == 0) {//对于time阶段,无障碍,能直接走
                    queue.offer(new int[]{nx, ny, time + 1});
                } else {
                    //不能直接走,看人物变小之后能不能走
                    if (time >= k) {//当前是3*3,下一阶段是1*1,肯定能走
                        queue.offer(new int[]{nx, ny, 2 * k + 1});
                    } else {//当前是5*5,下一阶段是3*3,再下一阶段是1*1
                        if (check == 1) {//3*3内有障碍,需要1*1的阶段
                            queue.offer(new int[]{nx, ny, 2 * k + 1});
                        } else {
                            queue.offer(new int[]{nx, ny, k + 1});
                        }
                    }
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    /**
     检查(x,y)位置在time时刻是否抵达
     */
    static int check(int x, int y, int time) {
        if (time >= 2 * k) return 0;//(x,y)不能走已经跳过了
        //检查3*3
        for (int[] d : dir_eight) {
            int nx = x + d[0], ny = y + d[1];
            if (!isValid(nx, ny) || map[nx][ny] == '*') return 1;
        }
        if (time >= k) return 0;
        //检查5*5
        for (int[] d : dir_sixteen) {
            int nx = x + d[0], ny = y + d[1];
            if (!isValid(nx, ny) || map[nx][ny] == '*') return 2;
        }
        return 0;
    }

}
