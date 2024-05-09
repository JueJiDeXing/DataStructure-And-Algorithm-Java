package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class J反异或01串 {
    public static void main(String[] args) {
        //最多1的回文01串,如果是中心是1的奇数长度串,则无效
        Scanner sc = new Scanner(System.in);
        char[] T = sc.next().toCharArray();
        int len = T.length;
        longestPalindrome(T);//求1最多的回文01串,[left,right]
        int count = 0;
        for (int i = 0; i < left; i++) {
            if (T[i] == '1') count++;
        }
        for (int i = right + 1; i < len; i++) {
            if (T[i] == '1') count++;
        }
        System.out.println(count + count1);
    }

    static int left, right, count1;

    static void longestPalindrome(char[] chars) {
        left = -1;
        right = -1;
        count1 = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            //中心拓展算法
            if (chars[i] != '1') extend(chars, i, i);//一个字符作为中心点
            extend(chars, i, i + 1);//两个字符作为中心点
        }

    }

    static void extend(char[] chars, int i, int j) {
        // 选择一个中间点,向两边查找
        int count = 0;
        while (i >= 0 && j < chars.length && chars[i] == chars[j]) {
            if (chars[i] == '1') count++;
            i--;
            j++;
        }
        //直到不是回文,退出循环
        i++;
        j--;
        if (count > count1) {//记录区间
            left = i;
            right = j;
            count1 = count;
        }
    }
}
