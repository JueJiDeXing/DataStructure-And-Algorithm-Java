package 算法.动态规划.题集.最长递增序列;

import java.util.Arrays;

/**
 难度:中等
 */
public class _300最长递增子序列 {
    /*
    [10,2,5,3,3,7,101,18]  --> result=len([2,3,7,101])=4
     */
    public static void main(String[] args) {
        System.out.println(new _300最长递增子序列().lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

    /**
     <h1>动态规划</h1>
     dp[i]表示以i结尾组成的最长递增子序列长度<br>
     初始化:<br>
     dp[i]=1 单个元素作为序列<br>
     状态转移方程:<br>
     dp[i]=MAX(dp[j]+1)<br>
     // i与j的数字进行组合(其中0 <= j < i,且nums[j]与nums[i]满足升序)
     */
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {//第一个数字不需要再处理
            for (int j = 0; j < i; j++) {//第i个数字与0~i进行组合
                if (nums[i] > nums[j]) {//满足升序
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    /**
     <h1>二分</h1>
     定义数组tails，其中tails[i]=k表示长度为i+1的递增子序列的末位数字为k
     性质①：在i>0时，假如存在长度为i+1的子序列，那么必然有长度为i的子序列
     性质②：tails数组是递增的，这是因为初始为0时没有元素，视为递增，并且向其中添加元素后依然保持递增(这点在后面说明)
     从前往后遍历nums数组，对于当前遍历值num，
     假设在tails里第一个大于等于num的元素为tails[pos]=k，
     由①可知tails[pos-1]存在，且小于num，
     所以num可以接在长度为pos的序列后面，组成一个新的长度为pos+1的序列。
     对于原来的长度为pos+1的序列，它的末位数为k>num，
     在长度相等的情况下，末位越小的序列更优，因为序列后可以接的数字更多，
     所以此时可以将tails[pos]替换为num，并且这不会破坏性质②，tails数组依然递增
     当遍历完数组后,检查tails数组的长度即可
     */
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length];
        int maxLen = 0;
        for (int num : nums) {
            int left = 0, right = maxLen;//tails的有效数据区域[0,maxLen)
            while (left < right) {
                int m = (left + right) / 2;
                if (tails[m] < num) left = m + 1;
                else right = m;
            }
            tails[left] = num;
            if (maxLen == right) maxLen++;
        }
        return maxLen;
    }

    public int lengthOfLIS3(int[] nums) {//改写上面的二分
        int n = nums.length;
        int[] tails = new int[n + 1];
        int maxLen = 1;//当前最长序列长度
        tails[1] = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (tails[maxLen] < num) {
                tails[++maxLen] = num;
                continue;
            }
            //查找最后一个小于num的位置left
            int left = 0, right = maxLen + 1;//tails的有效数据区域[1,maxLen)
            while (left + 1 != right) {
                int m = (left + right) / 2;
                if (tails[m] < num) {//left:小于num
                    left = m;
                } else {//right:大于等于num
                    right = m;
                }
            }
            tails[right] = num;// 长度为left的序列拼接num,覆盖长度为right的序列
        }
        return maxLen;
    }

    /**
     <h1>更新 - 模版</h1>
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];//dp[i]:长度为i的子序列末尾是多少
        int maxLen = 0;
        for (int x : nums) {
            if (maxLen == 0 || dp[maxLen] <= x) {
                dp[++maxLen] = x;
                continue;
            }
            int l = 0, r = maxLen;// r:>s[i]; l:<=s[i]
            while (l + 1 != r) {
                int mid = (l + r) >>> 1;
                if (dp[mid] <= x) {
                    l = mid;
                } else {
                    r = mid;
                }
            }
            dp[r] = x;
        }
        return maxLen;
    }
}


