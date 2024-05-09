package 算法OJ.蓝桥杯.算法赛.小白入门赛.第7场;

import java.util.*;
/**
 已AC
 */
public class _2霓虹 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next(), T = sc.next();
        int n = S.length();
        int count = 0;

        int[][] map = new int[][]{
                {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
                {0, 0, 5, 3, 2, 5, 6, 1, 5, 4},
                {0, 0, 0, 2, 5, 4, 3, 4, 2, 3},
                {0, 0, 0, 0, 3, 2, 3, 2, 2, 1},
                {0, 0, 0, 0, 0, 3, 4, 3, 3, 2},
                {0, 0, 0, 0, 0, 0, 1, 4, 2, 1},
                {0, 0, 0, 0, 0, 0, 0, 5, 1, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 4, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };

        for (int i = 0; i < n; i++) {
            int x1 = S.charAt(i) - '0', x2 = T.charAt(i) - '0';
            if (x1 != x2) {
                count += x1 < x2 ? map[x1][x2] : map[x2][x1];
            }
        }
        System.out.println(count);
    }
}
