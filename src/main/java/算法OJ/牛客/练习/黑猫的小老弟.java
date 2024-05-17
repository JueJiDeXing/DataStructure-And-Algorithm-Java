package 算法OJ.牛客.练习;

import java.io.*;

/**
 已AC
 */
public class 黑猫的小老弟 {
    /*
    如果 a/b 和 c/d 相邻, 那么中间的位置会生成一个(a+c)/(b+d)并约分
    一代有 0/1 和 1/1 两个数
    再经过迭代后, 分母大于n的全部去掉, 求剩余的数个数
    0/1  1/1
    0/1 1/2 1/1
    0/1 1/3 1/2 2/3 1/1
    ...
     */

    /**
     求 [1,n]上与n互质的数个数
     */
    public static void main(String[] args) throws Exception {
        int t = I();
        while (t-- > 0) {
            int n = I();
            pw.println(sum[n + 1] + 1);
        }
        pw.flush();
        pw.close();
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);
    static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int n = 10000;
    static int[] phi = new int[n + 1];
    static long[] sum = new long[n + 2];// 前缀和

    static {
        for (int i = 1; i <= n; i++) phi[i] = i;
        for (int i = 2; i <= n; i++) {
            if (phi[i] == i) {
                //i是质数,i的倍数含有质因子i,将其筛掉 E(x) = x * (1 - 1/pi)
                for (int j = i; j < n; j += i) { // j=i, i也要乘一遍
                    phi[j] = phi[j] / i * (i - 1);
                }
            }
        }
        for (int i = 1; i <= n + 1; i++) {
            sum[i] = sum[i - 1] + phi[i - 1];
        }
    }

}
