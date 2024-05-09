package 算法.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _216组数总和3 {
    //数字1~9,选择k个,相加为target的组合
    //每个数字只能使用一次
    //1~n,任取k个数的组合
    public static void main(String[] args) {
        System.out.println(combineSum(8, 3));
    }

    static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> combineSum(int target, int k) {
        dfs(k, 1, target, new LinkedList<>());
        return lists;
    }

    /**
     @param k      目标个数
     @param start  当前索引,从start开始与后面进行组合
     @param target 还需要凑的大小
     @param stack  当前存储的组合
     */
    public static void dfs(int k, int start, int target, LinkedList<Integer> stack) {
        if (target < 0) {
            return;
        }
        if (stack.size() == k) {//凑够k个
            if (target == 0) {
                lists.add(new ArrayList<>(stack));
            }
            return;
        }
        for (int i = start; i <= 9; i++) {//1~9
            if (target < i || k - stack.size() > 10 - i) {//剪枝
                // 1.当前想放的i比target大
                // 2.缺少的个数 > 剩余可用元素数量,不需要再尝试组合
                break;
            }
            stack.push(i);
            dfs(k, i + 1, target - i, stack);
            stack.pop();
        }
    }
}
