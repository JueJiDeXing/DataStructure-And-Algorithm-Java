package 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场;

import java.io.*;
/**
 已AC
 */
public class _3质数王国 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int N = 100010;
    static int[] prime = new int[N];
    static int k = 0;
    static boolean[] isCom = new boolean[N + 1];

    static {
        for (int i = 2; i <= N; i++) {
            if (!isCom[i]) {
                //i是质数
                prime[k++] = i;
            }
            for (int j = 0; j < k; j++) {
                int t = i * prime[j];
                if (t > N) break;
                isCom[t] = true;
                if (i % prime[j] == 0) break;
            }
        }

    }

    public static void main(String[] args) {
        int n = Int();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int A = Int();
            ans += Math.abs(A - nearest(A));
        }
        System.out.println(ans);
    }

    /**
     n的最近的质数
     */
    static int nearest(int n) {
        int left = 0, right = k;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (prime[mid] == n) return n;
            if (n < prime[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return prime[right] - n > n - prime[left] ? prime[left] : prime[right];
    }
}
