package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;

import java.util.*;

/**
 已AC
 */
public class C三国游戏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n], B = new int[n], C = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        for (int i = 0; i < n; i++) B[i] = sc.nextInt();
        for (int i = 0; i < n; i++) C[i] = sc.nextInt();

        int ans = 0;
        //枚举赢的国家
        int happen = getHappen(n, A, B, C);
        ans = Math.max(ans, happen);
        happen = getHappen(n, B, A, C);
        ans = Math.max(ans, happen);
        happen = getHappen(n, C, A, B);
        ans = Math.max(ans, happen);
        System.out.println(ans == 0 ? -1 : ans);
    }

    private static int getHappen(int n, int[] A, int[] B, int[] C) {
        long[] list = new long[n];
        for (int i = 0; i < n; i++) {
            list[i] = A[i] - B[i] - C[i];//A国赢
        }
        Arrays.sort(list);
        long curr = list[n - 1];
        int happen = 1;
        if (curr <= 0) return 0;
        for (int i = n - 2; i >= 0; i--) {
            if (curr + list[i] <= 0) break;
            curr += list[i];
            happen++;
        }
        return happen;
    }
}
