package 算法OJ.ICPC.江西2024;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class J魔法麻将 {
    /*
    给出一副牌, 判断是7对子牌型( 7 Pairs ),还是13幺牌型( Thirteen Orphans ), 如果都不是, 则为其他牌型( Otherwise )
    P={1~9p}, S={1~9s}, M={1~9m}, Z={1~7z}
    7对子: 7个不同的对子
    13幺: 仅有一个对子, 且所有牌要么为'z'类牌, 要么数字为1或9
    例如: 1s9s1p9p1m9m1z2z3z4z5z6z7z9s 为13幺
     */
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

    public static void main(String[] args) throws Exception {
        int T = I();
        while (T-- > 0) {
            solve();
        }
        pw.flush();
    }

    static void solve() throws IOException {
        char[] s = S().toCharArray();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 28; i += 2) {
            String p = s[i] + "" + s[i + 1];
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        int cntTwo = 0;//对子个数
        for (int v : map.values()) {
            if (v > 2) {// 某牌个数大于2张, 不能成为对子或13幺牌型
                pw.println("Otherwise");
                return;
            }
            if (v == 2) cntTwo++;
        }
        if (cntTwo == 7) {// 7对
            pw.println("7 Pairs");
            return;
        }
        if (cntTwo != 1) {// 13幺需要1个对子
            pw.println("Otherwise");
            return;
        }
        //检查是否为 13幺
        for (String p : map.keySet()) {
            // z类牌 或 数为1|9
            char c1 = p.charAt(0), c2 = p.charAt(1);
            if (c2 == 'z') continue;
            if (c1 == '1' || c1 == '9') continue;
            pw.println("Otherwise");
            return;
        }
        pw.println("Thirteen Orphans");
    }

}
