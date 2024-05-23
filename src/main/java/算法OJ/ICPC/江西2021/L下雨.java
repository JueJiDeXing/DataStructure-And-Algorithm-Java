package 算法OJ.ICPC.江西2021;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 已AC(求x区间并集)
 */
public class L下雨 {
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
        int n = I();
        int[][] X = new int[n][2];
        for (int i = 0; i < n; i++) {
            int x1 = I(), y1 = I(), x2 = I(), y2 = I();
            X[i] = new int[]{x1, x2};
        }
        Arrays.sort(X, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        long ans = 0;
        long left = X[0][0], right = X[0][1];
        for (int i = 1; i < n; i++) {
            long L = X[i][0], R = X[i][1];
            if (L > right) {
                ans += right - left;
                left = L;
            }
            right = Math.max(right, R);

        }
        ans += right - left;
        System.out.println(ans);
    }


}
