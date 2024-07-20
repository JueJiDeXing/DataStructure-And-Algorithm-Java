package 算法.暴力优化.莫队;

import 算法.暴力优化.莫队.模版.Q;

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

    static int n, q;
    static int[] a;


    public static void main(String... args) throws Exception {
        n = I();
        q = I();
        a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = I();
        Q[] questions = new Q[q];
        for (int i = 0; i < q; i++) {
            int l = I(), r = I(), k = I();
            questions[i] = new Q(l, r, i, k);
        }
        int size = (int) Math.sqrt(n);
        Arrays.sort(questions, 0, q, (a, b) -> {
            if (a.l / size != b.l / size) return a.l - b.l;
            return ((a.l / size) & 1) == 0 ? (a.r - b.r) : (b.r - a.r);
        });
        int[] ans = new int[q];
        int l = 1, r = 0;
        cnt = new HashMap<>();
        for (int i = 0; i < q; i++) {
            while (l > questions[i].l) Add(--l);
            while (r < questions[i].r) Add(++r);
            while (l < questions[i].l) Sub(l++);
            while (r > questions[i].r) Sub(r--);
            int k = (int) questions[i].object;
            ans[questions[i].id] = cal(k);
        }
        for (int i = 0; i < q; i++) {
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
