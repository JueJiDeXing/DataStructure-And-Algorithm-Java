package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第10场;

import java.util.Scanner;
/**
 已AC
 */
public class B字符画 {

    static Scanner sc = new Scanner(System.in);
    static char[][] map;
    static int n, m;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '*') ans = Math.max(ans, count(i, j));
            }
        }
        System.out.println(ans);
    }

    private static int count(int i, int j) {
        int left = 0, right = 0;
        for (int x = 1; x <= Math.min(i, j); x++) {
            if (map[i - x][j - x] == '*') {
                left++;
            } else {
                break;
            }
        }
        for (int x = 1; x <= Math.min(i, m - j - 1); x++) {
            if (map[i - x][j + x] == '*') {
                right++;
            } else {
                break;
            }
        }
        return Math.min(left, right);
    }


}
