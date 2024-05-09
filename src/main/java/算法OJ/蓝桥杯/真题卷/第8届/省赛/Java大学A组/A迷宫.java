package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 已AC
 */
public class A迷宫 {
    /*
    给出由0/1组成的迷宫,0可以走,1不能走,左上角为起点,右下角为终点
    求步数最少的方案中字典序最小的一种方案,字典序D<L<R<U
    迷宫大小30行50列
     */

    /**
     bfs,按D,L,R,U次序扫描四个方向<br>
     第一次到达[n-1][m-1]时就是最短路径,且字典序最小
     */
    public static void main(String[] args) {
        //输入
        Scanner sc = new Scanner(System.in);
        int n = 30, m = 50;
        String[] map = new String[n];
        for (int i = 0; i < 30; i++) {
            map[i] = sc.next();
        }
        //初始
        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(new StringBuilder(), 0, 0));
        boolean[][] isVisit = new boolean[n][m];
        isVisit[0][0] = true;
        //从(0,0)开始bfs
        while (!queue.isEmpty()) {
            Path poll = queue.poll();
            int x = poll.x, y = poll.y;
            StringBuilder s = poll.s;//走到(x,y)的最短路径
            if (x == n - 1 && y == m - 1) {//走到终点了,输出最短路径
                System.out.println(s);//DDDDRRURRRRRRDRRRRDDDLDDRDDDDDDDDDDDDRDDRRRURRUURRDDDDRDRRRRRRDRRURRDDDRRRRUURUUUUUUULULLUUUURRRRUULLLUUUULLUUULUURRURRURURRRDDRRRRRDDRRDDLLLDDRRDDRDDLDDDLLDDLLLDLDDDLDDRRRRRRRRRDDDDDDRR
                return;
            }
            for (int i = 0; i < 4; i++) {//检查四个方向
                int nextX = x + dir[i][0], nextY = y + dir[i][1];
                if (!isValid(nextX, nextY) || isVisit[nextX][nextY] || map[nextX].charAt(nextY) == '1') {
                    continue;//不能越界,不能已访问,不能是1
                }
                StringBuilder nextS = new StringBuilder(s);
                nextS.append(di[i]);
                isVisit[nextX][nextY] = true;
                queue.offer(new Path(nextS, nextX, nextY));
            }
        }
    }


    static class Path {
        StringBuilder s;
        int x, y;

        public Path(StringBuilder s, int x, int y) {
            this.s = s;
            this.x = x;
            this.y = y;
        }
    }

    static int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    static char[] di = {'D', 'L', 'R', 'U'};


    static boolean isValid(int x, int y) {
        return 0 <= x && x < 30 && 0 <= y && y < 50;
    }


}
