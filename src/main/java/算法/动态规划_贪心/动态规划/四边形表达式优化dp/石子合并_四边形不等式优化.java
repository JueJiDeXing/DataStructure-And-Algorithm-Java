package 算法.动态规划_贪心.动态规划.四边形表达式优化dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 石子合并_四边形不等式优化 {
    /*
    四边形不等式优化--区间dp模型
    转移方程形如:
        f[i][j] = min( f[i][k] + f[k+1][j] + w[i][j])
    对于每个区间都要枚举k,时间复杂度为O(n^3)
    状态数为n^2无法改变, 使用四边形不等式将k的枚举优化到O(1)

    四边形不等式:
     对于 a < b < c < d
     如果 有 f(a,c) + f(b,d) <= f(a,d) + f(b,c)  // 交叉<=包含
     则称 f 满足 四边形不等式

    如果 代价函数w(i,j) 满足 单调性 和 四边形不等式
    定义 s(i,j)=k 为 f(i,j) 的最优转移
    如果dp函数 f(i,j) 满足 四边形不等式, 那么 s(i,j) 单调, 即
    s(i,j) <= s(i,j+1) <= s(i+1,j+1)
     */
    /*
    有n堆石子,第i堆有a[i]个,每次可以合并相邻两堆,代价为两堆的总数
    求合并石子为1堆的最小代价
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
     <h1>动态规划</h1>
     令 f[i][j] 表示合并 [i,j] 区间的最小代价 <br>
     f[i][j] = min{ f[i][k] + f[k+1][j] + sum[j] - sum[i-1] }<br>
     显然: w(i,j) = sum[j] - sum[i-1] 有 w(a,c) + w(b,d) = w(a,d) + w(b,c) 满足四边形不等式<br>
     <p>
     <h2>证明: f(i,j)满足四边形不等式</h2>
     要证 f[i][j] + f[i+1][j+1] <= f[i][j+1] + f[i+1][j]<br>
     令右式的最优转移为x=s[i][j+1], y=s[i+1][j]<br>
     现在用x和y在左式转移,显然,它的转移会大于等于最优转移<br>
     代入得: 左式 <= {f[i][x] + f[x+1][j] + w(i,j)} + {f[i+1][y] + f[y+1][j+1] + w(i+1,j+1)}<br>
     <= f[i][x] + f[x+1][j] + f[i+1][y] + f[y+1][j+1] + {w(i,j) + w(i+1,j+1)}<br>
     <= f[i][x] + f[x+1][j] + f[i+1][y] + f[y+1][j+1] + {w(i+1,j) + w(i,j+1)}<br>
     <= {f[i][x] + f[x+1][j] + w(i,j+1)} + {f[i+1][y] + f[y+1][j+1] + w(i+1,j)}<br>
     <= f[i][j+1] + f[i+1][j]<br>
     即证.
     <h2>证明: s[i][j-1] <= s[i][j] <= s[i+1][j]</h2>
     对于 s[i][j-1] <= s[i][j], 设 y = s[i][j-1]<br>
     对于 x < y, x+1 < y+1 <= j-1 < j<br>
     根据四边形不等式: f[x+1][j-1] + f[y+1][j] <= f[y+1][j-1] + f[x+1][j]<br>
     将两边加上f[i][x]+w[i][j-1]+f[i][y]+w[i][j]<br>
     得: {f[i][x]+f[x+1][j-1+w[i][j-1]} + {f[i][y]+f[y+1][j]+w[i][j]}
     <= {f[i][y]+f[y+1][j-1]+w[i][j-1]} + {f[i][x]+f[x+1][j]+w[i][j]}<br>
     即: f[i][j-1] + f[i][j] <= f[i][j-1] + f[i][j]<br>
     ``````k=x         k=y          k=y       k=x<br>
     因为f[i][j-1]选k=y时是小的,选k=x时是大的,所以f[i][j]选k=y时是小的<br>
     所以如果y是f[i][j-1]的最优转移, 那么对于f[i][j]的转移, 如果x < y,则x一定不会更优<br>
     所以 s[i][j] >= s[i][j-1]<br>
     同理可证 s[i][j] <= s[i+1][j]<br>
     <p>
     根据结论:s[i][j-1] <= s[i][j] <= s[i+1][j]<br>
     在枚举f[i][j]的转移时,只需要从 [s[i][j-1],s[i+1][j]]中枚举即可<br>
     可以证明这个时间复杂度从O(n^3)降为了O(n^2)<br>
     */
    public static void main(String[] args) {
        int n = Int();
        int[][] f = new int[n + 2][n + 2], s = new int[n + 1][n + 1];
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = Int() + sum[i - 1];
        }
        for (int i = 1; i <= n; i++) s[i][i] = i;//初始化最优转移
        for (int i = n; i > 0; i--) {
            for (int j = i + 1; j <= n; j++) {
                //求f[i][j]的最优转移, 最小化f[i][k] + f[k+1][j]
                int min = Integer.MAX_VALUE, p = 0;
                for (int k = s[i][j - 1]; k <= s[i + 1][j]; k++) {
                    if (min > f[i][k] + f[k + 1][j]) {
                        min = f[i][k] + f[k + 1][j];
                        p = k;
                    }
                }
                f[i][j] = min + sum[j] - sum[i - 1];//f[i][j] = min{ f[i][k] + f[k+1][j] + sum[j] - sum[i-1] }
                s[i][j] = p;
            }
        }
        System.out.println(f[1][n]);
    }

}
