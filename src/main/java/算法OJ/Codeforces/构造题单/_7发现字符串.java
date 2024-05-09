package 算法OJ.Codeforces.构造题单;

import java.util.*;
/**
 已AC
 */
public class _7发现字符串 {
    /*
    n个长度为m的字符串A[i], 寻找一个字符串s,s需要满足s与A[i]最多有1位不同
    如果没有答案,输出-1,如果有多个答案,输入任意一个答案
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int T = sc.nextInt();
        while (T-- > 0) {
            String ans = solve();
            System.out.println(ans);
        }
    }

    static int n, m;
    static char[][] A;

    static String solve() {
        n = sc.nextInt();
        m = sc.nextInt();
        A = new char[n][m];
        for (int i = 0; i < n; i++) A[i] = sc.next().toCharArray();
        char[] A0 = A[0];
        //修改A0的每一位
        for (int i = 0; i < m; i++) {
            for (char change = 'a'; change <= 'z'; change++) {
                char origin = A0[i];
                A0[i] = change;
                if (check(A0)) {//这位修改可行
                    return String.valueOf(A0);
                }
                A0[i] = origin;//不可行,回溯
            }
        }
        return "-1";//都不可行
    }

    static boolean check(char[] A0) {
        for (int i = 1; i < n; i++) {
            int cnt = 0;//与A0不同的位置的个数
            for (int j = 0; j < m; j++) {
                if (A[i][j] != A0[j]) {
                    cnt++;
                    if (cnt >= 2) return false;
                }
            }
        }
        return true;
    }


}
