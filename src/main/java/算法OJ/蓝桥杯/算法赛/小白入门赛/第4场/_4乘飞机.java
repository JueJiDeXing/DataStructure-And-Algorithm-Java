package 算法OJ.蓝桥杯.算法赛.小白入门赛.第4场;

import java.io.*;
/**
 已AC
 */
public class _4乘飞机 {
    /*
    一个长度为n的数组A, 0<=A[i]<=36500
    q次询问,每次询问给出l,r
    如果A[l,r]上存在abs(A[i]-A[j])<=365,则输出YES,否则输出NO
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     鸽巢原理: 当查询数字个数大于100时,0~36500这个范围一定有重叠部分
     当数字个数小于100时,可以直接暴力求解了
     */
    public static void main(String[] args) {
        int n = Int(), q = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = Int();
        out:
        for (int i = 0; i < q; i++) {
            int l = Int() - 1, r = Int() - 1;
            if (r - l + 1 > 100) {
                System.out.println("YES");
                continue;
            }
            //暴力枚举数对(A[j],A[k]),判断差是否小于等于365
            for (int j = l; j < r; j++) {
                for (int k = j + 1; k <= r; k++) {
                    if (Math.abs(A[j] - A[k]) <= 365) {
                        System.out.println("YES");
                        continue out;
                    }
                }
            }
            System.out.println("NO");
        }

    }
}
