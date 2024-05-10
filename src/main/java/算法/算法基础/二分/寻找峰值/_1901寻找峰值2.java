package 算法.算法基础.二分.寻找峰值;

/**
 难度:中等
 */
public class _1901寻找峰值2 {
    /*
    一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。

    给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。

    你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。

    要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
     */

    /**
     从(0,0)开始沿着大数走,一定会遇到峰顶,但是这不满足时间复杂度要求<br>
     例如: 下图会走一条弯曲的道路<br>
     <pre>

     -1 -1 -1 -1 -1 -1
     -1  2  3  4  5 -1
     -1  1  2  1  6 -1
     -1 10  9  8  7 -1
     -1 11  1  2  1 -1
     -1 12 13 14 15 -1
     -1 -1 -1 -1 -1 -1
     </pre>
     下面开始尝试二分<br>
     选取中间行[10,9,8,7]找到最大值k,比较它的上下<br>
     <p>
     如果下面行大于k,则答案在k下面的行中<br>
     因为k是这一行最大的,而根据"沿着大数走,一定会遇到峰顶"<br>
     所以下面的行必然有解,而上面的行不一定有解,满足二分的二段性<br>
     <p>
     同理: 如果上面行大于k,则答案在上面的行中<br>
     <p>
     如果k大于两边,则k为峰顶(k大于两边,k又是这行的最大值,那么k为峰)<br>
     */
    public int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int left = 0, right = m - 1;//第几行
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int maxIndex = getMaxIndex(mat[mid]); //找mid行的最大值所在位置

            if (mid - 1 >= 0 && mat[mid][maxIndex] < mat[mid - 1][maxIndex]) {
                //在maxIndex列,mid行的上一行大于mid行,峰在上面
                right = mid - 1;
            } else if (mid + 1 < m && mat[mid][maxIndex] < mat[mid + 1][maxIndex]) {
                //mid行的最大值处大于下一行,峰在下面
                left = mid + 1;
            } else {
                //mid就是最大的
                return new int[]{mid, maxIndex};
            }
        }
        return null;//only if mat==null
    }

    /**
     获取数组arr中最大值的索引
     */
    private int getMaxIndex(int[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) maxIndex = i;
        }
        return maxIndex;
    }
}
