package 算法OJ.蓝桥杯.算法赛.小白入门赛.第1场;

import java.util.*;

/**
 已AC
 */
public class _1蘑菇炸弹 {
    /*
    A[i] >= A[i - 1] + A[i + 1] 则i为高爆蘑菇
    求数量
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (A[i] >= A[i - 1] + A[i + 1]) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}
