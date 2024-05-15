package 算法OJ.牛客.周赛.周赛42;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 已AC
 */
public class C素数合并 {
    /*
    n个素数, 每次选出2个素数合并, 求最后的数组极差最小值
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int n = I();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = I();
        }
        Arrays.sort(a);
        int i = 0, j = n - 1;
        long max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        if (n % 2 == 1) {
            j--;
            max = a[n - 1];
            min = a[n - 1];
        }
        while (i < j) {
            max = Math.max(max, a[i] * a[j]);
            min = Math.min(min, a[i] * a[j]);
            i++;
            j--;
        }
        System.out.println(max - min);
    }

}
