package 基础数据结构算法.堆;

import 数据结构实现.堆.MaxHeap;
import 数据结构实现.堆.MinHeap;

public class 堆排序 {
    MinHeap minHeap;

    //堆排序
    // 将大顶堆堆顶与堆底交换,size--,执行下潜调整
    public void heapSort(int[] arr) {
        MaxHeap maxHeap = new MaxHeap(arr);//引用
        while (maxHeap.size > 1) {
            maxHeap.swap(0, --maxHeap.size);
            maxHeap.down(0);
        }
    }
}
