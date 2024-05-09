package 算法OJ.南昌大学OJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 摸鱼题 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {

        int n = nextInt(), q = nextInt();
        SegmentTree tree = new SegmentTree(n + 1);
        for (int i = 1; i <= n; i++) {
            tree.add(i, i, nextInt());
        }
        for (int i = 0; i < q; i++) {
            int type = nextInt(), x = nextInt(), y = nextInt();
            if (type == 1) {
                int k = nextInt();
                tree.add(x, y, k);
            } else {
                //System.out.println(tree.query(x, y));
            }
        }
    }

    static class SegmentTree {
        static class Node {
            int l, r;
            int sum = 0;

            public Node(int l, int r) {
                this.l = l;
                this.r = r;
            }
        }

        Node[] tree;

        public SegmentTree(int n) {
            tree = new Node[4 * n];
            build(1, 1, n);
        }

        private void build(int u, int l, int r) {

            tree[u] = new Node(l, r);
            if (l == r) return;

            int mid = (l + r) >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }

        public void add(int left, int right, int k) {
            add(1, left, right, k);
        }

        private void add(int u, int left, int right, int k) {
            if (right < tree[u].l || tree[u].r > left) return;
            int mid = (tree[u].l + tree[u].r) >> 1;
            if (left <= mid) add(u << 1, left, right, k);
            if (right > mid) add(u << 1 | 1, left, right, k);
            pushUp(u);
        }


        private void pushUp(int u) {
            tree[u].sum = tree[u << 1].sum + tree[u << 1 | 1].sum;
        }


    }

}
