package 算法OJ.ICPC.江西2021;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 已AC(递归)
 */
public class B连除式 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) {
            List<Integer> ans = solve();

            pw.print(ans.size() - 1 + " ");
            for (int i = 0; i < ans.size(); i++) {
                pw.print(ans.get(i) + " ");
            }
            pw.println();
        }
        pw.flush();
        pw.close();

    }

    static List<Integer> solve() throws IOException {
        int a = I(), b = I();
        List<Integer> ans = new ArrayList<>();
        ans.add(a / b);//先放一个(否则循环里停的时候还有考虑前面是不是0)
        int c = a % b;
        a = b;
        b = c;
        while (b != 0) {
            if (a >= b) {// a/b = a//b + a%b / b = a//b + 1/(b / a%b)
                ans.add(a / b);
                c = a % b;
                a = b;
                b = c;
            } else {// a/b
                ans.add(b);
                break;
            }
        }
        return ans;
    }
}
