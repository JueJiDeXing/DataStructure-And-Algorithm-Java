package 算法.算法基础.排序.排序问题;

import java.util.ArrayList;
import java.util.Arrays;

public class 最大间距 {
    /**
     _164最大间距

     @param args
     */
    public static void main(String[] args) {
        最大间距 test = new 最大间距();
        System.out.println(test.maximumGap(new int[]{5, 20, 1, 4, 9, 10}));
    }

    //数组排序后,相邻元素的最大差值
    //限制:O(n)

    /**
    设数组大小为n, 最小值min, 最大值max
    则问题可以表示为, 在[min,max]上切n-2刀, 最长的一块是多长

    考虑平均长度: d = (max - min) / (n-1) 上取整
    在最极限的情况下, 恰好平分, 答案为d
    其他情况下, 一定有大于d的
    即: 答案质至少为d

    根据"答案至少为d", 用桶排序
    设置一个桶的桶内距离最大为d-1, 那么答案一定就是桶与桶之间的距离
     */
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        // 找这个数组的最大最小值
        int max = Arrays.stream(nums).max().getAsInt();
        int min = Arrays.stream(nums).min().getAsInt();
        int diff = max - min;
        if (diff <= 1) return diff;
        // 准备桶
        int d = (diff + n - 2) / (n - 1); // 答案至少是d
        int bucketCnt = diff / d + 1;
        int[][] buckets = new int[bucketCnt][2];
        for (int[] b : buckets) {
            b[0] = Integer.MAX_VALUE;
            b[1] = Integer.MIN_VALUE;
        }
        for (int x : nums) {
            int[] b = buckets[(x - min) / d];
            b[0] = Math.min(b[0], x); // 维护桶内元素的最小值和最大值
            b[1] = Math.max(b[1], x);
        }

        int ans = 0;
        int preMax = Integer.MAX_VALUE;
        for (int[] b : buckets) {
            if (b[0] != Integer.MAX_VALUE) { // 非空桶
                // 桶内最小值，减去上一个非空桶的最大值
                ans = Math.max(ans, b[0] - preMax);
                preMax = b[1];
            }
        }
        return ans;

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
