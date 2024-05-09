package 算法.深搜_广搜.广度优先;

import java.util.*;

public class _994腐烂橘子 {
    /*
    在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
    值 0 代表空单元格；
    值 1 代表新鲜橘子；
    值 2 代表腐烂的橘子。
    每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
    返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     */
    public int orangesRotting(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        LinkedList<int[]> queue = new LinkedList<>();//广度优先搜索
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    //将所有腐烂橘子加入搜索队列
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    count++;//统计新鲜橘子个数
                }
            }
        }
        int res = 0;
        while (count > 0 && !queue.isEmpty()) {
            res++;//当前时间,每搜索一层,加1
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] p = queue.poll();//拿该层的腐烂橘子搜索
                assert p != null;
                int x = p[0];
                int y = p[1];
                if (x > 0 && grid[x - 1][y] == 1) {
                    count--;//新鲜橘子腐烂
                    grid[x - 1][y] = 2;
                    queue.offer(new int[]{x - 1, y});//加入搜索队列
                }
                if (x < row - 1 && grid[x + 1][y] == 1) {
                    count--;
                    grid[x + 1][y] = 2;
                    queue.offer(new int[]{x + 1, y});
                }
                if (y > 0 && grid[x][y - 1] == 1) {
                    count--;
                    grid[x][y - 1] = 2;
                    queue.offer(new int[]{x, y - 1});
                }
                if (y < col - 1 && grid[x][y + 1] == 1) {
                    count--;
                    grid[x][y + 1] = 2;
                    queue.offer(new int[]{x, y + 1});
                }
            }
        }
        if (count > 0) return -1;//还有新鲜橘子
        return res;
    }
}
