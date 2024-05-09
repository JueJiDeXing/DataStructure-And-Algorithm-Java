package 算法OJ.蓝桥杯.题单.第15届第2期模拟;

import java.io.*;
/**
 已AC
 */
public class D区间最大和 {
    /*
    在数组a中,求长度为k的子数组的最大和
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
     滑动窗口求子数组最大和
     */
    public static void main(String[] args) {
        int n = Int(), k = Int();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Int();
        }
        int left = 0, right = 0;
        long max = 0, curr = 0;
        for (; right < n; right++) {
            curr += a[right];
            if (right - left >= k) {
                curr -= a[left];
                left++;
            }
            max = Math.max(max, curr);
        }
        System.out.println(max);
    }
}
