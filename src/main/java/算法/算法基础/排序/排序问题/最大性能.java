package 算法.算法基础.排序.排序问题;

import java.util.concurrent.ThreadLocalRandom;

public class 最大性能 {


    int MOD = 1000000007;

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        quickSort(speed, efficiency, 0, n - 1);
        long max = 0;
        for (int i = n - 1; i >= 0; i--) {
            int b = efficiency[i];
            int[] as;
            if (i <= n - k) {
                as = select(speed, i + 1, k - 1);
            } else if (i < n - 1) {
                as = select(speed, i + 1, n - i - 1);
            } else {
                as = new int[0];
            }
            int sum = speed[i];
            for (int a : as) {
                sum += a;
            }
            max = Math.max((long) sum * b, max);
        }
        return (int) (max % MOD);
    }

    public void quickSort(int[] speed, int[] efficiency, int left, int right) {
        if (left >= right) {
            return;
        }
        //快排核心方法:分区
        int p = partition(speed, efficiency, left, right);//找基准点,小的放左边,大的放右边

        quickSort(speed, efficiency, left, p - 1);//对两个区域再次分区排序
        quickSort(speed, efficiency, p + 1, right);
    }

    public int partition(int[] speed, int[] efficiency, int left, int right) {
        int index = ThreadLocalRandom.current()
                .nextInt(right - left + 1) + left;
        swap(speed, efficiency, index, left);
        int pv = efficiency[left];//随机基准点
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && efficiency[j] > pv) {
                //寻找小的
                j--;
            }
            while (i < j && pv >= efficiency[i]) {//内层循环也要判断i<j
                //寻找大的
                i++;
            }
            //找到则交换
            swap(speed, efficiency, i, j);
        }
        swap(speed, efficiency, left, i);
        return i;
    }

    private static void swap(int[] speed, int[] efficiency, int i, int j) {
        int temp1 = speed[i];
        speed[i] = speed[j];
        speed[j] = temp1;

        int temp2 = efficiency[i];
        efficiency[i] = efficiency[j];
        efficiency[j] = temp2;
    }

    public int[] select(int[] speed, int start, int k) {
        if (k == 0) return new int[0];
        MinHeap minHeap = new MinHeap(k);

        for (int i = start; i < start + k; i++) {
            minHeap.offer(speed[i]);
        }
        for (int i = start + k; i < speed.length; i++) {
            if (speed[i] > minHeap.peek()) {
                minHeap.replace(speed[i]);
            }
        }
        return minHeap.array;
    }

    static class MinHeap {
        public int[] array;
        int size;

        public MinHeap(int capacity) {
            this.array = new int[capacity];
        }

        public boolean offer(int offered) {
            if (isFull()) {
                return false;
            }
            int child = size++;//child:插入位置
            int parent = (child - 1) / 2;//父节点索引=(子节点索引-1)/2
            while (child > 0 && offered < array[parent]) {//比父节点小
                array[child] = array[parent];//将父节点向下移,插入位置变为父节点位置,再次比较
                child = parent;
                parent = (parent - 1) / 2;
            }
            //找到插入位置
            array[child] = offered;//插入
            return true;
        }


        public int poll() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
            size--;
            int e = array[size];
            down(0);//下潜
            array[size--] = 0;
            return e;
        }

        private void down(int parent) {//优先级低的元素下潜
            int left = 2 * parent + 1;
            int right = left + 1;
            int min = parent;//寻找 父,左,右 三者较大<!--小顶堆,查找最小-->的优先级
            if (left < size && array[left] < array[min]) {
                min = left;
            }
            if (right < size && array[right] < array[min]) {
                min = right;
            }
            if (min != parent) {//如果子节点比父节点优先级大
                swap(parent, min);// 交换
                down(min);//递归,直到max==parent,父节点大于左右子节点时停止
            }
        }

        /**
         替换堆顶元素
         */
        public void replace(int replaced) {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            array[0] = replaced;
            down(replaced);
        }

        private void swap(int i, int j) {//交换
            int e = array[i];
            array[i] = array[j];
            array[j] = e;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException();
            }
            return array[0];//返回堆顶元素
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == array.length;
        }
    }

}
