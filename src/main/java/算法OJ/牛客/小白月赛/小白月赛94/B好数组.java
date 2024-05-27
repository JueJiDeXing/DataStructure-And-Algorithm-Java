package 算法OJ.牛客.小白月赛.小白月赛94;

import java.io.*;
/**
 已AC
 */
public class B好数组 {
    
    public static void main(String[] args) throws Exception {
        int n = I();
        int[] a = new int[n];
        for (int i = 1; i < n; i++) a[i] = I();
        System.out.println(check(a, n) ? 0 : n);
    }

    static boolean check(int[] a, int n) {//a是否有序
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > a[i + 1]) return false;
        }
        return true;
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
 
    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    } 

}

