package 算法OJ.ICPC.江西2024;

import java.io.*;
import java.util.Scanner;

public class E魔法子序列 {
    /*
    给出长度为n的数组, 从中选择两个和相等的不同的子序列
    输出选择的方案
    如果没有方案,输出-1
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static Scanner sc = new Scanner(System.in);
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

    public static void main(String[] args) throws Exception {
        int T = I();

        while (T-- > 0) {
            solve();
        }
        pw.flush();
    }

    static void solve() throws IOException {
        int n = I();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = I();
        }
        //TODO
    }


}
