package 基础数据结构算法.栈.单调栈应用;

import java.io.*;

public class 全1子矩阵个数 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    /**
     输入n*n的01矩阵, 求全0组成的子矩阵个数
     */
    public static void main(String[] args) {
        int n = nextInt();
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = 1 - nextInt();
                if (map[i][j] == 1) {
                    map[i][j] += map[i][j - 1];//上方1个数
                }
            }
        }
        int ans = 0;
        int[] up = new int[n + 1];
        int[] down = new int[n + 1];
        int[] stack = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            int stackLen = 0;
            for (int i = 1; i <= n; i++) {
                if (map[i][j] != 0) {
                    up[i] = 1;
                    while (stackLen != 0 && map[i][j] <= map[stack[stackLen]][j]) {
                        up[i] += up[stack[stackLen]];
                        --stackLen;
                    }
                    stack[++stackLen] = i;
                } else {
                    stackLen = 0;
                    up[i] = 0;
                }
            }
            stackLen = 0;
            for (int i = n; i >= 1; i--) {
                if (map[i][j] != 0) {
                    down[i] = 1;
                    while (stackLen != 0 && map[i][j] < map[stack[stackLen]][j]) {
                        down[i] += down[stack[stackLen]];
                        --stackLen;
                    }
                    stack[++stackLen] = i;
                } else {
                    stackLen = 0;
                    down[i] = 0;
                }
                ans += up[i] * down[i] * map[i][j];
            }
        }
        System.out.println(ans);
    }
}
