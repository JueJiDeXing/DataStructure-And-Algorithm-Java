package 算法OJ.蓝桥杯.算法赛.小白入门赛.第6场;

import java.io.*;
import java.util.Arrays;

/**
 已AC
 */
public class _5等腰三角形 {
    /*
    A[i]=k表示有2个长度为k的红木棍,共2N个红木棍
    B[i]=k表示有1个长度为k的蓝木棍,共N个蓝木棍
    2个相同长度的红木棍+1个蓝木棍组成等腰三角形
    求最多组成多少个等腰三角形
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
     两边之和大于第三边
     A[i]+B[j]>A[j]恒成立,只需要保证A[i]*2>B[j]即可
     不能匹配是因为B[j]过大,所以优先匹配小的B[j],大B[j]留下来不匹配,这样会有更多A[i]可选(如果为了匹配大的B[j]放弃小的B[j],选择的可能性就少了)
     双指针,先对A和B升序排序,优先把小的B[j]匹配了,匹配成功i++,j++,A[i]不够则i++
     */
    public static void main(String[] args) {
        int N = Int();
        int[] A = new int[N], B = new int[N];
        for (int i = 0; i < N; i++) A[i] = 2 * Int();
        for (int i = 0; i < N; i++) B[i] = Int();
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0, j = 0;
        int ans = 0;
        while (i < N && j < N) {
            if (A[i] > B[j]) {
                i++;
                j++;
                ans++;
            } else {
                i++;
            }
        }
        System.out.println(ans);
    }
}
