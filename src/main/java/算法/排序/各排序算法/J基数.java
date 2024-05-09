package 算法.排序.各排序算法;

import java.util.ArrayList;

public class J基数 {
    //基数排序
    public void RadixSort(int[] arr) {
        int n = arr.length;
        //max为数组中最大值,循环次数为最大数的位数
        int max = arr[0];
        for (int j : arr) {
            if (j > max) max = j;
        }
        int[] tmp = new int[n];//临时数组
        int base = 1;
        while (max / base > 0) {
            int[] bucket = new int[10]; // 0~9
            for (int j : arr) {
                int k = j / base % 10;
                bucket[k]++;//统计数位为k的有多少个
            }
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];//累加和,统计数位为k的前面有多少数字
            }
            //开始放数到临时数组tmp
            for (int i = n - 1; i >= 0; i--) {
                int k = arr[i] / base % 10;
                int index = bucket[k] - 1;//要放到temp数组的位置
                tmp[index] = arr[i];
                bucket[k]--;
            }
            //拷贝回原数组
            System.arraycopy(tmp, 0, arr, 0, n);
            base *= 10;//排下一位
        }
    }

    //场景:排序电话号码(字符串),数字太大用其他算法效率低
    //先按个位排序,再按十位排序...
    public void radixSort1(String[] a, int length) {//length:每个字符串的长度
        //准备桶
        ArrayList<String>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        //按位 桶排序
        for (int i = length - 1; i >= 0; i--) {
            for (String s : a) {
                buckets[s.charAt(i) - '0'].add(s);
            }
            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                bucket.clear();
            }
        }
    }

    //扩大排序范围:原来10个桶存储0~9,现在128个桶存储127个字符
    public void radixSort2(String[] a, int length) {//length:每个字符串的长度
        //准备桶
        ArrayList<String>[] buckets = new ArrayList[128];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        //按位 桶排序
        for (int i = length - 1; i >= 0; i--) {

            for (String s : a) {
                buckets[s.charAt(i)].add(s);
            }

            int k = 0;
            for (ArrayList<String> bucket : buckets) {
                for (String s : bucket) {
                    a[k++] = s;
                }
                bucket.clear();
            }
        }
    }
}
