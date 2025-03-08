package 算法.动态规划.其他;

public class _2735收集巧克力 {
    /*
    给你一个长度为 n 、下标从 0 开始的整数数组 nums ，表示收集不同巧克力的成本。
    每个巧克力都对应一个不同的类型，最初，位于下标 i 的巧克力就对应第 i 个类型。

    在一步操作中，你可以用成本 x 执行下述行为：
    同时修改所有巧克力的类型，将巧克力的类型 ith 修改为类型 ((i + 1) mod n)th。 --> 此处有歧义,应为操作k次后的花费nums[i]->nums[(i+k)%n]
    假设你可以执行任意次操作，请返回收集所有类型巧克力所需的最小成本。
     */

    /**
     定义s[k]为操作k次的总花费<br>
     那么对于第i个巧克力,它能接触的到的巧克力为nums[i]~nums[i+k]<br>
     所以在操作k次时,第i个巧克力的最小花费为MIN(nums[i]~nums[i+k])<br>
     */
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        long[] s = new long[n]; // s[k] 统计操作 k 次的总成本
        for (int i = 0; i < n; i++) {
            s[i] = (long) i * x;//i次操作花费i*x
        }
        for (int i = 0; i < n; i++) { // 枚举每个巧克力,计算最小花费
            int mn = nums[i];
            //枚举操作次数0<=len<n,len=j-i
            //s[len]为操作次数为len时的最小花费(也就是从i到len+i的最小值)
            for (int j = i; j < n + i; j++) { // 子数组右端点（把数组视作环形的）
                mn = Math.min(mn, nums[j % n]); // 维护从 nums[i] 到 nums[j] 的最小值
                s[j - i] += mn; // 累加操作 j-i 次的花费
            }
        }
        long ans = Long.MAX_VALUE;
        for (long v : s) {
            ans = Math.min(ans, v);
        }
        return ans;
    }
}
