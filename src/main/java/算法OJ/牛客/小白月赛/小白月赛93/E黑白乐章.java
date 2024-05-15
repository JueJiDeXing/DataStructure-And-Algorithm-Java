package 算法OJ.牛客.小白月赛.小白月赛93;

import java.io.*;

/**
 已AC
 */
public class E黑白乐章 {
    /*
    长为n的0/1序列
    在区间[L,R]上, 如果 a[l]^a[r]==1, 则(l,r)有l-r的价值
    给出m个询问，每次询问一个区间[L,R]的价值
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String S() throws IOException {
        String res = bf.readLine();
        while (res.isEmpty()) res = bf.readLine();
        return res;
    }

    static int n, m;
    static int maxn = 200005;
    static long mod = 998244353;

    public static void main(String[] args) throws IOException {
        n = I();
        m = I();
        char[] s = S().toCharArray();
        for (int i = 1; i <= n; i++) {
            cnt1[i] = cnt1[i - 1];
            cnt0[i] = cnt0[i - 1];
            sum0[i] = sum0[i - 1];
            sum1[i] = sum1[i - 1];
            if (s[i - 1] == '1') {
                cnt1[i]++;
                sum1[i] += i;
                ans[i] = (ans[i - 1] + cnt0[i - 1] * i - sum0[i - 1] + mod) % mod;
            } else {
                cnt0[i]++;
                sum0[i] += i;
                ans[i] = (ans[i - 1] + cnt1[i - 1] * i - sum1[i - 1] + mod) % mod;
            }
        }
        while (m-- > 0) {
            int l = I(), r = I();
            long res = (ans[r] - ans[l - 1] + mod) % mod;
            long c0 = cnt0[r] - cnt0[l - 1];
            long c1 = cnt1[r] - cnt1[l - 1];
            long ss1 = sum1[r] - sum1[l - 1], ss0 = sum0[r] - sum0[l - 1];
            res = (res - (ss1 * cnt0[l - 1] % mod - c0 * sum1[l - 1] % mod + mod) % mod + mod) % mod;
            res = (res - (ss0 * cnt1[l - 1] % mod - c1 * sum0[l - 1] % mod + mod) % mod + mod) % mod;
            pw.println(res);
        }
        pw.flush();
    }


    static long[] sum0 = new long[maxn];//sum of (s[i]=='0'?i:0) from 1 to i
    static long[] sum1 = new long[maxn];//sum of (s[i]=='1'?i:0) from 1 to i
    static long[] cnt0 = new long[maxn];//sum of (s[i]=='0'?1:0) from 1 to i
    static long[] cnt1 = new long[maxn];//sum of (s[i]=='1'?1:0) from 1 to i
    static long[] ans = new long[maxn];//answer of [1,i]


}
