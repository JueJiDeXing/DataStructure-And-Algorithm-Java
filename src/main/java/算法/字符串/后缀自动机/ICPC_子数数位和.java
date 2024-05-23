package 算法.字符串.后缀自动机;

import java.util.Arrays;
import java.util.Scanner;

public class ICPC_子数数位和 {

    static int N = 2000010;
    static int MOD = 10_0000_0007;
    static SAM sam = new SAM();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            sam.insert(sc.next());
        }
        sam.build();
        System.out.println(solve());
    }

    static long[] f = new long[N], g = new long[N];
    static long[] din = new long[N];

    static long solve() {
        long res = 0;
        for (int i = 1; i <= sam.tot; i++) {
            for (int j = 0; j < 10; j++) {
                din[sam.node[i].ch[j]]++;
            }
        }
        g[1] = 1;
        int tt = -1, hh = 0;
        sam.q[++tt] = 1;
        while (hh <= tt) {
            int t = sam.q[hh++];
            res = (res + f[t]) % MOD;
            for (int j = 0; j < 10; j++) {
                int v = sam.node[t].ch[j];
                if (v == 0) continue;
                g[v] = (g[v] + g[t]) % MOD;
                f[v] = (f[v] + 10 * f[t] % MOD + g[t] * j % MOD) % MOD;
                if (--din[v] == 0) sam.q[++tt] = v;
            }
        }
        return res;
    }

    static class SAM {
        static class Node {
            int fa = 0, len = 0;
            int[] ch = new int[10];
        }

        public SAM() {
            node = new Node[N];
            Arrays.setAll(node, k -> new Node());
        }

        Node[] node;
        int[] q = new int[N];
        int[][] tr = new int[N][26];
        int idx = 1;
        int tot = 1;
        int[] pos = new int[N];
        int[] ch = new int[N], fa = new int[N];

        void insert(String s) {
            int p = 1;
            for (char c : s.toCharArray()) {
                int u = c - '0';
                if (tr[p][u] == 0) {
                    tr[p][u] = ++idx;
                    fa[idx] = p;
                    ch[idx] = u;
                }
                p = tr[p][u];
            }
        }

        int extend(int c, int last) {
            int p = last, np = ++tot;
            node[np].len = node[p].len + 1;
            while (p != 0 && node[p].ch[c] == 0) {
                node[p].ch[c] = np;
                p = node[p].fa;
            }
            if (p == 0) node[np].fa = 1;
            else {
                int q = node[p].ch[c];
                if (node[q].len == node[p].len + 1) node[np].fa = q;
                else {
                    int nq = ++tot;
                    node[nq].ch = node[q].ch.clone();
                    node[nq].fa = node[q].fa;
                    node[nq].len = node[p].len + 1;
                    node[q].fa = node[np].fa = nq;
                    while (p != 0 && node[p].ch[c] == q) {
                        node[p].ch[c] = nq;
                        p = node[p].fa;
                    }
                }
            }
            return np;
        }

        void build() {
            int tt = -1, hh = 0;
            for (int i = 0; i < 10; i++)
                if (tr[1][i] != 0) q[++tt] = tr[1][i];
            pos[1] = 1;
            while (hh <= tt) {
                int t = q[hh++];
                pos[t] = extend(ch[t], pos[fa[t]]);
                for (int i = 0; i < 10; i++)
                    if (tr[t][i] != 0) q[++tt] = tr[t][i];
            }
        }
    }


}



