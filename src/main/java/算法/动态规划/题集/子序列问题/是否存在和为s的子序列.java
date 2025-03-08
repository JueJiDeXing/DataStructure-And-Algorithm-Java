package 算法.动态规划.题集.子序列问题;

import 算法OJ.Codeforces.构造题单._9二分数组;

public class 是否存在和为s的子序列 {
    /**
     背包
     f[i][j]:前i个数能否选出j
     如果有f[k][j]->f[i][j+nums[i]],前k个数可以选出j,加入nums[i]后则可以选出j+nums[i]
     {@link _9二分数组}
     */
    public boolean exist(int[] nums, int s) {
        boolean[] f = new boolean[s + 1];
        f[0] = true;//什么都不选
        for (int num : nums) {
            for (int j = s; j >= num; j--) {
                f[j] |= f[j - num];
            }
        }
        return f[s];
    }
}
