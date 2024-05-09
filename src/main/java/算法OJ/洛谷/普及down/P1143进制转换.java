package 算法OJ.洛谷.普及down;

import java.util.*;
/**
 已AC
 */
public class P1143进制转换 {
    static HashMap<Character, Integer> CharToInt = new HashMap<>();
    static HashMap<Integer, Character> IntToChar = new HashMap<>();

    static {
        for (int i = 0; i <= 9; i++) {
            CharToInt.put((char) (i + '0'), i);
            IntToChar.put(i, (char) (i + '0'));
        }
        for (int i = 0; i <= 5; i++) {//A~F
            CharToInt.put((char) (i + 'A'), i + 10);
            IntToChar.put(i + 10, (char) (i + 'A'));
        }
    }

    /*
    给定n进制数s,将其转为m进制输出
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        char[] ss = s.toCharArray();
        int m = sc.nextInt();
        long origin = 0;
        for (int i = 0; i < ss.length; i++) {
            origin += CharToInt.get(ss[i]) * (long) Math.pow(n, ss.length - i - 1);
        }
        StringBuilder ans = new StringBuilder();
        while (origin != 0) {
            ans.append(IntToChar.get((int) (origin % m)));
            origin /= m;
        }
        ans.reverse();
        System.out.println(ans);
    }
}
