package 算法OJ.ICPC.江西2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class A求和 {
    /*
    输入3个数,求和输出
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        int a = I(), b = I(), c = I();
        System.out.println(a + b + c);
    }

}
