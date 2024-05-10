package 算法.动态规划.题集.子数组问题;

/**
 第 133 场周赛 Q3
 难度分:1680
 */
public class _1031两个非重叠子数组的最大和 {
    /*
    给你一个整数数组 nums 和两个整数 firstLen 和 secondLen，
    请你找出并返回两个非重叠 子数组 中元素的最大和，长度分别为 firstLen 和 secondLen 。

    长度为 firstLen 的子数组可以出现在长为 secondLen 的子数组之前或之后，但二者必须是不重叠的。

    子数组是数组的一个 连续 部分。
     */

    /**
     令f[i]表示[0,i]上[i-secondLen,i]为第二段,maxSumA为第一段的最大子数组和
     令g[i]表示[0,i]上[0,i-firstLen]为第一段,maxSumB为第二段的最大子数组和
     那么ans=MAX(f[i],g[i])
     */
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] s = new int[n + 1];//s[i]:sum(nums[0,i))
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }
        int ans = 0, maxSumA = 0, maxSumB = 0;
        //maxSumA:[0,i-secondLen]上长为first的最大子数组和
        //maxSumA + s[i] - s[i - secondLen]:maxSumA为第一段,[i-second,i]为枚举出的第二段
        //maxSumB同理,二者取最大值
        for (int i = firstLen + secondLen; i <= n; ++i) {
            maxSumA = Math.max(maxSumA, s[i - secondLen] - s[i - secondLen - firstLen]);
            maxSumB = Math.max(maxSumB, s[i - firstLen] - s[i - firstLen - secondLen]);
            ans = Math.max(ans, Math.max(maxSumA + s[i] - s[i - secondLen],  // 左 a 右 b
                    maxSumB + s[i] - s[i - firstLen])); // 左 b 右 a
        }
        return ans;
    }
}
