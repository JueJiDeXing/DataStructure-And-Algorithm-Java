package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.io.*;

/**
 不会
 */
public class I高塔 {
    /*
    n个回合,第i回合状态为A[i]
    初始能量m
    每回合可以选择消耗[1,m]点能量
    假设消耗了Ci点能量,那么可以向上爬[1,Ci*A[i]]个单位
    如果能量耗尽,或者进行了n回合,则游戏结束
    求游戏的总方案数
     */
    static int MOD = 998244353;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static long Long() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (long) st.nval;
    }

    /**
     <pre>
        \ 剩余能量    0      1         2                              3                                    4                  5                   6
     当前回合   n     0   1*A[n-1]   (1+2)*A[n-1]                 (1+2+3)*A[n-1]                 (1+2+3+4)*A[n-1]   (1+2+3+4+5)*A[n-1]   (1+2+3+4+5+6)*A[n-1] ...
              n-1   0   1*A[n-2]   1*A[n-2]+1*A[n-1]+2*A[n-2]  1*A[n-2]+(1+2)*A[n-1]+2*A[n-2]
              n-2   0   1*A[n-3]
              n-2   0
              ...


                  k

     n-t      k(k+1)/2 * A[n-t]+sum(0,k)
     </pre>

     */
    public static void main(String[] args) {
        n = Int();
        int m = (int) Long();
        A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        //System.out.println(f(0, m));//TODO 翻译为递推
        int[]dp=new int[n+1];
        System.arraycopy(A, 0, dp, 1, n);
        for(int j=0;j<m;j++){

        }
    }

    static int[] A;
    static int n;
static  long[][]memo;
    /**
     当前进行到了第i回合,剩余remain点能量的方案数
     */
    static long f(int i, long remain) {//5AC 15TLE
        if (i == n || remain == 0) return 1;
        long ans = 0;
        for (long Ci = 1; Ci <= remain; Ci++) {
            ans = (ans + Ci * A[i] % MOD * f(i + 1, remain - Ci) % MOD) % MOD;
        }
        return ans;
    }
}






