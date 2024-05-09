package 算法.排序.各排序算法;

public class b选择 {
    //每次选择未排序区域的最大元素,放在未排序区域的末尾

    /**
     <div color=rgb(155,200,80)>
     <h1>选择排序</h1>
     </div>
     */
    public void selectSort(int[] arr) {
        // 选择轮数:length-1
        // 交换位置(right) 初始length-1,每次递减
        for (int right = arr.length - 1; right > 0; right--) {
            int max = right;//循环前面未排序区域,找到最大值的索引
            for (int i = 0; i < right; i++) {
                if (arr[i] > arr[max]) {
                    max = i;
                }
            }
            if (max != right) {
                swap(arr, max, right);//交换
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
