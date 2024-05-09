package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.Arrays;
import java.util.Scanner;

/**
 已AC
 */
public class H修路 {
    static double inf = Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), d = sc.nextInt();
        int[] A = new int[n + 1], B = new int[m + 1];
        for (int i = 1; i <= n; i++) A[i] = sc.nextInt();
        for (int i = 1; i <= m; i++) B[i] = sc.nextInt();
        Arrays.sort(A);
        Arrays.sort(B);
        /*
        dp[i][j]:已走过A[0...i)和B[0...j)的最短路径长  [0]在A,[1]在B
         */
        double[][][] dp = new double[n + 1][m + 1][2];

        for (int i = 1; i <= n; i++) {
            dp[i][0][1] = inf;
            dp[i][0][0] = A[i];
        }
        if (m > 0) {
            dp[0][1][0] = inf;
            dp[0][1][1] = Math.hypot(d, B[1]);
            dp[0][0][1] = d;
        }
        for (int i = 2; i <= m; i++) {
            dp[0][i][0] = inf;
            dp[0][i][1] = dp[0][i - 1][1] + B[i] - B[i - 1];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j][0] = Math.min(dp[i - 1][j][0] + A[i] - A[i - 1], dp[i - 1][j][1] + Math.hypot(d, A[i] - B[j]));
                dp[i][j][1] = Math.min(dp[i][j - 1][1] + B[j] - B[j - 1], dp[i][j - 1][0] + Math.hypot(d, A[i] - B[j]));
            }
        }
        System.out.printf("%.2f", Math.min(dp[n][m][0], dp[n][m][1]));
        sc.close();
    }


}
