package 算法OJ.牛客.练习赛124_PLUS;

import java.util.Scanner;
import java.util.*;

/**
 已AC
 */
public class D_原始部落 {
    static long INF = (long) 1e18;
    static int n, m, a, b;
    static boolean flag;
    static long ans = INF;
    static LinkedList<Integer> ta, tb;

    public static void main(String[] args) {
        scanAndInit();

        dfs(0, n, m, 0, false);
        dfs(0, n, m, 0, true);

        System.out.println(ans + " " + (ta.size() - 1) + " " + (tb.size() - 1));
        for (int t : ta) System.out.print(t + " ");
        System.out.println();
        for (int t : tb) System.out.print(t + " ");
    }

    private static void scanAndInit() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        flag = true;
        ans = INF;
        ta = new LinkedList<>();
        tb = new LinkedList<>();
    }

    /**
     长度为n,m,当前最大可表示数为now,代价为sum
     */
    static void dfs(int now, int n, int m, long sum, boolean option) {
        if (sum > ans || !flag) return;
        if (n == 0 && m == 0) {
            if (option) flag = false;
            else ans = sum;//取min,但前面sum>ans时已return
            return;
        }
        if (0 < n && n <= now + 1) {
            if (option & flag) ta.push(n);
            dfs(now + n, 0, m, sum, option);
            if (option & flag) ta.pop();
        }
        if (0 < m && m <= now + 1) {
            if (option & flag) tb.push(m);
            dfs(now + m, n, 0, sum, option);
            if (option & flag) tb.pop();
        }
        if (0 < n && n > now + 1) {
            if (option & flag) ta.push(now + 1);
            dfs(now << 1 | 1, n - now - 1, m, sum + a, option);
            if (option & flag) ta.pop();
        }
        if (0 < m && m > now + 1) {
            if (option & flag) tb.push(now + 1);
            dfs(now << 1 | 1, n, m - now - 1, sum + b, option);
            if (option & flag) tb.pop();
        }
    }
}
