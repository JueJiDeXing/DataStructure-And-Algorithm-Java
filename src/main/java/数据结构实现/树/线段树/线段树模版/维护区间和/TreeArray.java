package 数据结构实现.树.线段树.线段树模版.维护区间和;

/**
 树状数组
 */
public class TreeArray {
    int[] tree;
    int[] nums;
    int n;

    /**
     传入数组,构建线段树
     */
    public TreeArray(int[] _nums) {
        nums = _nums;
        n = nums.length;
        tree = new int[n + 1];//从1开始
        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    int lowbit(int x) {
        return x & -x;
    }

    /**
     查询前缀和,[1,x]
     */
    int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) {
            ans += tree[i];
        }
        return ans;
    }

    /**
     在树状数组 x 位置中增加值 u
     */
    void add(int x, int u) {
        for (int i = x; i <= n; i += lowbit(i)) {
            tree[i] += u;
        }
    }


    /**
     单点修改,将索引i位置的值改为val
     */
    public void update(int i, int val) {
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    /**
     范围查询,查询[l,r]区间的和
     */
    public int sumRange(int l, int r) {
        return query(r + 1) - query(l);//两前缀和相减
    }

}
