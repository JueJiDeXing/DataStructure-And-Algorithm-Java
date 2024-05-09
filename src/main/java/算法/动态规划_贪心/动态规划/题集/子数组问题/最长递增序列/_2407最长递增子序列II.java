package 算法.动态规划_贪心.动态规划.题集.子数组问题.最长递增序列;

/**
 第 310 场周赛 Q4
 难度分:2280
 */
public class _2407最长递增子序列II {
    /*
    给你一个整数数组 nums 和一个整数 k 。

    找到 nums 中满足以下要求的最长子序列：

    1.子序列 严格递增
    2.子序列中相邻元素的差值 不超过 k 。
    请你返回满足上述要求的 最长子序列 的长度。

    子序列 是从一个数组中删除部分元素后，剩余元素不改变顺序得到的数组。
     */

    /**
     f[i][j]表示nums的前i个元素,以元素值为j结尾的最长递增子序列长度
     如果j!=nums[i],则f[i][j] = f[i-1][j]
     如果j==nums[i],则f[i][j] = 1 + max( f[i-1][j`] )   j-k <= j` < j
     由于f[i]只会由f[i-1]转移而来,所以可以优化掉f的第一个维度
     当nums[i]==1时, 以1结尾的最长递增子序列长度为1, 将f[j]置为1
     f[j] = 1 + max( f[j`] )   j-k <= j` < j
     对于 max( f[j`] ) 可以使用线段树表示整个f数组,优化dp
     */
    public int lengthOfLIS(int[] nums, int k) {
        int m = 0;
        for (int x : nums) m = Math.max(m, x);//f数组的第二维度是下标是nums的元素值
        SegmentTree tree = new SegmentTree(m);
        for (int j : nums) {
            if (j == 1) {
                tree.modify(1, 1);//以1结尾的最长递增子序列长度为1, 将f[j]置为1
            } else {
                tree.modify(j, 1 + tree.query(Math.max(j - k, 1), j - 1)); //f[j] = 1 + max( f[j`] ), j-k <= j` < j
            }
        }
        return tree.max[1];//1号节点的值,1号节点管辖[1,m]范围的数据,max[1]的值为[1,m]上的最大值
    }

    static class SegmentTree {
        // max[o]=val:节点o的值为val,其中管辖索引范围为[l,r],数组在[l,r]的最大值为val
        // [l,r]的确定方式:根节点为[1,m],左孩子为[1,(1+m)/2],右孩子为[(1+m)/2+1,m]
        int[] max;
        int m;

        public SegmentTree(int m) {
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


}
