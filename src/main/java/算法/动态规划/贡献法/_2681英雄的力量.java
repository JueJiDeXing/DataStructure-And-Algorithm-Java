package 算法.动态规划.贡献法;

import java.util.Arrays;

public class _2681英雄的力量 {
    /*
    给你一个下标从 0 开始的整数数组 nums ，它表示英雄的能力值。如果我们选出一部分英雄，这组英雄的 力量 定义为：
    i0 ，i1 ，... ik 表示这组英雄在数组中的下标。那么这组英雄的力量为 max(nums[i0],nums[i1] ... nums[ik])2 * min(nums[i0],nums[i1] ... nums[ik]) 。

    请你返回所有可能的 非空 英雄组的 力量 之和。由于答案可能非常大，请你将结果对 109 + 7 取余。
     */
/*
翻译: 求所有子集的 max^2 * min 之和
 */

    /**
     由于顺序与答案无关,所以先排序
     设nums=[a,b,c,d,e]
     <p>
     考虑d为最大值的子集:
     1. d为最小值, 则价值为 d^3
     2. c为最小值, 则价值为 d^2 * c
     3. b为最小值, c可选可不选, 则价值为 d^2 * b * 2
     4. a为最小值, b和c可选可不选, 则价值为 d^2 * a * 4
     即: value_d = d^3 + d^2 * (2^0 * c + 2^1 * b + 2^2 * a)
     记 s = 2^0 * c + 2^1 * b + 2^2 * a
     则 value_d = d^2 * (d + s)
     <p>
     再以同样方式考虑e为最大值的子集:
     value_e = e^3 + e^2 * (2^0 * d + 2^1 * c + 2^2 * b + 2^3 * a )
     = e^2 * (e + (d + 2s))
     <p>
     可得 s[i+1] = nums[i] + 2 * s[i]
     value[i] = nums[i]^2 * (nums[i] + s[i])
     */
    public int sumOfPower(int[] nums) {
        final int MOD = 1_000_000_007;
        Arrays.sort(nums);
        long ans = 0;
        long s = 0;
        for (long x : nums) { // x 作为最大值
            long value_x = x * x % MOD * (x + s) % MOD;
            ans = (ans + value_x) % MOD; // 中间模一次防止溢出
            s = (s * 2 + x) % MOD; // 递推计算下一个 s
        }
        return (int) ans;

    }
}
