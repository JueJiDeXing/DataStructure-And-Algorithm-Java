package 算法.算法基础.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 难度:中等
 */
public class _39组数总和 {
    /*
    给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
    找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    candidates 中的 同一个 数字可以 无限制重复被选取 。
    如果至少一个数字的被选数量不同，则两种组合是不同的。
    对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, new LinkedList<>());
        return res;
    }

    /**
     @param candidates 全集
     @param remainder  剩余量
     @param start      起始组合索引
     @param stack      栈
     */
    public void dfs(int[] candidates, int remainder, int start, LinkedList<Integer> stack) {
        if (remainder < 0) {//无解
            return;
        }
        if (remainder == 0) {//找到解
            res.add(new ArrayList<>());
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (remainder < candidate) {//剪枝
                continue;
            }
            //深搜_广搜
            stack.push(candidate);
            dfs(candidates, remainder - candidate, i, stack);
            stack.pop();
        }
    }
}
