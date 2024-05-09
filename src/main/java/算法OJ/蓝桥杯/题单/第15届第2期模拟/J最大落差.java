package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.io.*;
/**
 已AC
 */
public class J最大落差 {
    /*
    如果a[i]>a[i+1],则称i到i+1为下落
    求最大落差
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
        int pre = Int();
        int max = 0;
        for (int i = 1; i < n; i++) {
            int curr = Int();
            int b = pre - curr;
            if (b > max) max = b;
            pre = curr;
        }
        System.out.println(max);

    }
}
