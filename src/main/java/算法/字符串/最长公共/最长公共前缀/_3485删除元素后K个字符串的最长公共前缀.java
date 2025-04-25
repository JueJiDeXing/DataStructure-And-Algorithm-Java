package 算法.字符串.最长公共.最长公共前缀;

import java.util.Arrays;
import java.util.Comparator;

/**
 难度:困难
 */
public class _3485删除元素后K个字符串的最长公共前缀 {
    /*
    给定一个长度为n的字符串数组words, 和长度K

    输出长度为n的answer数组, answer[i]表示删除words[i]后从words中选择K个字符串的最长公共前缀长度

     */

    /**
     对words进行排序, 具有相同前缀的字符串会靠在一起
     要想最长, 一定是选择连续的K个字符串
     <p>
     <p>
     对于连续的字符串的LCP: LCP(i ~ j) = LCP(i,j)
     证明:
     若 LCP(i ~ j) > LCP(i, j)
     则 LCP(i, j, LCP(i+1,j-1)) > LCP(i ,j)
     ∵ LCP(i, j, LCP(i+1,j-1)) <= LCP(i, j)
     ∴ 假设不成立
     <p>
     若 LCP(i ~ j) < LCP(i, j)
     ∵ i ~ j 有序
     ∴ i ~ j 都含有长度为LCP(i,j)的前缀, LCP(i ~ j)不会小于LCP(i,j)
     <p>
     求出 LCP(i ~ i+k-1) 的最大值(mx_i,mx_i+k-1)和次大值(mx2_i,mx2_i+k-1)
     a. 如果删除的字符串不在最大值区间内, 那么可以选择这个最大值区间
     b. 如果删除的字符串在最大值区间内, 但不在次大值区间内, 那么可以选择次大值区间
     c. 如果删除的字符串在最大值区间内, 又在次大值区间内 (两者重叠)
     ∵ 删除的字符串在最大区间和次大区间内
     ∴ 最大区间的字符串都含有次大区间的前缀
     又∵ LCP(最大区间) >= LCP(次大区间)
     ∴ 取一个在最大区间内,但不在次大区间内的字符串, 补充到次大区间, 结果仍为次大区间值
     <p>
     即: 删除字符串不在最大内, 答案为最大; 删除字符串在最大内, 答案为次大
     */
    public int[] longestCommonPrefix(String[] words, int k) {
        int n = words.length;
        // 对words排序 -> 对下标排序
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, Comparator.comparing(a -> words[a]));
        // 计算最大区间值和次大区间值
        int mx_i = 0;// 记录最大区间位置
        int mx_v = 0, mx2_v = 0;
        for (int i = 0; i + k - 1 < n; i++) {// 取排序后的words[i,i+k-1]
            int lcp = LCP(words[idx[i]], words[idx[i + k - 1]]);
            if (lcp > mx_v) {
                mx2_v = mx_v;
                mx_i = i;
                mx_v = lcp;
            } else if (lcp > mx2_v) {
                mx2_v = lcp;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {// 删除第i个字符串(排序后)
            if (mx_i <= i && i <= mx_i + k - 1) {// 在最大区间内
                ans[idx[i]] = mx2_v;// 取次大
            } else {// 不在最大区间内
                ans[idx[i]] = mx_v;// 取最大
            }
        }
        return ans;
    }

    static int LCP(String a, String b) {
        int n = Math.min(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) return i;
        }
        return n;
    }


}
