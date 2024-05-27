package 算法OJ.ICPC.江西2021;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 已AC(莫队)
 */
public class G素因子 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int N = (int) 5e4;//数组长5e4

    static List<Integer>[] factor = new List[N + 1];

    static class Q {
        int l, r, k;

        public Q(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }

    static int[] a = new int[N + 1];
    static Q[] questions = new Q[N + 1];

    static {
        Arrays.setAll(factor, k -> new ArrayList<>());
        Arrays.setAll(questions, k -> new Q(0, 0, 0));
    }

    static int n;

    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) solve();
        pw.flush();
    }


    static void init() {
        for (int i = 1; i <= n; i++) {
            factor[i].clear();
            for (int j = 2; j <= a[i] / j; j++) {
                if (a[i] % j == 0) {
                    while (a[i] % j == 0) a[i] /= j;
                    factor[i].add(j);
                }
            }
            if (a[i] != 1) factor[i].add(a[i]);
        }
    }


    static void solve() throws Exception {
        n = I();
        int q = I();
        int size = (int) Math.sqrt(n);
        for (int i = 1; i <= n; i++) a[i] = I();
        init();
        for (int i = 0; i < q; i++) {
            questions[i].l = I();
            questions[i].r = I();
            questions[i].k = i;
        }
        Arrays.sort(questions, 0, q, (a, b) -> {
            if (a.l / size != b.l / size) return a.l - b.l;
            return ((a.l / size) & 1) == 0 ? (a.r - b.r) : (b.r - a.r);
        });
        int L = 1, R = 0;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            while (L > questions[i].l) Add(--L);
            while (R < questions[i].r) Add(++R);
            while (L < questions[i].l) Sub(L++);
            while (R > questions[i].r) Sub(R--);
            ans[questions[i].k] = res;
        }
        for (int i = 0; i < q; i++) {
            pw.println(ans[i]);
        }
        while (L <= R) Sub(L++);
    }

    static int res = 0;
    static int[] cnt = new int[(int) 1e6 + 1];//a[i]=1e6
    static int[] num = new int[(int) 1e6 + 1];

    static void Add(int x) {
        List<Integer> f = factor[x];
        for (int i = 0; i < f.size(); i++) {
            int p = f.get(i);
            cnt[p]++;
            num[cnt[p]]++;
            if (num[cnt[p]] == 1) res++;// eg: cnt=1,2,2,3 -> 1,2,2,4
        }
    }

    static void Sub(int x) {
        List<Integer> f = factor[x];
        for (int i = 0; i < f.size(); i++) {
            int p = f.get(i);
            num[cnt[p]]--;
            if (num[cnt[p]] == 0) res--;// eg: cnt=1,2,2,3 -> 1,2,2
            cnt[p]--;
        }
    }
}
