package 算法OJ.牛客.小白月赛.小白月赛94;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class C数字合并最大化极差 {
    /*
    操作:
    合并a[i]和a[i+1]
    可以操作任意次
    求出操作后数组的最大极差
     */
    public static void main(String[] args) throws Exception {
        int n = I();
        long[] a = new long[n + 1];
        long[] preSum = new long[n + 1];//前缀和,[1,i]
        for (int i = 1; i <= n; i++) {
            a[i] = I();
            preSum[i] = preSum[i - 1] + a[i];
        }
        long suffSum = preSum[n];//后缀和,a[i+1,n]
        long ans = 0;
        for (int i = 1; i <= n; i++) {//枚举min
            suffSum -= a[i];
            ans = Math.max(ans, preSum[i - 1] - a[i]);// 左边合并
            ans = Math.max(ans, suffSum - a[i]); // 右边合并
        }
        System.out.println(ans);
    }


    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

}

