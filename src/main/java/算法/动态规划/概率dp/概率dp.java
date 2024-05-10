package 算法.动态规划.概率dp;

import java.util.Arrays;
import java.util.Scanner;

public class 概率dp {
    /*
    每个状态都有它的后继状态

    假设当前状态为A, 它的所有后继状态B1,B2...Bk
    A到B[i]的概率为P(A,Bi),权值为W(A,Bi) // sum(P[i])=1
    则可以根据状态的转移,画出一个图

    设E(A)为从状态A到终止状态T的期望花费:初值E(T)=0
    可得转移公式:
    E(A) = sum( P(A,B)* [E(B)+W(A,B)] | B是A的后继态 )
    当转移关系不成环时, 是线性的, 可以使用拓扑序递推求解
    当转移关系成环时, 列出所有状态转移方程, 使用高斯消元解决
     */

    /*
    例1 - 路径长度
     给定一个n个点的有向带权无环图
     在每个顶点,走任意一条出边的概率相等
     求从1到n的路径总长度期望

     令dp[i]为从i走到n的总长度期望
     转移(递推):dp[i]=sum{  dp[to] + cost(i,to) } / size(G_i)
     初始值: dp[n]=0

     多解-贡献法正向dp:
     期望总长度
     = sum{  边的期望贡献之和 }
     = sum{ 经过边的概率 * 边权 }
     所以只需要计算经过每条边的概率
     设 点u经过边e到达点v
     则边e的经过概率为 dp[u]/size(G_u), 贡献为 W[e]*dp[u]/size(G_u)
     转移(刷表): dp[v] <- dp[u]/size(G_u)
     初始值dp[1]=1

     */

    /*
    例2 - 乘坐电梯
    n个人排一列,每秒队伍最前面的人有p的概率走上电梯(出队),(1-p)的概率不动
    求T秒后电梯上人数的期望

    令dp[i][j]为i秒时有j个人上电梯的概率
    ans = sum{ j*dp[T][j] }
    转移(刷表):
    (1) dp[i][j] -> p * dp[i+1][j+1]
    (2) dp[i][j] -> (1-p) * dp[i+1][j]
    初始值:dp[0][0]=1

    多解-贡献法:
    延续之前方法,将转移抽象为边
    (i,j)->(i+1,j+1)边权为1,概率为p, 其余边权为0
    再去dp求解
     */

    /*
    例3 - ox?
    给定一个ox?的序列, ?位置有50%的概率为o
    连续的长度a的o序列会提供a^2的价值
    求序列的期望价值

    贡献法:
    计算每一位的贡献
    如果前面有k个o,现在又出现一个o
    那么这个o的贡献为(k+1)^2 - k^2 = 2k+1

    设L[i]表示以s[i]位置结尾的最长连续o序列长度期望

    (1) s[i]=o, L[i]=L[i-1]+1, 贡献为 2L[i-1]+1
    (2) s[i]=x, L[i]=0, 贡献为0
    (3) s[i]=?, L[i]=(L[i-1]+1)/2, 贡献为(2L[i-1]+1)/2
     */

    /*
    例4 - 游走(有环,高斯消元)
    n个点的无向图,点编号1~n, 初始在1号顶点, 在n号节点结束
    边权为边的编号值(未编号,需要决策编号值)乘以边的经过次数
    在某个顶点选择走的每条边概率相同
    现在需要对边进行编号(1~m)
    求分数期望最小

    令 g[e]为 边e 的 经过期望次数, f[u]为点u的经过期望次数, d[u]为u的度数
    假如u与v由边e相连, 则 g[e] = f[u]/d[u] + f[v]/d[v]
    考虑每个点的期望次数:
    转移:f[u] = sum{ f[v]/d[v] }
    特殊点:
        f[1]=1+sum{ f[v]/d[v] }
        f[n]=0
    高斯消元:
    将f的n-1个式子看作n-1个n-1元的方程组
    1 * f[1] + a(1,2) * f[2] + ... + a(1,n-1) * f[n-1] = 1
    a(2,1) * f[1] + 1 * f[2] + ... + a(2,n-1) * f[n-1] = 0
    ...
    a(k,1) * f[1] + ... + 1 * f[k] + ... + a(k,n-1) * f[n-1] = 0
    解释:
     第一个方程 f[1]=1+sum{ f[v]/d[v] } -> f[1] - sum{f[v]/d[v]} = 1
     第u个方程f[u] = sum{f[v]/d[v]} -> f[u] - sum{f[v]/d[v]} = 0
    增广矩阵为:
    |1 ? ? ... ? | 1 |
    |? 1 ? ... ? | 0 |
    |? ? 1 ... ? | 0 |
    |......... . | . |
    |? ? ? ... 1 | 0 |
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();//点数与边数
        int[] s = new int[m + 1], t = new int[m + 1];
        int[][] graph = new int[n + 1][n + 1];
        int[] d = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            s[i] = u;
            t[i] = v;
            graph[u][v] = 1;
            graph[v][u] = 1;
            d[u]++;
            d[v]++;
        }
        double[][] a = new double[n + 1][n + 1];
        for (int u = 1; u <= n; u++) {
            for (int v = 0; v <= n; v++) {
                if (u == v || graph[u][v] == 0) continue;
                a[u][v] = -1.0 / d[v];//走到u的概率,方程组系数
            }
            a[u][u] = 1;
        }
        a[1][n] = 1;
        double[] g = new double[m + 1];
        for (int i = 1; i <= m; i++) {
            g[i] = a[s[i]][n] / d[s[i]] + a[t[i]][n] / d[t[i]];
        }
        //贪心编号
        Arrays.sort(g);
        double ans = 0;
        for (int i = 1; i <= m; i++) ans += g[i] * (m - i + 1);//最小的次数编号放最大
        System.out.println(ans);
    }
}
