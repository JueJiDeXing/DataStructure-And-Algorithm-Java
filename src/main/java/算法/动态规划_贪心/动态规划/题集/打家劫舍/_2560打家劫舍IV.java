package 算法.动态规划_贪心.动态规划.题集.打家劫舍;

public class _2560打家劫舍IV {
    /*
    沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。

    由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。

    小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。

    给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。

    另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。

    返回小偷的 最小生成树 窃取能力。
     */

    /**
     限制: 相邻不能偷, 偷k个,最小化最大值
     <h1>二分+DP/贪心</h1>
     令f[i]表示偷[0,i]中不连续且不超过max的可偷个数(其中max由二分枚举)<br>
     那么f[n]时最小的max即为答案<br>
     二分枚举max,检查[0,len-1]的可偷个数
     如果max大于等于k就是可行的,right减小继续找更小的max
     如果max小于k,则max不可性,必须增大偷的上限值,则left增加
     */
    public int minCapability(int[] nums, int k) {
        int left = 0, right = 0;
        for (int x : nums) {
            right = Math.max(right, x);//预处理二分区间
        }
        while (left + 1 < right) { // 开区间写法
            int mid = (left + right) >>> 1;
            if (check(nums, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    /**
     定义f[i]:偷[0,i]的个数
     如果i是可偷的(nums[i]<=max)
     如果不偷i, f[i]=f[i-1] ; 如果偷i, i-1就不能偷, f[i]=f[i-2]+1
     则f[i]=max(f[i-1],f[i-2]+1)
     */
    private boolean check(int[] nums, int k, int mx) {
        int f0 = 0, f1 = 0;
        for (int x : nums) {
            if (x > mx) {//不能偷
                f0 = f1;
            } else {//能偷,选择偷或不偷
                int tmp = f1;
                f1 = Math.max(f1, f0 + 1);
                f0 = tmp;
            }
        }
        return f1 >= k;
    }
}
