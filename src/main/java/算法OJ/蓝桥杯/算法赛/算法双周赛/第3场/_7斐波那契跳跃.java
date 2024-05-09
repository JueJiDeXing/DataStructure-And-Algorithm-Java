package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.io.*;
import java.util.Arrays;
/**
 已AC
 */
public class _7斐波那契跳跃 {
    /*
    长度为n的一个排列
    在索引i位置能跳到索引j位置需要满足:
    (1) 方向向右, i<j
    (2) 跳到大数上, ai<aj
    (3) 步数是斐波那契数, (j-i) in Fib{1,2,3,5,8,11,...}
    (4) 步数递增, (j-i)>(j`-i`)
    求在每个位置上的先手必胜or必输情况
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        n = nextInt();
        Init();
        A = new int[n];
        for (int i = 0; i < n; i++) A[i] = nextInt();
        memo = new int[n][lf + 1];
        for (int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
        for (int i = 0; i < n; i++) {//对每个位置进行搜索,判断是否必胜或必败
            System.out.println(dfs(i, 0) ? "Little Lan" : "Little Qiao");
        }
    }

    static int n;
    static int[] A, fib = new int[100];
    static int lf;
    static int[][] memo;

    static void Init() {//斐波那契数列
        fib[1] = 1;
        fib[2] = 2;
        for (lf = 3; ; lf++) {
            fib[lf] = fib[lf - 1] + fib[lf - 2];
            if (fib[lf] > n) { //1<ai<n<1e5
                break;
            }
        }
    }

    /**
     返回(idx,k)条件下,必胜或者是必败
     如果必胜返回true,否则返回false

     @param idx 当前在数组的下标位置
     @param k   上一次使用斐波那契数列的索引位置
     */
    static boolean dfs(int idx, int k) {
        if (memo[idx][k] != -1) return memo[idx][k] == 1;//记忆化剪枝

        for (int i = k + 1; i < lf; i++) {
            int step = fib[i]; //跳转步长
            if (idx - step >= 0 && A[idx - step] > A[idx]) {//往前跳
                if (!dfs(idx - step, i)) { // 下一个位置先手是必败的,则当前有必胜策略,返回true
                    memo[idx][k] = 1;
                    return true;
                }
            }
            if (idx + step < n && A[idx + step] > A[idx]) {//往后跳
                if (!dfs(idx + step, i)) {
                    memo[idx][k] = 1;
                    return true;
                }
            }
        }
        memo[idx][k] = 0;
        return false;
    }

}
