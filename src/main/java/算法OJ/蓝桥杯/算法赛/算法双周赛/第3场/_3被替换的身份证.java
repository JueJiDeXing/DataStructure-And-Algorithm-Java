package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.util.*;
/**
 已AC
 */
public class _3被替换的身份证 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int ans = solve();
            System.out.println(ans == 0 ? "ShallowDream" : "Joker");
        }
    }

    static int solve() {
        String s1 = sc.next(), s2 = sc.next();
        char a1 = s1.charAt(0), a2 = s1.charAt(1);
        char b1 = s2.charAt(0), b2 = s2.charAt(1);
        if (a1 == a2) return 0;

        if (s1.equals("MF") || s1.equals("FM")) return 0;
        if (s2.equals("MF") || s2.equals("FM")) return 1;

        if (compare(a1, b1) >= 0 && compare(a1, b2) >= 0) return 0;
        if (compare(a2, b1) >= 0 && compare(a2, b2) >= 0) return 0;

        if (compare(b1, a1) == 1 && compare(b1, a2) == 1) return 1;
        if (compare(b2, a1) == 1 && compare(b2, a2) == 1) return 1;
        return -1;
    }

    static char[] c = {'F', 'M', '2', 'A', 'K', 'Q', 'J', 'X', '9', '8', '7', '6', '5', '4', '3'};

    static int compare(char a, char b) {
        for (char ch : c) {
            if (ch == a && ch == b) return 0;
            if (ch == a) return 1;
            else if (ch == b) return -1;
        }
        return 0;
    }
}
