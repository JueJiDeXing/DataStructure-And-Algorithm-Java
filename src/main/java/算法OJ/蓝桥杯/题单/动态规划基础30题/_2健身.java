package 算法OJ.蓝桥杯.题单.动态规划基础30题;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
/**
 已AC
 */
public class _2健身 {
    /*
    n天
    m个健身计划,第i个计划需要2^k[i]天,如果完成获得s[i]的收益
     同一个计划可以多次完成,同一时间只能进行一个计划
     给出q天不能健身的日期
     求最大增益
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }


    public static void main(String[] args) {

        int n = Int(), m = Int(), q = Int();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < q; i++) list.add(Int()); // 1~n天中不能训练的日期

        int[][] ks = new int[m][2];
        for (int i = 0; i < m; i++) {
            ks[i][0] = 1 << Int();
            ks[i][1] = Int(); // 不同训练需要的天数和收益
        }
        Arrays.sort(ks, (a, b) -> a[0] - b[0]); // 将ks升序排列
        // dp[i]: 前i天最多收益
        long[] dp = new long[n + 1];
        int stop = -1;//上一个不能练的日期
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];//第i天不练的情况
            // 在第i天有不能训练的日期
            if (stop + 1 < q && i >= list.get(stop + 1)) {//stop~i中有不能练的(本质是在可行区间中dp)
                stop++;
            }
            if (list.contains(i)) continue;//第i天练不了
            //练第i天
            for (int j = 0; j < m; j++) {//枚举训练计划
                int[] plan = ks[j];
                int needDay = plan[0], get = plan[1];
                if (i < needDay) break;// 天数不够
                if (stop >= 0 && i - list.get(stop) < needDay) break;// 天数不够
                dp[i] = Math.max(dp[i], dp[i - needDay] + get);
            }
        }
        System.out.print(dp[n]);


    }
}
