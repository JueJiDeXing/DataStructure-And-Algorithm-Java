package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.util.Scanner;

/**
 已AC
 */
public class K最后的元音 {
    /*
    元音字母 a e i o u
    求输入字符串的最后一个元音是什么(一定存在)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u') {
                System.out.println(s[i]);
                break;
            }
        }
    }
}
