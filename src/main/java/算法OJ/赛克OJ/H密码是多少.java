package 算法OJ.赛克OJ;

import java.util.*;

/**
 已AC
 */
public class H密码是多少 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        s = s.substring(3) + s.substring(0, 3);
        for (char ch : s.toCharArray()) {
            System.out.print((int) ch);
        }
    }
}
