package 算法OJ.洛谷.普及down;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 已AC
 */
public class P3383_模板_线性筛素数 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
static List<Integer> prime = new ArrayList<>();


    public static void main(String[] args) throws Exception {
        int n = I();
        boolean[] isCom = new boolean[n + 1];
        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!isCom[i]) prime.add(i);
            for (int j = 0; j < prime.size() && i * prime.get(j) <= n; j++) {
                isCom[i * prime.get(j)] = true;
                if (i % prime.get(j) == 0) break;
            }
            //for (int j : prime) { // 很慢, 超时
            //    if (i * j > n) break;
            //    isCom[i * j] = true;
            //    if (i % j == 0) break;
            //}
        }
        int q = I();
        while (q-- > 0) {
            pw.write(prime.get(I() - 1) + "\n");
        }
        pw.flush();
        pw.close();
    }


}
