package 算法.深搜_广搜.深度优先;

public class _695岛屿的最大面积 {
    /*
    给你一个大小为 m x n 的二进制矩阵 grid 。
    岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
    你可以假设 grid 的四个边缘都被 0（代表水）包围着。
    岛屿的面积是岛上值为 1 的单元格的数目。
    计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
     */
    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }

    /*
    深搜_广搜,寻找当前点的四个方向是否为1
    */
    static int[][] map = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//搜索四个方向

    public static int dfs(int[][] grid, int cur_i, int cur_j) {
        if (cur_i < 0 || cur_i >= grid.length || cur_j < 0 || cur_j >= grid[0].length || grid[cur_i][cur_j] == 0) {
            //越界为0、不是陆地为0
            return 0;
        }
        grid[cur_i][cur_j] = 0;//走过后置0,防止重复;
        int count = 1;//统计连通的1的个数
        for (int[] direct : map) {
            int next_i = cur_i + direct[0];
            int next_j = cur_j + direct[1];
            count += dfs(grid, next_i, next_j);
        }
        return count;
    }
}
