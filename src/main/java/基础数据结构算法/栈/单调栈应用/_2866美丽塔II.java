package 基础数据结构算法.栈.单调栈应用;

import java.util.LinkedList;
import java.util.List;

public class _2866美丽塔II {
    /*
    给你一个长度为 n 下标从 0 开始的整数数组 maxHeights 。

    你的任务是在坐标轴上建 n 座塔。第 i 座塔的下标为 i ，高度为 heights[i] 。

    如果以下条件满足，我们称这些塔是 美丽 的：

    1 <= heights[i] <= maxHeights[i]
    heights 是一个 山脉 动态数组。
    如果存在下标 i 满足以下条件，那么我们称数组 heights 是一个 山脉 动态数组：

    对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
    对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
    请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
     */

    /**
     <h1>枚举+前后缀累加和+单调栈</h1>
     左侧塔递增,右侧塔递减<br>
     枚举点位i,计算以i为峰的高度和,求最大值<br>
     <p>
     定义prev[i]为以i为峰,i与i左侧的累加高度,suff[i]为以i为峰,i与i右侧的累加高度
     则以i为峰时,高度和为prev[i]+suff[i]-h[i]<br>
     <p>
     prev和suff数组可通过单调递增栈求解<br>
     <p>
     以prev为例:<br>
     如果点位i左侧都是大于它的<br>
     因为左侧为递增序列,所以左侧的最大高度都为i的高度,prev[i]=(i+1)*h[i]<br>
     <p>
     如果点位i左侧有小于它的,假设第一个小于它的为点位j<br>
     prev[j]已知,只需要知道j~i的累加和即可<br>
     由于j是左侧第一个小于它的,所以j~i中间都是大于i的,他们的高度最大为i的高度(左侧需要递增)<br>
     则 prev[i]=prev[j]+(i-j)*h[i]
     */
    public long maximumSumOfHeights(List<Integer> maxHeights) {
        long ans = 0;
        int n = maxHeights.size();
        LinkedList<Integer> stack = new LinkedList<>();   //单调栈
        long[] prev = new long[n];//prev[i]表示以i为峰,i左侧的累加高度
        long[] suff = new long[n];//suff[i]表示以i为峰,i右侧的累加高度
        for (int i = 0; i < n; i++) {
            int mH = maxHeights.get(i);
            while (!stack.isEmpty() && mH < maxHeights.get(stack.peek())) {
                stack.pop();//遇到单减抛出前面
            }
            if (stack.isEmpty()) {//该点位前面都是比它大的,它的累加和就是它自身乘以数量
                prev[i] = (long) (i + 1) * mH;
            } else {//前面第一个比它小的元素k的累加和 + 该点位到k(中间比它大的都变成它)的累加和
                int k = stack.peek();
                prev[i] = prev[k] + (long) (i - k) * mH;
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {//与prev同理
            int mH = maxHeights.get(i);
            while (!stack.isEmpty() && mH < maxHeights.get(stack.peek())) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                suff[i] = (long) (n - i) * mH;
            } else {
                int k = stack.peek();
                suff[i] = suff[k] + (long) (k - i) * mH;
            }
            stack.push(i);
            ans = Math.max(ans, prev[i] + suff[i] - mH);//在此过程中计算当前点位的高度最大值
        }

        return ans;
    }

    public long maximumSumOfHeights2(List<Integer> maxHeights) {
        int n = maxHeights.size();
        int[] stack = new int[n];//单调递增栈
        int stackSize = 0;
        long[] prev = new long[n];//prev[i]表示以i为峰,i左侧的累加高度
        long[] suff = new long[n];//suff[i]表示以i为峰,i右侧的累加高度
        for (int i = 0; i < n; i++) {
            int mH = maxHeights.get(i);
            while (stackSize > 0 && mH < maxHeights.get(stack[stackSize - 1])) {
                stackSize--;//单调递增栈遇到小的抛出前面
            }
            if (stackSize == 0) {//该点位前面都是比它大的
                //以它为峰前面都修mH的高度,它的累加和就是它自身乘以数量
                prev[i] = (long) (i + 1) * mH;
            } else {//前面有比他矮的
                //前面第一个比它小的元素k的累加和 + 该点位到k(中间比它大的都变成它)的累加和
                int k = stack[stackSize - 1];
                prev[i] = prev[k] + (long) (i - k) * mH;
            }
            stack[stackSize++] = i;//当前点位入栈
        }
        stackSize = 0;
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {//与prev同理
            int mH = maxHeights.get(i);
            while (stackSize > 0 && mH < maxHeights.get(stack[stackSize - 1])) {
                stackSize--;
            }
            if (stackSize == 0) {
                suff[i] = (long) (n - i) * mH;
            } else {
                int k = stack[stackSize - 1];
                suff[i] = suff[k] + (long) (k - i) * mH;
            }
            stack[stackSize++] = i;
            ans = Math.max(ans, prev[i] + suff[i] - mH);//在此过程中计算当前点位的高度最大值
        }
        return ans;
    }
}
