package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.*;
import java.util.Scanner;
/**
 已AC
 */
public class G_宗师 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static Scanner sc = new Scanner(System.in);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));


    static long L() throws IOException {
        return sc.nextLong();
    }


    public static void main(String[] args) throws Exception {
        long x = L(), y = L(), k = L(), d = L();
        if (x > 0 && y > 0 && Math.abs(x + 99 * y - d) <= k) {
            System.out.println("Hello World!");
        } else {
            System.out.println("Help Need!");
        }
    }

}
/*
1 1 0 100
2 1 1 103
0 20 10000000001 1991
 */
