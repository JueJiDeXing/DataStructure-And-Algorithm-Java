package 算法OJ.牛客.小白月赛._93;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC
 */
public class _1 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int t = nextInt();
        while (t-- > 0) solve();
    }

    public static void solve() {
        int n = nextInt(), a = nextInt() + 1, k = nextInt();
        for (int i = 0; i < k; i++) {
            System.out.print((check(a) ? "p" : a) + " ");
            a += n;
        }
        System.out.println();
    }

    public static boolean check(int a) {
        if (a % 7 == 0) return true;
        return ("" + a).contains("7");
    }

}
