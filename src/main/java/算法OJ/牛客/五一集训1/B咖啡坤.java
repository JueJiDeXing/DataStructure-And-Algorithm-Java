package 算法OJ.牛客.五一集训1;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 已AC
 */
public class B咖啡坤 {
    /*
    S[1]=COFFEE
    S[2]=CHICKEN
    S[n]=S[n-2]+S[n-1]
    t组询问,求S[n][k]
     */
    static Scanner sc = new Scanner(System.in);

    static int I() {
        return sc.nextInt();
    }

    static long L() {
        return sc.nextLong();
    }

    static long[] L = new long[100];

    static {
        L[1] = 6;
        L[2] = 7;
        for (int i = 3; i <= 56; i++) {
            L[i] = L[i - 2] + L[i - 1];
        }
    }

    public static void main(String[] args) {
        int t = I();
        while (t-- > 0) {
            solve();
        }
        pw.flush();
    }


    /**
     令f(n,k)为S[n]的第k个字符所在的单词,以及是单词的第几个
     <p>
     (1) k <= len(S[n-2])
     _______ _____
     S[n-2]  S[n-1]
     ↑
     k
     f(n,k) = f(n-1,k)
     <p>
     (2) k > len(S[n-2])
     _______ _____
     S[n-2]  S[n-1]
     ↑
     k
     f(n,k) = f(n-1,k-len(S[n-2]))
     <p>
     所以转移方程为:
     f(n,k) = [ f(n-1,k) | k <= len(S[n-2]) ; f(n-1,k-len(S[n-2])) | k > len(S[n-2]) ]
     */
    static void solve() {
        int n = I();
        if (n > 55) {
            n = 55;
        }
        long k = L();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10 && k + i <= L[n]; i++) {
            sb.append(f(n, k + i));
        }
        pw.println(sb);
    }

    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    static String s1 = "COFFEE", s2 = "CHICKEN";

    static Character f(int n, long k) {
        if (n == 1) return s1.charAt((int) k - 1);
        if (n == 2) return s2.charAt((int) k - 1);

        if (k <= L[n - 2]) {
            return f(n - 2, k);
        } else {
            return f(n - 1, k - L[n - 2]);
        }
    }
}
