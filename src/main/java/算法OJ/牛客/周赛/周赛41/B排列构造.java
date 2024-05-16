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
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = I();
        if (k == 1 || k > n) {
            System.out.println(-1);
            return;
        }
        // 有k个不同, 让前面n-k个相同, 后面k个不同
        for (int i = 0; i < n - k; i++) System.out.println(a[i]);
        for (int i = n - k + 1; i < n; i++) System.out.println(a[i]);
        System.out.println(a[n - k]);
    }


}
