package 算法OJ.洛谷.ETOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;
/**
 无法提交答案
 */
public class P1023同鱼系  {
    /*
    给出一个长度为n的数组a,和一个整数k
    可以执行任意次操作(可以为0次):
    选择一个下标i,将a[i]与a[i+k]交换 // 0<=i<n-k
     问是否能将a变为非降数组,输出Yes或No
     */
    /**
     该操作等同于将数组a按步长k分组
     整个数组非降序,所以这k组组内一定是非降序的
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int(), k = Int();
        //分组+组内排序
        Queue<Integer>[] group = new PriorityQueue[k];
        Arrays.setAll(group, __ -> new PriorityQueue<>());
        for (int i = 0; i < n; i++) {
            group[i % k].offer(Int());
        }
        //检查新序列
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = group[i % k].poll();
            if (i > 0 && ans[i] < ans[i - 1]) {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }
}
