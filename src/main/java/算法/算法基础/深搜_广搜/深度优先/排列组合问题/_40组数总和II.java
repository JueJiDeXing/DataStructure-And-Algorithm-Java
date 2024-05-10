package 算法.算法基础.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 难度:中等
 */
public class _40组数总和II {
    /*
    给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

    candidates 中的每个数字在每个组合中只能使用 一次 。
    
    注意：解集不能包含重复的组合。
     */
    // 每个数字只能使用1次
    // 给出的集合可能存在重复元素,结果不能出现重复的组合
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//排序,使重复元素相邻
        dfs(candidates, target, 0, new LinkedList<>(), new boolean[candidates.length]);
        return res;
    }

    /**
     @param candidates 全集
     @param remainder  剩余量
     @param start      起始组合索引
     @param stack      栈
     @param isUsed     记录是否使用
     */
    public void dfs(int[] candidates, int remainder, int start, LinkedList<Integer> stack, boolean[] isUsed) {
        if (remainder < 0) return;   //无解
        if (remainder == 0) {//找到解
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (remainder < candidate) continue; //剪枝
            if (i > 0 && candidate == candidates[i - 1] && !isUsed[i - 1]) {
                continue; //遇到重复元素,必须先使用前面的才能使用后面重复的
            }
            stack.push(candidate);
            isUsed[i] = true;
            dfs(candidates, remainder - candidate, i + 1, stack, isUsed);//start从i+1开始,一个数字不能重复使用
            isUsed[i] = false;
            stack.pop();
        }
    }

    /**
     剪枝
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] cnt = new int[target+1];//计数,[1,target]
        for (int j : candidates) {
            if (j <= target)  cnt[j]++;  //计数,大于目标值的部分忽略
        }
        List<List<Integer>> ans = new ArrayList<>();
        if (cnt[target] != 0) ans.add(Arrays.asList(target));//若数组中存在x,直接加入答案即可
        //再次剪枝，去除不存在的元素，并记录限制个数
        List<Integer> itemValue = new ArrayList<>(), count = new ArrayList<>();
        for (int i = 1; i < target; i++) {
            if (cnt[i] == 0) continue;
            itemValue.add(i);
            count.add(cnt[i]);
        }
        LinkedList<Integer> stack = new LinkedList<>();
        //深度搜索
        dfs(itemValue, count, ans, stack, target, 0, 0);
        return ans;
    }

    public void dfs(List<Integer> itemValue, List<Integer> count, List<List<Integer>> ans,
                    LinkedList<Integer> stack, int target, int sum, int start) {
        if (start >= itemValue.size()) return;//达到结尾
        int cur = itemValue.get(start);
        if (sum + cur > target) return;
        int tempSum = 0;
        //先加满
        int c = count.get(start);
        while (sum + tempSum < target && c > 0) {
            c--;
            tempSum += cur;
            stack.add(cur);
        }
        //一边回溯一边进行深度遍历,并不再考虑当前值
        //若此时sum+tempSum<target，后续操作会少一次，进行补全
        if (sum + tempSum < target) {
            dfs(itemValue, count, ans, stack, target, sum + tempSum, start + 1);
        } else if (sum + tempSum == target) {//加满后判断是否符合
            ans.add(new ArrayList<>(stack));
        }
        if (sum + tempSum >= target) {
            tempSum -= cur;
            stack.removeLast();
        }
        while (tempSum > 0) {
            tempSum -= cur;
            stack.removeLast();
            dfs(itemValue, count, ans, stack, target, sum + tempSum, start + 1);
        }
    }
}
