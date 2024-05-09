package 算法OJ.蓝桥杯.算法赛.小白入门赛.第5场;

import java.io.*;
/**
 已AC
 */
public class _4元素交换 {
    /*
    N个0和N个1组成的二进制数组A
    可以交换数组中的任意两个元素,最少需要交换多少次,才能使所有0和1不相邻
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     贪心:检查010101...和101010...两种与数组A有多少位不同,取小的一种
     */
    public static void main(String[] args) {
        int n = Int();
        boolean one = true;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < (n << 1); i++) {
            int a = Int();
            if (one) {
                if (a != 1) {
                    count1++;
                } else {
                    count2++;
                }
            } else {
                if (a != 0) {
                    count1++;
                } else {
                    count2++;
                }
            }
            one = !one;
        }
        System.out.println(Math.min(count1, count2) / 2);
    }
}
