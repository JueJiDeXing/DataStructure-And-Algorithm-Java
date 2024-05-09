package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.util.ArrayList;
import java.util.*;

/**
 已AC
 */
public class B9数算式 {
    /*
    a*b=c
    其中a和b恰好使用1~9各一次,c也恰好使用1~9各一次
    例如: 9213 * 85674 = 789314562
     */

    /**
     枚举1~9的全排列, 取里面的数字划分给a和b,此时a和b一定满足要求,再检查a*b是否满足要求即可
     */
public static void main(String[] args) {
    int ans = 0;
    dfs(0, new boolean[10], 0);//求全排列
    for (int num : list) {
        for (int lenA = 5; lenA < 9; lenA++) {//乘数与被乘数交换为同一方案,这里只算a>b的方案
            String s = String.valueOf(num);
            int a = Integer.parseInt(s.substring(0, lenA));//a,b满足要求
            int b = Integer.parseInt(s.substring(lenA));
            if (check("" + (long) a * b)) { //检查c=a*b是否满足要求
                ans++;
            }
        }
    }
    System.out.println(ans);//1625
}

static List<Integer> list = new ArrayList<>();//全排列

/**
 @param i     当前枚举到第几位
 @param isUse 哪些数字已被使用
 @param num   当前枚举出来的数
 */
static void dfs(int i, boolean[] isUse, int num) {
    if (i == 9) {//枚举完了9个数字
        list.add(num);
        return;
    }
    for (int j = 1; j <= 9; j++) {//枚举下一位数字j
        if (!isUse[j]) {
            isUse[j] = true;
            dfs(i + 1, isUse, num * 10 + j);
            isUse[j] = false;
        }
    }
}

static boolean check(String c) {
    if (c.length() != 9) return false;//使用1~9,长度一定为9
    boolean[] use = new boolean[10];
    for (char ch : c.toCharArray()) {
        if (ch == '0') return false;//0不能出现
        if (use[ch - '0']) return false;//不能重复使用一个数字
        use[ch - '0'] = true;
    }
    return true;
}
}
