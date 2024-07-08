package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.*;

/**
 补
 */
public class A_sg {
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
            solve();
        }
        pw.flush();
    }

    private static void solve() throws Exception {
        int n = I();
        int cntLose = 0, cntWin = 0, cntControl = 0, cntBeControl = 0;
        for (int i = 0; i < n; i++) {
            int a = I(), b = I();
            int state = f(a, b);
            if (state == 0) {
                cntLose++;
            } else if (state == 1) {
                cntWin++;
            } else if (state == 2) {
                cntControl++;
            } else {
                cntBeControl++;
            }
        }
        boolean isAWin = (cntWin % 2 == 0) ^ (cntControl == 0);
        pw.println(isAWin ? "Yes" : "No");
    }

    static int f(int a, int b) {
        if (a == b) return 0;// 必败: A无法放置
        if (2 * b >= a) return 1;// 必胜: A一步放完
        int ans = 0;
        for (int t = 1; t <= b && t <= a - b; t++) {
            int state = f(a - t, b + t);
            if (state == 3) return 2;// 后面有被决定态, 让B被决定
            ans |= 1 << state;
            if ((ans & 0b11) == 0b11) return 2;// 有0和1, 决定态
        }
        if (ans == 0b001) return 0;//必输
        if (ans == 0b010) return 1;//必赢
        if (ans == 0b100) return 3;// 都是决定态, 被决定
        // 2,0  或 2,1, 不能交给B决定
        return ans & 1;
    }
}
/*
4
1
6 3
2
6 3
6 3
2
3 1
4 1
3
1 1
2 1
3 2
 */
