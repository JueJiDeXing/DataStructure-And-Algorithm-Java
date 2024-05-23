package 算法OJ.ICPC.江西2020;

import java.io.*;
/**
 已AC(线段树基操)
 */
public class H序列 {
    /*
    给定长度为n的数组a
    操作1: x,y, 将a[x]修改为y
    操作2: x, 查询包含a[x]的子数组个数, 子数组需满足a[x]为子数组最小值
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    /**
     线段树 维护区间最小值
     操作1: 单点修改
     操作2: 查询[1,x]中第一个比x小的数位置,查询[x,n]中第一个比x小的数位置
     */
    public static void main(String[] args) throws IOException {
        int n = I(), q = I();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = I();
        Tree tree = new Tree(n, a);//线段树区间最小值
        for (int i = 0; i < q; i++) {
            int type = I();
            if (type == 1) {
                int x = I(), y = I();
                tree.update(1, 1, n, x, y);
                a[x] = y;
            } else {
                int x = I();
                int left = tree.queryLeft(x);
                int right = tree.queryRight(x);
                pw.println((x - left + 1L) * (right - x + 1L));
            }
        }
        pw.flush();
        pw.close();
    }

    static class Tree {
        int[] tree = new int[100010 << 2];
        int n;
        int[] a;

        public Tree(int n, int[] a) {
            this.a = a;
            this.n = n;
            build(1, 1, n);
        }

        void build(int rt, int l, int r) {
            if (l == r) {
                tree[rt] = a[l];
                return;
            }
            int mid = (l + r) >>> 1;
            build(rt << 1, l, mid);
            build(rt << 1 | 1, mid + 1, r);
            tree[rt] = Math.min(tree[rt << 1], tree[rt << 1 | 1]);
        }

        /**
         单点修改
         */
        void update(int rt, int l, int r, int x, int y) {
            if (l == r) {
                tree[rt] = y;
                return;
            }
            int mid = (l + r) >>> 1;
            if (x <= mid) {
                update(rt << 1, l, mid, x, y);
            } else {
                update(rt << 1 | 1, mid + 1, r, x, y);
            }
            tree[rt] = Math.min(tree[rt << 1], tree[rt << 1 | 1]);
        }

        /**
         区间最小值查询
         */
        int query(int rt, int l, int r, int L, int R) {
            if (L <= l && r <= R) {
                return tree[rt];
            }
            int mid = (l + r) >>> 1;
            int ans = (int) (1e9 + 10);
            if (L <= mid) ans = Math.min(ans, query(rt << 1, l, mid, L, R));
            if (R > mid) ans = Math.min(ans, query(rt << 1 | 1, mid + 1, r, L, R));
            return ans;
        }


        public int queryLeft(int x) {
            int l = 0, r = x;//r:大于等于x
            while (l + 1 != r) {
                int mid = (l + r) >>> 1;
                if (query(1, 1, n, mid, x) >= a[x]) r = mid;
                else l = mid;
            }
            return r;
        }

        public int queryRight(int x) {
            int l = x, r = n + 1;//l:大于等于x
            while (l + 1 != r) {
                int mid = (l + r) >> 1;
                if (query(1, 1, n, x, mid) >= a[x]) l = mid;
                else r = mid;
            }
            return l;
        }
    }
}
