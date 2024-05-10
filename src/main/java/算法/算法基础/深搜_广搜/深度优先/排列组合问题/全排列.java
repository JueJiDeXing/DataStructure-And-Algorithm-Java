package 算法.算法基础.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 全排列 {
    //求一个序列的全部排列

    /**
     @param nums 不含重复元素的序列
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        dfs(nums, 0, lists, new ArrayList<>(), new boolean[nums.length]);
        return lists;//传入数组有重复元素时需要对排列去重
    }

    /**
     深度优先搜索,多路递归

     @param nums
     @param depth  当前深度(已排列元素个数)
     @param lists  所有排列
     @param list   当前排列
     @param isUsed 记录当前排列中已使用的元素
     */
    public static void dfs(int[] nums, int depth, List<List<Integer>> lists, List<Integer> list, boolean[] isUsed) {
        if (depth == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {//多路递归,枚举所有情况
            if (isUsed[i]) {//数组记录是否已使用过
                continue;
            }
            isUsed[i] = true;//未使用过则可以加入,进行递归
            list.add(nums[i]);
            int index = list.size() - 1;
            dfs(nums, depth + 1, lists, list, isUsed);//递归下一层搜索
            //list.remove((Integer) nums[i]);有重复元素时不可使用此方法
            list.remove(index);//出栈
            isUsed[i] = false;
        }
    }

    /**
     对重复元素的处理

     @param nums
     @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);//对数组排序,使重复元素相邻
        dfs2(nums, 0, lists, new ArrayList<>(), new boolean[nums.length]);
        return lists;
    }

    public static void dfs2(int[] nums, int depth, List<List<Integer>> lists, List<Integer> list, boolean[] isUsed) {
        if (depth == nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {//多路递归,枚举所有情况
            if (i > 0 && nums[i] == nums[i - 1] && !isUsed[i - 1]) {//重复元素排列时必须先排列前面的
                continue;
            }
            if (isUsed[i]) {//数组记录是否已使用过
                continue;
            }
            isUsed[i] = true;//未使用过则可以加入,进行递归
            list.add(nums[i]);
            int index = list.size() - 1;
            dfs2(nums, depth + 1, lists, list, isUsed);//递归下一层搜索
            //list.remove((Integer) nums[i]);有重复元素时不可使用此方法
            list.remove(index);//出栈
            isUsed[i] = false;
        }
    }


}
