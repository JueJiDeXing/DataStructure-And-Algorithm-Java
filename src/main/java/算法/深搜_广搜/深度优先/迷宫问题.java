package 算法.深搜_广搜.深度优先;

import java.util.LinkedList;
import java.util.Scanner;

public class 迷宫问题 {
    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static boolean MazePath(int[][] maze, Pos start, Pos end, LinkedList<Pos> stack) {
        if (maze[start.x][start.y] == 1) {//墙
            return false;
        }
        if (start == end) {
            System.out.println(stack);//TODO 打印路径
            return true;
        }
        maze[start.x][start.y] = 1;//走过
        boolean[] flag = new boolean[4];
        for (int i = 0; i < 4; i++) {
            Pos next = new Pos(start.x + directions[i][0], start.y + directions[i][1]);
            stack.push(next);
            flag[i] = MazePath(maze, next, end, stack);
            stack.pop();
        }
        return flag[0] || flag[1] || flag[2] || flag[3];
    }

    public static void main(String[] args) {
        //找不同路径种数
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] map = new int[row][col];
        int start_x = 0, start_y = 0, end_x = 0, end_y = 0;
        for (int i = 0; i < row; i++) {
            char[] line = sc.next().toCharArray();
            for (int j = 0; j < col; j++) {
                char ch = line[j];
                if (ch == 's') {
                    start_x = i;
                    start_y = j;
                } else if (ch == 'e') {
                    end_x = i;
                    end_y = j;
                } else if (ch == '.') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }
            }
        }
        System.out.println(dfs(map, start_x, start_y, end_x, end_y));
    }

    static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int dfs(int[][] map, int start_x, int start_y, int end_x, int end_y) {
        if (start_x == end_x && start_y == end_y) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int next_x = start_x + dir[i][0];
            int next_y = start_y + dir[i][1];
            if (next_x < 0 || next_y < 0 || next_x >= map.length || next_y >= map[0].length || map[next_x][next_y] == 1)
                continue;
            map[next_x][next_y] = 1;
            count += dfs(map, next_x, next_y, end_x, end_y);
        }
        return count;
    }
}
