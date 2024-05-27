package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.*;
import java.util.Scanner;

public class F_皇后放置 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 998244353;

    public static void main(String[] args) throws Exception {
        int n = I();
        int[] a = new int[n];
        int min = n;
        for (int i = 0; i < n; i++) {
            a[i] = I();
            if (i <= n / 2) {
                min = Math.max(min, a[i]);
            } else {
                min = Math.max(min, n - a[i] + 1);
            }
        }
    }
}
/*
5
1
2
3
4
5
 */
