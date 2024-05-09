package 算法OJ.Codeforces.构造题单;

import java.io.*;
import java.util.ArrayList;
import java.util.*;

/**
 已AC
 */
public class _10数字游戏 {
    /*
    给定初始值n,每次操作可以选择一个大于1的正整数x,将n变为n/x
    当无法选择x时,游戏结束
    求最大操作次数

    t次游戏,每次给出a和b,表示n=a!/b!,输出每次的最大操作次数
     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    /**
     对于给定的n,操作次数最大,就是选择n的素因子,只要求n的素因子个数即可
     题目给的n形式是a!/b!
     a!/b!=mul[1,a]/mul[1,b]
     f(a!/b!)=f(mul[1,a]) - f(mul[1,b])
     所以 求的是[1,a]的素因子个数之和 - [1,b]的素因子个数这和
     对于[1,x]的素因子个数可以使用素因子筛预处理,然后处理成前缀和形式,询问时就可以O(1)求解
     */
    public static void main(String[] args) {
        int t = nextInt();
        while (t-- > 0) {
            int a = nextInt(), b = nextInt();
            pw.println(preFix[a] - preFix[b]);
        }
        pw.flush();
        pw.close();
    }

    static int N = 5_000_000;
    static long[] preFix = new long[N + 1];

    static {
        long[] s = new long[N + 1];
        //素因子筛
        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (s[i] == 0) {
                s[i] = 1;
                prime.add(i);
            }
            for (int j : prime) {
                int m = j * i;
                if (m > N) break;
                s[m] = s[i] + 1;
            }
        }
        for (int i = 1; i <= N; i++) {
            preFix[i] = preFix[i - 1] + s[i];
        }
    }
}
