package 算法OJ.ICPC.江西2024;

import java.io.*;

public class D魔法LCM {
    /*
    给出n个数, 可以执行任意次以下操作:
    选择两个数x,y, 将x变为gcd(x,y), y变为lcm(x,y)
    求最大和
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
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
