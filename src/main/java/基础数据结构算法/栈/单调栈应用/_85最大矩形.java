package 基础数据结构算法.栈.单调栈应用;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _85最大矩形 {
    /*
    给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，
    找出只包含 1 的最大矩形，并返回其面积。
     */

    public static void main(String[] args) {
        _85最大矩形 test = new _85最大矩形();
        System.out.println(test.maximalRectangle2(new char[][]{{'1'}}));
    }

    /**
     <h2>思路 :</h2>
     假设矩阵只到第i行, 那么统计第i行上方连续1的个数, 可以得到一个柱状图<br>
     此时就与{@link _84柱状图中的最大矩形}是一样的了, 可以使用单调栈进行求解<br>
     那么, 可以以行遍历这个矩阵, 计算以每一行为基准柱状图的最大矩形面积, 记录这个过程中的最大值即可<br>
     <hr>
     <ul>
     <li>
     1. 如何求得每行所对应的柱状图 : <br>
     这是一个动态规划递推的过程<br>
     令sum[i][j]表示第i行的第j列上方连续1的个数(含自身)<br>
     如果mat[i][j]为0,则sum[i][j]=0;如果mat[i][j]为1,则sum[i][j]为上一行的加1;<br>
     </li>
     <li>
     2. 如何计算柱状图中的最大矩形 :<br>
     方法: 枚举高度h[i], 计算在高度为h[i]时的面积, 统计这个过程产生的最大值<br>
     要计算高度为h[i]时的面积, 还需要知道宽度, weight = 左边所有比它高的数量 + 右边比它高的数量 + 自身<br>
     也可表示为 右边第一个比它小的索引-左边第一个比它小的索引-1 = right[i]-left[i]-1<br>
     <b>对于"第一个"比它"大/小"的问题通常可以使用单调栈进行求解</b><br>
     right[i]:从左到右入栈,如果当前值比栈顶小,就出栈,出栈元素就找到了右边第一个比它小的索引<br>
     left[i]:从右到左入栈,如果当前值比栈顶小,就出栈,出栈元素就找到了左边第一个比它小的索引<br>
     </li>
     </ul>
     */
    public int maximalRectangle(char[][] mat) {
        int n = mat.length, m = mat[0].length, ans = 0;
        int[][] sum = new int[n + 10][m + 10];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                sum[i][j] = sum[i - 1][j] + mat[i - 1][j - 1] - '0';
            }
        }
        int[] l = new int[m + 10], r = new int[m + 10];
        for (int i = 1; i <= n; i++) {//枚举基准行
            int[] hs = sum[i];//柱状图
            Arrays.fill(l, 0);
            Arrays.fill(r, m + 1);
            Deque<Integer> d = new ArrayDeque<>();
            for (int j = 1; j <= m; j++) {
                while (!d.isEmpty() && hs[d.peekLast()] > hs[j]) {
                    r[d.pollLast()] = j;
                }
                d.addLast(j);
            }
            d.clear();
            for (int j = m; j >= 1; j--) {
                while (!d.isEmpty() && hs[d.peekLast()] > hs[j]) {
                    l[d.pollLast()] = j;
                }
                d.addLast(j);
            }
            for (int j = 1; j <= m; j++) {
                ans = Math.max(ans, (r[j] - l[j] - 1) * hs[j]);
            }
        }
        return ans;
    }

    /**
     使用数组代替栈
     */
    public int maximalRectangle2(char[][] mat) {
        int n = mat.length, m = mat[0].length, ans = 0;
        int[][] sum = new int[n + 1][m];//空第一行
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i][j] = mat[i - 1][j] == '0' ? 0 : sum[i - 1][j] + 1;
            }
        }
        int[] l = new int[m], r = new int[m], d = new int[m];//left,right,stack栈
        for (int i = 1; i <= n; i++) {//sum空了第一行,i从1开始
            int[] hs = sum[i];
            Arrays.fill(l, -1);
            Arrays.fill(r, m);
            int k = 0;//栈内元素个数
            for (int j = 0; j < m; j++) {
                while (k != 0 && hs[d[k - 1]] > hs[j]) {
                    r[d[--k]] = j;
                }
                d[k++] = j;
            }
            k = 0;
            for (int j = m - 1; j >= 0; j--) {
                while (k != 0 && hs[d[k - 1]] > hs[j]) {
                    l[d[--k]] = j;
                }
                d[k++] = j;
            }
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, hs[j] * (r[j] - l[j] - 1));
            }
        }
        return ans;
    }
}
