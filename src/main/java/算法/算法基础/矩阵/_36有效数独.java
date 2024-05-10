package 算法.算法基础.矩阵;

/**
 难度:中等
 */
public class _36有效数独 {
    /*
    请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

    数字 1-9 在每一行只能出现一次。
    数字 1-9 在每一列只能出现一次。
    数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）


    注意：

    一个有效的数独（部分已被填充）不一定是可解的。
    只需要根据以上规则，验证已经填入的数字是否有效即可。
    空白格用 '.' 表示。
     */

    /**
     <h1>一次遍历,使用三个二维数组</h1>
     记录判断三个条件是否满足<br>
     num表示当前行的数字,count表示num出现的次数<br>
     1. 记录行, rowCount[i][num]=count, i表示行数 <br>
     2. 记录列, colCount[j][num]=count, j表示行数<br>
     3. 记录小九宫格, litterBoard[k][num]=count, k表示第几个小九宫格<br>
     对于k, 可以通过i,j进行计算 k = (i/3)*3+j/3<br>
     */
    public boolean isValidSudoku1(char[][] board) {
        int[][] rowCount = new int[9][10];
        int[][] colCount = new int[9][10];
        int[][] litterBoard = new int[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char ch = board[i][j];
                if ('.' == ch) {//跳过空字符
                    continue;
                }
                int num = ch - '0';
                rowCount[i][num] += 1;//计数
                colCount[j][num] += 1;

                int k = (i / 3) * 3 + j / 3;//小九宫格
                litterBoard[k][num] += 1;

                // 判断num是否在行,列,小九宫格内重复出现
                if (rowCount[i][num] > 1 || colCount[j][num] > 1 || litterBoard[k][num] > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     <h1>二进制优化</h1>
     使用9个比特位表示当前数字
     */
    public boolean isValidSudoku2(char[][] board) {
        int[] cols = new int[9];//存储9列数字信息
        int[] littleBoard = new int[9];//存储小九宫格内数字信息
        int row;//存储当前遍历行的数字信息
        for (int i = 0; i < 9; i++) {
            row = 0;//行检测,每轮置0
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {//跳过空字符
                    continue;
                }
                int mask = 1 << (board[i][j] - '0');//使用9个比特位判定重复
                //对于num生成的mask只有第num位为1,其余为0,可以使用"与"运算,如果大于0,说明该位为1,即重复出现数字

                int k = (i / 3) * 3 + j / 3;//第k个小九宫格
                if ((row & mask) > 0 || (cols[j] & mask) > 0 || (littleBoard[k] & mask) > 0) {
                    return false;
                }

                row |= mask;
                cols[j] |= mask;
                littleBoard[k] |= mask;
            }
        }
        return true;
    }
}
