package 算法.动态规划.其他;

public class 积木画 {//蓝桥杯

    /*
    输入一个整数N,代表有一个2行N列的矩形
    你有无限多2种形状的积木,长条I型(2)和弯三角L型(3)
    ┌─┐  and ┌─┐
    │ │      │ └──┐
    └─┘      └────┘
    返回摆放积木的情况种数
     */
    int mod = 1000000007;

    /**
     dp[n]: n列时的放置方案数
     手算前5项得  dp[i] = 2 * dp[i - 1] + dp[i - 3]
     */
    public int place1(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % mod;
        }
        return (int) dp[n];
    }

    /**
     dp[i][j]: 前i列已放满, 第i列状态为j的方案数, j=00,01,10,11
     则ans=dp[n][11]

     初始化:
     n=0时可视为已放满, 方案数为1, dp[0][3] = 1

     转移:
     如果需要第i列在放置当前块后为00状态, 那么第i-1列必须是竖向放置I型(如果i-1列不满,放置后i列肯定不为空):
        dp[i][0] = dp[i-1][3]
     如果需要第i列在放置当前块后为01状态, i-1列空+L型  或  i-1列上放+横I型:
        dp[i][1] = dp[i-1][0] + dp[i-1][2]
     如果需要第i列在放置当前块后为10状态,同理:
        dp[i][2] = dp[i-1][0] + dp[i-1][1]
     如果需要第i列在放置当前块后为11状态:
        i-1满+竖I  或  i-1上or下放+L型  或  i-1空+横I*2
        dp[i][3] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3]
     */
    public int place2(int n) {
        long[][] dp = new long[n + 1][4];
        dp[0][3] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % mod + dp[i - 1][2]) % mod + dp[i - 1][3]) % mod;
        }
        return (int) dp[n][3];
    }
}

