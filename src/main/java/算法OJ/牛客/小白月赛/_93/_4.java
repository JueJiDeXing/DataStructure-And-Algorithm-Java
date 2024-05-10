package 算法OJ.牛客.小白月赛._93;

import java.util.Scanner;
/**
 已AC
 */
public class _4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            long x = sc.nextLong();
            long ans = 0;
            for (int k = 0; k < n; k++) {
                long b = x % 2;
                x /= 2;
                ans += b * (1L << (n - k - 1));
            }
            System.out.println(ans);
        }
    }


}
