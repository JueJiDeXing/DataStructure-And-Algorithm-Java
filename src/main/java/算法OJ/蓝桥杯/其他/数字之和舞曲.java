package 算法OJ.蓝桥杯.其他;

import java.util.HashMap;

/**
 爆栈了
 */
public class 数字之和舞曲 {
    /*
    d(k):k的数位之和
    S(n): k%23==0 && d(k)=23 && k<pow(10,n)的k个数
    求S(pow(11,12))
     */
    public static void main(String[] args) {
        n = 1;
        for (int i = 0; i < 12; i++) n *= 11;
        dfs(0, 23, 0);
    }

    static long n = 1;
    static HashMap<String, Long> memo = new HashMap<>();

    static long dfs(int i, int d, int y) {
        String key = i + " " + d + " " + y;
        if (memo.containsKey(key)) return memo.get(key);
        if (i == n) {
            long res = y == 0 && d == 0 ? 1 : 0;
            memo.put(key, res);
            return res;
        }
        if (d == 0) {
            //后面全是0
            for (int t = i; t < n; t++) {
                y = (y * 10) % 23;
            }
            long res = y == 0 ? 1 : 0;
            memo.put(key, res);
            return res;
        }
        long res = 0;
        int up = Math.min(9, d);
        for (int k = 0; k <= up; k++) {
            res = (res + dfs(i + 1, d - k, (y * 10 + k) % 23)) % 10_0000_0000;
        }
        memo.put(key, res);
        return res;
    }
}
