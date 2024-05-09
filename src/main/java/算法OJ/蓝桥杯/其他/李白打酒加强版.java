package 算法OJ.蓝桥杯.其他;

import java.util.*;
/**
 已AC
 */
public class 李白打酒加强版 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        memo = new HashMap<>();
        System.out.println(dfs(2, n, m));
    }

    static int MOD = 10_0000_0007;
    static HashMap<String, Long> memo;

    static long dfs(int x, int n, int m) {
        String key = x + " " + n + " " + m;
        if (memo.containsKey(key)) return memo.get(key);
        if (n == 0 && m == 0) {
            long ans = x == 0 ? 1 : 0;
            memo.put(key, ans);
            return ans;
        }
        long ans = 0;
        if (n > 0 && m > 0 && x * 2 <= m) {
            ans = (ans + dfs(x * 2, n - 1, m)) % MOD;
        }
        if (m > 0 && x > 0) {
            ans = (ans + dfs(x - 1, n, m - 1)) % MOD;
        }
        memo.put(key, ans);
        return ans;
    }
}
