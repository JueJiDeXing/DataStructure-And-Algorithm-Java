package 算法.算法基础.矩阵;

/**
 难度:中等
 */
public class _48旋转图像 {
    /*
    给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     */
    public void rotate(int[][] matrix) {
        //顺时针旋转90°⇔上下翻转+沿主对角线翻转
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {//上下翻转
            int[]row=matrix[i];
            matrix[i]=matrix[len-i-1];
            matrix[len-i-1]=row;
        }
        for (int i = 0; i < len; i++) {//沿主对角线翻转
            for (int j = i; j < len; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

    }
}
