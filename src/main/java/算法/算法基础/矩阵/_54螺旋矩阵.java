package 算法.算法基础.矩阵;

import java.util.*;

/**
 难度:中等
 */
public class _54螺旋矩阵 {
    /*
    给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int currDir = 0, x = 0, y = 0;
        int up = 0, down = m - 1, left = 0, right = n - 1;//边界,例如第一行走完,上边界up++,相当于将第一行删去
        List<Integer> ans = new ArrayList<>();
        while (up <= down && left <= right) {
            ans.add(matrix[x][y]);
            int nextX = x + direction[currDir][0], nextY = y + direction[currDir][1];
            if (nextX > down || nextX < up || nextY > right || nextY < left) {
                if (currDir == 0) {
                    up++;
                } else if (currDir == 1) {
                    right--;
                } else if (currDir == 2) {
                    down--;
                } else {
                    left++;
                }
                currDir = (currDir + 1) % 4;
                nextX = x + direction[currDir][0];
                nextY = y + direction[currDir][1];
            }
            x = nextX;
            y = nextY;
        }
        return ans;
    }
}
