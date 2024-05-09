package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class F二进制问题 {
    //[1,n]上有多少个数二进制中1的个数为k
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong();
    int k = sc.nextInt();
    high = Long.toBinaryString(n);//将n转为二进制
    memo = new long[high.length()][k + 1][2];//记忆化
    for (int i = 0; i < high.length(); i++) {
        for (int j = 0; j <= k; j++) {
            Arrays.fill(memo[i][j], -1);//初始化为-1
        }
    }
    System.out.println(f(0, k, true));// 二进制数位dp
}

static String high;
static long[][][] memo;

/**
 @param i       当前枚举数位
 @param k       剩余可填1的个数
 @param isLimit 前面填的数字是否都到达high的上界
 */
static long f(int i, int k, boolean isLimit) {
    int n = high.length();
    if (i == n) {//已枚举全部数位
        return k == 0 ? 1 : 0;//需要恰好为k个1
    }
    if (memo[i][k][isLimit ? 1 : 0] != -1) return memo[i][k][isLimit ? 1 : 0];//记忆化
    int up;
    if (k == 0) {//不能填1了
        up = 0;
    } else {//还能填1
        up = isLimit ? high.charAt(i) - '0' : 1;//根据上界确定范围,如果前面的数都触达n上界,那么该位也受到限制,否则无限制可1可0
    }
    long ans = 0;
    for (int j = 0; j <= up; j++) {
        ans += f(i + 1, k - (j == 1 ? 1 : 0), isLimit && j == up);
    }
    return memo[i][k][isLimit ? 1 : 0] = ans;
}
}
