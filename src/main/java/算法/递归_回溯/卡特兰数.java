package 算法.递归_回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 卡特兰数 {
    /*
    C(n)= &sum i=0 → n-1  C(i)*C(n-1-i)<br>
     C(5) = C(0)*C(4) + C(1)*C(3) + C(2)*C(2) + C(4)*C(0) + C(3)*C(1)
     */
    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            //求第C(i)的拆分
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
    //不需要改动
}

class 出栈序列 {
    /*
    元素以 1,2,...,n进栈 ,进栈的同时随时可以出栈,最后一个元素入栈后,全部出栈
    问出栈顺序有多少
     */

    /*
    右节点表示在之前出栈的个数,左节点表示在之后出栈的个数
    n=4
        1最先出        1第二出        1第三出        1最后出
        /   \          /  \         /   \         /   \
      C(3)  C(0)     C(2) C(1)    C(1)  C(2)     C(0)  C(3)
     */
    //不需要改动
}

class 括号生成 {
    //n对括号组成有效序列种数
    /*
     左节点表示包含括号对数,右节点表示剩余括号对数
     n=4时
         1              2            3            4
        /   \         /    \       /    \       /   \
     C(0)   C(3)    C(1)   C(2)  C(2)   C(1)   C(3) C(0)
     */
    public static void main(String[] args) {
        int n = 10;
        List<String>[] dp = new ArrayList[n + 1];
        dp[0] = Arrays.asList("");//0对括号为""
        dp[1] = Arrays.asList("()");//1对括号为"()"
        for (int i = 2; i <= n; i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                int n1 = j;//内层嵌套的括号数
                int n2 = i - 1 - j;//与之平级的括号数
                //组合
                for (String k1 : dp[n1]) {
                    for (String k2 : dp[n2]) {
                        dp[j].add("(" + k1 + ")" + k2);//k1被嵌套,k2平级拼接
                    }
                }

            }

        }
        System.out.println(Arrays.toString(dp));
        System.out.println(dp[n]);
    }
}
