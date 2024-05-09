package 算法OJ.Codeforces;

import java.io.*;

/**
 已AC
 */
public class A_Rudolf_and_the_Ticket {
    /*
    鲁道夫要去贝马德，他决定坐地铁去接他。
    这张票可以在一台只接受两枚硬币的机器上购买，硬币的总和不超过k。

    鲁道夫有两个口袋，里面装着硬币。
    在左口袋中，有n枚面额为b1、b2..的硬币，bn。
    在右侧口袋中，有m枚硬币，面额分别为c1、c2、。。，n.
    他想从左口袋里选一枚硬币，从右口袋里选另一枚硬币（总共两枚）。

    帮助Rudolf确定有多少种方法可以选择索引 f 和 s，使得 b[f]+c[s] ≤k。

    输入

    第一行包含一个整数t（1<t<100）-测试用例的数量。接下来是对每个测试用例的描述。

    每个测试用例的第一行包含三个自然数n、m和k（1≤n，m≤100，1＜k＜2000）——分别是左口袋和右口袋中的硬币数量，以及柜台处支付机票的两个硬币的最大总和。

    每个测试用例的第二行包含n个整数b；（1≤b≤1000）-左侧口袋中硬币的面额。

    每个测试用例的第三行包含m个整数c；（1≤c；≤1000）-右侧口袋中硬币的面额。

    输出

    对于每个测试用例，输出一个整数——鲁道夫可以选择两个硬币的方式，从每个口袋中取出一个，这样硬币的总和就不会超过k。
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
     n*m<=1e6,数据量不大,直接O(nm)枚举

     */
    public static void main(String[] args) {
        int T = Int();
        for (int t = 0; t < T; t++) {
            int n = Int(), m = Int(), k = Int();
            int[] b = new int[n], c = new int[m];
            for (int i = 0; i < n; i++) b[i] = Int();
            for (int i = 0; i < m; i++) c[i] = Int();
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (b[i] + c[j] <= k) {
                        ans++;
                    }
                }
            }
            System.out.println(ans);
        }
    }
}
