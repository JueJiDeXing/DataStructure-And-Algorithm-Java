package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.util.*;

/**
 测试通过:11/20 9个TL
 */
public class J括号序列树 {
    /*
    一颗二叉树,根节点为空字符串,向左走会添加一个"(",向右走会添加一个")"
    括号序列树的叶子节点都可与n对括号组成的合法括号序列匹配
    给定n(n<=10^6),问此时这颗树的最大匹配所含边数,答案对998244353取余

     最大匹配:
     树可以被看作是一个无向图G.
     对于无向图G的最大匹配是边集的一个子集, 满足:
     对于G中每一个点来说, 都只有最多一条与之相连的边在这个子集中.
     最大匹配就是这个子集大小可以到达的最大值.
     简单来说就是:尽可能选择多的边,并且在选择一些边后,各组端点不相连
     例如:
     1-2-3
     └4-5
     匹配方案数为3,分别是{(1,2),(4,5)},{(2,3),(1,4)},{(2,3),(4,5)}
     匹配方案中的边数最多为2,所以最大匹配为2

     例如:n=2的括号序列树的最大匹配为3,带√的边为最大匹配项
                  null
                 /√
               (
            /     \
          ((       ()
           \       /
           (()    ()(
             \√     \√
            (())    ()()
     */

    /**
     <a href="https://www.cnblogs.com/SpaceJellyfish/p/Lanqiao_KuoHaoXuLieShu.html">题解</a><br>
     f(n)=Sum_{i 0->n-1}{ C(2i+1,i) } - Sum_{i ceil(n/2) -> n-1}{ C(2i+1,2i-n) }
     <br>
     假设节点u的度为一,与它相邻的节点为v,v的邻居有{a1,a2,...}
     如果v不在最大匹配中:那么u也无法匹配,那么最后u是可以与v匹配的
     如果v在最大匹配中:case1:v与u匹配; case2:v与其他节点匹配,假设为a1
     那么比较case1和case2,已匹配边数都为1,但是case2的待匹配节点数更少,所以case1更优
     总上:度为1的节点要尽可能参与匹配
     <p>
     那么可以从叶子节点开始,让叶子节点与父节点匹配,匹配后删除叶子节点和父节点
     这样自底而上进行匹配,那么最大匹配的边数就等于这个过程中叶子节点的个数
     由于叶子节点与n对括号的括号序列对应,那么它的位置就在2n+1层,所以最终答案就等于奇数层的节点个数
     */
    public static void main(String[] args) {
        main_enter2();
    }

    private static final int MOD = 998244353;

    /**
     11AC 9TL
     */
    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] arr = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            arr[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                arr[i][j] = (arr[i][j - 1] + arr[i - 1][j]) % MOD;
            }
        }
        long sum = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                arr[i][j] = (arr[i][j] - arr[i - 1][j] + MOD) % MOD;
                if (i != n) sum = (sum + arr[i][j]) % MOD;
            }
        }
        for (int j = 1; j <= n; j++) {
            sum = (sum + arr[n][j - 1]) % MOD;
            arr[n][j] = (arr[n][j] - arr[n][j - 1] + MOD) % MOD;
        }
        System.out.println(sum);
    }

    /**
     20AC
     */
    private static void main_enter2() {
        int n = new Scanner(System.in).nextInt();
        init(n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + C(2 * i + 1, i)) % MOD;
        }
        for (int i = (n + 1) / 2; i < n; i++) {
            ans = (ans - C(2 * i + 1, 2 * i - n)) % MOD;
        }
        System.out.println((ans + MOD) % MOD);
    }

    /**
     <h1>模组合数</h1>
     C(n,m)%MOD
     组合数C(n,m) = n! / ((n-m)! m!)
     */
    private static int C(int n, int m) {
        return (int) ((long) factorial[n] * inverse[m] % MOD * inverse[n - m] % MOD);
    }

    private static final int N = 2000001;//2*n <= 2*10^6
    private static final int[] factorial = new int[N], inverse = new int[N];//fac[i]=i!, facinv[i]为i!在模p下的逆元


    /**
     取模性质对除法不适用,所以需要逆元,用乘法代替除法
     预处理出阶乘数组和乘法逆元数组
     */
    private static void init(int n) {
        factorial[0] = 1;
        for (int i = 1; i <= 2 * n; i++) {//求阶乘
            factorial[i] = (int) ((long) factorial[i - 1] * i % MOD);
        }
        inverse[2 * n] = fastPow(factorial[2 * n]);
        for (int i = 2 * n; i > 0; i--) {//倒序递推求逆元
            inverse[i - 1] = (int) ((long) inverse[i] * i % MOD);
        }
    }

    /**
     快速幂,base^(MOD-2)
     */
    private static int fastPow(int base) {
        int ans = 1;
        int p = MOD - 2;
        while (p > 0) {
            if ((p & 1) == 1) ans = (int) ((long) ans * base % MOD);
            base = (int) ((long) base * base % MOD);
            p >>= 1;
        }
        return ans;
    }

}
