package 算法OJ.ICPC.江西2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC(线段树基操)
 */
public class K猴子分桃 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int n = I(), q = I();
        Tree tree = new Tree(n);
        while (q-- > 0) {
            int k = I(), a = I(), b = I();
            if (k == 0) {
                System.out.println(tree.quary(1, a, b));
            } else {
                tree.update(1, a, b, k);
            }
        }
    }

    static class Tree {
        static class Node {
            int l, r;
            int add = 0;// 懒加
            boolean flag = false;//置0标记
            int min = 0, max = 0;// 区间最值
            long sum = 0;//区间和

            public Node(int l, int r) {
                this.l = l;
                this.r = r;
            }

            /**
             置0
             */
            public void set() {
                this.sum = 0;
                this.add = 0;
                this.min = 0;
                this.max = 0;
                this.flag = true;
            }

            public void add(int add) {
                if (add == 0) return;
                this.sum += (long) (r - l + 1) * add;
                this.add += add;
                this.min += add;
                this.max += add;
            }
        }

        Node[] nodes = new Node[100010 << 2];

        public Tree(int n) {
            build(1, 1, n);
        }

        int toL(int f) {
            return f << 1;
        }

        int toR(int f) {
            return f << 1 | 1;
        }

        void up(int f) { //向上更新sum
            Node node = nodes[f], left = nodes[toL(f)], right = nodes[toR(f)];
            node.sum = left.sum + right.sum;
            node.max = Math.max(left.max, right.max);
            node.min = Math.min(left.min, right.min);
        }

        void down(int f) { //向下更新add
            Node node = nodes[f], left = nodes[toL(f)], right = nodes[toR(f)];
            if (node.flag) {// 置0
                left.set();
                right.set();
                node.flag = false;
            }
            // 懒加
            left.add(node.add);
            right.add(node.add);
            node.add = 0;

        }

        void build(int f, int l, int r) { //建树
            nodes[f] = new Node(l, r);
            if (l == r) return;
            int m = (l + r) >> 1;
            build(toL(f), l, m);
            build(toR(f), m + 1, r);
        }

        /**
         区间更新

         @param f   当前节点编号
         @param l,r 操作区间
         @param k   +k
         */
        void update(int f, int l, int r, int k) { //区间更新
            Node node = nodes[f];
            if (l <= node.l && r >= node.r) {
                if (node.min + k >= 0) {// 加操作 or 减的数较小(区间每个数都正), 直接加上
                    node.add(k);
                    return;
                }
                if (node.max + k <= 0) {// 减的数较大(区间每个数都会变负), 置0
                    node.set();
                    return;
                }
            }
            int m = (node.l + node.r) >>> 1;
            down(f);
            if (l <= m) update(toL(f), l, r, k);
            if (r > m) update(toR(f), l, r, k);
            up(f);
        }

        /**
         区间和查询

         @param f   当前节点编号
         @param l,r 操作区间
         @return 区间和
         */
        long quary(int f, int l, int r) {
            if (l <= nodes[f].l && r >= nodes[f].r) return nodes[f].sum;
            int m = (nodes[f].l + nodes[f].r) >>> 1;
            long sum = 0;
            down(f);
            int ls = f << 1, rs = f << 1 | 1;
            if (l <= m) sum += quary(ls, l, r);
            if (r > m) sum += quary(rs, l, r);
            return sum;
        }
    }
}
