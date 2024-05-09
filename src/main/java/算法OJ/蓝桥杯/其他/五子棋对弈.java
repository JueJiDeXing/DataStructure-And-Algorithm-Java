package 算法OJ.蓝桥杯.其他;

public class 五子棋对弈 {
    static int[][] map = new int[5][5];

    public static void main(String[] args) {
        System.out.println(dfs(0, 0, 13, 12));
    }

    static long dfs(int x, int y, int s1, int s2) {
        if (y == 5) {
            y = 0;
            x++;
            if (x == 5) {
                //check
                return check() ? 1 : 0;
            }
        }
        long ans = 0;
        if (s1 > 0) {
            map[x][y] = 1;
            ans += dfs(x, y + 1, s1 - 1, s2);
            map[x][y] = 0;
        }
        if (s2 > 0) {
            map[x][y] = -1;
            ans += dfs(x, y + 1, s1, s2 - 1);
            map[x][y] = 0;
        }
        return ans;
    }

    /**
     是否和棋
     */
    static boolean check() {
        for (int i = 0; i < 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 1) cnt++;
            }
            if (cnt == 0 || cnt == 5) return false;
        }
        for (int j = 0; j < 5; j++) {
            int cnt = 0;
            for (int i = 0; i < 5; i++) {
                if (map[i][j] == 1) cnt++;
            }
            if (cnt == 0 || cnt == 5) return false;
        }
        int cntM = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 1) cntM++;
        }
        if (cntM == 0 || cntM == 5) return false;
        int cntF = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][4 - i] == 1) cntF++;
        }
        return cntF == 0 || cntF == 5;
    }
}
