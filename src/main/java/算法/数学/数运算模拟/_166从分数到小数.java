package 算法.数学.数运算模拟;

import java.util.HashMap;
import java.util.Map;

public class _166从分数到小数 {
    /*
    给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
    如果小数部分为循环小数，则将循环的部分括在括号内。
    如果存在多个答案，只需返回 任意一个 。
    对于所有给定的输入，保证 答案字符串的长度小于 104 。
     */

    /**
     <h1>模拟竖式除法</h1>
     乘10除后取余,如果该余数已出现过,则找到循环节
     */
    public String fractionToDecimal(int numerator, int denominator) {
        // 转 long 计算，防止溢出
        long a = numerator, b = denominator;
        // 如果本身能够整除，直接返回计算结果
        if (a % b == 0) return String.valueOf(a / b);

        StringBuilder sb = new StringBuilder();
        if (a * b < 0) sb.append('-');
        a = Math.abs(a);
        b = Math.abs(b);
        // 计算小数点前的部分，并将余数赋值给 a
        sb.append(a / b).append(".");
        a %= b;
        Map<Long, Integer> map = new HashMap<>();//记录余数对应的索引出现位置
        while (a != 0) {
            // 记录当前余数所在答案的位置，并继续模拟除法运算
            map.put(a, sb.length());
            a *= 10;
            sb.append(a / b);
            a %= b;
            // 如果当前余数之前出现过，则将 [出现位置 到 当前位置] 的部分抠出来（循环小数部分）
            if (map.containsKey(a)) {
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }
}
