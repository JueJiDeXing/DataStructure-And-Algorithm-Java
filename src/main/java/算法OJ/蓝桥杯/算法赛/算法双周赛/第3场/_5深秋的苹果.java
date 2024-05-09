package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.io.*;

/**
 已AC
 */
public class _5深秋的苹果 {
    /*
    n个苹果排一排
    第i个苹果的值为A[i]
    现在将苹果分为m段(完全划分)
    每一段的价值为段内苹果的两两相乘的和 sum{ A[i]*A[j] | L<=i<j<=R }
     求划分的最大价值的一段的最小值
     */


    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int n, m;
    static long[] A;

    /**
     二分
     求最大价值的一段的最小值
     那么二分枚举最小值, 每一段都需要小于等于最小值, 检查能否划分即可
     */
    public static void main(String[] args) {
        n = nextInt();
        m = nextInt();
        long left = -1, right = 0;
        A = new long[n + 1];
        long preSum = 0;//只划分一段,段内的当前前缀和
        for (int i = 1; i <= n; i++) {
            A[i] = nextInt();
            preSum += A[i];
            right += A[i] * preSum;//求一个二分的上界
        }
        //二分枚举最大价值的最小值
        while (left + 1 != right) {
            long mid = (left + right) >>> 1;
            if (check(mid)) {//检查能否在mid的上界限制下划分m段
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.println(right);// left:×  right:√
    }

    /**
     n个苹果是否能划分m段,每一段不超过max
     */
    static boolean check(long max) {
        long count = 0;//当前段的划分值
        int div = 1;//当前划分段
        long preSum = 0;//当前段内的前缀和
        for (int i = 1; i <= n; i++) {
            long c = A[i] * preSum;
            if (count + c > max) {//这段满了
                div++;//新的一段
                if (div > m) return false;//划不出来
                count = 0;//价值重置为0
                preSum = A[i];//A[i]已经在新段里了
            } else {
                count += c;
                preSum += A[i];
            }
        }
        return true;//能划分
    }

}
