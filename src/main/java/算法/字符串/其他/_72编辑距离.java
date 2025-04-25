package 算法.字符串.其他;

import java.util.*;

public class _72编辑距离 {
    /*
    给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
    你可以对一个单词进行如下三种操作：

    插入一个字符
    删除一个字符
    替换一个字符
     */

    /**
     <h1>递归</h1>
     令f(i,j)表示将A[0,i]转换为B[0,j]的最小操作次数
     从两个字符串的末尾开始比较
     如果A[i]==B[j],那么该位匹配,不需要操作,f(i,j)=f(i-1,j-1)
     如果A[i]!=B[j],那么有三种操作方法
     1. 删除:删除A[i],那么f(i,j) = 1 + f(i-1,j) // 删除1次,匹配A[0,i-1]和B[0,j]为f(i-1,j)
     2. 插入:在A中插入等价于在B中删除,那么f(i,j) = 1 + f(i,j-1) //删除1次,匹配A[0,i]和B[0,j-1]为f(i,j-1)
     3. 替换:替换A[i]为B[j],那么f(i,j) = 1 + f(i-1,j-1) // 替换1次,匹配A[0,i-1]和B[0,j-1]为f(i-1,j-1)
     */
    public int minDistance1(String text1, String text2) {
        s = text1.toCharArray();
        t = text2.toCharArray();
        int n = s.length, m = t.length;
        memo = new int[n][m];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1); // -1 表示还没有计算过
        return dfs(n - 1, m - 1);
    }

    private char[] s, t;
    private int[][] memo;//记忆化

    private int dfs(int i, int j) {
        if (i < 0) return j + 1;//考虑边界
        if (j < 0) return i + 1;
        if (memo[i][j] != -1) return memo[i][j];
        if (s[i] == t[j]) return memo[i][j] = dfs(i - 1, j - 1);
        return memo[i][j] = Math.min(Math.min(dfs(i - 1, j), dfs(i, j - 1)), dfs(i - 1, j - 1)) + 1;
    }

    /**
     <h1>翻译为递推</h1>
     */
    public int minDistance2(String text1, String text2) {
        char[] s = text1.toCharArray(), t = text2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int j = 1; j <= m; ++j) f[0][j] = j;
        for (int i = 0; i < n; ++i) {
            f[i + 1][0] = i + 1;
            for (int j = 0; j < m; j++) {
                if (s[i] == t[j]) {
                    f[i + 1][j + 1] = f[i][j];
                } else {
                    f[i + 1][j + 1] = min(f[i][j + 1], f[i + 1][j], f[i][j]) + 1;
                }
            }
        }
        return f[n][m];
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    /**
     <h1>降维-滚动数组</h1>
     */
    public int minDistance3(String text1, String text2) {
        char[] t = text2.toCharArray();
        int m = t.length;
        int[] f = new int[m + 1];
        for (int j = 1; j <= m; j++) f[j] = j;//f[0][j] = j;
        for (char x : text1.toCharArray()) {
            int pre = f[0];// f[i + 1][0] = i + 1;
            f[0]++;
            for (int j = 0; j < m; ++j) {
                int tmp = f[j + 1];
                if (x == t[j]) {
                    f[j + 1] = pre;
                } else {
                    f[j + 1] = min(f[j + 1], f[j], pre) + 1;
                }
                pre = tmp;
            }
        }
        return f[m];
    }
}
