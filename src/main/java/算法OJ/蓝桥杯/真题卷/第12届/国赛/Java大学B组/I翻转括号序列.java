package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学B组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class I翻转括号序列 {
    /*
    给出一个长度为n的括号序列
    操作1: 将[L,R]翻转,左括号变右括号,右括号变左括号
    操作2: 求以L为左端点,最长的合法括号序列对应的R
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignore) {
        }
        return (int) st.nval;
    }

    static String Str(int n) {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < n; i++) {
                st.nextToken();
                sb.append(st.toString().charAt(7));
            }
        } catch (Exception ignore) {
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n = Int();
        int m = Int();
        String sequence = Str(n);
        int[] array = new int[n + 1];//array[i]:1为左括号,-1为右括号
        for (int i = 1; i <= n; i++) {
            array[i] = sequence.charAt(i - 1) == '(' ? 1 : -1;
        }
        for (int i = 0; i < m; i++) {
            int operate = Int();
            if (operate == 1) {//翻转
                int left = Int(), right = Int();
                for (int j = left; j <= right; j++) {
                    array[j] *= -1;
                }
            } else {//求最长括号序列
                int left = Int();
                int right = 0;
                int pair = 0;//0:括号成对,有效括号序列; <0:右括号多于左括号,非有效括号序列; >0:左括号多于右括号,待有效括号序列
                for (int j = left; j <= n; j++) {
                    pair += array[j];
                    if (pair == 0) {//左右括号匹配
                        right = j;
                    } else if (pair == -1) {//右括号多于左括号时为非法括号序列,即使后面有左括号
                        break;
                    }
                }
                System.out.println(right);
            }
        }
    }
}
