package 算法OJ.蓝桥杯.算法赛.小白入门赛.第8场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class _6小蓝的跳跃 {
    /*
    长度为n+1的数组A[1~n],A[0]为起点,A[n+1]为终点,起点和终点为0, A[1~n]为1或-1,
    从A[0]开始起跳,初始值为0, 每次可以跳1格或2格,跳到A[i]后把A[i]加到值上
    求从0跳到n+1,的值能否为x
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
     假设跳数组A的最大值为R,最小值为L
     对于某一个跳的值为t (L < t < R) 的方案
     因为每次可以选择跳1格或2格,那么每个位置都是能跳到的
     这个方案在i位置,跳到i+2位置,那么它可以选择跳到i+1位置再跳到i+2位置
     也就是说,当前方案它的值为t,但是可以选择多拿一个1或者多拿一个-1
     因为L < t < R, 所以1和-1都没用完
     对于任意的i位置,如果i位置数字没有使用,一定是因为i-1位置选择跳了两格,只需要把路径在i-1位置改成跳1格就能拿到
     所以区间[L,R]的值它都能取到
     只需要求出上下界,判断x能否取到即可
     <p>
     求上下界是简单的动态规划
     MAX[i]:跳到i的最大和, MAX[i] = max{MAX[i-1],MAX[i-2]} + A[i]
     MIN同理
     */
    public static void main(String[] args) {
        int T = Int();
        for (int i = 0; i < T; i++) {
            int n = Int(), x = Int();
            int[] A = new int[n + 1];
            for (int j = 0; j < n; j++) A[j] = Int();
            //简单动态规划求上下界
            int[] MAX = new int[n + 2], MIN = new int[n + 2];//dp[i]:跳到i的最大值/最小值
            MAX[1] = MIN[1] = A[0];//可以用滚动变量压缩
            for (int j = 2; j <= n + 1; j++) {
                MAX[j] = Math.max(MAX[j - 1], MAX[j - 2]) + A[j - 1];
                MIN[j] = Math.min(MIN[j - 1], MIN[j - 2]) + A[j - 1];
            }
            //判断x是否在上下界里面
            if (MIN[n + 1] <= x && x <= MAX[n + 1]) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
