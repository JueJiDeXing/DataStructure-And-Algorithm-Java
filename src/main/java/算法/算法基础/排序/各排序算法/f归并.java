package 算法.算法基础.排序.各排序算法;

public class f归并 {
    /*
    初始:9 3 7 2 8 5 1 4
        9 3 7 2   8 5 1 4      split
        9 3   7 2   8 5   1 4  split
        9   3   7   2   8   5   1   4  split
        3 9   2 7   5 8   1 4  merge
        2 3 7 9   1 4 5 8    merge
        1 2 3 4 5 7 8 9     merge
     */

    public void mergeSort(int[] a1) {
        int[] a2 = new int[a1.length];//临时数组,在合并时将数据放入
        split(a1, 0, a1.length - 1, a2);
    }



    private void split(int[] a1, int left, int right, int[] a2) {
        // 2.治
        if (left == right) {
            return;
        }
        // 1.分
        int m = (left + right) >>> 1;//从中间分隔
        split(a1, left, m, a2);
        split(a1, m + 1, right, a2);

        // 3.合
        merge(a1, left, m, m + 1, right, a2);//合并两个有序区域到a2
        System.arraycopy(a2, 0, a1, left, right - left + 1);//将合并后的再存入a1
    }
    /**
     合并两个有序数组

     @param a1   数据源
     @param i    第一个数组的起始索引
     @param iEnd 第一个数组的结束索引
     @param j    第二个数组的起始索引
     @param jEnd 第二个数组的结束索引
     @param a2   临时数组
     */
    public void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = 0;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        //处理剩余没放入a2的部分
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }

    }
    //非递归实现--------------------------------------------------------------
    public void mergeSort2(int[] a1) {
        int n = a1.length;
        int[] a2 = new int[n];
        for (int width = 1; width < n; width *= 2) {//width:两个有序区间各自的宽度
            // [left,right] 待合并区间的左右边界
            for (int left = 0; left < n; left += 2 * width) {//width为1时,left:0,2,4,6  width为2时,left:0,4
                int right = Math.min(n - 1, left + 2 * width - 1);//right=left_next - 1,min防止越界
                int m = Math.min(n - 1, left + width - 1);
                merge(a1, left, m, m + 1, right, a2);
                System.arraycopy(a2, 0, a1, left, Math.min(n - left, 2 * width));
            }
        }
    }

    //归并＋插入----------------------------------------------------------
    private void split2(int[] a1, int left, int right, int[] a2) {
        // 2.治
        if (right - left <= 32) {//优化---分隔到长度为1太浪费
            //插入排序
            insertSort(a1, left, right);
            return;
        }
        // 1.分
        int m = (left + right) >>> 1;//从中间分隔
        split2(a1, left, m, a2);
        split2(a1, m + 1, right, a2);

        // 3.合
        merge(a1, left, m, m + 1, right, a2);//合并两个有序区域
        System.arraycopy(a2, left, a1, left, right - left + 1);
    }

    public void insertSort(int[] a, int left, int right) {
        for (int low = left + 1; low < right; low++) {
            int t = a[low];
            int i = low - 1;
            while (left <= i && t < a[i]) {//寻找插入位置
                a[i + 1] = a[i];//比待插入值大的右移,空出一个位置
                i--;
            }
            if (i != low - 1) {//找到插入位置
                a[i + 1] = t;
            }
        }
    }

}
