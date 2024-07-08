package 算法OJ.ICPC.江西2024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class K魔法树 {
    /*
    给出一个2*m的矩阵
    然后 从(1,1)开始dfs, 1个节点只能访问1次, 求不同搜索方案的个数

    具体搜索过程:
    首先 (1,1) 入栈, 不断抛出栈顶元素, 标记为已访问, 将周围格的未访问格入栈
     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 998244353;

    static long pow2(long n) {
        long x = 2;
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        int m = I();
        System.out.println(pow2(m - 1));
    }

}
