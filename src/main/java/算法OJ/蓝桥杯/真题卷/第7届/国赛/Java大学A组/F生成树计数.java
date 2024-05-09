package 算法OJ.蓝桥杯.真题卷.第7届.国赛.Java大学A组;

import java.util.*;

public class F生成树计数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            char[] s = sc.next().toCharArray();
            for (int j = 0; j < m; j++) {
                if (s[j] == 'E') {
                    map[i][j] = true;
                    count++;

                }
            }
        }
        int ans = 0;
        for (int firstI = 0; firstI < n; firstI++) {
            for (int firstJ = 0; firstJ < m; firstJ++) {//枚举起点,终点不能在起点的左上方
                if (!map[firstI][firstJ]) continue;
                boolean[][] isVisited = new boolean[n][m];
                isVisited[firstI][firstJ] = true;
                ans += dfs(firstI, firstJ, firstI, firstJ, count - 1, isVisited);
            }
        }
        System.out.println(ans);
    }

    static int dfs(int si, int sj, int i, int j, int remain, boolean[][] isVisited) {
        if (remain == 0) {
            int r = i < si || (i == si && j <= sj) ? 0 : 1;
            System.out.println("(" + si + "," + sj + ")" + "->" + "(" + i + "," + j + ")" + " " + r);
            return r;//终点(i,j)不能在起点(si,sj)的左上角,因为(i,j)作为起点时已经计算过
        }
        int ans = 0;
        for (int[] d : direction) {
            int ni = i + d[0], nj = j + d[1];
            if (!isValid(ni, nj)) continue;
            if (!map[ni][nj] || isVisited[ni][nj]) continue;
            isVisited[ni][nj] = true;
            ans += dfs(si, sj, ni, nj, remain - 1, isVisited);
            isVisited[ni][nj] = false;
        }
        return ans;
    }

    static boolean[][] map;
    static int n, m;

    static boolean isValid(int i, int j) {
        return 0 <= i && i < n && 0 <= j && j < m;
    }

    static int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

}
