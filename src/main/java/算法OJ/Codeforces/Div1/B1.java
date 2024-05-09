package 算法OJ.Codeforces.Div1;

import java.io.*;
import java.util.*;

public class B1 {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int T = nextInt();
        while (T-- > 0) {
            solve();
        }
    }

    static List<Integer> prime = new ArrayList<>();

    static {
        int N = 200_0000;
        boolean[] isCom = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (!isCom[i]) prime.add(i);
            for (int p : prime) {
                int t = p * i;
                if (t > N) break;
                isCom[t] = true;
                if (i % p == 0) break;
            }
        }
    }

    static void solve() {
        int n = nextInt(), m = nextInt();
        System.out.println(dfs(n, m, 0));
    }

    static long dfs(int n, int m, int i) {
        //
        if (n == 0 || m == 0) return 0;
        long ans = 1;
        if (n == 1) {
            //a只能为0
            int t = prime.get(i);
            for (int k = 1; t <= m; k++, t *= prime.get(i)) {
                ans += dfs(n, m / t, i + 1);
            }
            return ans;
        }
        if (m == 1){
            //b只能为0
            int t = prime.get(i);
            for (int k = 1; t <= n; k++, t *= prime.get(i)) {
                ans += dfs(n / t, m, i + 1);
            }
            return ans;
        }

        //case1:a为0,b为0
        if (prime.get(i) <= n && prime.get(i) <= m) {
            ans += dfs(n, m, i + 1);
        }
        //case2:a为0,b不为0
        int t = prime.get(i);
        for (int k = 1; t <= m; k++, t *= prime.get(i)) {
            ans += dfs(n, m / t, i + 1);
        }
        //case3:a不为0,b为0
        t = prime.get(i);
        for (int k = 1; t <= n; k++, t *= prime.get(i)) {
            ans += dfs(n / t, m, i + 1);
        }
        //case3:ab不为0
        int t1 = prime.get(i);
        for (int k1 = 1; t1 <= n; k1++, t1 *= prime.get(i)) {
            long t2 = (long) t1 * t1;
            for (int k2 = 2 * k1; t2 <= m; k2++, t2 *= prime.get(i)) {
                ans += dfs(n / t1, (int) (m / t2), i + 1);
            }
        }
        return ans;
    }
}
