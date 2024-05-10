package 算法.算法基础.矩阵;

import java.util.Arrays;

/**
 难度:中等
 */
public class _73矩阵置零 {
    /*
    给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
    请使用 原地 算法。
     */
    public void setZeroes(int[][] matrix) {
        //进阶:直接使用第一行与第一列代替O(m+n)的额外空间
        int m = matrix.length, n = matrix[0].length;
        //预处理第一行第一列是否有0
        boolean isFirstRowZero = false, isFirstColZero = false;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                isFirstRowZero = true;
                break;
            }
        }
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                isFirstColZero = true;
                break;
            }
        }
        //记录标志
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //置0
        for (int i = 1; i < n; i++) {//注意这里别把第一格包括进去了,否则会影响到第一列标志
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //再处理第一行第一列
        if (isFirstRowZero) Arrays.fill(matrix[0], 0);
        if (isFirstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
