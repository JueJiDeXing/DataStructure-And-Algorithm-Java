package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学B组;

/**
 已AC
 */
public class D最小权值 {
    public static void main(String[] args) {
        System.out.println(f(2021));//2653631372L

    }

    static long[] memo = new long[2022];

    /**
     n个节点的最小权值
     */
    static long f(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo[n] != 0) return memo[n];
        long min = 1 + 3 * f(n - 1);//左子树空
        for (int left = 1; left <  n; left++) {
            int right = n - left - 1;
            min = Math.min(min, 1 + 2 * f(left) + 3 * f(right) + (long) left * left * right);
        }

        return memo[n] = min;
    }
}
