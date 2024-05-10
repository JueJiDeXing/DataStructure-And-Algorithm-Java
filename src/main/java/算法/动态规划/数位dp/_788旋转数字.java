package 算法.动态规划.数位dp;

import java.util.*;

/**
 第 73 场周赛 Q1
 难度分:1397
 */
public class _788旋转数字 {
    /*
    我们称一个数 X 为好数, 如果它的每位数字逐个地被旋转 180 度后，我们仍可以得到一个有效的，且和 X 不同的数。
    要求每位数字都要被旋转。
    如果一个数的每位数字被旋转以后仍然还是一个数字， 则这个数是有效的。
    0, 1, 和 8 被旋转后仍然是它们自己；
    2 和 5 可以互相旋转成对方（在这种情况下，它们以不同的方向旋转，换句话说，2 和 5 互为镜像）； 6 和 9 同理，
    除了这些以外其他的数字旋转以后都不再是有效的数字。
    现在我们有一个正整数 N, 计算从 1 到 N 中有多少个数 X 是好数？
     */
    //无法旋转: 3 4 7(不能含有这些数字)
    //可旋转: 0 1 8(可以含有这些数字,但不能全为这些数字)
    //好数: 2,5  6,9(这些数字至少需要含有一项)
    // F:32 100 1080
    // T:20 999 10086
    static int[] diffs = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    char[] s;
    int[][] dp;//记忆化

    public int rotatedDigits(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][2];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        return f(0, 0, true);
    }

    public int f(int i, int hasDiff, boolean isLimit) {
        if (i == s.length) {
            return hasDiff;
        }
        if (!isLimit && dp[i][hasDiff] >= 0) {
            return dp[i][hasDiff];
        }
        int res = 0;
        int up = isLimit ? s[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            if (diffs[d] != -1) {
                res += f(i + 1, hasDiff | diffs[d], isLimit && d == up);
            }
        }
        if (!isLimit) {
            dp[i][hasDiff] = res;
        }
        return res;
    }
}
