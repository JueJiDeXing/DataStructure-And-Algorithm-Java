package 基础数据结构算法.树.其他;

import java.util.Scanner;

public class _1040加分二叉树 {
    /*
   # [NOIP2003 提高组] 加分二叉树
   ## 题目描述
   设一个 n 个节点的二叉树 tree 的中序遍历为(1,2,3,...,n)，其中数字 1,2,3,...,n 为节点编号
   每个节点都有一个分数(均为正整数),记第 i 个节点的分数为 d_i
   tree 及它的每个子树都有一个加分,任一棵子树 subtree的加分计算方法如下:
   subtree的左子树的加分 * subtree的右子树的加分 + subtree的根的分数。
   若某个子树为空,规定其加分为1,叶子的加分就是叶节点本身的分数。不考虑它的空子树。

   试求一棵符合中序遍历为 (1,2,3,...,n) 且加分最高的二叉树tree,要求输出:
   1. tree的最高加分。
   2. tree的前序遍历。

   ## 输入格式
   第 $1$ 行 $1$ 个整数 $n$，为节点个数。
   第 $2$ 行 $n$ 个用空格隔开的整数，为每个节点的分数
   ## 输出格式
   第 $1$ 行 $1$ 个整数，为最高加分（$ Ans \le 4,000,000,000$）。
   第 $2$ 行 $n$ 个用空格隔开的整数，为该树的前序遍历。
   ## 样例 #1
   ### 样例输入 #1
   ```
   5
   5 7 1 2 10
   ```
   ### 样例输出 #1
   ```
   145
   3 1 2 4 5
   ```
   ## 提示
   ### 数据规模与约定
   对于全部的测试点，保证 1 < n < 30，节点的分数是小于 100 的正整数，答案不超过 4*10^9。
    */
    public static void main(String[] args) {
        //输入
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] str = scanner.nextLine().split(" ");
        /*
        dp[i][j]表示由i到j可组成的最大加分子树的分数
        dp[i][i] = e为初始分数
        dp[i][i - 1] = 1;//前一项表示起点左边的空左子树,分值为1
        在区间i~j内枚举根节点k,dp[i][j]=MAX(dp[i][k-1]+dp[k-1][j]+dp[k][k])
         */
        int[][] dp = new int[n + 1][n + 1];
        /*
        root[i][j]表示由i到j可组成的最大加分子树的根节点
        root[i][i]=i,仅有一个节点
        在区间i~j内枚举根节点k,dp[i][j]=MAX(dp[i][k-1]+dp[k-1][j]+dp[k][k]),最大的k即为root[i][j]
         */
        int[][] roots = new int[n + 1][n + 1];
        for (int i = 1; i <= str.length; i++) {
            int e = Integer.parseInt(str[i - 1]);
            dp[i][i] = e;
            roots[i][i] = i;
            dp[i][i - 1] = 1;//前一项表示起点左边的空左子树,分值为1
        }
        doAdd(n, dp, roots);
        System.out.println(dp[1][n]);
        print(1, n, roots);
    }

    public static void doAdd(int n, int[][] dp, int[][] roots) {

        for (int len = 1; len <= n; len++) {//枚举区间长度
            for (int start = 1; start + len <= n; start++) {//枚举起点
                int end = start + len;//终点
                dp[start][end] = dp[start + 1][end] + dp[start][start];//以start为根,左子树为空,右子树分数为dp[start + 1][end]
                roots[start][end] = start;
                for (int root = start + 1; root < end; root++) {//枚举根节点
                    //start ~ root-1为左子树,root+1 ~ end为右子树
                    int newScore = dp[start][root - 1] * dp[root + 1][end] + dp[root][root];
                    if (dp[start][end] < newScore) {//以当前root为根分数更高
                        dp[start][end] = newScore;
                        roots[start][end] = root;
                    }
                }
            }
        }
    }

    /**
     前序遍历<br>
     打印从start~end可组成的最大加分子树的根节点<br>
     print(1,n) <-> 打印root[1][n] && print(1,k-1) && print(k+1,n)  根、左、右
     */
    public static void print(int start, int end, int[][] roots) {
        if (start > end) return;
        System.out.printf("%d ", roots[start][end]);
        if (start == end) return;
        print(start, roots[start][end] - 1, roots);
        print(roots[start][end] + 1, end, roots);
    }


}
