package 算法.数学.数学基础.逆序对;

import java.util.Arrays;

public class 逆序对_树状数组_离散化 {
    static class Node {
        int origin;
        int id;

        public Node(int origin, int id) {
            this.origin = origin;
            this.id = id;
        }

    }

    public static void main(String[] args) {
        int[] origins = {7, 5, 6, 4};
        int n = origins.length;
        Node[] nums = new Node[n];
        for (int i = 0; i < n; i++) {
            nums[i] = new Node(origins[i], i);
        }
        Arrays.sort(nums, (a, b) -> a.origin - b.origin);// 排序后的id就是映射值
        BitTree bitTree = new BitTree();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            bitTree.add(nums[i].id, 1);
            ans += bitTree.getSum(n) - bitTree.getSum(nums[i].id);
        }
    }

    static class BitTree {
        int[] tree;
        int n;

        public void add(int i, int val) {
            while (i <= n) {
                tree[i] += val;
                i += lowbit(i);
            }
        }

        private static int lowbit(int i) {
            return i & -i;
        }

        public int getSum(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= lowbit(i);
            }
            return sum;
        }
    }
}
