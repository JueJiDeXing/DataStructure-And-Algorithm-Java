package 算法OJ.牛客.周赛.周赛41;

import java.io.*;
/**
 已AC
 */
public class D好串 {
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

    static int[][] cnt;//red

    public static void main(String[] args) throws Exception {
        int n = I(), q = I();
        String s = S();
        char[] c = s.toCharArray();
        cnt = new int[n + 1][3];//red
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1].clone();
            if (c[i - 1] == 'r') {
                cnt[i][0]++;
            } else if (c[i - 1] == 'e') {
                cnt[i][1]++;
            } else {
                cnt[i][2]++;
            }
        }
        for (int i = 0; i < q; i++) {
            int l = I(), r = I();
            int len = r - l + 1;
            if (len < 3) {
                pw.println(0);
                continue;
            }
            int t = len / 3, k = len % 3;
            int v1 = l - 1 + t;
            int v2 = r - t;
            if (k == 0) {
                pw.println(cal(l, v1, v2, r));
            } else if (k == 1) {
                // 1 2 3
                pw.println(min(cal(l, v1 + 1, v2, r), cal(l, v1, v2, r), cal(l, v1, v2 - 1, r)));
            } else {
                // 12 13 23
                pw.println(min(cal(l, v1 + 1, v2, r), cal(l, v1 + 1, v2 - 1, r), cal(l, v1, v2 - 1, r)));
            }
        }
        pw.flush();
    }

    static int min(int x, int... y) {
        for (int i : y) x = Math.min(x, i);
        return x;
    }

    // [l,v1] [v1+1,v2] [v2+1,r]
    private static int cal(int l, int v1, int v2, int r) {
        int cntR = cnt[v1][0] - cnt[l - 1][0];
        int cntE = cnt[v2][1] - cnt[v1][1];
        int cntD = cnt[r][2] - cnt[v2][2];
        return r - l + 1 - cntR - cntE - cntD;
    }
}
