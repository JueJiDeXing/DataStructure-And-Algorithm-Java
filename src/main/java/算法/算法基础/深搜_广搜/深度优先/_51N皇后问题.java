package 算法.算法基础.深搜_广搜.深度优先;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _51N皇后问题 {
    public static void main(String[] args) {
        System.out.println(queen(4));
        for (int[] result : res) {
            System.out.println(Arrays.toString(result));
        }
        for (char[][] result : res2) {
            System.out.println(Arrays.deepToString(result));
        }
    }

    static int count = 0;//解的个数

    static List<int[]> res = new ArrayList<>();//解的情况,记录每行皇后的列索引

    static List<char[][]> res2 = new ArrayList<>();//解的情况,Q表示皇后
    static char[][] result;

    public static int queen(int n) {
        result = new char[n][n];
        for (char[] a : result) {
            Arrays.fill(a, '.');//'.'表示空,'Q'表示皇后
        }
        int[] map = new int[n];//记录每行皇后的列信息
        dfs(map, n, 0);
        return count;
    }

    /**
     @param map map[i]=j表示第i-1行的皇后放在第j-1列上
     @param n   皇后总数 && 棋盘边长
     @param row 当前已放置个数 && 当前需要放置皇后的行索引
     */
    public static void dfs(int[] map, int n, int row) {
        if (row == n) {//最后一行放完了
            int[] res_map = new int[n];
            System.arraycopy(map, 0, res_map, 0, n);
            res.add(res_map);

            char[][] resul = new char[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(result[i], 0, resul[i], 0, n);
            }
            res2.add(resul);

            count++;
            return;
        }

        for (int col = 0; col < n; col++) {//i为当前列
            if (check(map, row, col)) {//检测当前行列能否放置
                //能放置
                map[row] = col;
                result[row][col] = 'Q';
                dfs(map, n, row + 1);//放入后搜索下一行
                result[row][col] = '.';
                map[row] = 0;
            }
        }
    }

    /**
     检查当前位置能否放置

     @param map     所有行的放置情况
     @param row,col 放置的位置
     @return
     */
    private static boolean check(int[] map, int row, int col) {
        for (int i = 0; i < row; i++) {//检测之前的行
            if (map[i] == col) {//列
                return false;
            }
            if (i + map[i] == row + col) {//副对角线
                // 解释:在同一副对角线上的点row+col都相等,i+map[i]看[row,col]是否在第i行的皇后的副对角线
                return false;
            }
            if (i - map[i] == row - col) {//主对角线
                return false;
            }
        }
        return true;
    }
}

class 二进制解法 {
    public int totalNQueens(int n) {
        int limit = (1 << n) - 1;
        return f(limit, 0, 0, 0);
    }

    public int f(int limit, int col, int left, int right) {
        if (limit == col) {
            return 1;
        }
        int ans = 0;
        int ban = col | left | right;
        int candidate = limit & (~ban);
        int place;
        while (candidate != 0) {
            place = candidate & (-candidate);
            candidate ^= place;
            ans += f(limit, col | place, (left | place) >> 1, (right | place) << 1);
        }
        return ans;
    }
}
