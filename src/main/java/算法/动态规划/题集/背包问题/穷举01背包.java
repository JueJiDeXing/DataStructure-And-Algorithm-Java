package 算法.动态规划.题集.背包问题;

public class 穷举01背包 {
    static int[] w = {5, 6, 3, 2};
    static int[] v = {7, 5, 4, 2};
    static int W = 10;// 最大容量

    public static void main(String[] args) throws Exception {
        System.out.println(dfs(0, W));
    }

    /**
     枚举每件物品选或不选
     */
    public static int dfs(int i, int remainW) {
        if (i >= w.length) return 0;
        int ans = dfs(i + 1, remainW);//不选
        if (w[i] <= remainW) {// 可以选
            ans = Math.max(ans, v[i] + dfs(i + 1, remainW - w[i]));
        }
        return ans;
    }
}
