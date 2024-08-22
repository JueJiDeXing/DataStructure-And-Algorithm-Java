package 算法.算法基础.快速选择算法;

import 数据结构实现.堆.Heap;

public class 数组中的中位数 {
    //如果是偶数个元素,取平均值
    public double findMedian(int[] nums) {
        //import static 快速选择算法.算法.快速选择.quick;
        int len = nums.length;
        if (len % 2 == 1) {
            return 快速选择.quick(nums, 0, len - 1, len / 2);
        } else {
            int x = 快速选择.quick(nums, 0, len - 1, len / 2);
            int y = 快速选择.quick(nums, 0, len - 1, len / 2 - 1);
            return (x + y) / 2.0;
        }
    }

    /**
     <h1>求数据流的中位数:小顶堆</h1>
     在数据平分为2份,在大顶堆中找最大的,小顶堆找最小的,左边大顶堆,右边小顶堆,两堆堆顶相向<br>
     两个堆的元素个数最多相差一个,为保证平衡,自定义规则:<br>
     如果两边个数一样则添加到左边,否则添加到右边<br>
     左边添加时,先加到右边,并把右边最小的加到左边; 右边添加时,先加到左边,并把左边最大的加到右边
     */
    public double findMedian2() {
        if (left.getSize() == right.getSize()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return left.peek();
        }
    }

    private Heap left = new Heap(10, ((o1, o2) -> o2 - o1));
    private Heap right = new Heap(10, ((o1, o2) -> o1 - o2));

    public void addNum(int num) {
        if (left.getSize() == right.getSize()) {
            right.offer(num);
            left.offer(right.poll());
        } else {
            left.offer(num);
            right.offer(left.poll());
        }
    }
}
