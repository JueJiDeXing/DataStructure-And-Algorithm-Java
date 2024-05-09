package 算法OJ.蓝桥杯.算法赛.小白入门赛.第3场;

import java.io.*;
/**
 已AC
 */
public class _4蓝桥快打 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int T = Int();
        for (int i = 0; i < T; i++) {
            int A1 = Int(), A2 = Int(), B2 = Int();
            int k = (A1 + B2 - 1) / B2;//2需要攻击1的次数,1必须在k次攻击内打败2
            int B1=(A2+k-1)/k;
            System.out.println(B1);
        }
    }
}
