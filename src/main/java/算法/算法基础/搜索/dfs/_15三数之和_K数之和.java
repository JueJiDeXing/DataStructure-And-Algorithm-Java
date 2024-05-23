package 算法.算法基础.搜索.dfs;

import 算法.算法基础.双指针._15三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 难度:困难
 */
public class _15三数之和_K数之和 {
    /*
    三数之和:
    给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]]
    满足 i != j、i != k 且 j != k ， 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
    请你返回所有和为 0 且不重复的三元组。
    注意：答案中不可以包含重复的三元组。
     */

    /**
     将其引申为K数之和为target,可写出通解dfs<br>
     三数之和解法{@link  _15三数之和#threeSum(int[])}
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);//排序,使重复元素相邻,便于跳过,使答案没有重复
        dfs(nums, 3, 0, 0, nums.length - 1);
        return res;
    }

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> stack = new LinkedList<>();

    /**
     搜索在升序nums数组[start,end]中n数之和为target的元组,结果存储在res中
     */
    public void dfs(int[] nums, int n, int target, int start, int end) {
        if (n == 2) {//两数之和
            twoSum(nums, start, end, target);
            return;
        }
        for (int k = start; k < end; k++) {
            if (k > start && nums[k] == nums[k - 1]) continue;//跳过重复项
            if (nums[k] > target) break;//优化1:因为nums升序,所以如果第k项大于target,那么后面的都更大
            stack.push(nums[k]);
            dfs(nums, n - 1, target - nums[k], k + 1, end);//n数之和化n-1数之和,将n化到2后可以直接调用两数之和方法
            stack.pop();
        }
    }

    /**
     搜索在nums数组[i,j]中和为target的元组,结果存储在res中
     */
    public void twoSum(int[] nums, int i, int j, int target) {
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum < target) {
                i++;
            } else if (sum > target) {
                j--;
            } else {//找到解
                List<Integer> result = new ArrayList<>(stack);
                result.add(nums[i]);
                result.add(nums[j]);
                res.add(result);
                i++;//继续搜索
                j--;
                while (i < j && nums[i] == nums[i - 1]) i++;//去重
                while (i < j && nums[j] == nums[j + 1]) j--;
            }
        }
    }

}
