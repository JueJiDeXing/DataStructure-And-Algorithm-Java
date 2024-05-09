package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 已AC
 */
public class H倍数问题 {
    /*
    长度为n的数组,找到3个数,它们的和是K的倍数
    求和的最大值
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
     排序+暴力
     从大数开始枚举,一旦三个最大的可选数相加无法超过当前ans,则break
     */
    public static void main(String[] args) {
        int n = Int(), k = Int();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Int();
        }
        int ans = 0;
        //对数组排序，直接从后面大的进行相加
        Arrays.sort(A);
        for (int i = n - 1; i >= 2; i--) {//枚举数对(t,j,i)
            if (A[i] + A[i - 1] + A[i - 2] < ans) break;//(i-2,i-1,i)是可选的最大的和
            for (int j = i - 1; j >= 1; j--) {
                if (A[i] + A[j] + A[j - 1] < ans) break;//i固定,(j-1,j)是可选最大的和

                for (int t = j - 1; t >= 0; t--) {
                    int sum = A[i] + A[j] + A[t];
                    if (sum < ans) break;
                    if (sum % k == 0) ans = sum;
                }
            }
        }
        System.out.println(ans);
    }
}
