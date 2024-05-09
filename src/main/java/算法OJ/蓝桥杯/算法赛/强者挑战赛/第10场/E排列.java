package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第10场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;

public class E排列 {

    static int[] a;
    static int n, k, max, min;
    static HashSet<Integer> isUse;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        n = nextInt();
        k = nextInt();
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
    }

    static int MOD = 998244353;

}
