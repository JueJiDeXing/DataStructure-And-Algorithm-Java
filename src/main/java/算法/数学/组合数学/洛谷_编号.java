package 算法.数学.组合数学;


import java.util.Arrays;
import java.util.Scanner;

/**
 {@link 算法OJ.洛谷.普及down.P1866编号}
 */
public class 洛谷_编号 {
    /*
    N个元素,第i个元素的编号范围为1~M[i],求方案数
     */
    static int MOD = 10_0000_0007;

    /**
     把每个位置的限制升序排序<br>
     第1个位置有M[0]种选择<br>
     第2个位置有M[1]种选择<br>
     ...<br>
     根据乘法原理相乘即可<br>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] m = new int[n];
        for (int i = 0; i < n; i++) m[i] = sc.nextInt();
        Arrays.sort(m);
        long ans = 1;
        for (int i = 0; i < n; i++) ans = (ans * (m[i] - i)) % MOD;
        System.out.println(ans);
    }

}
