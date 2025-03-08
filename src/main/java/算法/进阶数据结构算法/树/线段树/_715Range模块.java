package 算法.进阶数据结构算法.树.线段树;

/**
 难度:困难
 */
public class _715Range模块 {
    /*
    Range模块是跟踪数字范围的模块。设计一个数据结构来跟踪表示为 半开区间 的范围并查询它们。

    半开区间 [left, right) 表示所有 left <= x < right 的实数 x 。

    实现 RangeModule 类:

    RangeModule() 初始化数据结构的对象。
    void addRange(int left, int right)
        添加 半开区间 [left, right)，跟踪该区间中的每个实数。
        添加与当前跟踪的数字部分重叠的区间时，应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
    boolean queryRange(int left, int right)
        只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true ，否则返回 false 。
    void removeRange(int left, int right)
        停止跟踪 半开区间 [left, right) 中当前正在跟踪的每个实数。
     */
    class Node {
        Node left, right;//左右孩子节点
        int sum, add;//sum:节点表示的区间和;add:懒更新
    }

    int N = (int) 1e9 + 10;
    Node root = new Node();

    /**
     将[l,r]区间更新为v

     @param node  当前节点
     @param lc,rc 当前节点的区间
     @param l,r   任务区间
     @param v     1表示添加进Range,-1表示移除
     */
    private void update(Node node, int lc, int rc, int l, int r, int v) {
        int len = rc - lc + 1;
        if (l <= lc && rc <= r) {//节点的区间被任务区间完全覆盖
            node.sum = v == 1 ? len : 0;//注意v==1时置为len,v==-1时置为0
            node.add = v;
            return;
        }
        pushdown(node, len);//处理之前的懒更新
        int mid = lc + rc >> 1;//将更新任务下发到左右孩子
        if (l <= mid) update(node.left, lc, mid, l, r, v);
        if (r > mid) update(node.right, mid + 1, rc, l, r, v);
        pushup(node);//更新node的sum值
    }

    /**
     查询[l,r]区间和

     @param node  当前节点
     @param lc,rc 当前节点的区间
     @param l,r   任务区间
     */
    private int query(Node node, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return node.sum;
        pushdown(node, rc - lc + 1);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans += query(node.left, lc, mid, l, r);
        if (r > mid) ans += query(node.right, mid + 1, rc, l, r);
        return ans;
    }

    /**
     处理node的懒更新任务
     */
    private void pushdown(Node node, int len) {
        if (node.left == null) node.left = new Node();
        if (node.right == null) node.right = new Node();
        if (node.add == 0) return;//没有懒更新任务
        int add = node.add;
        if (add == -1) {//更新为-1:从Range中移除
            node.left.sum = node.right.sum = 0;
        } else {//更新为1:添加到Range
            node.left.sum = (len + 1) / 2;
            node.right.sum = len / 2;
        }
        node.left.add = node.right.add = add;
        node.add = 0;
    }

    /**
     汇总左右孩子的sum值给node
     */
    private void pushup(Node node) {
        node.sum = node.left.sum + node.right.sum;
    }

    public void addRange(int left, int right) {
        update(root, 1, N - 1, left, right - 1, 1);
    }

    public boolean queryRange(int left, int right) {
        return query(root, 1, N - 1, left, right - 1) == right - left;
    }

    public void removeRange(int left, int right) {
        update(root, 1, N - 1, left, right - 1, -1);
    }

}
