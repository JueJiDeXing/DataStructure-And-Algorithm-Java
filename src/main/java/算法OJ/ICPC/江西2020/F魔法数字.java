package 算法OJ.ICPC.江西2020;

import java.math.BigInteger;
import java.util.Scanner;

/**
 已AC(胆子大的人敢写dfs就能过)
 */
public class F魔法数字 {
    /*
    如果一个数字的所有前缀 [a1,a2,..ai] 能被i整除, 则它为魔法数字
    求n根火柴能组成的最大魔法数字(n根火柴必须恰好全部使用)
    如果没有方案,输出-1
     */
    static BigInteger[] cost = new BigInteger[10];
    static BigInteger[] B = new BigInteger[11];

    static {
        for (int i = 0; i < B.length; i++) {
            B[i] = BigInteger.valueOf(i);
        }
        int[] l = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
        for (int i = 0; i <= 9; i++) {
            cost[i] = B[l[i]];
        }
    }

    static BigInteger ans = BigInteger.valueOf(-1);
    static BigInteger n;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = new BigInteger(sc.next());
        for (int first = 1; first <= 9; first++) {//枚举首位
            dfs(1, B[first], cost[first]);
        }
        System.out.println(ans);
    }

    /**
     第i位数, 当前为num, 使用了consume个火柴
     */
    static void dfs(int i, BigInteger num, BigInteger consume) {
        int compareTo = consume.compareTo(n);
        if (compareTo == 0) {
            ans = ans.max(num);
            return;
        }
        if (compareTo > 0) return;
        for (int d = 0; d <= 9; d++) {
            BigInteger newNum = num.multiply(B[10]).add(B[d]);
            if (newNum.mod(BigInteger.valueOf(i + 1)).equals(B[0])) {
                dfs(i + 1, newNum, consume.add(cost[d]));
            }
        }
    }
}
