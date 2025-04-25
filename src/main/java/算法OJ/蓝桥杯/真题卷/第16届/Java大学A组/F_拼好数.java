package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

import java.util.Scanner;

/**
 赛时代码错误, 赛后AC
 10% = 2分
 */
public class F_拼好数 {
    /*
     给定数组a, 将a分为若干组,每组不超过3个数
     若组内各数数位上含有至少6个6, 则答案+1
     求答案最大值
     */
    static int[] cnt = new int[7];

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            cnt[getCnt(a[i])]++;
        }
        int ans = cnt[6];

        ans += check(1, 5);
        ans += check(1, 1, 4);
        ans += check(1, 2, 3);

        ans += check(2, 4);
        ans += check(2, 5);
        ans += check(2, 2, 2);
        ans += check(2, 2, 3);

        ans += check(3, 3);
        ans += check(3, 4);
        ans += check(3, 5);
        ans += check(4, 4);
        ans += check(4, 5);
        ans += check(5, 5);

        System.out.println(ans);
    }

    static int check(int i, int j) {
        if (i == j) {
            int v = cnt[i] / 2;
            cnt[i] %= 2;
            return v;
        } else {
            int v = Math.min(cnt[i], cnt[j]);
            cnt[i] -= v;
            cnt[j] -= v;
            return v;
        }
    }

    static int check(int i, int j, int k) {
        if (i == j && j == k) {
            int v = cnt[i] / 3;
            cnt[i] %= 3;
            return v;
        } else if (i == j) {
            int v = Math.min(cnt[i] / 2, cnt[k]);
            cnt[i] -= 2 * v;
            cnt[k] -= v;
            return v;
        } else {
            int v = Math.min(Math.min(cnt[i], cnt[k]), cnt[j]);
            cnt[i] -= v;
            cnt[j] -= v;
            cnt[k] -= v;
            return v;
        }
    }

    static int getCnt(int x) {
        int c = 0;
        while (x > 0) {
            if (x % 10 == 6) c++;
            if (c == 6) break;
            x = x / 10;
        }
        return c;
    }
}
