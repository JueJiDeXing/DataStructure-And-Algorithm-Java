package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学C组;

import java.util.Scanner;

public class F最大公约数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt(), k = sc.nextInt();
            int ans = 0;
            for (int x = a; x <= b; x++) {
                for (int y = c; y <= d; y++) {
                    if (gcd(x, y) == k) ans++;
                }
            }
            System.out.println(ans);
        }
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
