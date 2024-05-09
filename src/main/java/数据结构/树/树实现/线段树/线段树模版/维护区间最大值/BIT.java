package 数据结构.树.树实现.线段树.线段树模版.维护区间最大值;

import java.util.Arrays;

/**
 树状数组维护前缀最大值
 */
public class BIT {
    public static void main(String[] args) {
        BIT tree=new BIT(10);
        tree.update(1,1);
        tree.update(2,2);
        tree.update(3,3);
        System.out.println(tree.preMax(1));//1
        System.out.println(tree.preMax(2));//2
        System.out.println(tree.preMax(3));//3
        tree.update(2,5);
        System.out.println(tree.preMax(1));//1
        System.out.println(tree.preMax(2));//5
        System.out.println(tree.preMax(3));//5
    }
    int[] tree;

    public BIT(int n) {
        tree = new int[n];
        Arrays.fill(tree, Integer.MIN_VALUE);
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    public void update(int i, int v) {
        while (i < tree.length) {
            tree[i] = Math.max(tree[i], v);
            i += lowbit(i);
        }
    }

    public int preMax(int i) {
        int ans = Integer.MIN_VALUE;
        while (i > 0) {
            ans = Math.max(ans, tree[i]);
            i -= lowbit(i);
        }
        return ans;
    }
}

