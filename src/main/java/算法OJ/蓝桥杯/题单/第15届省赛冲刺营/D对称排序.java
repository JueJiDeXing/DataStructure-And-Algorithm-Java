package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.*;
/**
 已AC
 */
public class D对称排序 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] A = new int[n];

        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        int min = Math.min(A[0], A[n - 1]);
        int max = Math.max(A[0], A[n - 1]);
        for (int i = 1; i < n / 2; i++) {
            int mi = Math.min(A[i], A[n - 1 - i]);
            int ma = Math.max(A[i], A[n - 1 - i]);
            if (!(min <= mi && ma <= max)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }
}
