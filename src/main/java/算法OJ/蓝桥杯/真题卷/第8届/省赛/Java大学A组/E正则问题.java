package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class E正则问题 {
    /*
    求x()|组成的正则表达式能匹配的最大长度
    例如:
    ((xx|xxx)x|(x|xx))xx -> ( xxx x| xx)xx 能匹配 xxx x xx 长度为6
    ( xx(xx|x)xx ((x)|(x)xx|x(x)|(x)x) x(x)xx(x|xx(xx(xx)|xx)) | (x|(xxx(x|xx)|xxx)) xxx(x)xxx(x) | xxxxxxxxx
    xx_xxxxx_xxxxx_xxxxx_xxxxx
     */

/**
 递归分割子问题求解
 */
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    s = sc.next();
    System.out.println(solve());
}

static int pos = -1;
static String s;


static int solve() {
    int max = 0, curr = 0;
    while (pos < s.length() - 1) {
        pos++;
        if (s.charAt(pos) == '(') {//解析下一层
            curr += solve();
        } else if (s.charAt(pos) == 'x') {//x直接拼接到当前段
            curr++;
        } else if (s.charAt(pos) == '|') {//或运算,取当前段和上一段的最大值
            max = Math.max(curr, max);
            curr = 0;
        } else {//这一层结束了
            break;
        }
    }
    return Math.max(max, curr);
}
}
