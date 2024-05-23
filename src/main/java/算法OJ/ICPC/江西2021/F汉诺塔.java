package 算法OJ.ICPC.江西2021;

import java.io.*;
import java.math.BigInteger;

/**
 已AC(打表吗?)
 */
public class F汉诺塔 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static BigInteger[] ans = new BigInteger[100001];

    static {
        ans[1] = BigInteger.ONE;
        int flag = 2, cnt = 0;
        BigInteger B2 = BigInteger.valueOf(2);
        BigInteger f = B2;
        for (int i = 2; i <= 10000; i++) {
            ans[i] = ans[i - 1].add(f);
            cnt++;
            if (cnt == flag) {
                flag++;
                cnt = 0;
                f = f.multiply(B2);
            }
        }
        for (int i=0;i<20;i++){
            System.out.print(ans[i]+" ");
        }
    }


    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) {
            pw.println(ans[I()]);
        }
        pw.flush();
        pw.close();
    }
}
