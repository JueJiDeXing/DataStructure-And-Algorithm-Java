package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class J积木 {
    static int N = 100000;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int(), m = Int();
        int[] d = new int[N + 1];//前缀和差分
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int h = Math.min(Int(), N + 1);
                if (h == 0) continue;
                //f[1~h]+1 <=> d[1]+1,d[h+1]-1
                d[1]++;
                d[h + 1]--;
            }
        }
        int H = Int();
        for (int i = 1; i <= H; i++) {//一次前缀和还原差分数组
            d[i] += d[i - 1];
        }
        long sum = 0;
        for (int i = 1; i <= H; i++) {//求前缀和
            sum += d[i];
            System.out.println(sum);
        }
    }

    static class SegmentTree {
        static class Node {
            int l, r, mid;
            long sum;
            int lazy;
            Node left, right;

            public Node(int l, int r) {
                this.l = l;
                this.r = r;
                this.mid = (l + r) >>> 1;
            }
        }

        Node root = new Node(1, N);

        public void add(int L, int R) {
            add(L, R, root);
        }

        private void add(int L, int R, Node node) {
            if (L <= node.l && node.r <= R) {
                node.sum += (node.r - node.l + 1);
                node.lazy += 1;
                return;
            }
            pushDown(node);
            if (L <= node.mid) add(L, R, node.left);
            if (R > node.mid) add(L, R, node.right);
            pushUp(node);
        }

        private void pushDown(Node node) {
            if (node.left == null) node.left = new Node(node.l, node.mid);
            if (node.right == null) node.right = new Node(node.mid + 1, node.r);
            if (node.lazy == 0) return;
            node.left.sum += (long) (node.left.r - node.left.l + 1) * node.lazy;
            node.right.sum += (long) (node.right.r - node.right.l + 1) * node.lazy;
            node.left.lazy = node.right.lazy = node.lazy;
            node.lazy = 0;
        }

        private void pushUp(Node node) {
            node.sum = node.left.sum + node.right.sum;
        }

        public long query(int L, int R) {
            return query(L, R, root);
        }

        private long query(int L, int R, Node node) {
            if (L <= node.l && node.r <= R) {
                return node.sum;
            }
            pushDown(node);
            long ans = 0;
            if (L <= node.mid) ans += query(L, R, node.left);
            if (R > node.mid) ans += query(L, R, node.right);
            return ans;
        }
    }
}
