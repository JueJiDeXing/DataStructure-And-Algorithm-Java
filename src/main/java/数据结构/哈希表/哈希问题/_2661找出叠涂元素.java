package 数据结构.哈希表.哈希问题;

public class _2661找出叠涂元素 {
    /*
    给你一个下标从 0 开始的整数数组 arr 和一个 m x n 的整数 矩阵 mat 。
    arr 和 mat 都包含范围 [1，m * n] 内的 所有 整数。

    从下标 0 开始遍历 arr 中的每个下标 i ，并将包含整数 arr[i] 的 mat 单元格涂色。

    请你找出 arr 中在 mat 的某一行或某一列上都被涂色且下标最小的元素，并返回其下标 i 。
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int t = m * n;
        int[][] map = new int[t + 1][2];//将mat转存为key-value形式,key为数值,value为位置(行与列)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[mat[i][j]][0] = i;
                map[mat[i][j]][1] = j;
            }
        }
        int[] visitRow = new int[m], visitCol = new int[n];
        for (int i = 0; i < t; i++) {
            int[] index = map[arr[i]];
            if (++visitRow[index[0]] == n || ++visitCol[index[1]] == m) {
                return i;
            }
        }
        return -1;
    }
}
