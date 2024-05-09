package 算法OJ.蓝桥杯.算法赛.小白入门赛.第7场;

import java.io.*;
import java.util.*;

/**
 AC
 */
public class _6矩阵X {
    /*
    f(D`)为子矩阵D`的和,g(D`)为子矩阵最大值与最小值的差
    求矩阵D的最大f(D`)*g(D`)为多少
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = nextInt(), m = nextInt();
        int a = nextInt(), b = nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = nextInt();
            }
        }
        long[][] prefix = new long[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int col = m - b + 1;
        int[][] rowMax = new int[n][col], rowMin = new int[n][col];
        for (int i = 0; i < n; i++) {// 求matrix每一行的长度为b的最大最小值数组
            rowMax[i] = shiftWindow(matrix[i], m, b, (Integer x, Integer y) -> x - y);
            rowMin[i] = shiftWindow(matrix[i], m, b, (Integer x, Integer y) -> y - x);
        }
        int row = n - a + 1;
        int[][] Max = new int[col][row], Min = new int[col][row];
        int[][] GD = new int[row][col];
        for (int j = 0; j < col; j++) {// 求rowMax的每一列的长度为a的最大值数组, 求rowMiin的每一列的长度为a的最小值数组
            int[] temp1 = new int[n], temp2 = new int[n];
            for (int i = 0; i < n; i++) {
                temp1[i] = rowMax[i][j];
                temp2[i] = rowMin[i][j];
            }
            Max[j] = shiftWindow(temp1, n, a, (Integer x, Integer y) -> x - y);
            Min[j] = shiftWindow(temp2, n, a, (Integer x, Integer y) -> y - x);
            for (int i = 0; i < row; i++) {
                GD[i][j] = Max[j][i] - Min[j][i];//g(D)为极差
            }
        }
        long ans = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                long FD = prefix[i + a][j + b] - prefix[i + a][j] - prefix[i][j + b] + prefix[i][j];
                ans = Math.max(ans, FD * GD[i][j]);
            }
        }
        System.out.println(ans);
    }

    /**
     滑动窗口,求数组a的长度为k的所有子数组的最小值数组

     @param a 源数组
     @param n 数组长度
     @param k 子数组长度
     @param c 比较器,(x,y)->x-y为求最大值,y-x为求最小值
     */
    public static int[] shiftWindow(int[] a, int n, int k, Comparator<Integer> c) {
        int len = n - k + 1;
        int[] b = new int[len];
        Deque<Integer> de = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!de.isEmpty() && c.compare(a[i], a[de.peekLast()]) > 0) {
                de.pollLast();
            }
            de.offer(i);
            if (i >= de.peekFirst() + k) de.pollFirst();
            if (i >= k - 1) b[i - k + 1] = a[de.peekFirst()];
        }
        return b;
    }

}
