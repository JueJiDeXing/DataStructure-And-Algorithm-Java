package 算法.算法基础.双指针;

import java.util.*;

/**
 难度:困难
 */
public class _15三数之和 {
    /*
    给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
    满足 i != j、i != k 且 j != k ， 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
    请你返回所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。
     */

    /**
     <h1>排序+双指针</h1>
     枚举下标i,在[i+1,n-1]中使用双指针(target=-nums[i]的两数之和)寻找满足条件的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; ++i) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) continue; // 跳过重复数字
            if (x + nums[i + 1] + nums[i + 2] > 0) break; // 优化一
            if (x + nums[n - 2] + nums[n - 1] < 0) continue; // 优化二
            //两数之和
            int j = i + 1, k = n - 1;
            while (j < k) {
                int s = x + nums[j] + nums[k];
                if (s > 0) --k;
                else if (s < 0) ++j;
                else {
                    // 找到一组三元组
                    ans.add(Arrays.asList(x, nums[j], nums[k]));
                    for (++j; j < k && nums[j] == nums[j - 1]; ) ++j; // 跳过重复数字 //如果有的题是计算数对个数,可以在这里统计重复数字个数,再组合计数
                    for (--k; k > j && nums[k] == nums[k + 1]; ) --k;
                }
            }
        }
        return ans;
    }

}
