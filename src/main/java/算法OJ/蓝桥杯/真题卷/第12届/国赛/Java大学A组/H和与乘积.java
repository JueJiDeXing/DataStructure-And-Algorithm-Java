package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.math.BigInteger;
import java.util.*;

/**
 已AC
 */
public class H和与乘积 {
    /*
    数组A,区间[left,right]的和等于乘积,求个数
     */
    static long MAX_VALUE = 400_0000_0000L;
    static int maxN = 200_0007;
    static long[] a = new long[maxN], pre = new long[maxN];

    /**
     积是成倍增长的,积的增长比和要快很多<br>
     唯一的特殊点在于1,乘1不变,和增加<br>
     所以根据单调性,对于一段连续的1区间,最多有1个解<br>
     前缀和pre[i] = Sum( a[0...i] )<br>
     Sum( a[l,r] ) = pre[r] - pre[l-1]<br>
     枚举区间左端点l,维护区间乘积res,再枚举区间右端点r,其中r位置的数不为1<br>
     x 1...1  y1 1..1 y2 ..<br>
     l        r1      r2     r3...<br>
     (rk-1,rk)上最多有一个解,再检查rk是否是一个解,这样就把所有位置的解求出来了<br>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> pos = new ArrayList<>();//存储非1数的下标
        pos.add(0);//索引从1开始
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
            if (a[i] != 1) pos.add(i);
            pre[i] = pre[i - 1] + a[i];//前缀和
        }
        pos.add(n + 1);//区间终点
        System.out.println(pos);
        long ans = 0;
        for (int l = 1; l <= n; l++) {//枚举区间左端点
            long res = 1;//区间乘积,如果l位置是1,则初始为1,如果l位置不是1,那么now就是l
            int now = 0; //找到下标l之后第一个不为1的数的下标idx,其中idx=pos[now],满足a[idx]!=1 && idx>=l
            for (int i = 0; i < pos.size(); i++) {//TODO 二分
                Integer idx = pos.get(i);
                if (idx >= l) {
                    now = i;
                    break;
                }
            }

            for (int j = now; j < pos.size(); j++) {//枚举区间右端点(不为1的数)
                int r = pos.get(j);
                int cnt = pos.get(j) - pos.get(j - 1) - 1;//前一个不为1的数到当前不为1的数的区间上1的个数
                long m = pre[r - 1] - pre[l - 1];
                if (res <= m && m - cnt < res) {  //如果连续的1区间内有解
                    //m>=res:区间总和大于(等于)乘积; m-cnt<res:区间总和减去1的个数小于乘积 ==> 根据单调关系,必然存在一个位置有解
                    ans++;
                }
                res = res * a[r];
                if (r != n + 1 && res == pre[r] - pre[l - 1]) {  //当前不为1的位置 是一个解
                    //r!=n+1:n+1是数组越界位置; res == pre[r] - pre[l - 1]:区间乘积等于区间乘积
                    ans++;
                }
                if (res >= MAX_VALUE) break;//res太大了,可以提前退出
            }
        }
        System.out.println(ans);
    }


    private static void solve1() {//2/10
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        long[] preSum = new long[n + 1];
        BigInteger[] preMul = new BigInteger[n + 1];
        preMul[0] = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + A[i];
            preMul[i + 1] = preMul[i].multiply(BigInteger.valueOf(A[i]));
        }
        int ans = n;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //区间A[i,j]是否满足要求
                if (preMul[j + 1].divide(preMul[i]).compareTo(BigInteger.valueOf(preSum[j + 1] - preSum[i])) == 0)
                    ans++;
            }
        }
        System.out.println(ans);
    }
}
