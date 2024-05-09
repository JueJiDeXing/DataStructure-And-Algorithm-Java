package 算法OJ.蓝桥杯.其他;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 转前缀和后需要对数组排序,但是开头和结尾不能动,两两之间差的最大值需要最小
 */
public class 灵能传输 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int t = nextInt();
        while (t-- > 0) {
            solve();
        }
    }

    static int n;


    static void solve() {
        n = nextInt();
        long[] preSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nextInt();
        }
        if (preSum[n] < 0) {
            swap(preSum, 0, n);
        }
        long end = preSum[n];
        Arrays.sort(preSum);
        int s = Arrays.binarySearch(preSum, 0);
        int e = Arrays.binarySearch(preSum, end);
        long[] ans = new long[n + 1];

    }

    static void swap(long[] arr, int i, int j) {
        long t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}
