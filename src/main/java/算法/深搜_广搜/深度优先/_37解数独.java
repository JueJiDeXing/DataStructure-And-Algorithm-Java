package 算法.深搜_广搜.深度优先;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _37解数独 {
    // 空格以'.'填充
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        solveSudoku(board);

    }

    static boolean[][] rowMap;
    static boolean[][] colMap;
    static boolean[][] partMap;// i/3*3 + j/3 == 九宫格索引

    public static void solveSudoku(char[][] board) {
        int n = 9;
        rowMap = new boolean[n][n];//9行,1~9
        colMap = new boolean[n][n];
        partMap = new boolean[n][n];
        //初始化冲突状态
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char ch = board[i][j];
                if (ch != '.') {
                    int index = ch - '1';
                    rowMap[i][index] = true;
                    colMap[j][index] = true;
                    partMap[i / 3 * 3 + j / 3][index] = true;
                }
            }
        }
        dfs(board, 0, 0);//从第一格开始填空
        System.out.println(Arrays.deepToString(board));
    }

    public static boolean dfs(char[][] board, int i, int j) {
        while (board[i][j] != '.') {//跳过空格
            j++;
            if (j >= 9) {//j到头,换下一行
                j = 0;
                i++;
            }
            if (i >= 9) {//找到解
                return true;
            }
        }
        for (int x = 1; x <= 9; x++) {
            if (checkMap(i, j, x)) {//检测冲突
                //可以放入
                board[i][j] = (char) (x + '0');
                updateMap(i, j, x, true);
                //继续搜索
                if (dfs(board, i, j)) {//找到解
                    return true;
                }
                //没找到解,说明刚填入的不行,回溯
                updateMap(i, j, x, false);
                board[i][j] = '.';

            }
        }
        return false;
    }

    public static void updateMap(int i, int j, int x, boolean b) {
        rowMap[i][x - 1] = colMap[j][x - 1] =
                partMap[i / 3 * 3 + j / 3][x - 1] = b;
    }

    public static boolean checkMap(int i, int j, int x) {
        return !rowMap[i][x - 1] && !colMap[j][x - 1]
                && !partMap[i / 3 * 3 + j / 3][x - 1];
    }
}


class Solution {

    int[] rowMap = new int[9];//行冲突情况
    int[] colMap = new int[9];//列冲突情况
    int[][] partMap = new int[3][3];//小九宫格冲突情况
    List<int[]> spaces = new ArrayList<>();//空位的位置信息

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    updateMap(i, j, board[i][j] - '1');
                }
            }
        }
        //深搜前的一个优化,找只能填一个数的空位,将其填入
        boolean modified = true;//记录本次循环是否有操作
        while (modified) {
            modified = false;
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    if (board[i][j] == '.') {
                        int mask = getMask(i, j);//获取能填的数(进制,1为能填,0为不能填)
                        if ((mask & (mask - 1)) == 0) {//mask是2的幂,说明该空位只有一个数字能填
                            int x = Integer.bitCount(mask - 1); // 2^x = mask 找对应的1是第几位
                            updateMap(i, j, x);//填入这个数
                            board[i][j] = (char) (x + '1');
                            modified = true;
                        }
                    }
                }
            }
        }
        //找空格位置,存储进spaces集合
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        //开始深搜
        dfs(board, 0);
    }

    /**
     @param board   数独表
     @param currNum 当前操作的是第几个空位
     */
    public boolean dfs(char[][] board, int currNum) {
        if (currNum == spaces.size()) {//空格都操作过了
            return true;
        }
        int[] space = spaces.get(currNum);//获取当前空位
        int i = space[0], j = space[1];
        for (int mask = getMask(i, j); mask != 0; mask &= (mask - 1)) {//遍历所有能填的数
            int x = Integer.bitCount((mask & (-mask)) - 1); //mask & (-mask) 只保留mask最右侧的1 , 减1后的1的个数即为mask最右侧1是第几位的,对应到填入的数字x
            updateMap(i, j, x);
            board[i][j] = (char) (x + '1');//填入
            if (dfs(board, currNum + 1)) {//如果找到了答案,不用再回溯
                return true;
            } else {
                updateMap(i, j, x);
            }
        }
        return false;
    }

    private int getMask(int i, int j) {
        //rowMap[i] | colMap[j] | partMap[i / 3][j / 3] 得到所有不能填的位
        // 取反得到所有可以填的位
        // 和0b111111111做&运算,消去高位的1
        return ~(rowMap[i] | colMap[j] | partMap[i / 3][j / 3]) & 0b111111111;
    }

    /**
     用异或存储行、列、九宫格的数字存储信息

     @param i,j 当前位置
     @param x   当前位置的数减1(范围0到8)
     */
    public void updateMap(int i, int j, int x) {
        rowMap[i] ^= (1 << x);//异或运算将第x位变为1,表示使用数字(x-1)
        colMap[j] ^= (1 << x);
        partMap[i / 3][j / 3] ^= (1 << x);
    }
}
