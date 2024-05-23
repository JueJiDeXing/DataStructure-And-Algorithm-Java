package 算法OJ.ICPC.江西2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC(状压,不懂)
 */
public class L_N皇后问题 {
    /*
    n*n的矩阵, 放n个皇后,每行每列只能有1个, 有一些位置不能放
    求合法方案数
     */
    public static void main(String[] args) throws Exception {
        n = I();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) map[i][j] = I();
        }
        for (int i = 0; i < (1 << n); i++) {  //i: 表示一行的可选状态
            int cnt = Integer.bitCount(i);  // 有多少个1
            rp[cnt]++; // rp[x]: 有x个1的状态个数
            status[cnt][rp[cnt]] = i;  // s[x][k] = i 表示有x个1的第k个状态为i
        }
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 尝试在(i,j)上放
                if (map[i][j] == 1) continue;  //不可选
                for (int k = 1; k <= rp[i - 1]; k++) {
                    int pre = status[i - 1][k];  //前面i-1行时的状态
                    if (((pre >> (j - 1)) & 1) != 0) continue; // j这一列有了
                    int cur = pre | (1 << (j - 1));  // pre + 第j位 -> cur
                    dp[i][cur] = (dp[i][cur] + dp[i - 1][pre]) % MOD;
                }
            }
        }
        long ans = dp[n][(1 << n) - 1];
        // n个皇后可以调换顺序, A(n,n)=n!
        for (int i = 1; i <= n; i++) ans = ans * i % MOD;
        System.out.println(ans);
    }

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in), 65535));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 1000000007;
    static int N = 21, M = (1 << 20) + 1;
    static int n;
    static int[][] map = new int[N][N];// 图, 0为空位
    static int[] rp = new int[N];// rp[x]: 有x个1的状态个数
    static int[][] status = new int[N][M];// s[x][k] = i 表示有x个1的第k个状态为i
    static int[][] dp = new int[N][M]; // 前n行 m个状态 里有多少个合法情况, dp[i][cur] = sum{ dp[i - 1][pre] }

}
