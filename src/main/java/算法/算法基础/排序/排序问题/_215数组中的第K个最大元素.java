package 算法.算法基础.排序.排序问题;

public class _215数组中的第K个最大元素 {
    /**
     <h1>桶排序O(n)</h1>
     */
    public int findKthLargest(int[] nums, int k) {
        int[] buckets = new int[20001];
        for (int num : nums) {
            buckets[num + 10000]++;
        }
        for (int i = 20000; i >= 0; i--) {
            k = k - buckets[i];
            if (k <= 0) {
                return i - 10000;
            }
        }
        return 0;
    }
}
