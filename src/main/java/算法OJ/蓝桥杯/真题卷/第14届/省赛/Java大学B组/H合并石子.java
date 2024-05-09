package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 做不来,看不懂题解
 */
public class H合并石子 {
    static int N = 301;

    public static void main(String[] args) {
        //令f[l][r][c]表示[l,r]上合并颜色c的最小代价
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] stone = new int[N], preSum = new int[N];
        for (int i = 1; i <= n; i++) {
            stone[i] = sc.nextInt();
            preSum[i] = preSum[i - 1] + stone[i]; //前缀和
        }
        int[][][] f = new int[N][N][3];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                Arrays.fill(f[i][j], Integer.MAX_VALUE);
            }
        }
        int[] color = new int[N];
        for (int i = 1; i <= n; i++) {
            color[i] = sc.nextInt();
            f[i][i][color[i]] = 0;
        }
        int[][] num = new int[N][N];
        int[][] g = new int[N][N];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                num[i][j] = j - i + 1; //区间最少堆数初始化为区间长度
            }
        }
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l <= n - len + 1; l++) {
                int r = l + len - 1;   //根据区间长度和左端点确定右端点
                int val = preSum[r] - preSum[l - 1];
                for (int m = l; m < r; m++) {
                    for (int i = 0; i < 3; i++) {
                        int k = (i + 1) % 3; //由颜色i合成颜色k
                        if (f[l][m][i] != Integer.MAX_VALUE && f[m + 1][r][i] != Integer.MAX_VALUE) { //否则说明不存在满足条件的堆
                            f[l][r][k] = Math.min(f[l][r][k], f[l][m][i] + f[m + 1][r][i] + val);
                        }
                    }
                }
                if (f[l][r][0] != Integer.MAX_VALUE || f[l][r][1] != Integer.MAX_VALUE || f[l][r][2] != Integer.MAX_VALUE) {
                    num[l][r] = 1;
                    g[l][r] = Math.min(Math.min(f[l][r][0], f[l][r][1]), f[l][r][2]); //g[l][r]是三个颜色中的最小值
                }
            }
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= k; i++) {
                for (int j = k + 1; j <= n; j++) {
                    if (num[i][j] > num[i][k] + num[k + 1][j]) {
                        num[i][j] = num[i][k] + num[k + 1][j];
                        g[i][j] = g[i][k] + g[k + 1][j];
                    } else if (num[i][j] == num[i][k] + num[k + 1][j]) {
                        g[i][j] = Math.min(g[i][j], g[i][k] + g[k + 1][j]);
                    }
                }
            }
        }
        System.out.println(num[1][n] + " " + g[1][n]);
    }

    /**
     6/20 超时14个
     * @param args
     */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        minHeap = N;
        int[] stone = new int[N], color = new int[N];
        for (int i = 0; i < N; i++) {
            stone[i] = sc.nextInt();
            minCost += stone[i] * N;
        }
        for (int i = 0; i < N; i++) {
            color[i] = sc.nextInt();
        }
        dfs(stone, color, 0);
        System.out.println(minHeap + " " + minCost);
    }

    static int minHeap, minCost = 0;

    private static void dfs(int[] stone, int[] color, int cost) {
        int n = stone.length;
        //查找所有的能合并的项
        List<Integer> combineList = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (color[i] == color[i + 1]) {
                combineList.add(i);
            }
        }
        if (combineList.isEmpty()) {
            if (minHeap > n) {
                minHeap = n;
                minCost = cost;
            } else if (minHeap == n) {
                minCost = Math.min(minCost, cost);
            }
            return;
        }
        //选择其中一项进行合并
        for (int com : combineList) {
            int[] newStone = new int[n - 1];
            int[] newColor = new int[n - 1];
            combine(stone, newStone, com, false);
            combine(color, newColor, com, true);
            dfs(newStone, newColor, cost + (stone[com] + stone[com + 1]));
        }
    }

    private static void combine(int[] arr, int[] newArr, int idx, boolean isColor) {
        //合并idx和idx+1位置,其余元素相对位置不动
        int n = arr.length;
        System.arraycopy(arr, 0, newArr, 0, idx);
        if (isColor) {
            newArr[idx] = (arr[idx] + 1) % 3;
        } else {
            newArr[idx] = arr[idx] + arr[idx + 1];
        }
        System.arraycopy(arr, idx + 1 + 1, newArr, idx + 1, n - 1 - (idx + 1));
    }
}
