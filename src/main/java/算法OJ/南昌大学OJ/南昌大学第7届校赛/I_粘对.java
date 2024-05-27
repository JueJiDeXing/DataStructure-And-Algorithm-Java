package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class I_粘对 {
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

    public static void main(String[] args) throws Exception {
        System.out.println(solve());
    }

    private static String solve() throws IOException {
        int n = I();
        char[][] S = new char[n + 1][7];
        for (int i = 1; i <= n; i++) {
            S[i] = S().toCharArray();
        }
        boolean shiN = false, shiD = false;
        for (int i = 1; i < n; i++) {
            // 1-2对, 2-3 粘, 3-4对..
            if (i % 2 == 0) { //粘(需要相同)
                if (!isSame(S[i], S[i + 1])) shiN = true;
            } else {//对(需要对立)
                if (!isReverse(S[i], S[i + 1])) shiD = true;
            }
            if (shiN && shiD) break;
        }
        if (shiN && shiD) return ("gan jue bu ru yuan shen");
        if (!shiN && !shiD) return ("hao shi!");
        if (shiN) return ("shi nian...");
        return ("shi dui...");
    }

    private static boolean isSame(char[] s, char[] t) {
        return s[1] == t[1] && s[3] == t[3] && s[5] == t[5];
    }

    private static boolean isReverse(char[] s, char[] t) {
        return s[1] != t[1] && s[3] != t[3] && s[5] != t[5];
    }
}

/*
4
--||--|
||---|-
--||--|
||---|-
 */
