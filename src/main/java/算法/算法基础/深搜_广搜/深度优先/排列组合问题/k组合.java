package 算法.算法基础.深搜_广搜.深度优先.排列组合问题;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class k组合 {
    //1~n,任取k个数的组合
    public static void main(String[] args) {
        System.out.println(combine(5, 3));
    }

    static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new LinkedList<>());
        return lists;
    }

    /**
     @param n     1~n的数
     @param k     目标个数
     @param start 当前索引,从start开始与后面进行组合
     @param stack 当前存储的组合
     */
    public static void dfs(int n, int k, int start, LinkedList<Integer> stack) {
        if (stack.size() == k) {//凑够k个
            lists.add(new ArrayList<>(stack));
            return;
        }
        for (int i = start; i <= n; i++) {
            if (k - stack.size() > n - i + 1) {//剪枝
                break; // 缺少的个数 > 剩余可用元素数量,不需要再尝试组合
            }
            stack.push(i);
            dfs(n, k, i + 1, stack);//将start改为1,即为k排列
            stack.pop();
        }
    }
}
