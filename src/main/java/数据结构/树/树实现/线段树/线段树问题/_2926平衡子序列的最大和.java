package 数据结构.树.树实现.线段树.线段树问题;

/**
 第 370 场周赛 Q4
 2448
 */
public class _2926平衡子序列的最大和 {
    /*
    给你一个下标从 0 开始的整数数组 nums 。

    nums 一个长度为 k 的 子序列 指的是选出 k 个 下标 i0 < i1 < ... < ik-1 ，
    如果这个子序列满足以下条件，我们说它是 平衡的 ：

    对于范围 [1, k - 1] 内的所有 j ，nums[ij] - nums[ij-1] >= ij - ij-1 都成立。
    nums 长度为 1 的 子序列 是平衡的。

    请你返回一个整数，表示 nums 平衡 子序列里面的 最大元素和 。

    一个数组的 子序列 指的是从原数组中删除一些元素（也可能一个元素也不删除）后，剩余元素保持相对顺序得到的 非空 新数组。
     */

    /**
     在nums中选出k个下标,满足: <br>
     nums[ij] - nums[ij-1] >= ij - ij-1 <br>
     对不等式作变换: <br>
     nums[i] - nums[j] >= i - j <br>
     nums[i] - i >= nums[j] - j <br>
     令 b[i] = nums[i] - i <br>
     则 b[i] >= b[j] <br>
     问题转变为:在b中选出k个下标,满足b[i]>=b[j] <br>
     即,在b中选出一个长度为k的非降序子序列,求最大和 <br>
     <br>
     定义f[i]表示表示子序列最后一个数的下标是 i 时，对应的 nums 的元素和的最大值<br>
     则ans=max(f[i]) <br>
     状态转移: <br>
     f[i] = max_{j < i}( f[j] ) + nums[i] // 负数不考虑,所以f[j]还需要与0取max<br>

     */
    public long maxBalancedSubsequenceSum(int[] nums) {
        return 0;
    }

}
