package 算法.算法基础.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.List;

public class _60排列序列 {
    /*
    给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
    按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
    "123"
    "132"
    "213"
    "231"
    "312"
    "321"
    给定 n 和 k，返回第 k 个排列。
     */

    /**
     <h1>全排列-深搜解法</h1>
     {@link 全排列}
     */
    public String getPermutation(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i);
        }
        List<Integer> ans = dfs(nums, 0, k, lists, new ArrayList<>(), new boolean[n]);
        StringBuilder sb = new StringBuilder();
        for (int i : ans) {
            sb.append(i);
        }
        return sb.toString();
    }

    int curr = 0;

    /**
     深度优先搜索,多路递归

     @param nums   [1,n]
     @param depth  当前深度(已排列元素个数)
     @param lists  所有排列
     @param list   当前排列
     @param isUsed 记录当前排列中已使用的元素
     */
    public List<Integer> dfs(List<Integer> nums, int depth, int k, List<List<Integer>> lists, List<Integer> list, boolean[] isUsed) {
        if (depth == nums.size()) {
            curr++;
            if (curr == k) {//当计算到第k个排列时返回这个排列
                return list;
            }
            lists.add(new ArrayList<>(list));
            return null;
        }
        for (int i = 0; i < nums.size(); i++) {//多路递归,枚举所有情况
            if (isUsed[i]) {//数组记录是否已使用过
                continue;
            }
            isUsed[i] = true;//未使用过则可以加入,进行递归
            list.add(nums.get(i));
            int index = list.size() - 1;
            List<Integer> ans = dfs(nums, depth + 1, k, lists, list, isUsed);
            if (ans != null) {
                return ans;
            }
            //递归下一层搜索
            list.remove(index);//出栈
            isUsed[i] = false;
        }
        return null;
    }

    /**
     <h1>数学解法</h1>
     */
    public String getPermutation2(int n, int k) {
        return "";
    }
}
