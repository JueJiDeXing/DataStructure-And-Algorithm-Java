package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第1场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class _3数对统计 {
    /*
    统计 ai ^ aj = ai + aj 的下标对(i,j)个数
    i可以等于j, 1<=i,j<=n
    // 求 ai & aj = 0的对数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = Int();
        }
        int M = 1 << 20;//A[i]<2^20
        int[] d = new int[M];
        for (int i = 1; i <= n; ++i) d[A[i]]++;

        for (int i = 0; i < 20; ++i) {
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
        System.out.println(ans);
    }
}
