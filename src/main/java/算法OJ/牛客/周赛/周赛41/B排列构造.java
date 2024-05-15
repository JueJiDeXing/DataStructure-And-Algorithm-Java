package 算法OJ.牛客.周赛.周赛41;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class B排列构造 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int n = I(), k = I();
        if (k == 1 || k > n) {
            System.out.println(-1);
            return;
        }
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = I();
        int i = 0;
        if (k % 2 == 1) {
            swap(a, 0, 1);
            swap(a, 0, 2);
            i += 3;
        }
        for (; i < k; i += 2) {
            swap(a, i, i + 1);
        }
        for (int j = 0; j < n; j++) {
            System.out.print(a[j] + " ");
        }
    }

    static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
