package 算法.算法基础.二分;

/**
 难度:中等
 */
public class _74二维矩阵搜索 {
    /*
    给你一个满足下述两条属性的 m x n 整数矩阵：
    每行中的整数从左到右按非严格递增顺序排列。
    每行的第一个整数大于前一行的最后一个整数。
    给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
     */

    /**
     <h1>二维数组上的二分</h1>
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col;
        while (left < right - 1) {
            int mid = (left + right) >>> 1;
            int mid_row = mid / col;//中间索引
            int mid_col = mid - col * mid_row;
            if (matrix[mid_row][mid_col] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return matrix[left / col][left - col * (left / col)] == target;
    }
}
