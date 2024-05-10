package 数据结构实现.树.线段树.线段树模版.维护区间最大值;
/**
 纯数组+单点置数+区间最大值查询
 */
public class SegmentMax2 {
    // max[o]=val:节点o的值为val,其中管辖索引范围为[l,r],数组在[l,r]的最大值为val
    // [l,r]的确定方式:根节点为[1,m],左孩子为[1,(1+m)/2],右孩子为[(1+m)/2+1,m]
    int[] max;
    int m;

    public SegmentMax2(int m) {
        this.m = m;
        max = new int[m * 4];
    }

    /**
     将 数组idx位置 置为val
     */
    public void modify(int idx, int val) {
        modify(1, 1, m, idx, val);
    }

    private void modify(int o, int l, int r, int idx, int val) {
        if (l == r) {
            max[o] = val;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) modify(o * 2, l, mid, idx, val);
        else modify(o * 2 + 1, mid + 1, r, idx, val);
        max[o] = Math.max(max[o * 2], max[o * 2 + 1]);
    }

    /**
     返回区间 [L,R] 内的最大值
     */
    public int query(int L, int R) {
        if (R < L) return 0;
        return query(1, 1, m, L, R);
    }


    private int query(int o, int l, int r, int L, int R) {
        if (L <= l && r <= R) return max[o];
        int res = 0;
        int m = (l + r) / 2;
        if (L <= m) res = query(o * 2, l, m, L, R);
        if (R > m) res = Math.max(res, query(o * 2 + 1, m + 1, r, L, R));
        return res;
    }
}
