package 算法.数学.数论.进制;

import java.util.*;

public class _421数组中两个数的最大异或值 {
    /*
    给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，
    其中 0 ≤ i ≤ j < n 。
     */

    /**
     假设最大的数为 a ^ b = x <br>
     令 pre[k][n] 表示 n 的二进制前 k 位 <br>
     那么有 pre[k][a] ^ pre[k][b] = pre[k][x] <br>
     <p>
     令 xCurr = pre[k][x] , xNext = pre[k+1][x] <br>
     在已知 xCurr 情况下,要得到最大的 x ,那么需要尽可能考虑 xNext 是否可以为 1 <br>
     <p>
     所以总体做法是:<br>
     从高位开始枚举这位是否可以为 1 (不改变前面的情况下)<br>
     对于第 k 轮枚举,xNext = (xCurr<<1) + 1<br>
     判断数组中是否有两个数 pre[k][a] ^ pre[k][b] = xNext<br>
     如果有,则 xCurr = xNext, 否则该位只能为 0 , xCurr = xNext - 1 <br>
     */
    public int findMaximumXOR(int[] nums) {
        int x = 0;
        for (int k = 30; k >= 0; k--) { // 最高位的二进制位编号为 30
            Set<Integer> seen = new HashSet<>();
            // 将所有的 pre[k][num] 放入哈希表中
            for (int num : nums) {
                seen.add(num >> k);// 如果只想保留从最高位开始到第 k 个二进制位为止的部分,只需将其右移 k 位
            }
            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = (x << 1) + 1;
            boolean found = false;
            for (int num : nums) { // 枚举 num 看是否有两个数 pre(a) ^ pre(b) = xNext
                if (seen.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }
            x = xNext;
            if (!found) {
                x--;// 如果没有找到满足等式的 a 和 b，那么 x 的第 k 个二进制位只能为 0, 即为 x = x*2
            }
        }
        return x;
    }

    /**
     代码优化
     */
    public int findMaximumXOR2(int[] nums) {
        int max = 0;
        for (int x : nums) max = Math.max(max, x);
        int highBit = 31 - Integer.numberOfLeadingZeros(max);

        int ans = 0, mask = 0;//mask为置0变量,将i位以后的都置0
        Set<Integer> seen = new HashSet<>();
        for (int i = highBit; i >= 0; i--) { // 从最高位开始枚举
            seen.clear();
            mask |= 1 << i;
            int newAns = ans | (1 << i); // 这个比特位可以是 1 吗？
            for (int x : nums) {
                x &= mask; // 低于 i 的比特位置为 0
                if (seen.contains(newAns ^ x)) {
                    ans = newAns; // 这个比特位可以是 1
                    break;
                }
                seen.add(x);
            }
        }
        return ans;
    }
}
