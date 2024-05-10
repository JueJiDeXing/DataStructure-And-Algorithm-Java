package 数据结构实现.树.线段树.线段树模版.维护区间和;

/**
 内部节点类数组+通过原数组建树+单点置数+区间和查询(无懒更新)
 */
public class SegmentTree2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        SegmentTree2 arr = new SegmentTree2(nums);
        arr.update(0, 2);
        System.out.println(arr.sumRange(0, 1));//2+2=4
    }

    static class Node {
        int left, right, val;

        Node(int l, int r) {
            left = l;
            right = r;
        }
    }

    Node[] tree;
    int[] nums;

    /**
     传入一个初始数组,构建线段树
     */
    public SegmentTree2(int[] _nums) {
        nums = _nums;
        int n = nums.length;
        tree = new Node[n << 2];//满二叉树,4n个节点
        build(1, 1, n);//建树,第0个位置不使用
        //赋值
        for (int i = 0; i < n; i++) {
            update(1, i + 1, nums[i]);
        }
    }

    /**
     建树方法<br>

     @param u 节点索引
     @param l 节点的左区间
     @param r 节点的右区间
     */
    void build(int u, int l, int r) {
        tree[u] = new Node(l, r);//创建节点
        if (l == r) return;//左右区间相等,到叶子节点了
        //二分建树
        int mid = l + r >> 1;
        build(u << 1, l, mid);//左孩子索引=2*父索引
        build(u << 1 | 1, mid + 1, r);//有孩子索引=2*父索引+1
    }

    void update(int u, int x, int v) {
        if (tree[u].left == x && tree[u].right == x) {
            tree[u].val += v;
            return;
        }
        int mid = tree[u].left + tree[u].right >> 1;
        if (x <= mid) update(u << 1, x, v);
        else update(u << 1 | 1, x, v);
        pushup(u);
    }

    int query(int u, int l, int r) {
        if (l <= tree[u].left && tree[u].right <= r) return tree[u].val;
        int mid = tree[u].left + tree[u].right >> 1;
        int ans = 0;
        if (l <= mid) ans += query(u << 1, l, r);
        if (r > mid) ans += query(u << 1 | 1, l, r);
        return ans;
    }

    void pushup(int u) {
        tree[u].val = tree[u << 1].val + tree[u << 1 | 1].val;
    }

    /**
     更新索引值

     @param index 更新的索引
     @param val   要更新成的值
     */
    public void update(int index, int val) {
        update(1, index + 1, val - nums[index]);
        nums[index] = val;
    }

    /**
     范围求和 sum(arr[left,right])
     */
    public int sumRange(int left, int right) {
        return query(1, left + 1, right + 1);
    }
}

