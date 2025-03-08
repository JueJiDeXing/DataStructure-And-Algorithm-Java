package 算法.算法基础.排序.各排序算法;

public class h计数 {
    //遍历数组,统计个数放入计数数组,再遍历计数数组,读取个数放入原数组
    // 计数数组的长度为原数组最大值+1,索引对应原数组的元素个数
    // 前提:原数组的所有元素都>=0,且最大值不能太大
    /*例
    原:[5,1,1,3,0]
    计数:[1,2,0,1,0,1] -> 1个0,2个1,0个2,1个3,0个4,1个5
    -> [0,1,1,3,5]
     */
    public void countSort1(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        int[] count = new int[max + 1];
        for (int v : a) count[v]++;

        int k = 0;//配合原数组使用的索引值
        for (int i = 0; i < count.length; i++) {
            //i表示原数组元素,count[i]表示元素出现次数
            while (count[i] > 0) {
                a[k++] = i;
                count[i]--;
            }
        }
    }

    //优化:待排元素为负
    // 使最小值映射到0索引
    public void countSort2(int[] a) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
            if (a[i] < min) min = a[i];
        }
        int[] count = new int[max - min + 1];
        for (int v : a) count[v - min]++;//原元素值-最小值=索引

        int k = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                a[k++] = i+min;//索引+最小值=原元素值
                count[i]--;
            }

        }
    }
}
