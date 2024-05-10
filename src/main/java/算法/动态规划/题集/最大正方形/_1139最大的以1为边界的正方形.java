package 算法.动态规划.题集.最大正方形;

public class _1139最大的以1为边界的正方形 {
    /*
    给你一个由若干 0 和 1 组成的二维网格 grid，
    请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。
    如果不存在，则返回 0。
     */

    /**
     <h1>动态规划</h1>
     <ul>
     <li>思路:<br>
     枚举右下角 (i,j) 和 最大边长 L(要找最大的正方形, L从最大开始枚举) <br>
     left[i][j] 表示 (i,j) 左边连续 1 的个数, up[i][j] 表示上方连续 1 的个数<br>
     合法正方形要满足: 右下的left, 右下的up, 左下的up, 右上的left 都大于 L
     </li>
     <li>递推关系:<br>
     如果 (i,j) 为 0, 则 left[i][j] 为 0 <br>
     如果 (i,j) 为 1, 则 left[i][j] 为 left[i][j-1] + 1 <br>
     up同理
     </li>
     </ul>
     */
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m + 1][n + 1];
        int[][] up = new int[m + 1][n + 1];
        int maxBorder = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (grid[i - 1][j - 1] == 1) {
                    left[i][j] = left[i][j - 1] + 1;
                    up[i][j] = up[i - 1][j] + 1;
                    int border = Math.min(left[i][j], up[i][j]);
                    while (left[i - border + 1][j] < border || up[i][j - border + 1] < border) {
                        border--;
                    }
                    maxBorder = Math.max(maxBorder, border);
                }
            }
        }
        return maxBorder * maxBorder;
    }
}
