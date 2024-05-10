package 算法.数学.数学基础.其他;

public class 约瑟夫环 {
    /*
    n个人围成一个环,从第0个人开始从1报数,报到M的人出局,求最后剩下的人的编号
     */
    /*
    令f(N,M)表示N个人报数,最后剩余的人编号为多少
    f(N,M)=(f(N−1,M)+M)%N
    第M个人出局,剩余N-1个人,在N-1的问题中编号偏移为M
     */
    public static int josephus(int n, int m) {
        if (n == 1) return 0;
        return (josephus(n - 1, m) + m) % n;
    }

    /**
     <h1>动态规划写法</h1>
     从0开始编号:dp[1]=0,dp[n]=(dp[n-1]+m)%n
     从1开始编号:dp[1]=1,dp[n]=(dp[n-1]+m-1)%n+1
     */
    public static int josephus2(int n, int m) {
        int[] dp = new int[n + 1];//编号从0开始
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + m) % i;
        }
        return dp[n];
    }

    /**
     降维
     */
    public static int josephus3(int n, int m) {
        int dp = 0;//编号从0开始
        for (int i = 2; i <= n; i++) {
            dp = (dp + m) % i;
        }
        return dp;
    }

    /**
     第k个出环的人的编号
     */
    public static int josephus4(int n, int m, int k) {
        return ysf(n, m, k);
    }

    private static int ysf(int n, int m, int k) {
        if (k == 1) return (m - 1 + n) % n;//编号从0开始,m需要-1
        return (ysf(n - 1, m, k - 1) + m) % n;
    }

    /**
     <h1>周期函数</h1>
     */
    public static int ysf(int n, int m) {
        if (m == 1) return n;
        int a = 1, b = 1;
        while (true) {
            int x = (a - b) / (m - 1);
            if (a <= n && n < (a + x + 1)) {
                b += m * (n - a);
                break;
            }
            int y = (a - b) % (m - 1);
            a += x + 1;
            b = (m - 1 - y) % a;
            if (b == 0) b = a;
        }
        return b;
    }
}
