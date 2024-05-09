package 算法.排序.快速选择算法;

import java.util.PriorityQueue;
import java.util.Queue;

import static 算法.快速选择算法.快速选择.quick;

public class 数组中的中位数 {
    //中位数,如果是偶数个元素,取平均值

    /**
     快速选择算法 给定初始数组,求中位数
     */
    public double findMedian(int[] nums) {
        int len = nums.length;
        if (len % 2 == 1) {
            return quick(nums, 0, len - 1, len / 2);
        } else {
            int x = quick(nums, 0, len - 1, len / 2);
            int y = quick(nums, 0, len - 1, len / 2 - 1);
            return (x + y) / 2.0;
        }
    }

    /**
     <h1>求数据流的中位数:小顶堆</h1>
     在数据平分为2份,在大顶堆中找最大的,小顶堆找最小的,左边大顶堆,右边小顶堆,两堆堆顶相向<br>
     两个堆的元素个数最多相差一个,为保证平衡:<br>
     如果两边个数一样则添加到左边,否则添加到右边<br>
     左边添加时,先加到右边,并把右边最小的加到左边; 右边添加时,先加到左边,并把左边最大的加到右边
     */
    public double findMedian2() {
        if (left.isEmpty()) {
            return 0;
        }
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    static Queue<Integer> left = new PriorityQueue<>(((o1, o2) -> o2 - o1));//左侧大顶堆
    static Queue<Integer> right = new PriorityQueue<>((o1, o2) -> o1 - o2);//右侧小顶堆

    public void addNum(int num) {
        if (left.size() == right.size()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }
}
