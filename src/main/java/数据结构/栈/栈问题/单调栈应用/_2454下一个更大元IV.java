package 数据结构.栈.栈问题.单调栈应用;

import java.util.*;

/**
 第 90 场双周赛 Q4
 难度分:2175
 */
public class _2454下一个更大元IV {
    /*
    给你一个下标从 0 开始的非负整数数组 nums 。
    对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。

    如果 nums[j] 满足以下条件，那么我们称它为 nums[i] 的 第二大 整数：

    j > i
    nums[j] > nums[i]
    恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
    如果不存在 nums[j] ，那么第二大整数为 -1 。

    比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
    请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
     */

    /**
     <h1>双单调栈</h1>
     总体思路,每个元素比较2次,第二次的就是第二大的元素
     对于一个单调递减栈,当遍历到比它大的元素时,栈顶元素要抛出,此为第一次比较
     在抛出后,将抛出元素存储起来,下一次又遇到比它大的元素时,即找到第二大的元素,此为第二次比较
     */
    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        // 两个单调递减栈,第1比较栈和第2比较栈,当在s1中遇到更大的被弹出后进入s2
        // 如果元素在s2被弹出,说明此时找到了第二大的元素
        int[] stack1 = new int[n], stack2 = new int[n];
        int len1 = 0, len2 = 0;//当前栈内元素个数
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            while (len2 > 0 && nums[stack2[len2 - 1]] < nums[i]) {//检查第2比较栈
                ans[stack2[len2 - 1]] = nums[i];//nums[i]是索引stack[len2-1]的右侧第二大的数
                len2--;
            }
            //将栈1中的小于nums[i]的部分抛出到栈2
            //整段抛出是因为需要保证栈2是递减的,如果一个一个抛栈2的序会混乱
            int p = len1 - 1;
            while (p >= 0 && nums[stack1[p]] < nums[i]) p--;
            System.arraycopy(stack1, p + 1, stack2, len2, len1 - p - 1);
            len2 += len1 - p - 1;//抛出后更新一下长度
            len1 = p + 1;
            stack1[len1++] = i;//当前元素入栈
        }
        return ans;
    }
}
