package 算法.排序.排序问题;

public class _1329将矩阵按对角线排序 {
    /*
    矩阵对角线 是一条从矩阵最上面行或者最左侧列中的某个元素开始的对角线，沿右下方向一直到矩阵末尾的元素。
    例如，矩阵 mat 有 6 行 3 列，从 mat[2][0] 开始的 矩阵对角线 将会经过 mat[2][0]、mat[3][1] 和 mat[4][2] 。

    给你一个 m * n 的整数矩阵 mat ，请你将同一条 矩阵对角线 上的元素按升序排序后，返回排好序的矩阵。
     */
    public int[][] diagonalSort(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        //冒泡排序
        for (int k = 0; k < Math.min(row, col) - 1; k++) {//每轮循环会把最大值放在最后一行和最后一列,循环次数min(m,n)-1
            for (int i = 0; i < row - 1; i++) {
                for (int j = 0; j < col - 1; j++) {
                    if (mat[i][j] > mat[i + 1][j + 1]) {
                        //交换
                        mat[i][j] = mat[i][j] ^ mat[i + 1][j + 1];
                        mat[i + 1][j + 1] = mat[i][j] ^ mat[i + 1][j + 1];
                        mat[i][j] = mat[i][j] ^ mat[i + 1][j + 1];
                    }
                }
            }
        }
        return mat;

    }
}
