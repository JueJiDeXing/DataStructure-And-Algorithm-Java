package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
/**
 已AC
 */
public class F分考场 {
    /*
    n个人,认识的两个人不能分在同一考场
    求考场最少需要的个数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     枚举
     */
    public static void main(String[] args) {
        n = Int();
        int m = Int();
        graph = new boolean[n][n];
        for (int i = 0; i < m; i++) {
            int a = Int() - 1, b = Int() - 1;
            graph[a][b] = graph[b][a] = true;
        }
        dfs(0, 0);
        System.out.println(ans);
    }

    static int n;
    static int ans = 100;//n<=100,最多分100个考场
    static boolean[][] graph;
    static List<List<Integer>> exam = new ArrayList<>();


    /**
     @param i 当前枚举的人
     @param k 当前分出的考场个数
     */
    static void dfs(int i, int k) {
        if (k >= ans) return;//剪枝:人还没枚举完,考场就更多了(大于过不了 3AC 2TLE ,大于等于能过)
        if (i == n) {
            ans = k;
            return;
        }
        for (int j = 0; j < k; j++) { //去第j个考场
            //检查能不能去
            List<Integer> e = exam.get(j);
            if (!check(i, e)) continue;
            //i去j考场
            e.add(i);
            dfs(i + 1, k);//继续枚举i+1
            e.remove(e.size() - 1);
        }
        //不去任何考场,单开一个
        ArrayList<Integer> e = new ArrayList<>();
        e.add(i);
        exam.add(e);
        dfs(i + 1, k + 1);
        exam.remove(exam.size() - 1);

    }

    /**
     检查i能不能去考场e
     */
    static boolean check(int i, List<Integer> e) {
        for (int j : e) {
            if (graph[i][j]) return false;
        }
        return true;
    }
}
