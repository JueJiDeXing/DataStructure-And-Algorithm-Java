package 算法OJ.蓝桥杯.真题卷.第6届.省赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class C九数分三组 {
    public static void main(String[] args) {
        List<Integer> ans = new ArrayList<>();
        for (int A = 100; A < 333; A++) {
            if (check(A)) {
                ans.add(A);
            }
        }
        System.out.println(ans);//192, 219, 273, 327
    }

    static boolean check(int A) {
        int B = 2 * A, C = 3 * A;
        if (C > 1000) return false;
        boolean[] count = new boolean[10];
        while (A > 0) {
            int t = A % 10;
            if (t == 0 || count[t]) return false;
            count[t] = true;
            A /= 10;
        }
        while (B > 0) {
            int t = B % 10;
            if (t == 0 || count[t]) return false;
            count[t] = true;
            B /= 10;
        }
        while (C > 0) {
            int t = C % 10;
            if (t == 0 || count[t]) return false;
            count[t] = true;
            C /= 10;
        }
        return true;
    }
}
