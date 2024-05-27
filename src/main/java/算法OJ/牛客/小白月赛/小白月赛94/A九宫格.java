package 算法OJ.牛客.小白月赛.小白月赛94;

import java.util.*;
import java.io.*;

/**
 已AC
 */
public class A九宫格 {
    public static void main(String[] args) throws Exception {
        int[] a = new int[10];
        for (int i = 1; i < 10; i++) a[i] = I();
        String s = S();
        for (char ch : s.toCharArray()) pw.print(a[ch - '0']);
        pw.flush();
    }

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
}
