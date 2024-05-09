package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class C货物摆放 {
    /*
    n箱1*1的正方体货物,摆放成L*W*H的长方体
    问n为2021041820210418时有多少种方案
    方案:数对(L,W,H)有多少对,顺序不同算不同的方案

    n=4时:
    (1,1,4),(1,2,2),(1,4,1),(2,1,2),(2,2,1),(4,1,1)共6种
     */
    public static void main(String[] args) {
        long n = 2021041820210418L;
        for (int i = 2; i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                    p.add(i);
                }
            }
        }
        System.out.println(p);

        dfs(0, 1, 1, 1);
        System.out.println(set.size());//2430
    }

    static HashSet<String> set = new HashSet<>();
    static List<Integer> p = new ArrayList<>();
    static long n = 2021041820210418L;

    static void dfs(int i, long L, long W, long H) {
        if (i == p.size()) {
            if (L * W * H != n) return;
            set.add(L + "" + W + H);
            return;
        }
        dfs(i + 1, L * p.get(i), W, H);
        dfs(i + 1, L, W * p.get(i), H);
        dfs(i + 1, L, W, H * p.get(i));
    }

}
