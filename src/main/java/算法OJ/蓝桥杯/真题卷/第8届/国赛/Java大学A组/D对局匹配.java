package 算法OJ.蓝桥杯.真题卷.第8届.国赛.Java大学A组;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 已AC
 */
public class D对局匹配 {
    /*
    长度为N的数组A
    从里面选择x个数字组成一个集合
    集合需要满足,任意两个数的差不等于k
    求集合大小的最大值
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
        int n = Int(), k = Int();
        int[] A = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            A[i] = Int();
            max = Math.max(max, A[i]);
        }
        int[] cnt = new int[max + 1];
        for (int i : A) cnt[i]++;

        if (k == 0) {//k为0,等价与选择不相同的数字
            int ans = 0;
            for (int i : cnt) {
                if (i != 0) ans++;
            }
            System.out.println(ans);
            return;
        }
        //k不为0
        int ans = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> group = new ArrayList<>();
            for (int j = i; j <= max; j += k) {//i,i+k,i+2k...为同一组,其余数对于该组无影响
                group.add(cnt[j]);
            }
            // i与i+k,i+2k...为同一组,不能选择相邻数字,因为他们相差为k
            ans += MaxChoose(group);//(打家劫舍)在group中做最大选择,选择的数不能相邻
        }
        System.out.println(ans);
    }

    static int MaxChoose(List<Integer> group) {
        int n = group.size();
        if (n == 0) return 0;
        if (n == 1) return group.get(0);
        int[] dp = new int[n];//dp[i]:前i个数的最大选择
        dp[0] = group.get(0);
        dp[1] = Math.max(group.get(0), group.get(1));
        for (int j = 2; j < n; j++) {
            dp[j] = Math.max(dp[j - 1], dp[j - 2] + group.get(j));//max(选j-1不选j,选j不选j-1)
        }
        return dp[n - 1];
    }
}
