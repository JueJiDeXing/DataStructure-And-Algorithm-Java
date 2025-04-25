package 算法.数学.概率.方差;

import java.io.*;
import java.util.Arrays;
/*
D(x) = E(x^2) - E^2(x)
 */
public class 蓝桥_15省CA_成绩统计 {
    /*
    给定n名同学的成绩, 第i名成绩为a[i] (无序)

    最少要检查前多少个人的成绩, 才能从中选出k个人, 使这k个人成绩的方差小于T

    输入: n,k,T 和 成绩数组a
    输出: x
    x是满足 " a[0,x) 中选择k个数, 存在一种选择使这k个数方差小于T " 的最小值
     */

    static long T;
    static int[] a;
    static int n, k;

    /**
     <h2>二分</h2>
     二分答案x
     对于x, 检查a[0,x)能否选出k个数,方差小于T
     <h2>前缀和</h2>
     将a[0,x)排序为tmp
     一定是从tmp中选择连续的k个数, 这样才会更接近平均值
     <p>
     由 D(x) = E(x^2) - E^2(x) = sum{x^2 | i∈[j,j+k) } / k - sum^2{x | i∈[j,j+k) } / k^2
     预处理前缀和preSum、前缀平方和prePowSum
     枚举j, 计算[j,j+k)上的D(x)
     判断是否存在D(x) < T
     */
    public static void main(String[] args) throws IOException {
        n = I();
        k = I();
        T = L();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = I();

        int ans = solve();
        pw.println(ans);
        pw.flush();
    }

    private static int solve() {
        if (check(k - 1)) return k;
        if (!check(n - 1)) return -1;

        int left = k - 1, right = n - 1;
        while (left + 1 != right) {
            int mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right + 1;
    }


    static boolean check(int x) {// a[0,x] 
        int n = x + 1;
        int[] tmp = new int[n];
        System.arraycopy(a, 0, tmp, 0, n);
        Arrays.sort(tmp);
        long[] preSum = new long[n + 1];
        long[] prePowSum = new long[n + 1];
        for (int i = 1; i <= n; i++) {// preSum[i] = sum{j=[0,i)}
            preSum[i] = preSum[i - 1] + tmp[i - 1];
            prePowSum[i] = prePowSum[i - 1] + (long) tmp[i - 1] * tmp[i - 1];
        }
        for (int i = 0; i <= n - k; i++) {  // tmp[i,i+k-1]
            long ke_x2 = prePowSum[i + k] - prePowSum[i];
            long ke_x = preSum[i + k] - preSum[i];
            long kkD = k * ke_x2 - ke_x * ke_x;
            if (kkD < k * k * T) return true;
        }
        return false;
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static long L() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }


}
