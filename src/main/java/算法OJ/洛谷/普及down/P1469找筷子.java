package 算法OJ.洛谷.普及down;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC(只能用C++)
 */
public class P1469找筷子 {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /*
    n只筷子(n为奇数),长度相等的可以配对,求落单的筷子长度
    本题限制:内存4MB
    Java会超内存,用C++需要开启ios读入优化防超时
     */
    public static void main(String[] args) {
        int n = nextInt();
        int ans = 0;
        int x;
        for (int i = 0; i < n; i++) {
            x = nextInt();
            for (int j = 0; j < 32; j++) {
                if ((x & (1 << j)) != 0) ans ^= (1 << j);
            }
        }
        System.out.println(ans);
    }
}
