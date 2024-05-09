package 算法OJ.洛谷.普及up_提高;

import java.io.*;

/**
 测试通过: 14/21  7TLE
 */
public class P2357守墓人 {
    /*
    有n个墓碑,编号从1开始
    进行f次操作
    1. 将[l,r]的墓碑全部+k
    2.将主墓碑(编号为1)增加k
    3.将主墓碑(编号为1)减少k
    4.求[l,r]的和
    5.求主墓碑的值
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        n = Int();
        int f = Int();
        SegmentTree tree = new SegmentTree(1, n);
        int prev = 0;
        for (int i = 1; i <= n; i++) {
            int ff = Int();
            tree.add(i, ff - prev);//前缀和差分
            prev = ff;
        }
        for (int i = 0; i < f; i++) {
            int type = Int();
            if (type == 1) {
                //将[l,r]增加v
                int l = Int(), r = Int(), v = Int();
                tree.add(l, v);
                tree.add(r + 1, -v);
            } else if (type == 2) {
                //将1号增加v
                int v = Int();
                tree.add(1, v);
                tree.add(2, -v);
            } else if (type == 3) {
                //将1号减少v
                int v = Int();
                tree.add(1, -v);
                tree.add(2, v);
            } else if (type == 4) {
                //查询[l,r]区间和
                int l = Int(), r = Int();
                out.println(tree.sumRange(l, r));
            } else {
                //查询1号值
                out.println(tree.sumRange(1, 1));
            }
        }
        out.flush();
    }

    static int n;


    static class SegmentTree {
        private static final int NoTask = -1;


        static class Node {
            public int left, right, mid;//节点的数据区间范围[left,right], mid为区间中间值,非必须
            public long val;//val:节点数据区间和
            public long lazy;//lazy:节点懒更新的更新值,-1表示没有更新任务
            public Node leftChild, rightChild;//左右孩子节点

            public Node(int l, int r) {
                left = l;
                right = r;
                mid = (left + right) >>> 1;
                val = 0;
                lazy = NoTask;
                leftChild = rightChild = null;
            }
        }

        public Node root;

        /**
         构建线段树,可查询、修改的数据范围[left,right]
         */
        public SegmentTree(int left, int right) {
            root = new Node(left, right);
        }

        public void add(int pos, long val) {
            _add(pos, pos, val, root);
        }

        private void _add(int left, int right, long val, Node node) {
            if (left <= node.left && node.right <= right) {//任务区间覆盖node,任务添加到node上,不再下发
                node.lazy += val;//添加更新懒标记
                node.val += val * (node.right - node.left + 1);//更新节点值(sum)
                return;
            }
            //任务区间不在节点范围,返回
            if (node.right < left || node.left > right) return;
            //对node的原有的懒更新任务进行处理
            _pushdown(node);
            //对node的左右孩子进行本次更新任务的下发
            if (left <= node.mid) {
                _add(left, right, val, node.leftChild);
            }
            if (right > node.mid) {
                _add(left, right, val, node.rightChild);
            }
            //对左右孩子的更新结果进行收集,sum(node)=sum(left)+sum(right)
            _pushup(node);
        }


        /**
         更新区间[left,right]的值为val
         */
        public void update(int left, int right, int val) {
            _update(left, right, val, root);
        }

        public long sumRange(int left, int right) {
            long ans = 0;
            for (int i = left; i <= right; i++) {
                ans += query(0, i);
            }
            return ans;
        }

        /**
         查询区间[left,right]的和
         */
        public long query(int left, int right) {
            return _query(left, right, root);
        }

        /**
         对node节点进行更新任务,更新任务的范围为[left,right],将[left,right]的值更新为val
         */
        private void _update(int left, int right, int val, Node node) {
            if (left <= node.left && node.right <= right) {//任务区间覆盖node,任务添加到node上,不再下发
                node.lazy = val;//添加更新懒标记
                node.val = (long) val * (node.right - node.left + 1);//更新节点值(sum)
                return;
            }
            //任务区间不在节点范围,返回
            if (node.right < left || node.left > right) return;
            //对node的原有的懒更新任务进行处理
            _pushdown(node);
            //对node的左右孩子进行本次更新任务的下发
            if (left <= node.mid) {
                _update(left, right, val, node.leftChild);
            }
            if (right > node.mid) {
                _update(left, right, val, node.rightChild);
            }
            //对左右孩子的更新结果进行收集,sum(node)=sum(left)+sum(right)
            _pushup(node);
        }

        /**
         查询node节点的区间[left,right]的和
         */
        private long _query(int left, int right, Node node) {
            if (left <= node.left && node.right <= right) {//任务区间覆盖node,直接返回node存储的结果即可
                return node.val;
            }
            if (node.right < left || node.left > right) {//查询范围完全不在node数据范围上,和为0
                return 0;
            }
            //需要查询左右孩子

            _pushdown(node); //先对node的懒更新任务进行处理
            long res_left = 0, res_right = 0;
            if (left <= node.mid) {//node左孩子需要查询
                res_left = _query(left, right, node.leftChild);
            }
            if (right > node.mid) {//node右孩子需要查询
                res_right = _query(left, right, node.rightChild);
            }
            return res_left + res_right;
        }

        /**
         对node节点的左右孩子数据进行收集,更新node的val
         */
        private void _pushup(Node node) {
            if (node.leftChild != null && node.rightChild != null) {
                node.val = node.leftChild.val + node.rightChild.val;
            }
        }

        /**
         对node节点现有的懒更新任务进行处理
         */
        private void _pushdown(Node node) {
            //没有左右节点则先开点
            if (node.leftChild == null) {
                node.leftChild = new Node(node.left, node.mid);
            }
            if (node.rightChild == null) {
                node.rightChild = new Node(node.mid + 1, node.right);
            }
            //对节点任务进行处理
            if (node.lazy == NoTask) return;//没有任务,不需要处理
            long lazy = node.lazy;
            node.leftChild.lazy = lazy;//添加懒更新标记
            node.leftChild.val = lazy * (node.leftChild.right - node.leftChild.left + 1);//更新节点值为更新任务完成后应该的值
            node.rightChild.lazy = lazy;
            node.rightChild.val = lazy * (node.rightChild.right - node.rightChild.left + 1);
            node.lazy = NoTask;//任务下发给左右孩子,node节点的任务取消
        }
    }
}
