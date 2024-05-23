package 算法.算法基础.搜索.dfs.排列组合问题;

import java.util.ArrayList;
import java.util.List;

public class 全子集 {
    //不含重复元素的集合,求它的所有子集
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        doSub(0, new ArrayList<>(), nums, res);
        return res;
    }

    public static void doSub(int start, ArrayList<Integer> path, int[] nums, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            doSub(i + 1, path, nums, res);//递归start从i+1开始
            //path.remove((Integer) nums[i]);
            path.remove(path.size() - 1);
        }
    }
    /*回溯算法
            /--2--3
        /--1 --3
       /
    null --2 --3
       \
        \--3
     */
}
