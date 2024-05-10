package 算法.算法基础.排序.各排序算法;

//已测试:src.java.数据结构与算法.算法.各排序算法
/*
    算法     最好       最坏      平均      空间      稳定      思想    注意事项
    冒泡     O(n)     O(n^2)    O(n^2)    O(1)     Y        比较    最好情况需要额外判断
    选择     O(n^2)   O(n^2)    O(n^2)    O(1)     N        比较    交换次数一般少于冒泡
    堆排     O(nlogn) O(nlogn)  O(nlogn)  O(1)     N        选择
    插入     O(n)     O(n^2)    O(n^2)    O(1)     Y        比较
    希尔     O(nlogn) O(nlogn)  O(nlogn)  O(1)     N        插入
    归并     O(nlogn) O(nlogn)  O(nlogn)  O(n)     Y        归并
    快速     O(nlogn) O(n^2)    O(nlogn)  O(logn)  N        快速选择算法
     */
public class a冒泡 {
    //每轮比较相邻元素,交换位置

    /**
     <div color=rgb(155,200,80)>
     <h1>冒泡排序:优化</h1>
     使用递归<br>
     j:初始为数组长度<br>
     优化:新加入了一个指针,每次确定更合适的右边界,以减少if判断次数
     </div>
     */
    public void bubbleSort1(int[] arr, int j) {
        if (j == 0) {//排序已完成
            return;
        }
        int x = 0;
        for (int i = 0; i < j; i++) {//遍历0~j的元素
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                x = i;//x记录一次遍历中最后一次交换的位置(无序的右边界)
            }
        }
        bubbleSort1(arr, x);//递归
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>冒泡排序:优化(改)</h1>
     递归改非递归<br>
     j:初始为数组长度<br>
     优化:新加入了一个指针,每次确定更合适的右边界,以减少if判断次数
     </div>
     */
    public void bubbleSort2(int[] arr) {
        int j = arr.length - 1;
        do {
            int x = 0;
            for (int i = 0; i < j; i++) {//遍历0~j的元素
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    x = i;//x记录一次遍历中最后一次交换的位置(无序的右边界)
                }
            }
            j = x;//递归的动态赋值
        } while (j != 0);//递归退出条件
    }
}
