package 数据结构.栈.栈问题.单调栈应用;

import java.util.Arrays;

public class _670最大交换 {
    /*
    给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
     */

    /**
     <h1>单调栈解法</h1>
     */
    public int maximumSwap(int num) {
        //转数组存储
        int[] number = new int[10];//[0,len)存储num的低位到高位
        int len = 0, temp_num = num;
        while (temp_num > 0) {
            int t = temp_num % 10;
            number[len++] = t;
            temp_num /= 10;
        }
        //单调递减栈求number中索引i左侧第一个大于等于i的索引
        int[] stack = new int[len], mask = new int[len];
        int k = 0;
        Arrays.fill(mask, -1);
        for (int i = 0; i < len; i++) {
            while (k > 0 && number[stack[k - 1]] < number[i]) {//把小于它的抛出
                k--;
            }
            if (k > 0) mask[i] = stack[k - 1];//栈中有比他大的(或相等)
            stack[k++] = i;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (mask[i] == -1) continue;//左边没有比它大的
            int j = i;//查找最左侧比i大的(大于等于都跳转)
            while (mask[j] != -1) {
                j = mask[j];
            }
            if (number[j] == number[i]) continue;//两个数相同不能交换
            //交换i和j
            int t = number[i];
            number[i] = number[j];
            number[j] = t;
            int ans = 0;
            for (int m = len - 1; m >= 0; m--) {
                ans = ans * 10 + number[m];
            }
            return ans;
        }
        //数是递减的,不交换
        return num;
    }

    /**
     <h1>贪心解法</h1>
     */
    public int maximumSwap2(int num) {
        char[] str = (num + "").toCharArray();
        char[] s = str.clone();
        Arrays.sort(s);
        //找第一个不同的
        int f = 0;
        while (f < s.length) {
            if (str[f] != s[s.length - f - 1]) break;
            f++;
        }
        if (f == s.length) return num;
        //在后面找最大的,最大里面最远的
        int k = f + 2, max = f + 1;
        while (k < s.length) {
            if (str[k] >= str[max]) max = k;
            k++;
        }
        //交换
        char t = str[f];
        str[f] = str[max];
        str[max] = t;
        return Integer.parseInt(new String(str));
    }
}
