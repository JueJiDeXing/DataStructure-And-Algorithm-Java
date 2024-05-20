package 算法.暴力优化.莫队;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class 牛客_数列互质 {
    /*
    长度为n的数列, 每次询问[l,r]上有多少数的出现次数与k互质
     */

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int n, m;
    static int[] a;

    static class Q {
        int l, r, k, id;

        public Q(int l, int r, int k, int id) {
            this.l = l;
            this.r = r;
            this.k = k;
            this.id = id;
        }
    }

    public static void main(String... args) throws Exception {
        n = I();
        m = I();
        a = new int[n + 1];
        int size = (int) Math.sqrt(n);
        int[] block = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = I();
            block[i] = i / size;
        }
        Q[] question = new Q[m];
        for (int i = 0; i < m; i++) {
            int l = I(), r = I(), k = I();
            question[i] = new Q(l, r, k, i);
        }
        Arrays.sort(question, (q1, q2) -> {
            if (block[q1.l] == block[q2.l]) {
                return q1.r - q2.r;
            }
            return block[q1.l] - block[q2.l];
        });
        int[] ans = new int[m];
        int l = 1, r = 0;
        cnt = new HashMap<>();
        for (int i = 0; i < m; i++) {
            while (l > question[i].l) Add(--l);
            while (r < question[i].r) Add(++r);
            while (l < question[i].l) Sub(l++);
            while (r > question[i].r) Sub(r--);
            ans[question[i].id] = cal(question[i].k);
        }
        for (int i = 0; i < m; i++) {
            pw.println(ans[i]);
        }
        pw.flush();
        pw.close();

    }

    static int cal(int k) {
        int res = 0;
        for (int t : cnt.values()) {
            if (gcd(t, k) == 1) {
                res++;
            }
        }
        return res;

    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static HashMap<Integer, Integer> cnt;

    static void Add(int x) {
        cnt.put(a[x], cnt.getOrDefault(a[x], 0) + 1);
    }

    static void Sub(int x) {
        cnt.put(a[x], cnt.get(a[x]) - 1);
        if (cnt.get(a[x]) == 0) {
            cnt.remove(a[x]);
        }
    }
}
