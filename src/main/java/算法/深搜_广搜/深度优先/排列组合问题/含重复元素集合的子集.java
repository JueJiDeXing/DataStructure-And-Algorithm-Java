package 算法.深搜_广搜.深度优先.排列组合问题;

import java.util.*;

public class 含重复元素集合的子集 {
    //含重复元素的集合,求它的所有子集
    /*
    示例 ：
    输入：nums = [1,2,2]
    输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, res, new ArrayList<>());
        return new ArrayList<>(new HashSet<>(res));
    }

    public void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> list) {
        res.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;//已经添加过了,再遇到重复元素不添加
            list.add(nums[i]);
            dfs(nums, i + 1, res, list);
            list.remove(list.size() - 1);
        }
    }
}

