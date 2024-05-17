package 算法.动态规划.题集.最长递增序列;

import java.util.Arrays;
import java.util.HashMap;

public class _100218求出所有子序列的能量和 {
    /*
    长度为n的整数数组,求出所有长度为k的子序列的能量和 (k<=n<=50)
    子序列的能量定义:子序列中任意两个元素差值的绝对值的最小值

     */
    static int MOD = 10_0000_0007;
    static int[] Nums;
    static int inf = Integer.MAX_VALUE / 2;

    public int sumOfPowers(int[] nums, int k) {
        Nums = nums;
        Arrays.sort(Nums);
        memo.clear();
        return (int) dfs(nums.length - 1, k, inf, inf);
    }

    static HashMap<String, Long> memo = new HashMap<>();

    /**
     时间复杂度:
     i有n个取值,j有k个,pre有n个,min有n^2个
     粗略估计为O(kn^4) 50^5约为1e8
     但是,复杂度的系数分母是比较大的,5!=120,所以大概是1e6,可以过
     <p>
     for i=0->n :
     for j=i+1->n:
     for l=j+1->n:
     ....
     这样的k重循环等价于从n个数里选k个数
     C(n,k), 它的分子可以认为是n^k, 分母是k!
     */
    long dfs(int i, int j, int pre, int min) {
        if (j > i + 1) return 0;
        if (j == 0) return min;
        String key = i + " " + j + " " + pre + " " + min;
        if (memo.containsKey(key)) return memo.get(key);
        long res1 = dfs(i - 1, j, pre, min);//不选
        long res2 = dfs(i - 1, j - 1, Nums[i], Math.min(min, pre - Nums[i]));//选
        long ans = (res1 + res2) % MOD;
        memo.put(key, ans);
        return ans;
    }

}
