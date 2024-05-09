package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.io.*;

/**
 已AC
 */
public class F包子凑数 {
    /*
    N种笼子,第i种笼子每笼有A[i]个包子,笼子是无限的
    每个笼子的包子要么不拿,要么全部拿走
    求多少种数目的包子不能被凑出来,如果有无限种数目的包子不能被凑出来,输出INF

    例如:
    只有3和7两种笼子
    可以凑出10=3+7, 13=3+3+7,  17=7+7+3
    不能凑出1,2,4,5,8,11,其余数字都能凑出来
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
     1. 当gcd(A)!=1时,一定有无限个正整数不能凑出来, 比如2,4,6只能凑偶数,无限个奇数凑不出来<br>
     2. 根据数论:a和b如果互质,则他们不能凑出的最大正整数为a*b-a-b<br>
     3. 数据量很小,N<=100,A[i]<=100, 可以暴力解<br>
     首先判断gcd(A)是否为1,不为1直接结束<br>
     定义dp[i]表示i能否凑出来,如果dp[i]=true,则dp[i+A[j]]=true<br>
     所以枚举i+A[j]将dp[i+A[j]]置为true<br>
     最后数多少个false即可<br>
     */
public static void main(String[] args) {
    int n = Int();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
        A[i] = Int();
    }
    int gcd = gcd(A[0], A[1]);
    for (int i = 2; i < n; i++) {
        gcd = gcd(gcd, A[i]);
    }
    if (gcd != 1) {
        System.out.println("INF");
        return;
    }
    boolean[] dp = new boolean[10000];//dp[i]表示i能否凑出来
    //如果dp[i]=true,则dp[i+A[j]]=true
    dp[0] = true;//0不用凑
    for (int i = 0; i < n; i++) {
        for (int j = 0; j + A[i] < 10000; j++) {
            if (dp[j]) {
                dp[j + A[i]] = true;
            }
        }
    }
    int ans = 0;
    for (int i = 0; i < 10000; i++) {
        if (!dp[i]) ans++;
    }
    System.out.println(ans);
}

private static int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
}
}
