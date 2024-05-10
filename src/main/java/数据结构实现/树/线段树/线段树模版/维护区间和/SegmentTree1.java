package 数据结构实现.树.线段树.线段树模版.维护区间和;

/**
 内部节点类+区间置数+区间和查询
 */
public class SegmentTree1 {
    //用于区间问题
    public static void main(String[] args) {
        SegmentTree1 segmentTree = new SegmentTree1(0, 100);
        segmentTree.update(1, 100, 1);
        segmentTree.update(5, 20, 2);
        System.out.println(segmentTree.query(1, 10));
    }

    private static final int NoTask = -1;

    static class Node {
        public int left, right, mid;//节点的数据区间范围[left,right], mid为区间中间值,非必须
        public int val;//val:节点数据区间和
        public int lazy;//lazy:节点懒更新的更新值,-1表示没有更新任务
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
    public SegmentTree1(int left, int right) {
        root = new Node(left, right);
    }

    /**
     更新区间[left,right]的值为val
     */
    public void update(int left, int right, int val) {
        _update(left, right, val, root);
    }

    /**
     查询区间[left,right]的和
     */
    public int query(int left, int right) {
        return _query(left, right, root);
    }

    /**
     对node节点进行更新任务,更新任务的范围为[left,right],将[left,right]的值更新为val
     */
    private void _update(int left, int right, int val, Node node) {
        //任务区间不在节点范围,返回
        if (node.right < left || node.left > right) return;
        //任务区间覆盖node,任务添加到node上,不再下发
        if (left <= node.left && node.right <= right) {
            node.lazy = val;//添加更新懒标记
            node.val = val * (node.right - node.left + 1);//更新节点值(sum)
            return;
        }
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
    private int _query(int left, int right, Node node) {
        if (left <= node.left && node.right <= right) {//任务区间覆盖node,直接返回node存储的结果即可
            return node.val;
        }
        if (node.right < left || node.left > right) {//查询范围完全不在node数据范围上,和为0
            return 0;
        }
        //需要查询左右孩子

        _pushdown(node); //先对node的懒更新任务进行处理
        int res_left = 0, res_right = 0;
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
        int lazy = node.lazy;
        node.leftChild.lazy = lazy;//添加懒更新标记
        node.leftChild.val = lazy * (node.leftChild.right - node.leftChild.left + 1);//更新节点值为更新任务完成后应该的值
        node.rightChild.lazy = lazy;
        node.rightChild.val = lazy * (node.rightChild.right - node.rightChild.left + 1);
        node.lazy = NoTask;//任务下发给左右孩子,node节点的任务取消
    }
}
