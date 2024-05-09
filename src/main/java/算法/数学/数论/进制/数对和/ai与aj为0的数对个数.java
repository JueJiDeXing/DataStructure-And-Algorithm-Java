package 算法.数学.数论.进制.数对和;

public class ai与aj为0的数对个数 {
    /*
    求 count{ ai&aj=0 }
     */
    private static long count(int n, int[] A) {
        int M = 0x7fffffff;
        int[] d = new int[M];
        for (int i = 1; i <= n; ++i) d[A[i]]++;
        for (int i = 0; i < 32; ++i) {
            for (int j = 1; j < M; ++j) {
                if ((j & (1 << i)) != 0) {
                    d[j] += d[j - (1 << i)];
                }
            }
        }
        long ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += d[M - 1 - A[i]];
        }
        return ans;
    }


}
