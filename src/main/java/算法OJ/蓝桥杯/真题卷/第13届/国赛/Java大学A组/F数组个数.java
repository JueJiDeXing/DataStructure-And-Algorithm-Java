package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.util.*;

/**
 没看懂答案
 */
public class F数组个数 {
    /*
    数组B=(b0,b1...bn-1)由环形数组A=(a0,a1...an-1)经过相邻最大化后得到
    b0=max(an-1,a0,a1),b1=max(a0,a1,a2)...
    输入数组B,问数组A可以有多少个,结果对1000000007取模
    元素非负,3<=n<=1000,0<=bi<=10

    B: 8 6 1 8 8
    A: 6 0 0 1 8
       6 0 1 0 8
       6 0 1 1 8
       6 1 0 0 8
       6 1 0 1 8
       6 1 1 0 8
       6 1 1 1 8
    A一共7个
     */
public static void main(String[] args) {
    main_enter2();
}

private static void main_enter() {
    //接收数据
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    sc.nextLine();
    int[] B = new int[2 * n];
    String[] arr = sc.nextLine().split(" ");
    for (int i = 0; i < n; i++) {
        B[i] = B[i + n] = Integer.parseInt(arr[i]);
    }
    //找最大值索引
    int maxIdx = 0;
    for (int i = 0; i < n; i++) {
        B[i] = B[i + n] = Integer.parseInt(arr[i]);
        if (B[i] > B[maxIdx]) maxIdx = i;
    }
    if (maxIdx == 0 && B[n - 1] == B[0]) {
        for (int i = n - 2; i >= 0; --i) {
            if (B[i] != B[maxIdx]) {
                maxIdx = i + 1;
                break;
            }
        }
    }
    if (maxIdx == n - 1) {
        maxIdx = 0;
    } else {
        n += ++maxIdx;
    }
    //[maxIdx,maxIdx+n)为一个循环数组B,其中B[maxIdx+n-1]为最大值
    //如果B有多个最大值,则其余最大值均分布在B[maxIdx+k],0<=k<最大值个数
    System.out.println("maxIdx:" + maxIdx + ", n:" + n);
    //动态规划
    int[][] dp = new int[2 * n][3];
    dp[maxIdx][2] = 1;
    for (int i = maxIdx + 1; i < n; ++i) {
        int up = min(B[i - 1], B[i], B[i + 1]);
        for (int k = 0; k <= up; ++k) {
            if (k < B[i - 1] && k < B[i] && k < B[i + 1]) {//k比三项都小
                dp[i][0] = (dp[i][0] + dp[i - 1][1]) % MOD;
                dp[i][1] = (dp[i][1] + dp[i - 1][2]) % MOD;
            } else if (k == B[i + 1]) {
                dp[i][2] = (dp[i][2] + dp[i - 1][2]) % MOD;
                if (k == B[i]) dp[i][2] = (dp[i][2] + dp[i - 1][1]) % MOD;
                if (k == B[i - 1]) dp[i][2] = (dp[i][2] + dp[i - 1][0]) % MOD;
            } else if (k == B[i]) {
                dp[i][1] = ((dp[i][1] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD;
                if (k == B[i - 1]) dp[i][1] = (dp[i][1] + dp[i - 1][0]) % MOD;
            } else if (k == B[i - 1]) {
                dp[i][0] = ((dp[i][0] + dp[i - 1][0]) % MOD + (dp[i - 1][1] + dp[i - 1][2]) % MOD) % MOD;
            }
        }
    }
    System.out.println(dp[n - 1][0]);
}

private static final int MOD = 1000000007;

    private static void main_enter2() {
        //接收数据
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int[] B = new int[n];
        String[] arr = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(arr[i]);
        }
        System.out.println(Arrays.toString(B));
        int ans = 0;
        int m = Math.min(B[0], B[1]);
        for (int a1 = 0; a1 < Math.min(m, B[n - 1]); a1++) {
            for (int a2 = 0; a2 < Math.min(m, B[2]); a2++) {
                ans += dfs(B, 2, n, a1, a2);
            }
        }
        System.out.println(ans);
    }


    private static int dfs(int[] B, int idx, int end, int a1, int a2) {
        if (idx == end) return 1;
        if (a2 > B[idx + 1]) return -1;

        if (a2 < B[idx] && a1 < B[idx - 1]) {
            return dfs(B, idx + 1, end, a2, B[idx]);
        }

        int ans = -1;
        int up = min(B[idx - 1], B[idx], B[idx + 1]);
        for (int a3 = 0; a3 <= up; a3++) {
            if (a3 > B[idx + 2]) continue;
            int dfs = dfs(B, idx + 1, end, a2, a3);
            ans = (ans + dfs) % MOD;
        }
        return ans;
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
