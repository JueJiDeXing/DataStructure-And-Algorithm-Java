package 算法OJ.蓝桥杯.算法赛.小白入门赛.第6场;

import java.io.*;

/**
 已AC
 */
public class _6计算方程 {
    /*
    t次询问,每次给定一组k,m
    求最小的x,其中x满足 sqrt(x) + floor( log_k{x} ) > m
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     二分枚举x即可
     */
    public static void main(String[] args) {
        int t = Int();
        for (int i = 0; i < t; i++) {
            int k = Int(), m = Int();
            System.out.println(solve(k, m));
        }
    }

    static int solve(int k, int m) {
        int left = 1, right = m * m + 1;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (check(k, m, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    static boolean check(int k, int m, int x) {
        return Math.sqrt(x) + Math.floor(Math.log(x) / Math.log(k)) > m;
    }
}
