package 算法.排序.各排序算法;

public class c堆排 {

    public void heapSort(int[] arr) {
        heapify(arr, arr.length);//建堆
        for (int right = arr.length - 1; right > 0; right--) {
            //交换第一个元素(大顶堆第一个元素为最大元素)与未排序区域最右侧元素
            // 并重新下潜排序(范围到未排序区域的最右侧)
            swap(arr, 0, right);
            down2(arr, 0, right);
        }
    }

    //建堆,时间O(n)
    private void heapify(int[] array, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            down2(array, i, size);//数组前一半的元素进行下潜
        }
    }

    //下潜
    private void down1(int[] array, int parent, int size) {//优先级低的元素下潜
        int left = 2 * parent + 1;
        int right = left + 1;
        int max = parent;//寻找 父,左,右 三者较大的优先级
        if (left < size && array[left] > array[max]) {
            max = left;
        }
        if (right < size && array[right] > array[max]) {
            max = right;
        }
        if (max != parent) {//如果子节点比父节点优先级大
            swap(array, parent, max);// 交换
            down1(array, max, size);//递归,直到max==parent,父节点大于左右子节点时停止
        }
    }

    //下潜(不使用递归)
    private void down2(int[] array, int parent, int size) {//优先级低的元素下潜
        while (true) {
            int left = 2 * parent + 1;
            int right = left + 1;
            int max = parent;//寻找 父,左,右 三者较大的优先级
            if (left < size && array[left] > array[max]) {
                max = left;
            }
            if (right < size && array[right] > array[max]) {
                max = right;
            }
            if (max == parent) {
                break;
            }
            swap(array, parent, max);// 交换
            parent = max;//不使用递归
        }
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
