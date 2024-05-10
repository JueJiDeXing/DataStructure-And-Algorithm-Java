package 数据结构实现.树.线段树.每天写一遍线段树;

/**
 已测试
 */
public class day1_区间置数_区间求和_懒更新_线段树 {
    public static void main(String[] args) {
        Segment segment = new Segment(30);
        segment.update(0, 10, 20, 2);
        System.out.println(segment.query(0, 10, 20));
        segment.update(0,15,25,1);
        System.out.println(segment.query(0,10,20));
    }

    static class Segment {
        static class Node {
            int left, right;
            int sum = 0;
            int lazy = -1;

            public Node(int left, int right) {
                this.left = left;
                this.right = right;
            }
        }

        Node[] tr;

        public Segment(int n) {
            tr = new Node[4 * n];
            build(0, 1, n);
        }

        void build(int id, int L, int R) {
            tr[id] = new Node(L, R);
            if (L != R) {
                int mid = getMid(L, R);
                build(toLeft(id), L, mid);
                build(toRight(id), mid + 1, R);
            }
        }

        int toLeft(int id) {
            return (id << 1) + 1;//因为建树是从索引0开始,所以这里加1,如果从1开始则不用加
        }

        int toRight(int id) {
            return (id << 1) + 2;
        }

        int getMid(int L, int R) {
            return (L + R) >>> 1;
        }

        void update(int id, int L, int R, int val) {
            Node node = tr[id];
            if (node.right < L || node.left > R) return;
            if (L <= node.left && node.right <= R) {//节点维护的区间全部在任务区间内
                node.sum = val * (node.right - node.left + 1);
                node.lazy = val;
                return;
            }
            pushDown(id);
            int mid = getMid(L, R);
            if (L <= mid) update(toLeft(id), L, R, val);//L,R是任务区间,不需要动的
            if (R > mid) update(toRight(id), L, R, val);
            pushUp(id);
        }

        void pushUp(int id) {//汇总sum到父节点
            Node node = tr[id];
            Node left = tr[toLeft(id)], right = tr[toRight(id)];
            node.sum = left.sum + right.sum;
        }

        void pushDown(int id) {//下发懒更新任务
            Node node = tr[id];
            Node left = tr[toLeft(id)], right = tr[toRight(id)];
            if (node.lazy != -1) {
                left.sum = node.lazy * (left.right - left.left + 1);
                right.sum = node.lazy * (right.right - right.left + 1);
                left.lazy = node.lazy;
                right.lazy = node.lazy;
            }
        }

        int query(int id, int L, int R) {
            Node node = tr[id];
            if (node.right < L || node.left > R) return 0;//任务不在这个节点的管理区间上
            if (L <= node.left && node.right <= R) {//这个节点的管理区间被任务完全包含,全部查询,返回sum
                return node.sum;
            }
            pushDown(id);
            int mid = getMid(L, R);
            int ans = 0;
            if (L <= mid) ans += query(toLeft(id), L, R);
            if (R > mid) ans += query(toRight(id), L, R);
            return ans;
        }
    }
}
