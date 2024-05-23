package 算法OJ.ICPC.江西2021;

import java.io.*;
import java.util.*;

/**
 怎么WA了,不应该啊
 */
public class G_素因子 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    static List<Integer>[] factor = new List[50001];
    static List<Integer> prime = new ArrayList<>();

    static {
        boolean[] isCom = new boolean[50001];
        for (int i = 2; i <= 50000; i++) {
            if (!isCom[i]) prime.add(i);
            for (int j = 0; j < prime.size(); j++) {
                int t = prime.get(j);
                if (i * t > 50000) break;
                isCom[i * t] = true;
                if (i % t == 0) break;
            }
        }
        Arrays.setAll(factor, i -> new ArrayList<>());
        for (int i = 2; i <= 50000; i++) {
            int x = i;
            for (int j = 0; j < prime.size(); j++) {
                int v = prime.get(j);
                if (v * v > x) break;
                if (x % v == 0) {
                    factor[i].add(v);
                    while (x % v == 0) x /= v;
                }
            }
            if (x != 1) factor[i].add(x);
        }
    }

    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) solve();
        pw.flush();
        pw.close();
    }

    static class Q {
        int l, r, k;

        public Q(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
    }

    static int[] a = new int[50001];
    static int[] block = new int[50001];


    static void solve() throws Exception {
        int n = I(), q = I();
        int size = (int) Math.sqrt(n);
        for (int i = 1; i <= n; i++) {
            a[i] = I();
        }
        Q[] questions = new Q[q];
        for (int i = 0; i < q; i++) {
            int l = I(), r = I();
            questions[i] = new Q(l, r, i);
            block[i] = i / size;
        }
        Arrays.sort(questions, (a, b) -> {
            if (block[a.l] != block[b.l]) return block[a.l] - block[b.l];
            return a.r - b.r;
        });
        int L = 0, R = 0;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            while (L < questions[i].l) Sub(L++);
            while (L > questions[i].l) Add(--L);
            while (R < questions[i].r) Add(++R);
            while (R > questions[i].r) Sub(R--);
            ans[questions[i].k] = max(map);
            //System.out.println("L=" + L + ", R=" + R + ", " + map);
        }
        for (int i = 0; i < q; i++) {
            pw.println(ans[i]);
        }
    }

    static int max(HashMap<Integer, Integer> map) {
        int res = 0;
        for (int cnt : map.values()) {
            res = Math.max(res, cnt);
        }
        return res;
    }

    static HashMap<Integer, Integer> map = new HashMap<>();// p->数量: 区间内能被p整除的数个数

    static void Add(int x) {
        for (int p : factor[a[x]]) {
            addMap(map, p, 1);
        }
    }

    static void Sub(int x) {
        for (int p : factor[a[x]]) {
            addMap(map, p, -1);
        }
    }

    private static void addMap(HashMap<Integer, Integer> map, int key, int x) {
        map.put(key, map.getOrDefault(key, 0) + x);
    }
}
