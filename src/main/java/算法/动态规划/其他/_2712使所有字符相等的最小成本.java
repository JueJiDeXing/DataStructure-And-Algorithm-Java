package 算法.动态规划.其他;

public class _2712使所有字符相等的最小成本 {
    /*
    给你一个下标从 0 开始、长度为 n 的二进制字符串 s ，你可以对其执行两种操作：

    选中一个下标 i 并且反转从下标 0 到下标 i（包括下标 0 和下标 i ）的所有字符，成本为 i + 1 。
    选中一个下标 i 并且反转从下标 i 到下标 n - 1（包括下标 i 和下标 n - 1 ）的所有字符，成本为 n - i 。
    返回使字符串内所有字符 相等 需要的 最小成本 。

    反转 字符意味着：如果原来的值是 '0' ，则反转后值变为 '1' ，反之亦然。
     */

    /**
     <h1>动态规划</h1>
     <ul>
     <li>定义: <br>
     prev[i] : [0, i-1] 翻转成 s[i] 需要的最小成本<br>
     suff[i] : [i+1, len-1] 翻转成 s[i] 的最小成本<br>
     则ans=MIN( prev[i]+suff[i] ) <br>
     </li>
     <li>
     递推关系: <br>
     对于 i , 此时 s[0~i-1] 都已经被翻转成了 s[i-1] <br>
     如果 s[i] 与前一个字符相同, 那么不需要翻转, prev[i] = prev[i-1] <br>
     如果 s[i] 与前一个字符不相同, 那么需要翻转前面的所有字符, prev[i] = prev[i-1] + i <br>
     suff同理<br>
     </li>
     </ul>
     */
    public long minimumCost(String s) {
        char[] str = s.toCharArray();
        int len = str.length;
        //求前后缀成本
        long[] prev = new long[len], suff = new long[len];
        for (int i = 1; i < len; i++) {//prev[0]=0
            prev[i] = prev[i - 1];
            if (str[i] != str[i - 1]) {//不同则需要翻转
                prev[i] += i;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            suff[i] = suff[i + 1];
            if (str[i] != str[i + 1]) {
                suff[i] += len - i - 1;
            }
        }
        //求最小成本
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            ans = Math.min(ans, prev[i] + suff[i]);
        }
        return ans;
    }

    /**
     <h1>贪心优化</h1>
     对于位置i,它的翻转不会影响到前半部分或后半部分的成本<br>
     例如 序列 0101(0i), s[i]=1, 此时前翻变为1010(0i) , 0101 与 1010 的翻转成本是相同的<br>
     所以,在翻转时只需要贪心的选择每一步更小的成本即可<br>
     */
    public long minimumCost2(String s) {
        char[] str = s.toCharArray();
        long ans = 0;
        int len = str.length;
        for (int i = 1; i < len; i++) {
            if (str[i - 1] != str[i]) ans += Math.min(i, len - i);
        }
        return ans;
    }
}
