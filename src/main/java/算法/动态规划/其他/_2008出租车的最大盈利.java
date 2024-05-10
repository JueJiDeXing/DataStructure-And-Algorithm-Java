package 算法.动态规划.其他;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _2008出租车的最大盈利 {

    /*
    你驾驶出租车行驶在一条有 n 个地点的路上。
    这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n ，通过接乘客订单盈利。
    你只能沿着编号递增的方向前进，不能改变方向。
    乘客信息用一个下标从 0 开始的二维数组 rides 表示，
    其中 rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费。
    每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。
    你同时 最多 只能接一个订单。
    给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
    注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
     */

    /**
     <h1>排序+动态规划+二分</h1>
     将 rides 按照 endi 从小到大进行排序，记 m 为 rides 的大小<br>
     dp[i+1] 表示只接区间 [0,i] 内的乘客的最大盈利，显然 dp[0]=0，<br>
     <p>
     而对于 i∈[0,m]，有两种情况：<br>
     <ul>
     <li>
     对第 i 位乘客接单，由于前面已经对 rides 进行排序，<br>
     因此我们可以通过二分查找来找到满足 endj≤starti 最大的 j，<br>
     那么 dp[i+1]=dp[j]+endi−starti+tipi<br>
     </li>
     <li>
     对第 i 位乘客不接单，那么有 dp[i+1]=dp[i]
     </li>
     </ul>
     根据以上情况，对于 i∈[0,m]，有转移方程为：<br>
     <p>
     dp[i+1]=max(dp[i],dp[j]+endi−starti+tipi)<br>
     其中 j 为满足 endj≤starti 最大的 j，那么 dp[m] 即为结果。
     */
    public long maxTaxiEarnings1(int n, int[][] rides) {
        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));
        int m = rides.length;
        long[] dp = new long[m + 1];
        for (int i = 0; i < m; i++) {
            int j = binarySearch(rides, i, rides[i][0]);
            dp[i + 1] = Math.max(dp[i], dp[j] + rides[i][1] - rides[i][0] + rides[i][2]);
        }
        return dp[m];
    }

    public int binarySearch(int[][] rides, int high, int target) {
        int low = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (rides[mid][1] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /**
     <h1>动态规划+记忆化搜索</h1>
     令dfs[i]为从 1 开到 i 最多可以赚多少钱<br>
     <ul>
     <li>
     case1: 没有乘客在i下车,或者不载在i下车的乘客<br>
     dfs[i]=dfs[i-1]
     </li>
     <li>
     case2: 有乘客在i下车,那么可以枚举在i下车的乘客找最大利润<br>
     dfs[i]= MAX_j ( dfs(start_j) + end_j - start_j + tip_j ) 其中end_j=i
     </li>
     </ul>
     以上取最大值即dfs[i]= max( dfs[i-1] , MAX_j ( dfs(start_j) + end_j - start_j + tip_j ) )<br>
     <p>
     代码实现时，为了方便枚举所有在 i 下车的乘客，可以把 rides 按照 end 分组。<br>
     分组时，对相同的 end ，记录 start 以及 end−start+tip。
     */
    public long maxTaxiEarnings2(int n, int[][] rides) {
        //按照end分组
        List<int[]>[] groups = new ArrayList[n + 1];
        for (int[] r : rides) {
            int start = r[0], end = r[1], tip = r[2];
            if (groups[end] == null) {
                groups[end] = new ArrayList<>();
            }
            groups[end].add(new int[]{start, end - start + tip});
        }
        //搜索
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1); // -1 表示没有计算过
        return dfs(n, memo, groups);
    }

    /**
     dfs[i]为从 1 开到 i 最多可以赚多少钱

     @param i      当前终点
     @param memo   记忆化
     @param groups 对end分组,看是否有乘客在i下车
     @return 最多可以赚多少钱
     */
    private long dfs(int i, long[] memo, List<int[]>[] groups) {
        if (i == 1) {//dfs[1]=0,1为起点,无盈利
            return 0;
        }
        if (memo[i] != -1) { // 之前计算过
            return memo[i];
        }

        long res = dfs(i - 1, memo, groups);//无乘客或不载在i下车的乘客,dfs[i]=dfs[i-1]
        if (groups[i] != null) {
            //如果有乘客在i下车
            for (int[] p : groups[i]) {//枚举找最大利润(或者不载乘客)
                res = Math.max(res, dfs(p[0], memo, groups) + p[1]);//dfs[i]= MAX_j ( dfs(start_j) + end_j - start_j + tip_j )
            }
        }
        return memo[i] = res; // 记忆化
    }


    /**
     <h1>从记忆化搜索到递推</h1>
     */
    public long maxTaxiEarnings(int n, int[][] rides) {
        //按照end分组
        List<int[]>[] groups = new ArrayList[n + 1];
        for (int[] r : rides) {
            int start = r[0], end = r[1], tip = r[2];
            if (groups[end] == null) {
                groups[end] = new ArrayList<>();
            }
            groups[end].add(new int[]{start, end - start + tip});
        }
        //递推
        long[] f = new long[n + 1];
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1];
            if (groups[i] != null) {
                for (int[] p : groups[i]) {
                    f[i] = Math.max(f[i], f[p[0]] + p[1]);
                }
            }
        }
        return f[n];
    }

}
