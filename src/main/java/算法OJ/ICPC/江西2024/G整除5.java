package 算法OJ.ICPC.江西2024;

import java.io.*;
import java.util.Scanner;

public class G整除5 {
    /*
    给出一个11进制数, 判断其是否能被5整除
    数的给出方式如下:
    给出n组二元组, (ai,bi), ai表示个数, bi表示数字(A为10), 按序拼接
    例如: (1,1), (4,5),(1,4) 表示 155554 (11进制)
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
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = I(), b = I();
            sum = (sum + a * b % 5) % 5;
        }
        pw.println(sum % 5 == 0 ? "Yes" : "No");
    }
}
