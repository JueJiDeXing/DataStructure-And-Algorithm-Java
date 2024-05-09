package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.*;

/**
 已AC
 */
public class B鸡哥的奇特密码 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] chars = sc.next().toCharArray();
        StringBuilder sb = new StringBuilder();
        int len = chars.length;
        int i = 0;
        while (i < len) {
            sb.append(chars[i]);
            if (chars[i] == 'L') {
                while (i < len && chars[i] == 'L') {
                    i++;//跳过中间的L
                }
            } else {
                i++;
            }
        }
        System.out.println(sb);
    }


}
