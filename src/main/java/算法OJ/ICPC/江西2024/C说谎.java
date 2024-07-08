package 算法OJ.ICPC.江西2024;

import java.io.*;
import java.util.Scanner;

public class C说谎 {
    /*
    n个人, 每个人有一个数字, 数字之和为s
    现在给出每个人说出的数字, 问最多有几个人没说谎
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int n = I(), s = I();
        long sum = 0;
        for (int i = 0; i < n; i++) sum += I();
        if (sum == s) {
            System.out.println(n);
        } else {
            System.out.println(n - 1);
        }
    }


}
