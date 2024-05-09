package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;
/**
 已AC
 */
public class I糖果 {
    /*
    M种口味的糖果,编号1~M
    K个糖果一包出售,每包含有的糖果种类已知
    给定N包糖果,求最少购买几包才能买到全部口味的糖果
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
        int n = Int(), m = Int(), k = Int();
        int M = 1 << m;
        int allCanBuy = 0;//检查能不能都买到
        int[] A = new int[n];//A[i]:第i包能买到的糖果种类有哪些
        for (int i = 0; i < n; i++) {
            int iCanBuy = 0;
            for (int j = 0; j < k; j++) {
                int kind = Int() - 1;//编号0~m-1
                iCanBuy |= (1 << kind);
            }
            allCanBuy |= iCanBuy;
            A[i] = iCanBuy;
        }

        if (allCanBuy != (M - 1)) {
            System.out.println(-1);
            return;
        }
        int[] dp = new int[M];//dp[i]:i是一个整数压缩的集合表示买到的糖果种类,dp[i]指买到i这些种类的最小购买包数
        Arrays.fill(dp, n + 1);//初始化,不可能买到的糖果种类,购买包数至少为n+1
        dp[0] = 0;//初始化,买0种糖果,购买包数为0
        for (int i = 0; i < n; i++) {
            //初始:第i包不买
            //dp[j|A[i]]:在买的种类集合为j的基础上买第i包,dp[j]+1
            for (int j = M - 1; j >= 0; j--) {
                dp[j | A[i]] = Math.min(dp[j | A[i]], dp[j] + 1);
            }
        }
        System.out.println(dp[M - 1]);
    }

    private static void solve1() {//朴素dfs  4AC,4TLE
        N = Int();
        M = Int();
        K = Int();
        kind = new HashSet[N];//可以压缩存储
        ans = Integer.MAX_VALUE;
        Arrays.setAll(kind, k -> new HashSet<>());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                kind[i].add(Int());
            }
        }
        Arrays.sort(kind, (a, b) -> b.size() - a.size());
        HashSet<Integer> isVisited = new HashSet<>();
        dfs(0, 0, isVisited);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static int N, M, K;
    static int ans;
    static HashSet<Integer>[] kind;


    /**
     @param i         当前决策的是第几包
     @param buy       已买的包数
     @param isVisited 买到的糖果种类
     */
    static void dfs(int i, int buy, HashSet<Integer> isVisited) {
        if (buy >= ans) return;
        if (isVisited.size() == M) {
            ans = buy;
            return;
        }
        if (i == kind.length) return;
        //不买
        dfs(i + 1, buy, isVisited);
        //买
        HashSet<Integer> newIsVisited = new HashSet<>(isVisited);
        newIsVisited.addAll(kind[i]);
        dfs(i + 1, buy + 1, newIsVisited);
    }
}
