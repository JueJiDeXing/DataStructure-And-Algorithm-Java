package 算法OJ.牛客.小白月赛.小白月赛90;

import java.util.*;
import java.io.*;

public class D_线段覆盖 {
    /*
    [1,n]的数轴上给定m条线段
    求有多少种线段的选择方案,使[1,n]每个点都被覆盖两次
     */

    static int mod = 998244353;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int[][][] dp = new int[401][401][401];

    public static void main(String[] args) {
        // 离散化
        int n = nextInt(), m = nextInt();
        Set<Integer> set = new HashSet<>();
        int[] s = new int[m + 1], t = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            s[i] = nextInt() - 1;
            t[i] = nextInt();
            set.add(s[i]);
            set.add(t[i]);
        }
        set.add(n);
        set.add(0);
        n = set.size();
        Integer[] v = set.toArray(new Integer[0]);
        Arrays.sort(v);
        // 存储线段id
        int[][] a = new int[m + 1][2];
        for (int i = 1; i <= m; i++) {
            //lower_bound,大于等于的第一个
            int id = Arrays.binarySearch(v, s[i]);
            s[i] = id < 0 ? -id - 1 : id;
            id = Arrays.binarySearch(v, t[i]);
            t[i] = id < 0 ? -id - 1 : id;
            a[i] = new int[]{s[i], t[i]};
        }
        Arrays.sort(a, 1, m + 1, (a1, a2) -> {
            if (a1[0] != a2[0]) return a1[0] - a2[0];
            return a1[1] - a2[1];
        });
        // dp扫描线段求解
        dp[0][0][0] = 1;
        for (int k = 1; k <= m; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (dp[k - 1][i][j] == 0) continue;
                    dp[k][i][j] = (dp[k][i][j] + dp[k - 1][i][j]) % mod;
                    if (a[k][0] <= i) {
                        int p2 = Math.max(j, a[k][1]);
                        int p1 = Math.max(i, p2);
                        dp[k][p1][p2] = (dp[k][p1][p2] + dp[k - 1][i][j]) % mod;
                    }
                }
            }
        }
        System.out.println(dp[m][n - 1][n - 1]);
    }


}
