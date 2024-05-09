package 算法OJ.蓝桥杯.其他;

import java.util.*;
/**
 已AC
 */
public class 学习小组技术培训 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int left = 0, right = Math.max(m, (n + m) / k) + 1;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (check(mid, n, m, k)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(left);
    }

    static boolean check(int x, int n, int m, int k) {
        if (m < x) return false;
        return n >= x * (k - 1);
    }
}
