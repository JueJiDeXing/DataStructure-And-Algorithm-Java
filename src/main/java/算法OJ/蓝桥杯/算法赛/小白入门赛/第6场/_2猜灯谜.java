package 算法OJ.蓝桥杯.算法赛.小白入门赛.第6场;

import java.io.*;
/**
 已AC
 */
public class _2猜灯谜 {
    /*
    n个灯笼排一圈,每个灯笼谜底等于相邻两个的和
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Int();
        }
        for (int i = 0; i < n; i++) {
            System.out.print(A[(i + 1) % n] + A[(i - 1 + n) % n] + " ");
        }
    }
}
