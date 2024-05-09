package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class E矩阵计数 {
    /*
    一个N*M的矩阵,每一格只能填0/1
    求不存在连续三个数字(行连续或列连续)都是1的矩阵有多少个
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        boolean[][] map = new boolean[n][m];
        int ans = dfs(map, 0, 0);
        System.out.println(ans);
    }

    static int n, m;

    static int dfs(boolean[][] map, int x, int y) {
        if (y == m) {
            y = 0;
            x++;
            if (x == n) return 1;
        }
        int ans = dfs(map, x, y + 1); //不填
        if (check(map, x, y)) {//能不能填(x,y)位置
            map[x][y] = true;
            ans += dfs(map, x, y + 1);
            map[x][y] = false;
        }
        return ans;
    }

    static boolean check(boolean[][] map, int x, int y) {
        //向左和上检查有没有连续三个(加当前位置就是3)
        int countX = 0, countY = 0;
        for (int i = 1; i <= 2; i++) {
            if (x - i >= 0 && map[x - i][y]) countX++;
            if (y - i >= 0 && map[x][y - i]) countY++;
        }
        return countX < 2 && countY < 2;
    }
}
