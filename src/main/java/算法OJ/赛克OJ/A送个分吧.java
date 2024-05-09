package 算法OJ.赛克OJ;

import java.util.*;
/**
 已AC
 */
public class A送个分吧 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        String[] ans = new String[5];
        Arrays.fill(ans, "zzzzzz");
        String ss;
        for (int left = 0; left < s.length(); left++) {
            for (int right = left; right < s.length() && right <= left + k; right++) {
                ss = s.substring(left, right + 1);
                int t = -1;
                for (int i = 0; i < 5; i++) {
                    int com = ss.compareTo(ans[i]);
                    if (com == 0) break;
                    if (com < 0) {
                        t = i;
                        break;
                    }
                }
                if (t != -1) {
                    System.arraycopy(ans, t, ans, t + 1, 4 - t);
                    ans[t] = ss;
                }
            }
        }
        System.out.println(ans[k - 1]);
    }
}
