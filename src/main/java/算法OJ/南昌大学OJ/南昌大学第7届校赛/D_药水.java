package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class D_药水 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(solve());
    }

    static int n;
    static long[] v;

    private static long solve() throws IOException {
        n = I();
        v = new long[n];
        long max = 0;
        for (int i = 0; i < n; i++) {
            v[i] = I();
            max = Math.max(max, v[i]);
        }
        // 二分每瓶的剩余量的最小值
        long left = 0, right = max + 1;//left:√
        while (left + 1 != right) {
            long mid = (left + right) >>> 1;
            if (check(mid)) {
                left = mid;//可以满足
            } else {
                right = mid;
            }
        }
        return left;
    }

    //最少留k点
    static boolean check(long k) {
        long[] clone = v.clone();
        // 从后往前倒
        for (int i = n - 1; i >= 2; i--) {
            long maxSub = Math.min(v[i], clone[i] - k);//最多浇v[i], 并且需要根据当前值留k点
            if (maxSub > 0) {
                long maxD = maxSub / 3;
                clone[i - 2] += 2 * maxD;
                clone[i - 1] += maxD;
            }
            if (clone[i] < k) return false;//当前瓶在后面倒过后少于k点
        }
        if (clone[0] < k || clone[1] < k) return false;//最后,前面两瓶不能少于k点
        return true;
    }
}
/*
4
1 2 10 100
 */
