package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.*;
import java.util.BitSet;
import java.util.Scanner;

public class A_sg {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) {
            solve();
        }
    }

    private static void solve() throws Exception {
        int n = I();
        int[] a = new int[n], b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = I();
            b[i] = I();
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans ^= dfs(a[i], b[i]);
        }
        if (ans == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }

    }

    static int[] sg = new int[40000];

    /**
     @param a 容量
     @param b 当前数量
     */
    static int dfs(int a, int b) {
        if (sg[a - b] != -1) return sg[a - b];
        BitSet set = new BitSet();
        for (int i = 1; i <= Math.min(b, a - b); i++) {
            set.set(dfs(a, b + i));
        }
        return sg[a - b] = set.nextClearBit(0);
    }
}
/*
4
1
6 3
2
6 3
6 3
2
3 1
4 1
3
1 1
2 1
3 2
 */
