package 算法OJ.蓝桥杯.题单.动态规划基础30题;

import java.util.*;
/**
 已AC
 */
public class _3保险箱 {

    /*
    n位数字的保险箱
    当前为x,要将其变为y
    每次可以对任意一位操作,加1或减1,可能发生进位或借位(只会向左)
    如果是9加1,它会向左边进位
    如果是0减1,它会向左边借位
    求最少操作次数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        String x = sc.next(), y = sc.next();
        X = new int[n];
        Y = new int[n];
        for (int i = 0; i < n; i++) {
            X[i] = x.charAt(i) - '0';
            Y[i] = y.charAt(i) - '0';
        }
        memo = new int[n][3];
        for (int[] m : memo) Arrays.fill(m, -1);
        int ans = dfs(n - 1, 0);
        System.out.println(ans);
        //for (int[] m : memo) System.out.println(Arrays.toString(m));
    }

    static int[] X, Y;
    static int n;
    static int[][] memo;

    /**
     @param i   当前是第几位要操作的
     @param val 上次操作发生的进位/借位值,1进位,-1借位,0无进位和借位
     */
    static int dfs(int i, int val) {
        if (i < 0) return 0;
        if (memo[i][val + 1] != -1) return memo[i][val + 1];
        int nx = X[i] + val;
        int nval = 0;
        if (nx > 9) {
            nval = 1;
            nx -= 9;
        } else if (nx < 0) {
            nval = -1;
            nx += 9;
        }
        if (nx == Y[i]) {
            return dfs(i - 1, nval);//不需要操作
        }
        int diff = nx - Y[i];
        //减
        int res1 = dfs(i - 1, nval + (diff < 0 ? -1 : 0)) + (diff < 0 ? 10 + diff : diff);
        //加
        diff=-diff;
        int res2 = dfs(i - 1, nval + (diff < 0 ? 1  : 0)) + (diff < 0 ? 10 + diff : diff);
        return memo[i][val + 1] = Math.min(res1, res2);
    }

}
