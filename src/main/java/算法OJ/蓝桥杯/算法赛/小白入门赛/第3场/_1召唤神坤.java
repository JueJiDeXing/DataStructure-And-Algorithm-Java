package 算法OJ.蓝桥杯.算法赛.小白入门赛.第3场;

import java.io.*;
/**
 已AC
 */
public class _1召唤神坤 {
    /*
    长度为N的数组,选择下标i<j<k,价值为floor((A[i]+A[k]) / A[j])
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
     前后缀最大值
     */
    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        int[] preMax = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preMax[i] = Math.max(preMax[i - 1], A[i - 1]);
        }
        int[] sufMax = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], A[i]);
        }
        long max = 0;
        for (int j = 1; j < n - 1; j++) {
            max = Math.max(max, ((long)preMax[j] + sufMax[j + 1]) / A[j]);
        }
        System.out.println(max);
    }
}
