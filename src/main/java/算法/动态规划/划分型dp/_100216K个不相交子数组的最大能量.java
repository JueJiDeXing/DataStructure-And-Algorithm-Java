package 算法.动态规划.划分型dp;

public class _100216K个不相交子数组的最大能量 {
    /*
    x个不相交子数组的能量定义为:
     sum[1]*x - sum[2]*(x-1) + sum[3]*(x-2) - ... + (-1)^(i+1) * sum[x]
     其中sum[i]为第i个子数组的和
     给定一个数组,将其分为k个不相交子数组,求最大能量
     */

    /**
     从数组最后开始考虑:
     如果最后一个元素在最后一段子数组内,子数组长度为L,那么问题转为前n-L项需要分出k-1段
     如果最后一个元素不在最后一段子数组内,那么问题转为前n-1项需要分出k段
     所以定义f[i][j]表示前j项需要分出i段子数组
     <p>
     那么有转移方程:
     如果最后一项不在子数组, f[i][j] = f[i][j-1]
     如果最后一项在子数组,子数组左端点下标为L,则 f[i][j] = max_{L=i-1}^{j-1} { f[i-1][j-L] + sum[L~j] * w_i }
     总的来说:
     f[i][j]=max{f[i][j-1], max_{L=i-1}^{j-1} { f[i-1][j-L] + sum[L~j] * w_i } }
     其中sum[L~j]可以使用前缀和表示,preFix[j]-preFix[L]
     w_i = (-1)^(i+1) * (k-i+1)仅与i有关
     <p>
     则答案=f[k][n], 初始值 f[0][j] = 0 , f[i][ < i ] = -inf
     <p>
     优化:
     max_{L=i-1}^{j-1} { f[i-1][j-L] + (preFix[j] - preFix[L]) * w_i }
     = max_{L=i-1}^{j-1} { f[i-1][j-L] + preFix[j]*w_i - preFix[L]*w_i }
     = preFix[j]*w_i + max_{L=i-1}^{j-1} { f[i-1][j-L] - preFix[L]*w_i }
     后面的项 max_{L=i-1}^{j-1} { f[i-1][j-L] - preFix[L]*w_i } 在枚举的一个i下,仅与L有关
     所以可以预处理出max_{L=i-1}^{j-1} { f[i-1][j-L] - preFix[L]*w_i }的值,这样就减去了一层循环
     <p>
     f[i][i]时 枚举 L={i-1}
     f[i][i+1]时 枚举 L={i-1,i}
     f[i][i+2]时 枚举 L={i-1,i,i+1}
     ...
     f[i][j-1]时 枚举 L={i-1,i,i+1,...,j-2} 假如这里的max已经求出来了
     那么f[i][j]时 枚举 L={i-1,i,i+1,...,j-1} 只需要与L=j-1取max即可
     所以维护的项是 f[i-1][j-1] - preFix[j-1]*w_i, 假设该变量为mx
     <p>
     那么转移方程f[i][j]=max{f[i][j-1], max_{L=i-1}^{j-1} { f[i-1][j-L] + (preFix[j]-preFix[L]) * w_i } }
     可以写为f[i][j]=max{f[i][j-1], preFix[j]*w_i + mx}  mx=max{ mx ,f[i-1][j-1] - preFix[j-1]*w_i }
     */
    public int maximumStrength(int[] nums, int k) {
        int n = nums.length;
        //前缀和
        int[] preFix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preFix[i + 1] = preFix[i] + nums[i];
        }
        int[][] f = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            f[i][i - 1] = Integer.MIN_VALUE;
            int mx = Integer.MIN_VALUE;
            int w = (i % 2 == 0 ? 1 : -1) * (k - i + 1);
            for (int j = 1; j <= n - k + i; j++) {//后面需要留k-i个数
                mx = Math.max(mx, f[i - 1][j - 1] - preFix[j - 1] * w);
                f[i][j] = Math.max(f[i][j - 1], preFix[j] * w + mx);
            }
        }
        return f[k][n];
    }
}
