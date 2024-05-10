package 算法.算法基础.深搜_广搜.深度优先.排列组合问题;

import 算法.字符串._340至多含K个重复字符的最长子串;

import java.util.*;

public class 最多含K个重复元素的最长子数组长度 {

    /**
     {@link _340至多含K个重复字符的最长子串}
     */
    public int lengthOfLongestSub(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int max_len = 0;
        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            while (map.getOrDefault(num, 0) >= k) {
                int l = nums[left];
                map.put(l, map.get(l) - 1);

                left++;
            }
            if (right - left + 1 > max_len) max_len = right - left + 1;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return max_len;
    }
}
