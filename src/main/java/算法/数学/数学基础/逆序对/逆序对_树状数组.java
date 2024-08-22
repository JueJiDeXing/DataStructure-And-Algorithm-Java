package 算法.数学.数学基础.逆序对;

public class 逆序对_树状数组 {
    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4};
        int n = 4;
        BitTree bitTree = new BitTree();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            bitTree.add(nums[i], 1);
            ans += bitTree.getSum(n) - bitTree.getSum(nums[i]);
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
