package 数据结构.栈.栈问题.单调栈应用;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 难度:困难
 */
public class _84柱状图中的最大矩形 {
    /*
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。
    每个柱子彼此相邻，且宽度为 1 。
    求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */

    /**
     遍历柱子高度数组h(枚举高度h[i]), 计算在高度为h[i]时的最大矩形面积<br>
     这个过程产生的最大值即为答案<br>
     要计算高度为h[i]时的面积, 还需要知道宽度<br>
     weight = 左边(连续的)所有比它高的数量 + 右边(连续的)比它高的数量 + 自身<br>
     也可表示为 右边第一个比它小的索引-左边第一个比它小的索引-1 = right[i]-left[i]-1<br>
     <b>对于"第一个"比它"大/小"的问题通常可以使用单调栈进行求解</b><br>
     right[i]:从左到右入栈,如果当前值比栈顶小,就出栈,出栈元素就找到了右边第一个比它小的索引<br>
     left[i]:从右到左入栈,如果当前值比栈顶小,就出栈,出栈元素就找到了左边第一个比它小的索引<br>
     */
    public int largestRectangleArea(int[] hs) {
        int n = hs.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        //region 单调栈求左右第一个比它小的元素索引
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {//右
            while (!d.isEmpty() && hs[d.peekLast()] > hs[i]) {
                r[d.pollLast()] = i;
            }
            d.addLast(i);
        }
        d.clear();
        for (int i = n - 1; i >= 0; i--) {//左
            while (!d.isEmpty() && hs[d.peekLast()] > hs[i]) {
                l[d.pollLast()] = i;
            }
            d.addLast(i);
        }
        //endregion
        //枚举高度hs[i],求最大矩形面积
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (r[i] - l[i] - 1) * hs[i]);
        }
        return ans;
    }

    public int largestRectangleArea2(int[] hs) {
        int n = hs.length;
        int[] l = new int[n], r = new int[n];
        Arrays.fill(l, -1);
        Arrays.fill(r, n);
        //单调栈求左右第一个比它小的元素索引
        int[] d = new int[n];//栈
        int k = 0;//元素个数
        for (int i = 0; i < n; i++) {
            while (k != 0 && hs[d[k - 1]] > hs[i]) {
                r[d[--k]] = i;
            }
            d[k++] = i;
        }
        k = 0;
        for (int i = n - 1; i >= 0; i--) {
            while (k != 0 && hs[d[k - 1]] > hs[i]) {
                l[d[--k]] = i;
            }
            d[k++] = i;
        }
        //求最大矩形面积
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, (r[i] - l[i] - 1) * hs[i]);
        }
        return ans;
    }
}

