package 算法OJ.蓝桥杯.算法赛.小白入门赛.第6场;

import java.io.*;
/**
 已AC
 */
public class _3数学奇才 {
    /*
    长度为n的数组a
    可以进行不超过n次操作:
    选择一个子数组,将它们都变为它们的相反数

    求数组a的最大总和
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
        long sum = 0;
        int n = Int();
        for (int i = 0; i < n; i++) {
            sum += Math.abs(Int());
        }
        System.out.println(sum);
    }
}
