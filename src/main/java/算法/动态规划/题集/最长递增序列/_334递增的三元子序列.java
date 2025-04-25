package 算法.动态规划.题集.最长递增序列;

/**
 难度:中等
 */
public class _334递增的三元子序列 {
    /*
    给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。

    如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
     */

    /**
     本题等价于 nums的最长递增子序列长度是否大于等于3
     <p>
     令f[len]=x表示长度为len的递增子序列末位为x
     对于nums[i]:
     若 nums[i] > x, 则f[len+1] = nums[i]
     若 nums[i] <= x, 则可以二分查找一个下标k, f[k] = t, t < nums[i], 然后f[k+1]=nums[i]
     <p>
     本题只需要判断长度为3, 那么可以将f数组压缩为2个变量
     */
    public boolean increasingTriplet1(int[] nums) {
        int n = nums.length;
        long[] f = new long[3];
        f[1] = f[2] = Long.MAX_VALUE;
        for (int t : nums) {
            if (f[2] < t) {// nums[i]可以拼接到长度为2的子序列后面
                return true;
            } else if (f[1] < t && t < f[2]) {// nums[i]可以拼接到长度为1的子序列后面
                f[2] = t;
            } else if (f[1] > t) {// nums[i]作为子序列开始
                f[1] = t;
            }
        }
        return false;
    }

    public boolean increasingTriplet2(int[] nums) {
        int n = nums.length;
        int i = 0, j = -1;
        for (int k = 1; k < n; k++) {
            if (j != -1 && nums[j] < nums[k]) {
                return true;
            }
            if (nums[i] < nums[k]) {
                j = k;
            } else {
                i = k;
            }
        }
        return false;
    }
}
