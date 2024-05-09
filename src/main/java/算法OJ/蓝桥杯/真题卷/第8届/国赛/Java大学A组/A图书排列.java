package 算法OJ.蓝桥杯.真题卷.第8届.国赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class A图书排列 {
    /*
    1~10的排列,i和i+1不能相邻
     */
    public static void main(String[] args) {
        dfs(0, new boolean[10], "");//全排列
        int ans = 0;
        for (String num : list) {//检查是否有数字相邻
            if (check(num)) ans++;
        }
        System.out.println(ans);//479306
    }

    static boolean check(String num) {
        char[] s = num.toCharArray();
        for (int i = 1; i < s.length; i++) {
            if (Math.abs(s[i] - s[i - 1]) == 1) return false;
        }
        return true;
    }

    static List<String> list = new ArrayList<>();

    static void dfs(int i, boolean[] use, String num) {
        if (i == 10) {
            list.add(num);
            return;
        }
        for (int j = 0; j <= 9; j++) {
            if (!use[j]) {
                use[j] = true;
                dfs(i + 1, use, num + j);
                use[j] = false;
            }
        }
    }
}
