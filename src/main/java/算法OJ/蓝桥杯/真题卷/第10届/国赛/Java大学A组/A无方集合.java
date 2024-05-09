package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.util.*;

/**
 已AC,但运行过慢
 */
public class A无方集合 {
    /*
    1~100中选择k个数,组成一个集合
    集合中任意两个数不能相加得到完全平方数,且自身不是完全平方数
    求k的最大值
     */
    static HashSet<Integer> PerfectSquare = new HashSet<>(Arrays.asList(1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196));
    static List<Integer>[] cannotAddMap = new List[101];//map[i]:所有小于等于i的数 and 大于i的数中是完全平方数,或与i相加是完全平方数的

    static {
        String ans1 = "6, 8, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 96, 99";
        String ans2 = "3, 7, 8, 11, 15, 19, 23, 27, 31, 32, 35, 39, 40, 43, 44, 47, 51, 52, 55, 59, 63, 67, 71, 72, 75, 76, 79, 80, 83, 84, 87, 88, 91, 95, 96, 99";
        String ans3 = "6, 8, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 48, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 99";
        String ans4 = "6, 8, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 48, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 76, 78, 80, 82, 84, 86, 88, 90, 95, 99";
        String ans5 = "6, 8, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 96, 99";
        String ans6 = "6, 8, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 76, 78, 80, 82, 84, 86, 88, 90, 95, 96, 99";
        String ans7 = "8, 10, 19, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 48, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 74, 76, 78, 80, 82, 84, 86, 88, 99";
        String ans8 = "8, 10, 19, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 48, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 76, 78, 80, 82, 84, 86, 88, 95, 99";
        String ans9 = "8, 10, 19, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 74, 76, 78, 80, 82, 84, 86, 88, 96, 99";
        String ans10 = "8, 10, 19, 21, 23, 27, 29, 32, 34, 38, 40, 42, 44, 46, 50, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 72, 76, 78, 80, 82, 84, 86, 88, 95, 96, 99";

        Arrays.setAll(cannotAddMap, k -> new ArrayList<>());
        for (int i = 1; i < 100; i++) {
            for (int j = 1; j < 100; j++) {
                if (j <= i || PerfectSquare.contains(j) || PerfectSquare.contains(i + j)) cannotAddMap[i].add(j);
            }
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> whatCanAdd = new HashSet<>();
        for (int i = 1; i < 100; i++) {
            if (!PerfectSquare.contains(i)) {
                whatCanAdd.add(i);
            }
        }
        for (int i = 1; i < 100; i++) {
            if (PerfectSquare.contains(i)) continue;
            stack.push(i);
            HashSet<Integer> canAdd = new HashSet<>(whatCanAdd);
            cannotAddMap[i].forEach(canAdd::remove);
            dfs(i, stack, canAdd);
            stack.pop();
            whatCanAdd.remove(i);
        }
        System.out.println(max);
    }

    static int max = 36;

    /**
     当前已加入addNums这些数,现在可加的数为[i+1,100]

     @param i          上一个加的数
     @param addNums    当前集合加入的数
     @param whatCanAdd 还能加入的大于i的数
     */
    static void dfs(int i, Stack<Integer> addNums, HashSet<Integer> whatCanAdd) {
        if (PerfectSquare.contains(i)) {
            dfs(i + 1, addNums, whatCanAdd);
            return;
        }
        if (addNums.size() >= max) {
            System.out.println(addNums.size());
            System.out.println(addNums);
            max = addNums.size();
        }
        if (addNums.size() + whatCanAdd.size() < max) return;
        for (int j : whatCanAdd) {
            addNums.push(j);
            HashSet<Integer> canAdd = new HashSet<>(whatCanAdd);
            cannotAddMap[j].forEach(canAdd::remove);
            dfs(j, addNums, canAdd);
            addNums.pop();
        }
    }
}
