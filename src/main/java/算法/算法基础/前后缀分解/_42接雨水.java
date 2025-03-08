package 算法.算法基础.前后缀分解;

/**
 难度:困难
 */
public class _42接雨水 {
    /*
      给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
       */

    /**
     对于当前柱子i, 如果知道左边最高的柱子高度为pre, 右边最高柱子高度为suf
     则柱子i可以接到 min(pre,suf) - height[i] 的雨水
     */
    public int trap(int[] height) {
        int n = height.length;
        int[] preMax = new int[n]; // preMax[i] 表示从 height[0] 到 height[i] 的最大值
        preMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], height[i]);
        }

        int[] sufMax = new int[n]; // sufMax[i] 表示从 height[i] 到 height[n-1] 的最大值
        sufMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sufMax[i] = Math.max(sufMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(preMax[i], sufMax[i]) - height[i]; // 累加每个水桶能接多少水
            // (为什么可以包含i, 因为柱i如果比左边或右边高 , 则min(preMax[i], sufMax[i]) == height[i]
        }
        return ans;
    }

    /**
     单调栈解法
     {@link 基础数据结构算法.栈.单调栈应用._42接雨水}
     {@link 算法.算法基础.双指针._42接雨水}
     */
    public int trap2(int[] height) {
        return 0;
    }
}
