package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
/**
 已AC
 */
public class I付账问题 {
    /*
    n个人,第i个人有a[i]元
    总消费为S元
    找到一种支付方案,使得每个人付的钱的标准差最小
    每个人付的钱可以是任意非负实数
    求这个最小标准差,结果保留4位小数
    标准差 = sqrt(1/n * sum(i=1..n)(x_i - x)^2)
     */
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
     贪心
     avg = S/n
     (1) 钱不够avg的全部都要拿出
     (2) 钱够avg的, 首先要拿出avg出来, 然后需要多拿一部分填补钱不够的人
         每个人总共要拿的 = (S - (1) ) / (2) = (S - sum(A[0,i]) ) / (n - i + 1)
         如果说这个均值拿不出来,他应当被算作(1)
     令假设现在前面i-1个人拿出了全部的钱, pay为剩余未支付的均值 S_remain / n - i + 1
     那么如果 A[i] < pay : 第i个人是(1),A[i]全部拿出, S -= A[i]
     如果 A[i] >= pay : 第i个人是(2),因为排序,所以后面的人都是(2),每个人需要支付的费用是 pay
     ans = sqrt( sum{ (pay[i] - avg)^2 } / n )
     */
    public static void main(String[] args) {
        int n = Int();
        double S = Long();
        double avg = S / n;
        long[] A = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            A[i] = Int();
        }
        Arrays.sort(A);
        double ans = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = n - i + 1;
            double pay = S / cnt;//剩余的人还需要支付的费用
            if (A[i] < pay) {//这个人不够,需要全部拿出
                ans += (A[i] - avg) * (A[i] - avg);
                S -= A[i];
            } else {//这个人是够的,因为排序了,那后面的都是够的
                ans += (pay - avg) * (pay - avg) * cnt;
                break;
            }
        }
        ans = Math.sqrt(ans / n);
        System.out.printf("%.4f", ans);
    }
}

