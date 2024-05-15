package 算法OJ.牛客.周赛.周赛42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class B数组移动 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String S() throws IOException {
        String res = bf.readLine();
        while (res.isEmpty()) res = bf.readLine();
        return res;
    }

    static int MOD = 10_0000_0007;

    public static void main(String[] args) throws IOException {
        int n = I();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = I();
        long ans = 0;
        char[] s = S().toCharArray();
        int pos = 0;
        for (char ch : s) {
            if (ch == 'R') {
                pos = Math.min(n - 1, pos + 1);
            } else {
                pos = Math.max(0, pos - 1);
            }
            ans = (ans + a[pos]) % MOD;
        }
        System.out.println(ans);
    }


}
