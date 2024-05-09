package 算法.动态规划_贪心.动态规划.题集.子数组问题;

import 算法.动态规划_贪心.动态规划.题集.子数组问题.最长递增序列._300最长递增子序列;

import java.util.*;

public class _1671得到山形数组的最少删除次数 {
    /*
    我们定义 arr 是 山形数组 当且仅当它满足：

    arr.length >= 3
    存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
    给你整数数组 nums ，请你返回将 nums 变成 山形状数组 的 最少 删除次数。
     */

    /**
     <h1>前后缀分解</h1>
     {@link _300最长递增子序列}<br>
     定义prev[i]为以i结尾的最长递增子序列长度<br>
     suff[i]为以i开头的最长递减子序列长度<br>
     则以i为峰时,最长的山脉数组长度为 prev[i]+suff[i]-1<br>
     求出最长的山脉数组长度,用原数组长度减得最少删除次数<br>
     <p>
     prev[i]的求法:<br>
     查看0~i中的元素j,如果j能够与i构成升序关系,则 prev[j]+1可以作为prev[i]的备选<br>
     最长递增序列在所有prev[j]+1中取最大值即为prev[i]<br>
     prev[i=MAX(prev[i]+1)<br>
     */
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        int[] prev = new int[n];//以i结尾的最长递增子序列长度
        Arrays.fill(prev, 1);
        for (int i = 1; i < n; i++) {//第一个数字不需要再处理
            for (int j = 0; j < i; j++) {//第i个数字与0~i进行组合
                if (nums[i] > nums[j]) {//满足升序
                    prev[i] = Math.max(prev[j] + 1, prev[i]);
                }
            }
        }
        int[] suff = new int[n];//以i开头的最长递减子序列长度
        Arrays.fill(suff, 1);
        for (int i = n - 2; i >= 0; i--) {//第一个数字不需要再处理
            for (int j = n - 1; j > i; j--) {//第i个数字与i~n-1进行组合
                if (nums[i] > nums[j]) {//满足降序
                    suff[i] = Math.max(suff[j] + 1, suff[i]);
                }
            }
        }

        int ans = 0;//最长山脉长度
        for (int i = 1; i < n - 1; i++) {
            if (prev[i] == 1 || suff[i] == 1) {
                continue; //前面和后面必须要有元素与它构成山脉
            }
            ans = Math.max(ans, prev[i] + suff[i] - 1);
        }

        return n - ans;//最少删除次数
    }
}
