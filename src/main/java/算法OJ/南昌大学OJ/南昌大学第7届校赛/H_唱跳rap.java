package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 补
 */
public class H_唱跳rap {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 998244353;

    public static void main(String[] args) throws Exception {
        int n = I();
        long[] X = new long[n + 1], Y = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            X[i] = I();
            Y[i] = I();
        }
        long[] dp = new long[n + 2];//dp[i]:到 (篮球路程的)第i秒的 期望经过时间
        for (int i = 1; i <= n; i++) { //dp[i+1] = dp[i] / (1 - P[i])  +  1
            long inv = pow(Y[i] - X[i], MOD - 2);//逆元
            dp[i + 1] = (Y[i] * dp[i] % MOD * inv % MOD + 1) % MOD;
        }
        System.out.println(dp[n + 1]);
    }

    static long pow(long x, long n) {
        x %= MOD;
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }


}
