package 算法.字符串.后缀自动机;

import java.util.Arrays;

public class SAM {

    static int N = 2000010;

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
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - '0';
            if (tr[p][u] == 0) {
                tr[p][u] = ++idx;
                fa[idx] = p;
                ch[idx] = u;
            }
            p = tr[p][u];
        }
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

    int extend(int c, int last) {
        int p = last, np = ++tot;
        node[np].len = node[p].len + 1;
        for (; p != 0 && node[p].ch[c] == 0; p = node[p].fa) node[p].ch[c] = np;
        if (p == 0) node[np].fa = 1;
        else {
            int q = node[p].ch[c];
            if (node[q].len == node[p].len + 1) node[np].fa = q;
            else {
                int nq = ++tot;
                node[nq] = node[q];
                node[nq].len = node[p].len + 1;
                node[q].fa = node[np].fa = nq;
                for (; p != 0 && node[p].ch[c] == q; p = node[p].fa) node[p].ch[c] = nq;
            }
        }
        return np;
    }

}
