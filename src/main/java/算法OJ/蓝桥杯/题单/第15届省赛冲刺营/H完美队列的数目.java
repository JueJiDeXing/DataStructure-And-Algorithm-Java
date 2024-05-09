package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.Arrays;
import java.util.Scanner;
/**
 已AC
 */
public class H完美队列的数目 {
    /**
     A[i] ... k-A[i]
     len
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int k = sc.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        Arrays.sort(A);
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] * 2 > k) break;
            int idx = binarySearch(i, k - A[i]);
            ans = (ans + fastPow(2, idx - i)) % MOD;
        }
        System.out.println(ans);
    }

    static int binarySearch(int left, int target) {
        int right = n;
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (A[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    static int n;
    static int[] A;
    static int MOD = 10_0000_0007;

    static long fastPow(long x, long n) {
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }

}
