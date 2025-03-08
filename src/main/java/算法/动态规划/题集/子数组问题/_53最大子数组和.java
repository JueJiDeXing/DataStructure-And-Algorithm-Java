package 算法.动态规划.题集.子数组问题;

public class _53最大子数组和 {
    /*
    给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    子数组 是数组中的一个连续部分。
    子数组可以为空
     */

    /**
     f[i]:以nums[i]结尾的子数组最大和
     如果nums[i]单独为一段: f[i] = nums[i]
     如果nums[i]加入以nums[i-1]结尾的段: f[i] = nums[i] + f[i-1]
     即: f[i] = max(nums[i],nums[i]+f[i-1])
     <p>
     ans = MAX(f)
     */
    public int maxSubArray(int[] nums) {
        int f = 0;// f[i]:以nums[i]结尾的子数组最大和
        int ans = Integer.MIN_VALUE;
        for (int a : nums) {
            f = Math.max(f, 0) + a; // f[i] = max(nums[i], nums[i]+f[i-1])
            ans = Math.max(ans, f);//ans = MAX(f)
        }
        return ans;
    }
}

/**
 分治法求解最大子数组和
 */
class Solution2 {
    static class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    /**
     F[l][r]: [l,r]上的最大子数组和
     FL[l][r]: [l,r]上, 以l为左端点的最大子数组和
     FR[l][r]: [l,r]上, 以r为右端点的最大子数组和
     则F[l][r] = max( F[l][mid], F[mid+1][r], FR[l][mid]+FL[mid+1][r])
     <p>
     已知左右子区间状态, 计算当前区间状态
     (1) 左端点的最大子数组和: 选左区间的左边,不选右区间 |  选右区间的左边,此时左区间必须全选
     FL[l][r] = max( FL[l][mid], sum[l][mid] + FL[mid+1][r])
     (2) 右端点的最大子数组和: 选右区间的右边,不选左区间 |  选左区间的右边,此时右区间必须全选
     FL[l][r] = max( FR[mid+1][r], sum[mid+1][r] + FR[l][mid])
     (3) 最大子数组和: 左最大 | 右最大 | 跨中间
     F[l][r] = max( F[l][mid], F[mid+1][r], FR[l][mid]+FL[mid+1][r])
     */
    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
