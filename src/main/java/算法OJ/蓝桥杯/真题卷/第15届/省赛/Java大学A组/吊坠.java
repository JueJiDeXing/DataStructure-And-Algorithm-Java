package 算法OJ.蓝桥杯.真题卷.第15届.省赛.Java大学A组;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 吊坠 {
/*
给定n个点,每个点的值为一个环形字符串
这些点两两之间有一条边, 边权为两个点的最长公共子串长度

求最大生成树的边权值之和
 */

    static class UnionSet {
        int[] root;

        UnionSet(int n) {
            root = new int[n];
            for (int i = 1; i < n; i++) {
                root[i] = i;
            }
        }

        int find(int x) {
            if (root[x] == x) return x;
            return root[x] = find(root[x]);
        }

        boolean union(int x, int y) {
            int rx = find(x), ry = find(y);
            root[rx] = ry;
            return rx == ry;
        }


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) strs[i] = sc.next();
        // 求边权
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new int[]{i, j, cal(strs[i], strs[j])});
            }
        }
        edges.sort((a, b) -> b[2] - a[2]);
        // 求最大生成树
        UnionSet set = new UnionSet(n);
        int choose = 0, ans = 0;
        for (int i = 0; i < edges.size(); i++) {
            int[] edge = edges.get(i);
            int u = edge[0], v = edge[1], w = edge[2];
            if (set.union(u, v)) continue;
            ans += w;
            if (++choose == n - 1) break;
        }
        System.out.println(ans);
    }

    static int cal(String s1, String s2) {
        int m = s1.length();
        s1 += s1;
        s2 += s2;
        int[][] dp = new int[2 * m + 1][2 * m + 1];// dp[i][j]: 以s1[i]和s2[j]结尾的最长公共子串长度
        int maxLen = 0;
        for (int i = 1; i <= 2 * m; i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= 2 * m; j++) {
                char c2 = s2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return Math.min(maxLen, m);
    }
}


