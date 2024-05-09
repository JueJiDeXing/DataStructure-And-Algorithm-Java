package 算法.排序.各排序算法;

import 数据结构.数组.DynamicArray;


public class I桶排 {

    /*
    将元素放入多个桶中,每个桶有一个数据范围,桶按升序排列,遍历桶,将桶内元素排序后放入原数组<br>
    (数组元素映射到外桶升序,再使内桶升序,放回原数组即可)
     */

    public void bucketSort1(int[] num) {//示例
        // 准备桶
        DynamicArray[] buckets = new DynamicArray[10];//年龄0~99
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }

        //放入数据
        for (int n : num) {
            buckets[n / 10].add(n);
        }

        int k = 0;
        for (DynamicArray bucket : buckets) {//桶为分区升序 0~9 10~19...
            //排序桶内元素
            int[] array = bucket.array();
            new d插入().insertSort(array);
            //放入原数组
            for (int value : array) {
                num[k++] = value;
            }
        }
    }

    /**
     <h1>优化:桶内元素分布不均</h1>

     @param range 每个桶的元素个数
     */
    public void bucketSort2(int[] a, int range) {
        int max = a[0];
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
            if (a[i] < min) min = a[i];
        }
        // 准备桶
        DynamicArray[] buckets = new DynamicArray[(max - min) / range + 1];//减少了桶的个数
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new DynamicArray();
        }

        //放入年龄数据
        for (int age : a) {
            buckets[(age - min) / range].add(age);//映射关系改变
        }

        int k = 0;
        for (DynamicArray bucket : buckets) {
            //排序桶内元素
            int[] array = bucket.array();
            new d插入().insertSort(array);
            //放入原数组
            for (int value : array) {
                a[k++] = value;
            }
        }
    }
}
