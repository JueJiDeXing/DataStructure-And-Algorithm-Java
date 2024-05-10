package 算法.算法基础.快速选择算法;

import 数据结构实现.堆.MinHeap;

import java.util.concurrent.ThreadLocalRandom;

public class 第K大的元素 {
    /**
     <h1>快速选择算法</h1>
     */
    public int _findKthLargest(int[] a, int k) {
        //快速选择是升序,求索引i
        //这里是降序,求第k个
        //所以对应关系是长度-k
        return 快速选择.quick(a, 0, a.length - 1, a.length - k);
    }

    //正向求解
    public int findKthLargest(int[] nums, int k) {
        return quick(nums, 0, nums.length - 1, k - 1);//索引为k-1
    }

    public int quick(int[] nums, int left, int right, int k) {
        int p = partition(nums, left, right);
        if (p == k) {
            return nums[p];
        } else if (k < p) {
            return quick(nums, left, p - 1, k);
        } else {
            return quick(nums, p + 1, right, k);
        }
    }

    public int partition(int[] nums, int left, int right) {
        int index = ThreadLocalRandom.current().nextInt(right - left + 1) + left;
        swap(nums, right, index);
        int pv = nums[right];//变为最右侧
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[i] > pv) {//按降序排序
                i++;//先找大的
            }
            while (i < j && nums[j] <= pv) {
                j--;//再找小的
            }
            swap(nums, i, j);
        }
        swap(nums, right, i);//最后交换的为最右侧
        return i;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     <h1>小顶堆法</h1>
     先放入k个元素,之后遇到大的就替换堆顶元素(堆中存储前k个最大元素),最后输出堆顶元素即为第k大的元素
     */
    public int findKthLargest2(int[] arr, int k) {
        MinHeap minHeap = new MinHeap(k);

        for (int i = 0; i < k; i++) {
            minHeap.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()) {
                minHeap.replace(0);
            }
        }
        return minHeap.peek();
    }

    //求数据流的第k个最大元素--------------------------------------
    MinHeap minHeap;

    public void 堆排序(int[] arr, int k) {
        minHeap = new MinHeap(k);
        for (int j : arr) {
            add(j);
        }
    }

    //add方法会被不断调用,模拟数据流
    public int add(int val) {
        if (!minHeap.isFull()) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.replace(val);
        }
        return minHeap.peek();
    }
}


