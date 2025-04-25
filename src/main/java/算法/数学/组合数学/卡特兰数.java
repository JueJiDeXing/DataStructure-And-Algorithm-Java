package 算法.数学.组合数学;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 卡特兰数 {
    /*
    C(n) = sum_{i=0}^{n-1}{ C(i) * C(n-i-1) }<br>
     C(5) = C(0)*C(4) + C(1)*C(3) + C(2)*C(2) + C(4)*C(0) + C(3)*C(1)
     */
    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }

        }
        System.out.println(Arrays.toString(dp));
    }
}

class 不同的二叉搜索树 {
    /*
    给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
    返回满足题意的二叉搜索树的种数。
     */
    /**
     令dp[n]表示n个节点可以组成的二叉搜索树个数
     以第i个节点为根, 左子树有i-1个节点,数量dp[i-1],右子树有n-i个节点,数量dp[n-i]
     根据乘法原理: dp[n] = sum_{i=1}^{n}{ dp[i-1]*dp[n-i] }
     =  sum_{i=0}^{n-1}{ dp[i]*dp[n-i-1] }
     */
}

class 出栈序列 {
    /*
    元素以 1,2,...,n进栈 ,进栈的同时随时可以出栈,最后一个元素入栈后,全部出栈
    问出栈顺序有多少
     */

    /**
     以第一次栈空为界: [1,k]进栈出栈过程中栈一直非空(仅有k位于第k个出栈)
     则 出栈顺序 = [1,k]的出栈顺序 * [k+1,n]的出栈顺序

     dp[n] = sum_{k=0}^{n-1}{ dp[k] * dp[n-k-1] }
     */
}

class 括号生成 {
    // n对括号组成有效序列种数
    /*
     考虑第一个括号, 设其中间嵌套了k个括号, 右侧有n-k-1对括号与其拼接
     则 dp[n] = sum_{k=0}^{n-1}{ dp[k] * dp[n-k-1] }
     */
    public static void bracketsSequence(int n) {
        List<String>[] dp = new ArrayList[n + 1];
        Arrays.setAll(dp, k -> new ArrayList<>());
        dp[0].add("");//0对括号为""
        dp[1].add("()");//1对括号为"()"
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                for (String k1 : dp[j]) {
                    for (String k2 : dp[i - j - 1]) {
                        dp[j].add("(" + k1 + ")" + k2);//k1被嵌套,k2平级拼接
                    }
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }
}


class 走方格_上三角 {
    // 从(0,0)走到(n,n), 每次只能向上或向右, 并且右下角是不安全的(不能越过y=x这条线)

    /**
     解法一:

     设某条路线第一次到达y=x时坐标为(k,k), 将其称为第k类路线

     对于后半段: 这是一个 (k,k)到(n,n)的相同性质子问题

     对于前半段: 由于第一次到达y=x时为(k,k), 要求前半段不能到达y=x
     那么将y=x向上平移一个单位, 第一步只能向上走,最后一半只能向右走
     这是一个(1,1)到(k-1,k)的相同性质子问题

     则dp[n] = sum_{k=1}^{k=n}{ dp[k-1] * dp[n-k] }
            = sum_{k=0}^{n-1}{ dp[k] * dp[n-k-1] }
     */


    /**
     解法二:

     首先, 不考虑上三角限制, 到达(n,n)的方案数为C(2n,n)

     对于那些不安全的方案, 设其第一次越过y=x时到达(k+1,k)
     将其后面的路线沿 y=x-1 翻转, 那么终点将到达(n+1,n-1)

     相对的, 假设要到达(n+1,n-1), 设其第一次到达(k+1,k)
     将其后面的路线按照 y=x-1 翻转, 那么终点将到达(n,n)
     由于到达(n+1,n-1)都要越过y=x, 这些方案都是不安全的

     即: 到达(n,n)的不安全路线 与 到达(n+1,n-1)的路线 一一对应


     则 方案数为 C(2n,n) - C(2n,n-1) = C(2n,n) / (n+1)

     */

}
