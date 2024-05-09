package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class F交换瓶子 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     只要保证一次交换中至少有一个元素回到原位,那么就是最优的交换方案
     */
    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n+1];
        for (int i = 1; i <= n; i++) {
            A[i] = Int();
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            while (A[i] != i) {
                ans++;
                swap(A, i, A[i]);
            }
        }
        System.out.println(ans);

    }

    static void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}
