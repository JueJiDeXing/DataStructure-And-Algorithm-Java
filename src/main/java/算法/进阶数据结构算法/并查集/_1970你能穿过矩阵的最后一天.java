package 算法.进阶数据结构算法.并查集;

public class _1970你能穿过矩阵的最后一天 {
    /*
    给你一个下标从 1 开始的二进制矩阵，其中 0 表示陆地，1 表示水域。
    同时给你 row 和 col 分别表示矩阵中行和列的数目。
    一开始在第 0 天，整个 矩阵都是 陆地 。但每一天都会有一块新陆地被 水 淹没变成水域。
    给你一个下标从 1 开始的二维数组 cells ，
    其中 cells[i] = [ri, ci] 表示在第 i 天，第 ri 行 ci 列（下标都是从 1 开始）的陆地会变成 水域 （也就是 0 变成 1 ）。
    你想知道从矩阵最 上面 一行走到最 下面 一行，且只经过陆地格子的 最后一天 是哪一天。
    你可以从最上面一行的 任意 格子出发，到达最下面一行的 任意 格子。你只能沿着 四个 基本方向移动（也就是上下左右）。
    请返回只经过陆地格子能从最 上面 一行走到最 下面一行的 最后一天
     */
    static class DisjointSet {
        int[] s;
        int[] size;//维护集合大小,在union时将顶点个数少的集合连接到顶点个数多的集合

        public DisjointSet(int size) {
            s = new int[size];
            this.size = new int[size];
            for (int i = 0; i < size; i++) {
                s[i] = i;
                this.size[i] = 1;//初始为1
            }
        }

        // find 寻找集合的老大
        public int find(int x) {
            if (x == s[x]) {
                return x;
            }
            return s[x] = find(s[x]);
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (size[rx] < size[ry]) {
                // y 老大  x 小弟
                s[rx] = ry;
                size[ry] = size[rx] + size[ry]; // 更新老大元素个数
            } else {
                // x 新老大 y 新小弟
                s[ry] = rx;
                size[rx] = size[rx] + size[ry]; // 更新老大元素个数
            }
        }

        public boolean isConnected(int up, int down) {
            return find(up) == find(down);
        }
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        //并差集
        int size = row * col;
        set = new DisjointSet(size + 2);
        int up = size;//上下超级节点
        int down = size + 1;
        map = new boolean[row][col];

        for (int i = cells.length - 1; i >= 0; i--) {//倒序,找陆地
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            map[r][c] = true;//陆地
            int index = convert(col, r, c);//转换到一维并查集坐标
            search(row, col, r, c, index);//查找四个方向是否有陆地连通,如果有连通则合并集合
            if (r == 0) {
                set.union(index, up);
            }
            if (r == row - 1) {
                set.union(index, down);
            }
            if (set.isConnected(up, down)) {
                return i;
            }
        }
        return -1;
    }

    DisjointSet set;
    boolean[][] map;
    int[][] directions = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private void search(int row, int col, int r, int c, int index) {
        for (int[] dir : directions) {
            int r2 = r + dir[0];
            int c2 = c + dir[1];
            if (isValid(r2, c2, row, col) && map[r2][c2]) {//索引不越界且为陆地
                int index2 = convert(col, r2, c2);
                set.union(index, index2);//连通
            }
        }
    }

    private boolean isValid(int r, int c, int row, int col) {
        return r >= 0 && c >= 0 && r < row && c < col;
    }

    private static int convert(int col, int r, int c) {
        return r * col + c;
    }
}
