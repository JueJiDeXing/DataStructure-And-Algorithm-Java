package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

/**
 已AC
 */
public class D方格分割 {
    /*
    6*6的方格裁成两份一样的块,求裁剪方案
     */

    /**
     7*7条线,从中心点(3,3)走到边界的方案数/4
     */
    public static void main(String[] args) {
        boolean[][] isVisit = new boolean[7][7];
        isVisit[3][3] = true;
        dfs(isVisit, 3, 3);//计算从中心点走到边界的方案数
        System.out.println(ans / 4);//4个边界,方案数除以4. 最终结果509
    }

    static int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int ans = 0;

    private static void dfs(boolean[][] isVisit, int x, int y) {
        if (x == 0 || x == 6 || y == 0 || y == 6) {
            ans++;
            return;
        }
        for (int[] dir : direction) {
            int newX = x + dir[0], newY = y + dir[1];
            if (!isValid(newX, newY, 7)) continue;
            if (!isVisit[newX][newY]) {
                isVisit[newX][newY] = true;
                isVisit[6 - newX][6 - newY] = true;
                dfs(isVisit, newX, newY);
                isVisit[newX][newY] = false;
                isVisit[6 - newX][6 - newY] = false;
            }
        }
    }

    static boolean isValid(int i, int j, int t) {
        return 0 <= i && i < t && 0 <= j && j < t;
    }

    private static void solve1() {// 610?
        int[][] isVisit = new int[6][6];
        isVisit[0][0] = 1;
        isVisit[5][5] = 2;
        dfs(isVisit, 0, 0, 1);
        System.out.println(ans);
    }

    static void dfs(int[][] isVisit, int i, int j, int count) {
        if (count == 18) {
            ans++;
            return;
        }
        for (int[] dir : direction) {
            int newI = i + dir[0], newJ = j + dir[1];
            if (!isValid(newI, newJ, 6)) continue;
            if (isVisit[newI][newJ] == 0) {
                isVisit[newI][newJ] = 1;
                isVisit[5 - newI][5 - newJ] = 2;
                dfs(isVisit, newI, newJ, count + 1);
                isVisit[newI][newJ] = 0;
                isVisit[5 - newI][5 - newJ] = 0;
            }
        }
    }

}
