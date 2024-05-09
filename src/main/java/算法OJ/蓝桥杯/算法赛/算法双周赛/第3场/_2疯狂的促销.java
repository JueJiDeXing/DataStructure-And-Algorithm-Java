package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.util.Scanner;
/**
 已AC
 */
public class _2疯狂的促销 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int w = sc.nextInt();
            ans += minCost(w);
        }
        System.out.println(ans  );
    }

    static int minCost(int w) {
        int a = w < 500 ? Integer.MAX_VALUE : (w - w / 10);
        int b = w < 1000 ? Integer.MAX_VALUE : (w - 150);
        int c = w == 1111 ? 0 : (w - w / 20);
        return Math.min(a, Math.min(b, c));
    }
}
