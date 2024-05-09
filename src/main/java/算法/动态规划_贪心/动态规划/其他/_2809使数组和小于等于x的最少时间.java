package 算法.动态规划_贪心.动态规划.其他;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _2809使数组和小于等于x的最少时间 {
    /*
    给你两个长度相等下标从 0 开始的整数数组 nums1 和 nums2 。
    每一秒，对于所有下标 0 <= i < nums1.length ，nums1[i] 的值都增加 nums2[i] 。
    操作 完成后 ，你可以进行如下操作：
    选择任一满足 0 <= i < nums1.length 的下标 i ，并使 nums1[i] = 0 。
    同时给你一个整数 x 。
    请你返回使 nums1 中所有元素之和 小于等于 x 所需要的 最少 时间，如果无法实现，那么返回 -1 。
     */

    /**
     <p>提示: 每个下标至多操作一次, 因为如果下标i操作第二次, 那么上一次操作 i 就没有任何意义, 完全可以等到这次进行操作, 操作2次一定不是最优解</p>
     <p>
     求使 nums1 中所有元素之和 小于等于 x 所需要的 最少 时间<br>
     <-->求 t 秒时元素之和的最小值 (0 <= t < n)<br>
     如果 t 秒不做操作,那么元素之和为 Sum1+Sum2*t , 元素之和最小值 = 元素之和 - 减少量的最大值<br>
     <p>
     <-->求 t 秒时减少量的最大值<br>
     如果现在为第 t 秒,对于元素 i :<br>
     如果不做操作,它的值为 nums1[i]+nums2[i]*t<br>
     如果在第 k 秒将其置0,那么当前元素 i 的值为 nums2[i]*(t-k)<br>
     所以元素 i 贡献的减少量为 nums1[i]+nums2[i]*k, 系数 k 恰好为操作它的时间<br>
     所以在第 t 秒,一定选择了 t 个元素,总减少量为 nums1[i]+nums2[i]*k, (1<=k<=t)<br>
     <p>
     如果 a 和 b 两个数都被操作了<br>
     由于 nums1[i] 是固定的,所以在 nums2 中大的一个应当晚操作,使系 数k 更大<br>
     所以要对 nums2 进行升序排序
     <p>
     <-->在升序数组 nums2 中选择一个长为 t 的子序列, 依次进行操作, 选择哪些下标减少量最大<br>
     序列中第j个元素的贡献为 nums1[i]+nums2[i]*j<br>
     定义 f[i+1][j] 为从 [0,i] 中选择 j 个下标的最大减少量<br>
     初始 f[i][0]=0, 选 0 个,减少量为 0<br>
     考虑下标i,如果不选, 则 f[i+1][j]=f[i][j]; 如果选, 则f[i+1][j]=f[i][j-1]+nums1[i]+nums2[i]*j<br>
     所以 f[i+1][j] = Max( f[i][j], f[i][j-1] + nums1[i]+nums2[i]*j ) <br>
     降维:f[i+1]=max(f[i+1],f[i] + nums1[i]+nums2[i]*(i+1)) // 此时遍历i需要反向,因为i会使用i和i-1的数据<br>
     <p>
     则 ans 为最小的t, 其中 t 满足 Sum1+Sum2*t-f[n][t] <= x
     */
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size(), s1 = 0, s2 = 0;//求和s1,s2
        int[][] pairs = new int[n][2];//捆绑排序的数组
        for (int i = 0; i < n; i++) {
            int a = nums1.get(i), b = nums2.get(i);
            pairs[i][0] = a;
            pairs[i][1] = b;
            s1 += a;
            s2 += b;
        }
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        //动态规划
        int[] f = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int a = pairs[i][0], b = pairs[i][1];
            for (int j = i + 1; j > 0; j--) {
                f[j] = Math.max(f[j], f[j - 1] + a + b * j);
            }
        }
        //找最小的满足的t
        for (int t = 0; t <= n; t++) {

            if (s1 + s2 * t - f[t] <= x) {
                return t;
            }
        }

        return -1;
    }
}
