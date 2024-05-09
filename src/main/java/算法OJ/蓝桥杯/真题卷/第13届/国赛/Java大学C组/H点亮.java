package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学C组;

import java.util.Scanner;

/**
 已AC
 */
public class H点亮 {
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.nextLine();
        char[][] map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = sc.nextLine().toCharArray();
        }
        //对4个灯泡预放置
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (map[i][j] == '4') {
                    //上下左右都为灯泡
                    map[i - 1][j] = 'O';
                    map[i + 1][j] = 'O';
                    map[i][j - 1] = 'O';
                    map[i][j + 1] = 'O';
                }
            }
        }
        ans = new char[n][n];
        dfs(map, 0, 0);
        for (char[] r : ans) {
            System.out.println(new String(r));
        }
    }

    static char[][] ans;
    static boolean isAns = false;

    static boolean check(char[][] map) {
        //检查数字是否匹配
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ('0' <= map[i][j] && map[i][j] <= '4') {
                    //统计上下左右的灯泡数
                    int cnt = 0;
                    if (i > 0 && 'O' == map[i - 1][j]) cnt++;
                    if (i < n - 1 && 'O' == map[i + 1][j]) cnt++;
                    if (j > 0 && 'O' == map[i][j - 1]) cnt++;
                    if (j < n - 1 && 'O' == map[i][j + 1]) cnt++;
                    if (map[i][j] - '0' != cnt) return false;
                }
            }
        }
        //检查白格是否都被点亮
        boolean[][] light = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != '.') {
                    light[i][j] = true;
                }
                if (map[i][j] == 'O') {
                    if (!light(map, i, j, light)) return false;
                }
            }
        }
        for (boolean[] l : light) {
            for (boolean b : l) {
                if (!b) return false;
            }
        }
        return true;
    }

    static boolean light(char[][] map, int i, int j, boolean[][] light) {
        for (int x = i - 1; x >= 0; x--) {
            if (map[x][j] == 'O') return false;
            if (map[x][j] != '.') break;
            light[x][j] = true;
        }
        for (int x = i + 1; x < n; x++) {
            if (map[x][j] == 'O') return false;
            if (map[x][j] != '.') break;
            light[x][j] = true;
        }
        for (int x = j - 1; x >= 0; x--) {
            if (map[i][x] == 'O') return false;
            if (map[i][x] != '.') break;
            light[i][x] = true;
        }
        for (int x = j + 1; x < n; x++) {
            if (map[i][x] == 'O') return false;
            if (map[i][x] != '.') break;
            light[i][x] = true;
        }
        return true;
    }

    static void dfs(char[][] map, int i, int j) {
        if (isAns) return;
        if (i == n) {
            if (check(map)) {
                isAns = true;
                clone(map);
            }
            return;
        }
        if (j == n) {
            i++;
            if (i == n) {
                if (check(map)) {
                    isAns = true;
                    clone(map);
                }
                return;
            }
            j = 0;
        }
        dfs(map, i, j + 1);
        if (map[i][j] == '.') {
            map[i][j] = 'O';
            dfs(map, i, j + 1);
            map[i][j] = '.';
        }
    }

    static void clone(char[][] map) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, ans[i], 0, n);
        }
    }
}
