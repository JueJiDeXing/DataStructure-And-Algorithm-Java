package 算法OJ.洛谷.普及up_提高;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC
 */
public class P2822_NOIP2016_提高组_组合数问题 {
    /*
    给定n,m,k
    求满足C(i,j)%k==0的数对(i,j)的个数
    其中 0 <= i <= n , 0 <= j <= min{i,m}
    t<1e4, n,m<2e3
     */
    /**
     c[i][j]:组合数, 模k后的结果
     */
    static long[][] c = new long[2010][2010];
    /**
     c[i][j]==0则填入1形成的矩阵的前缀和 , ans[n][m]为c[i][j]%k==0的方案数(i < n && j < m)
     */
    static long[][] ans = new long[2010][2010];

    static void build() {
        c[0][0] = 1;
        c[1][0] = c[1][1] = 1;
        for (int i = 2; i <= 2000; i++) {
            c[i][0] = 1;// 第一项为1
            for (int j = 1; j <= i; j++) {//中间项
                c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % k;
                ans[i][j] = ans[i - 1][j] + ans[i][j - 1] - ans[i - 1][j - 1];//前缀和
                if (c[i][j] == 0) ans[i][j]++;//0->找到一个方案->计数1
            }
            //最后一项为1
            ans[i][i + 1] = ans[i][i];
        }
    }

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int k;

    public static void main(String[] args) {
        int t = nextInt();
        k = nextInt();
        build();
        while (t-- > 0) {
            int n = nextInt(), m = nextInt();
            if (m > n) m = n;//如果m>n,ans只会达到n，只需输出ans[n,n]就可以了。
            System.out.println(ans[n][m]);
        }
    }

}
