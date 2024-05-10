package 算法.算法基础.排序.排序问题;

import java.util.ArrayList;
import java.util.Arrays;

public class 最大间距 {
    public static void main(String[] args) {
        最大间距 test = new 最大间距();
        System.out.println(test.maximumGap(new int[]{5, 20, 1, 4, 9, 10}));
    }

    //数组排序后,相邻元素的最大差值
    //限制:O(n)
    //桶排序,将排序后的数组元素间距转化为桶的间距
    static class Pair {//桶的最大最小值组成的数对
        int min = 1000_000_000;
        int max = 0;

        void add(int val) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }
    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        // 找这个数组的最大最小值
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();

        // 准备桶
        /* 计算桶个数               期望桶个数
        (max - min) / range + 1 =  a.length   -> range = (max - min) / (a.length -1)
        (max - min) / range + 1 =  a.length + 1   -> range = (max - min) / a.length
         */
        int range = Math.max((max - min) / nums.length, 1);//每个桶的元素个数
        int bucketSize = (max - min) / range + 1;//桶数比元素多一个,在有空桶的情况下,桶内间距不可能大于桶间距
        Pair[] buckets = new Pair[bucketSize];

        //放入数据
        for (int num : nums) {
            int index = (num - min) / range;
            if (buckets[index] == null) {
                buckets[index] = new Pair();
            }
            buckets[index].add(num);//每个桶只记录最大最小值
        }

        // 相邻元素(桶)的最大差值
        int r = Integer.MAX_VALUE;
        int lastMax = buckets[0].max;
        for (int i = 1; i < buckets.length; i++) {
            Pair bucket = buckets[i];
            if (bucket != null) {//跳过空桶
                r = Math.min(r, bucket.min - lastMax);
                lastMax = bucket.max;
            }
        }
        return r;
    }

    /**
     基数排序,桶排序会造成内存占用过多
     */
    public void radixSort(int[] a) {
        ArrayList<Integer>[] buckets = new ArrayList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) max = a[i];
        }
        int m = 1;//除数
        while (m <= max) {
            for (int i : a) {
                buckets[i / m % 10].add(i);//i/m%10从个位往前,按位桶排
            }
            int k = 0;
            for (ArrayList<Integer> bucket : buckets) {
                for (Integer i : bucket) {
                    a[k++] = i;
                }
                bucket.clear();
            }
            m *= 10;
        }
    }

}
