package 算法OJ.牛客.五一集训1;

import java.io.*;
import java.util.Scanner;

public class F射气球 {
    /*
    n个气球,第i个气球在(x[i],y[i])处
    给定r, 可以选择 x,x+r,x+2r 和 y,y+r,y+2r 六条线(水平和竖直分别选择三条距离为r的线)
    求线上的气球数最大值
    n,r,x,y<1e5
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String S() throws IOException {
        String res = bf.readLine();
        while (res.isEmpty()) res = bf.readLine();
        return res;
    }
    public static void main(String[] args) {

    }
}
