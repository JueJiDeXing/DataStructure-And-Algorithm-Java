package 算法.算法基础.双指针;

public class _16部分排序 {
    /*
    给定一个整数数组，编写一个函数，找出索引m和n，
    只要将索引区间[m,n]的元素排好序，整个数组就是有序的。

    注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
    函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
     */

    /**
     先找到左右的第一个减的位置left和right
     [0,left]递增, [right,n-1]递增
     再根据[left,right]部分的最大最小值向左右拓宽
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length <= 1) {
            return new int[]{-1, -1};
        }
        int len = array.length;
        int left = 0;
        while (left < len - 1 && array[left] <= array[left + 1]) {
            left++;
        }
        if (left == len - 1) {
            return new int[]{-1, -1};
        }
        int right = len - 1;
        while (right > 0 && array[right - 1] <= array[right]) {
            right--;
        }
        //
        int m = left, n = right;
        for (int i = left; i <= right; i++) {
            while (m > 0 && array[m - 1] > array[i]) {
                m--;
            }
            while (n < len - 1 && array[n + 1] < array[i]) {
                n++;
            }
        }
        return new int[]{m, n};
    }
}
