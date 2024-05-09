package 算法OJ.蓝桥杯.真题卷.第6届.国赛;

/**
 已AC
 */
public class B四阶幻方 {
    public static void main(String[] args) {
        dfs(0, 1);
        System.out.println(ans);// 416
    }

    static int[][] map = new int[4][4];
    static boolean[] isUsed = new boolean[17];
    static int sum = 34;

    static {
        map[0][0] = 1;
    }

    static void dfs(int x, int y) {
        if (y == 4) {
            if (!checkRow(x)) return;
            y = 0;
            x++;
            if (x == 4) {
                if (check()) {
                    ans++;
                }
                return;
            }
        }
        for (int i = 2; i <= 16; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            map[x][y] = i;
            dfs(x, y + 1);
            map[x][y] = 0;
            isUsed[i] = false;
        }
    }

    static int ans = 0;

    static boolean checkRow(int x) {
        int s = 0;
        for (int i = 0; i < 4; i++) {
            s += map[x][i];
        }
        return s == sum;
    }

    static boolean check() {
        for (int i = 0; i < 4; i++) {
            if (!checkCol(i)) return false;
        }
        return checkDia();
    }

    static boolean checkCol(int x) {
        int s = 0;
        for (int i = 0; i < 4; i++) {
            s += map[i][x];
        }
        return s == sum;
    }

    static boolean checkDia() {
        int s = 0;
        for (int i = 0; i < 4; i++) {
            s += map[i][i];
        }
        if (s != sum) return false;
        s = 0;
        for (int i = 0; i < 4; i++) {
            s += map[i][3 - i];
        }
        return s == sum;
    }

}
