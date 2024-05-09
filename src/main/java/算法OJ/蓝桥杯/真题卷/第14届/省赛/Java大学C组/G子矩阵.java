package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;


import java.io.*;
import java.util.*;

/**
 已AC
 */
public class G子矩阵 {
    /*
    矩阵的价值定义为矩阵最大值与最小值的乘积
    给定n*m的矩阵,求a*b大小的子矩阵的最大价值
     */

    static int MOD = 998244353;
    static int[][] arr;
    static int n, m;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {
        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        n = nextInt();
        m = nextInt();
        int a = nextInt(), b = nextInt();

        int[][] mat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = nextInt();
            }
        }
        int N = Math.max(n, m);
        int[][] resMin = new int[N][N], resMax = new int[N][N];
        for (int i = 0; i < n; i++) {//对每一行利用单调队列求最大最小值
            resMax[i] = getMax(mat[i], m, b);//求第i行的行块最大值,存储到resMax[i],子矩阵列数为b
            resMin[i] = getMin(mat[i], m, b);
        }
        long res = 0;
        for (int i = b - 1; i < m; i++) {//列,[i-b+1,i]
            int[] colMin = new int[N], colMax = new int[N];
            for (int r = 0; r < n; r++) {//取出第i列元素
                colMin[r] = resMin[r][i];
                colMax[r] = resMax[r][i];
            }
            int[] Min = getMin(colMin, n, a);//对每一列元素求长度为a的子数组最小值数组
            int[] Max = getMax(colMax, n, a);
            for (int r = a - 1; r < n; r++) {
                res = (res + (long) Max[r] * Min[r]) % MOD;//res为a*b的子矩阵最大值与最小值的乘积
            }
        }
        System.out.println(res);
    }

    /**
     对arr求长度为k的子数组最小值数组resMin
     */
    public static int[] getMin(int[] arr, int m, int k) {
        int[] resMin = new int[m];
        int[] que = new int[m];//单调递增队列
        int head = 0, tail = -1;
        for (int i = 0; i < m; i++) {//遍历每一行
            //最小值
            while (head <= tail && arr[que[tail]] >= arr[i]) tail--;//在i前面且比i大的不可能是最小值
            que[++tail] = i;
            if (que[head] < i - k + 1) head++;//检查队列头部(左)是否越出窗口
            resMin[i] = arr[que[head]];
        }
        return resMin;
    }

    public static int[] getMax(int[] arr, int m, int k) {//单调队列求最大值
        int[] resMax = new int[m];
        int[] que = new int[m];//单调队列
        int head = 0, tail = -1;
        for (int i = 0; i < m; i++) {//遍历每一行
            //最大值
            while (head <= tail && arr[que[tail]] <= arr[i]) tail--;
            que[++tail] = i;
            if (que[head] < i - k + 1) head++;
            resMax[i] = arr[que[head]];
        }
        return resMax;
    }


    private static void solve1() {//7/10 3TLE
        n = nextInt();
        m = nextInt();
        int a = nextInt(), b = nextInt();
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = nextInt();
            }
        }
        Queue<int[]> maxQ = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        Queue<int[]> minQ = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        for (int i = 0; i < a; i++) {//初始矩阵
            for (int j = 0; j < b; j++) {
                maxQ.offer(new int[]{arr[i][j], i, j});
                minQ.offer(new int[]{arr[i][j], i, j});
            }
        }
        long ans = 0;
        boolean toRight = true;
        for (int row = 0; row <= n - a; row++) {//Z字形滑动矩阵窗口
            if (toRight) {
                for (int col = 0; col <= m - b; col++) {
                    //取(row,col)~(row+a-1,col+b-1)子矩阵的最大值和最小值
                    int[] result = getResult(maxQ, minQ, row, col, a, b);
                    ans = (ans + (long) result[0] * result[1]) % MOD;
                    //将下一列的值加入
                    offset(maxQ, minQ, row, col, a, b, 1);
                }
                //下移一行
                offset(maxQ, minQ, row, m - b, a, b, 0);
            } else {
                for (int col = m - b; col >= 0; col--) {
                    //取(row,col)~(row+a-1,col+b-1)子矩阵的最大值和最小值
                    int[] result = getResult(maxQ, minQ, row, col, a, b);
                    ans = (ans + (long) result[0] * result[1]) % MOD;
                    //将下一列的值加入
                    offset(maxQ, minQ, row, col, a, b, -1);
                }
                //下移一行
                offset(maxQ, minQ, row, m - b, a, b, 0);
            }
            toRight = !toRight;
        }
        System.out.println(ans);
    }

    /**
     将子矩阵向direction方向偏移一格
     将下一行/列的值加入
     */
    private static void offset(Queue<int[]> maxQ, Queue<int[]> minQ, int row, int col, int a, int b, int direction) {
        if (direction == 1) {//右
            if (col + b < m) {
                for (int i = row; i < row + a; i++) {
                    maxQ.offer(new int[]{arr[i][col + b], i, col + b});
                    minQ.offer(new int[]{arr[i][col + b], i, col + b});
                }
            }
        } else if (direction == -1) {//左
            if (col - 1 >= 0) {
                for (int i = row; i < row + a; i++) {
                    maxQ.offer(new int[]{arr[i][col - 1], i, col - 1});
                    minQ.offer(new int[]{arr[i][col - 1], i, col - 1});
                }
            }
        } else if (direction == 0) {//下
            if (row + a < n) {
                for (int i = col; i < col + b; i++) {
                    maxQ.offer(new int[]{arr[row + a][i], row + a, i});
                    minQ.offer(new int[]{arr[row + a][i], row + a, i});
                }
            }
        }

    }

    private static int[] getResult(Queue<int[]> maxQ, Queue<int[]> minQ, int row, int col, int a, int b) {
        deal(maxQ, row, col, a, b);
        deal(minQ, row, col, a, b);
        int max = maxQ.peek()[0], min = minQ.peek()[0];
        return new int[]{max, min};
    }


    /**
     矩形区域(row,col)~(row+a-1,col+b-1)
     */
    static void deal(Queue<int[]> queue, int row, int col, int a, int b) {
        while (!queue.isEmpty()) {
            int[] pos = queue.peek();
            int i = pos[1], j = pos[2];
            //检查(i,j)是否在矩形区域内
            if (i >= row && i < row + a && j >= col && j < col + b) {
                return;
            }
            queue.poll();//不在区域,抛出
        }
    }
}
