package 数据结构.树.树实现.线段树.线段树问题;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 区间最小值查找,删除元素->置为极大值+树上二分查找真实节点
 */
public class 蓝桥杯_仪仗队 {
    /*
    给定一个长度为n的数组
    每次操作选择一个区间[l,r],将其中的最小元素删除(如果有多个,删除最左侧的)
    删除后数组长度会减少1
    求最后的数组和为多少
     */
    static class SegmentMin {
        public static class Node {
            int l, r;
            int min, id;

            public Node(int l, int r, int min, int id) {
                this.l = l;
                this.r = r;
                this.min = min;
                this.id = id;
            }
        }

        public SegmentMin(int n) {
        }


        public static void pushup(Node root, Node left, Node right) {
            if (left.min == right.min || left.min < right.min) {
                root.min = left.min;
                root.id = left.id;
            } else {
                root.min = right.min;
                root.id = right.id;
            }
        }

        private void add(int node, int val) {

        }

        private void modify(int node, int id, int val) {

        }

        private Node query(int node, int l, int r) {

            return null;
        }

        private int sum(int node) {

            return 0;
        }

        private void build(int node, int l, int r) {

        }
    }

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int INF = 0x3f3f3f3f;

    /*
        1.由于实际删除一个节点太麻烦，将删除节点转变为将该节点的位置的值置为极大值
        2.然后用树状数组维护下标矩阵的差分矩阵，
          在下标矩阵中，如果要删除下标为id的元素，那么对应到它的差分矩阵就是：
          自id起，后面的所有数都减1: add(id, -1)
        3.由于不是真的删除节点，那么对于每次查询的l，r，都要找到它在原矩阵中的真实位置，
          二分查找即可
    */
    static int n, m;
    static int[] w = new int[500001];

    public static void solve() {
        n = nextInt();
        SegmentMin segment = new SegmentMin(n);
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            w[i] = nextInt();
            sum += w[i];
            segment.add(i, i);
            if (i + 1 <= n) segment.add(i + 1, -i);
        }

        segment.build(1, 1, n);
        m = nextInt();
        for (int i = 1; i <= m; i++) {
            int l = nextInt(), r = nextInt();
            int ll = 1, rr = n;
            while (ll < rr) {
                int mid = ll + rr >> 1;
                if (segment.sum(mid) >= l) rr = mid;
                else ll = mid + 1;
            }
            l = rr;
            ll = 1;
            rr = n;
            while (ll < rr) {
                int mid = ll + rr >> 1;
                if (segment.sum(mid) >= r) rr = mid;
                else ll = mid + 1;
            }
            r = rr;
            // 最重要的一点，这里在查询后就要 sum -= e.min; add(e.id, -1);
            // 然后再修改 modify(1, e.id, INF);!!!!!
            SegmentMin.Node e = segment.query(1, l, r);
            sum -= e.min;
            segment.add(e.id, -1);
            segment.modify(1, e.id, INF);
        }
        System.out.println(sum);
    }


}
