package 算法.算法基础.双指针;

import java.util.Arrays;
import java.util.HashMap;

/**
 难度:困难
 */
public class _42接雨水 {
    /*
      给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
       */

    /**
     前后缀分解思路:
     对于当前柱子i, 如果知道左边最高的柱子高度为pre, 右边最高柱子高度为suf
     则柱子i可以接到 min(pre,suf) - height[i] 的雨水
     <p>
     双指针思路:
     对于当前柱子l和r, 如果知道左边最高的柱子(含l)高度为pre, 右边最高柱子(含r)高度为suf
     如果pre > suf:
     那么对于柱r, 无论[l,r]上是否有高于pre的柱子, 它只能接到suf-height[r]的雨水
     则可以计算柱r的雨水, 将r指针左移
     同理, 如果suf < pre:
     那么对于柱l, 无论[l,r]上是否有高于suf的柱子, 它只能接到pre-height[l]的雨水
     则可以计算柱l的雨水, 将l指针右移
     */
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int preMax = 0; // 前缀最大值，随着左指针 left 的移动而更新
        int sufMax = 0; // 后缀最大值，随着右指针 right 的移动而更新
        while (left < right) {
            preMax = Math.max(preMax, height[left]);
            sufMax = Math.max(sufMax, height[right]);
            if (preMax < sufMax) {
                ans += preMax - height[left++];
            } else {
                ans += sufMax - height[right--];
            }
        }
        return ans;
    }

    /**
     单调栈解法
     {@link 基础数据结构算法.栈.单调栈应用._42接雨水}
     {@link 算法.算法基础.前后缀分解._42接雨水}
     */
    public int trap2(int[] height) {
        return 0;
    }


}
