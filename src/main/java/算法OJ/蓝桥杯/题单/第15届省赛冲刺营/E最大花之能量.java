package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.Scanner;
/**
 已AC
 */
public class E最大花之能量 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        int[] dp = A.clone();//dp[i]:以A[i]结尾的最大递增子序列的和
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {// ' ???A[j] ' 的段 能与A[i]拼接
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }

}
