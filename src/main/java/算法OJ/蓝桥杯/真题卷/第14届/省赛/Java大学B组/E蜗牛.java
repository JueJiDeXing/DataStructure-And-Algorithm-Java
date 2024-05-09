package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class E蜗牛 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] x = new int[n + 1], a = new int[n + 1], b = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            x[i] = sc.nextInt();
        }
        for (int i = 1; i < n; i++) {
            a[i] = sc.nextInt();
            b[i + 1] = sc.nextInt();
        }
        /*
        dp[i][0]:到达杆i底部的最短时间
        dp[i][1]:到达杆i传送门的最短时间
        则ans=dp[n][0]
        到达杆i底部:从杆i-1底部通过x轴走; 从杆i-1的传送门处穿过传送门(不耗时),从传送门终点向下走
            dp[i][0] = min(dp[i - 1][1] + b[i] / 1.3, dp[i - 1][0] + x[i] - x[i - 1]);
        到达杆i传送门:从杆i-1底部通过x轴走,然后向上爬; 从杆i-1的传送门处穿越传送门(不耗时), 从传送门终点爬向i的传送门(需要判断方向)
            if (a[i] > b[i]) {
                dp[i][1] = Math.min(dp[i - 1][0] + x[i] - x[i - 1] + a[i] / 0.7, dp[i - 1][1] + (a[i] - b[i]) / 0.7);
            } else {
                dp[i][1] = Math.min(dp[i - 1][0] + x[i] - x[i - 1] + a[i] / 0.7, dp[i - 1][1] + (b[i] - a[i]) / 1.3);
            }
         */
        double[][] dp = new double[n + 1][2];
        dp[1][0] = x[1];
        dp[1][1] = x[1] + a[1] / 0.7;
        for (int i = 2; i <= n; i++) {
            double byX = dp[i - 1][0] + x[i] - x[i - 1];
            if (a[i] > b[i]) {
                dp[i][1] = Math.min(byX + a[i] / 0.7, dp[i - 1][1] + (a[i] - b[i]) / 0.7);
            } else {
                dp[i][1] = Math.min(byX + a[i] / 0.7, dp[i - 1][1] + (b[i] - a[i]) / 1.3);
            }
            dp[i][0] = Math.min(dp[i - 1][1] + b[i] / 1.3, byX);
        }
        System.out.printf("%.2f", dp[n][0]);
        sc.close();
    }
}
