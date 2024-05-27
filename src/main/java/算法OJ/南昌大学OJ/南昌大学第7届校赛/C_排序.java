package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class C_排序 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(solve());
    }

    private static int solve() throws IOException {
        int n = I();
        int[] a = new int[n];
        int min = 0;
        for (int i = 0; i < n; i++) {
            a[i] = I();
            if (a[i] < a[min]) min = i;//找最小值位置
        }
        //最小值前面的都进行操作, 后面的操作无效,最小值放末尾,又会回到开头
        for (int i = min + 1; i < n; i++) {//只需要后面满足有序
            if (a[i - 1] > a[i]) return -1;//不有序
        }
        return min;//有序,输出最少次数
    }
}
/*
5
6 4 1 2 5
4
5 2 4 2
 */
