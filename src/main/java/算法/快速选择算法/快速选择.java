package 算法.快速选择算法;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class 快速选择 {
    /*
    求一个乱序数组里按升序索引为i的元素(第K小元素)
     */
    public static void main(String[] args) {
        int[] array = new int[]{6, 5, 1, 2, 4};
        System.out.println(choose(array, 0));
        System.out.println(choose(array, 1));
        System.out.println(choose(array, 2));
        System.out.println(choose(array, 3));
        System.out.println(choose(array, 4));
    }

    public int _choose(int[] array, int i) {//基础版
        Arrays.sort(array);
        return array[i];
    }

    /**
     <h1>快速选择</h1>
     使用快速排序思想,选择基准点
     */
    public static int choose(int[] array, int i) {
        return quick(array, 0, array.length - 1, i);
    }

    public static int quick(int[] array, int left, int right, int i) {
        int p = partition1(array, left, right);//基准点元素的索引值
        if (p == i) {//选择的基准点就是排第i的元素
            return array[p];
        } else if (i < p) {//在左半区
            return quick(array, left, p - 1, i);
        } else {//在右半区
            return quick(array, p + 1, right, i);
        }
    }

    /**
     随机基准点分区

     @param a
     @param left
     @param right
     @return
     */
    private static int partition1(int[] a, int left, int right) {
        int index = ThreadLocalRandom.current()
                .nextInt(right - left + 1) + left;
        swap(a, index, left);
        int pv = a[left];//基准点元素值
        int i = left;//双边
        int j = right;
        while (i < j) {
            while (i < j && a[j] > pv) {
                j--;//寻找小的
            }
            while (i < j && a[i] <= pv) {
                i++;//寻找大的
            }
            swap(a, i, j);//找到则交换
        }
        //退出循环时,i==j,指向最右侧的小于基准值的元素
        swap(a, left, i);//将基准值交换过来
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
