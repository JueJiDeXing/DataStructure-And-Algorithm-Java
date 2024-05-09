package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class E123 {
    // (1),(1,2),(1,2,3),(1,2,3,4)...
    // 数列1 1 2 1 2 3 1 2 3 4...的前n项和

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int T = sc.nextInt();
    for (int i = 0; i < T; i++) {
        long l = sc.nextLong(), r = sc.nextLong();
        System.out.println(SRange(l, r)); // 数列第l项至第r项的和
    }
}

static int N = 10000000;
static long[] T = new long[N + 1];// T[n]: 数列{1,2,3,4...}的前n项和
static long[] pre = new long[N + 1];// T的前缀和

static {
    for (int i = 1; i < N; i++) T[i] = T[i - 1] + i;
    for (int i = 1; i < N; i++) pre[i] = pre[i - 1] + T[i];
}

private static long SRange(long l, long r) {
    return S(r) - S(l - 1);
}

/**
 S(n) = (1) + (1+2) +(1+2+3) + ... + (1+2+..+m) + (1+2+..+k)
 = T(1)+T(2)+...+T(m) + T(k)
 */
private static long S(long n) {
    if (n == 0) return 0;
    int m = getM(n);
    int k = (int) (n - (long) m * (m + 1) / 2);
    return pre[m] + T[k];
}

private static int getM(long n) {
    // (m+1)m/2 <= n < (m+2)(m+1)/2
    int left = 1, right = (int) Math.sqrt(2 * n);// m^2 + m <= 2n --> m < sqrt(2n)
    int m = 1;
    while (left <= right) {
        int mid = (left + right) >>> 1;
        if ((long) (mid + 1) * mid / 2 > n) {
            right = mid - 1;
        } else {
            m = mid;
            left = mid + 1;
        }
    }
    return m;
}
}
