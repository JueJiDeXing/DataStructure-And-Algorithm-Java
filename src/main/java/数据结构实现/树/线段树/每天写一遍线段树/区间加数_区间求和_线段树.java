package 数据结构实现.树.线段树.每天写一遍线段树;

import java.io.*;

public class 区间加数_区间求和_线段树 {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int n, m;

    public static void main(String[] args) {
        n = nextInt();
        m = nextInt();
        int[] origin = new int[n];
        for (int i = 0; i < n; i++) origin[i] = nextInt();

        SegmentTree tree = new SegmentTree(origin);
        for (int i = 0; i < m; i++) {
            int type = nextInt();
            int left = nextInt() - 1, right = nextInt() - 1;// 下标从1开始
            if (type == 1) {
                int val = nextInt();
                tree.add(left, right, val);
            } else {
                pw.println(tree.query(left, right));
            }
        }
        pw.flush();
    }

    static class SegmentTree {
        static class Node {
            Node left, right;
            int l, r;
            long lazy;
            long sum;

            public Node(int l, int r) {
                this.l = l;
                this.r = r;
            }
        }

        Node root;

        public SegmentTree(int[] nums) {
            int n = nums.length;
            root = build(0, n - 1, nums);
        }

        public SegmentTree(int n) {
            root = build(0, n - 1, new int[n]);
        }

        private Node build(int l, int r, int[] nums) {
            Node node = new Node(l, r);
            if (l == r) {
                node.sum = nums[l];
                return node;
            }
            int mid = (l + r) >>> 1;
            node.left = build(l, mid, nums);
            node.right = build(mid + 1, r, nums);
            pushUp(node);
            return node;
        }

        public void add(Integer l, Integer r, Integer val) {
            add(root, l, r, val);
        }

        private void add(Node node, Integer l, Integer r, Integer val) {
            if (node.r < l || node.l > r) return;
            if (l <= node.l && node.r <= r) {
                node.sum += (long) (node.r - node.l + 1) * val;
                node.lazy += val;
                return;
            }

            pushDown(node);
            add(node.left, l, r, val);
            add(node.right, l, r, val);
            pushUp(node);
        }

        private void pushDown(Node node) {
            if (node.lazy == 0) return;

            node.left.sum += (node.left.r - node.left.l + 1) * node.lazy;
            node.right.sum += (node.right.r - node.right.l + 1) * node.lazy;
            node.left.lazy += node.lazy;
            node.right.lazy += node.lazy;
            node.lazy = 0;

        }

        private void pushUp(Node node) {
            node.sum = node.left.sum + node.right.sum;
        }

        public long query(int l, int r) {
            return query(root, l, r);
        }

        private long query(Node node, int l, int r) {
            if (node.r < l || node.l > r) return 0;
            if (l <= node.l && node.r <= r) {
                return node.sum;
            }
            pushDown(node);
            return query(node.left, l, r) + query(node.right, l, r);
        }
    }
}


