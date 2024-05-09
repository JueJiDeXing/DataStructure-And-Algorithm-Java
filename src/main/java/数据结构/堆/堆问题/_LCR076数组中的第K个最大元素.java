package 数据结构.堆.堆问题;

import java.util.*;

/**
 难度:中等
 */
public class _LCR076数组中的第K个最大元素 {
    /*
    给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
    请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
    你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     */

    /**
     <h1>小顶堆解法</h1>
     */
    public int findKthLargest(int[] nums, int k) {
        //容量为k的小顶堆,当堆满之后,如果新数字大于堆顶数字,则更换堆顶数字
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            } else {
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }

    /**
     <h1>大顶堆解法</h1>
     */
    public int findKthLargest2(int[] nums, int k) {
        //从数组构建大顶堆,抛出k个后的堆顶即为答案
        MaxHeap heap = new MaxHeap(nums);//这里采用自己实现的大顶堆
        for (int i = 0; i < k - 1; i++) {
            heap.poll();
        }
        return heap.peek();
    }

    static class MaxHeap {
        int[] array;
        int size;

        public MaxHeap(int[] array) {
            this.array = array;
            this.size = array.length;
            build();
        }

        /**
         建堆
         */
        private void build() {
            for (int i = size / 2 - 1; i >= 0; i--) {//找非叶子节点进行下潜
                down(i);
            }
        }

        /**
         小元素下潜
         */
        private void down(int parent) {
            int left = parent * 2 + 1;
            int right = left + 1;
            int max = parent;
            if (left < size && array[left] > array[max]) {
                max = left;
            }
            if (right < size && array[right] > array[max]) {
                max = right;
            }
            if (max != parent) {//左右孩子大于父
                swap(max, parent);
                down(max);

            }
        }

        private void swap(int i, int j) {
            int t = array[i];
            array[i] = array[j];
            array[j] = t;
        }

        public int peek() {
            return array[0];
        }

        public int poll() {
            int val = array[0];
            swap(0, size - 1);//首尾交换,把尾抛出,首部下潜重排堆
            size--;
            down(0);
            return val;
        }

    }
}
