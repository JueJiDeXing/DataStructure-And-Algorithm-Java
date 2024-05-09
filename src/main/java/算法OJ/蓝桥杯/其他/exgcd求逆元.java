package 算法OJ.蓝桥杯.其他;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class exgcd求逆元 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int q = Int();
        for (int i = 1; i <= q; i++) {
            int a = Int(), b = Int();
            long x = inv(a, b);
            System.out.println(x);
        }
    }

    public static long inv(int a, int b) {
        x = 0;
        y = 0;
        doExgcd(a, b);
        return (x + b) % b;
    }

    static long x, y;


    /**
     求解 ax + by = gcd(a,b)
     解由static变量存储

     @param a,b 方程参数
     */
    private static void doExgcd(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return;
        }
        doExgcd(b, a % b);
        long x1 = x, y1 = y;
        x = y1;
        y = x1 - (a / b) * y1;
    }
}
