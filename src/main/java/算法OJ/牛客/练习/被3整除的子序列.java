package 算法OJ.牛客.练习;

import java.util.Scanner;
/**
 已AC
 */
public class 被3整除的子序列 {
    /*
    给定一个数字串,求有多少个子序列能被3整除
     */
    static int MOD = 10_0000_0007;

    //  dp[i][j]: 以i结尾的子序列余3为j的有多少个
    //  与k拼接: dp[k][l] -> dp[i][l*10+s[i]%3]
    //  自己开头: dp[i][s[i]%3]++
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[n][3];
        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < i; k++) {
                for (int l = 0; l < 3; l++) {
                    int j = (str[i] - '0' + l * 10) % 3;
                    dp[i][j] = (dp[i][j] + dp[k][l]) % MOD;
                }
            }
            dp[i][(str[i] - '0') % 3]++;
            ans = (ans + dp[i][0]) % MOD;
        }
        System.out.println(ans);
    }

}
