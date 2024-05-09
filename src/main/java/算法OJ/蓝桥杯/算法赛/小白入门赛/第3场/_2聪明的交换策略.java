package 算法OJ.蓝桥杯.算法赛.小白入门赛.第3场;

import java.util.*;

/**
 已AC
 */
public class _2聪明的交换策略 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] s = sc.next().toCharArray();
        //把1搬去右侧
        long op1 = 0;
        int i = 0, j = n - 1;
        while (i < j) {
            if (s[j] == '1') {
                j--;
                continue;
            }
            if (s[i] == '0') {
                i++;
                continue;
            }
            op1 += j - i;
            i++;
            j--;
        }
        //把1搬去左侧
        long op2 = 0;
        i = n - 1;
        j = 0;
        while (j < i) {
            if (s[j] == '1') {
                j++;
                continue;
            }
            if (s[i] == '0') {
                i--;
                continue;
            }
            op2 += i - j;
            i--;
            j++;
        }
        System.out.println(Math.min(op1, op2));
    }
}
