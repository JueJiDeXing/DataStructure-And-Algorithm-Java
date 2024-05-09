package 算法.排序.各排序算法;

public class d插入 {
    //将数组分为两部分,已排序[0,low-1]和未排序[low,length-1]
    //每次从未排区取出low位置的元素,寻找插入位置

    /**
     <h1>不使用递归</h1>
     */
    public void insertSort(int[] a) {
        for (int low = 1; low < a.length; low++) {
            int t = a[low];
            int i = low - 1;
            while (0 <= i && t < a[i]) {//寻找插入位置
                a[i + 1] = a[i];//比待插入值大的右移,空出一个位置
                i--;
            }
            if (i != low - 1) {//找到插入位置
                a[i + 1] = t;
            }
        }
    }

    /**
     <h1>使用递归</h1>

     @param low 初始值为1,表示索引0已排序,从索引1开始为未排序区域
     */
    public  void insertSort(int[] arr, int low) {
        if (low == arr.length) {
            return;
        }
        int temp = arr[low];//要排序的元素
        int i = low - 1;//已排序的区域0~low-1
        while (i >= 0 && arr[i] > temp) {
            //从右向左查找第一个比arr[low]小的元素,确定插入位置
            arr[i + 1] = arr[i];//比temp大的元素向后移动
            i--;
        }
        //找到插入位置
        if (i + 1 != low) {//小优化,如果插入位置就是low的位置,则可以减少一次赋值
            arr[i + 1] = temp;
        }

        insertSort(arr, low + 1);
    }
}
