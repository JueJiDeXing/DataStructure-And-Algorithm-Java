package 算法OJ.洛谷.普及up_提高;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 已AC
 */
public class P2638安全系统 {
    /*
    n个盒子,每个盒子可以放任意个0和1
    一共有a个0和b个1, 0和1不用全部放
    求方案数
    a,b<=50, n+a<=50,n+b<=50
     */
    static BigInteger[][] c = new BigInteger[100][100];// 最多到c[49],会爆long, 需要unsigned long

    static {
        for (BigInteger[] b : c) Arrays.fill(b, BigInteger.ZERO);
        //c(n,m)=c(n-1,m)+c(n-1,m-1)
        c[0][0] = BigInteger.ONE;
        for (int i = 1; i < 100; i++) {
            c[i][0] = BigInteger.ONE;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j - 1].add(c[i - 1][j]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), a = sc.nextInt(), b = sc.nextInt();
        if (n == 0) {
            System.out.println(1);
            return;
        }
        BigInteger ans = BigInteger.ZERO;
        for (int x = 0; x <= a; x++) {// 枚举0和1一定放的数量
            for (int y = 0; y <= b; y++) {
                // 隔板法, x个球放入n个盒子,盒子中球数不限, 有c(x+n-1,n-1)种
                ans = ans.add(c[x + n - 1][n - 1].multiply(c[y + n - 1][n - 1]));
            }
        }
        System.out.println(ans);
    }
}
