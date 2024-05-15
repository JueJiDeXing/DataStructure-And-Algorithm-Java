package 算法OJ.牛客.周赛.周赛42;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 已AC
 */
public class E数字序列 {
    /*
    长度为n的数字串,求长度为k的子序列之和
     */
    static int MOD = 10_0000_0007;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt();
        String s = sc.next();
        long[][] c = initC();
        long[] pow10 = initP();
        long ans = 0;
        for (int i = 0; i < n; i++) {// 枚举第i个数字
            for (int j = 0; j < k; j++) {// 填在第几位
                long v = (s.charAt(i) - '0') * pow10[k - j - 1] % MOD; // 权值
                long choose = c[i][j] * c[n - i - 1][k - j - 1] % MOD; // 前面i个数选j个,后面n-i-1个数选k-j-1个
                ans = (ans + v * choose % MOD) % MOD;
            }
        }
        System.out.println(ans);
    }

    private static long[] initP() {
        long[] pow10 = new long[1001];
        pow10[0] = 1;
        for (int i = 1; i <= 1000; i++) pow10[i] = pow10[i - 1] * 10 % MOD;
        return pow10;
    }

    private static long[][] initC() {
        long[][] c = new long[1001][1001];
        for (int i = 0; i <= 1000; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    c[i][j] = 1;
                } else {
                    c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
                }
            }
        }
        return c;
    }


}
