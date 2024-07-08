package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class F_皇后放置 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 998244353;
    static int n;
    static int[] a;

    public static void main(String[] args) throws Exception {
        n = I();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = I();
        int k = getK();
        System.out.println(cal(k));
    }

    private static int getK() {
        int k = 0;
        for (int i = 0; i < n; i++) {
            k = Math.max(k, Math.min(n - i, a[i]));
        }
        return k;
    }

    /**
     放置k个皇后,覆盖全部格子的方案数
     */
    private static int cal(int k) {
        return k;
    }
}
/*
7
1 2 2 3 5 5 7
 */
