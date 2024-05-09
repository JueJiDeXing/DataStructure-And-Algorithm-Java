package 算法OJ.蓝桥杯.算法赛.小白入门赛.第3场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class _3怪兽突击 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int n, k;
    static int[] a, B;

    /**
     假设在i位置停止, i位置后面的怪兽都不打:
     现在[0,i]都打了第一次,一共i+1只
     对于贪心的来讲, 我们肯定会选择第二次[0,i]中价格最低的一个一直去打, 需要打k-i-1只
     其总费用是 [0,i]的第一次和 + (k-i-1) * [0,i]第二次的最小价格
     = sum( a[0,i] + min{B[0,i]} * (k-i-1) )  其中B[i]为a[i]+b[i]
     = preSumA[i] + preMinB[i] * (k-i-1)
     所以枚举停止的位置i, 最小化 preSumA[i] + preMinB[i] * (k-i-1) 即可
     */
    public static void main(String[] args) {
        n = Int();
        k = Int();
        a = new int[n];
        B = new int[n];
        for (int i = 0; i < n; i++) a[i] = Int();
        for (int i = 0; i < n; i++) B[i] = a[i] + Int();
        long preSumA = 0;//a的前缀和
        int preMinB = Integer.MAX_VALUE;//B的前缀最小值
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            preSumA += a[i];
            preMinB = Math.min(preMinB, B[i]);

            ans = Math.min(ans, preSumA + (long) (k - i - 1) * preMinB);//如果在i这里停止
        }
        System.out.println(ans);
    }

}
