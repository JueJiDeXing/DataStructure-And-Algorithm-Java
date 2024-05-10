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
    public int place(int N) {
        int mod = 1000000007;
        long[] prev = new long[4], curr = new long[4];
        prev[3] = 1;
        for (int i = 1; i <= N; i++) {
            curr[0] = prev[3] % mod;
            curr[1] = (prev[0] + prev[2]) % mod;
            curr[2] = (prev[0] + prev[1]) % mod;
            curr[3] = (prev[0] + prev[1] + prev[2] + prev[3]) % mod;
            prev = curr.clone();
        }
        return (int) prev[3];
    }
}

