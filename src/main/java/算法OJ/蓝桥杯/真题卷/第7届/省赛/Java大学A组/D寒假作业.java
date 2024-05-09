package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class D寒假作业 {
    /*
     加减乘除的4个式子,一共12空,每空只能填1~13,且不重复
     求解法个数
     */
    public static void main(String[] args) {
        dfs(1, new boolean[14], new ArrayList<>());
        System.out.println(ans);//64
    }

    static int ans = 0;

    /**
     @param i         第几空
     @param isVisited 哪些数已使用
     @param stack     使用的数
     */
    static void dfs(int i, boolean[] isVisited, ArrayList<Integer> stack) {
        int size = stack.size();
        if (i == 3) {
            int need = stack.get(size - 2) + stack.get(size - 1);
            dfsAndPop(i, need, isVisited, stack);
            return;
        } else if (i == 6) {
            int need = stack.get(size - 2) - stack.get(size - 1);
            dfsAndPop(i, need, isVisited, stack);
            return;
        } else if (i == 9) {
            int need = stack.get(size - 2) * stack.get(size - 1);
            dfsAndPop(i, need, isVisited, stack);
            return;
        } else if (i == 12) {
            Integer v1 = stack.get(size - 2);
            Integer v2 = stack.get(size - 1);
            if (v1 % v2 != 0) return;
            int need = v1 / v2;
            if (!isVisited[need]) ans++;
            return;
        }
        for (int j = 1; j <= 13; j++) {
            dfsAndPop(i, j, isVisited, stack);
        }
    }

    static void dfsAndPop(int i, int vis, boolean[] isVisited, ArrayList<Integer> stack) {
        if (vis < 1 || vis > 13) return;
        if (isVisited[vis]) return;
        stack.add(vis);
        isVisited[vis] = true;
        dfs(i + 1, isVisited, stack);
        isVisited[vis] = false;
        stack.remove(stack.size() - 1);
    }
}
