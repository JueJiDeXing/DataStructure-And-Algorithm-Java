package 算法.算法基础.二分;

import java.util.Arrays;

/**
 第 290 场周赛 Q4
 分数:2022
 */
public class _2251花期内花的数目 {
    /*
    给你一个下标从 0 开始的二维整数数组 flowers ，
    其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。
    同时给你一个下标从 0 开始大小为 n 的整数数组 people ，people[i] 是第 i 个人来看花的时间。

    请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
     */
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        //t时刻的花数=t之前的开花数-t之前的凋落数
        int n = flowers.length;
        int[] start = new int[n], end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        System.out.println("start--" + Arrays.toString(start));
        System.out.println("end--" + Arrays.toString(end));
        int k = people.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = binarySearch(start, people[i] + 1) - binarySearch(end, people[i]);
        }
        return ans;
    }

    /**
     返回>=target的最小下标
     */
    int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int res = right + 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= target) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
