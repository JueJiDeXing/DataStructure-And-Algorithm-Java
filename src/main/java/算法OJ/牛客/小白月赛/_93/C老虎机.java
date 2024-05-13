package 算法OJ.牛客.小白月赛._93;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC
 */
public class C老虎机 {
    /*
    m个图案, 三个都不同得到a,一对相同得到b,三个都相同得到c,求期望
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int t = nextInt();
        while (t-- > 0) solve();
    }

    static void solve() {
        long m = nextInt(), a = nextInt(), b = nextInt(), c = nextInt();
        if (m == 1) {
            System.out.println(c);
            return;
        }
        long ans = (a * (m - 1) % MOD * (m - 2) % MOD + 3 * b * (m - 1) % MOD + c) % MOD * pow(m * m % MOD, MOD - 2) % MOD;
        System.out.println(ans);
    }

    static int MOD = 998244353;

    static long pow(long x, long n) {
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }

}
