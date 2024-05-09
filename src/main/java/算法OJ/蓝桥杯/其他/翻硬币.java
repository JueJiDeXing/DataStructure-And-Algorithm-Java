package 算法OJ.蓝桥杯.其他;

import java.util.*;

/**
 已AC
 */
public class 翻硬币 {
    /*
    *为正面,o为反面
    桌上一排硬币,每次可以选择两个相邻硬币将他们都翻面
    初始状态为s,期望状态为t
    问从s到t最少翻多少次硬币
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] s = scan.next().toCharArray(), t = scan.next().toCharArray();
        int n = s.length;

        int count = 0;
        for (int i = n - 2; i >= 0; i--) {//贪心地翻转即可,从最后开始,如果第二位不同则需要翻转
            if (s[i + 1] != t[i + 1]) {
                s[i] = change(s[i]);
                s[i + 1] = change(s[i + 1]);
                count++;
            }
        }
        System.out.println(count);
    }

    private static char change(char ch) {
        return ch == '*' ? 'o' : '*';
    }
}
