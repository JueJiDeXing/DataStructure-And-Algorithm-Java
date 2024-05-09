package 算法.数学.数论.进制.数对和;

import 算法OJ.牛客.小白月赛.小白月赛91.F_异或世界;

public class ai异或aj的和 {
    public static void main(String[] args) {
        int[] A = new int[]{1, 2, 3, 4};
        System.out.println(sum2(A));
    }

    /**
     求 sum{ ai ^ aj } 1 <= i < j <= n
     对于二进制下的第k位计算全体贡献,如果有cnt0个0,cnt1个1,那么在第k位就有 cnt0 * cnt1 * ( 1 << k )的贡献
     */
    public static long sum1(int[] A) {
        long ans = 0;
        for (int k = 0; k < 32; k++) { // 计算a1与a2的异或和
            //求第k位,序列中1和0的个数
            long cnt0 = 0, cnt1 = 0;
            for (int a : A) {
                if (((a >> k) & 1) != 0) {
                    cnt1++;
                } else {
                    cnt0++;
                }
            }
            ans = ans + (cnt1 * cnt0 * (1 << k));
        }
        return ans;
    }

    /**
     将sum1化为逐步计算
     如果前面有cnt0个0和cnt1个1
     如果当前为0,那么贡献 cnt1 * ( 1 << k )
     如果当前为1,那么贡献 cnt0 * ( 1 << k )
     <p>
     这样做是因为有时候求解的问题是 ai^aj * fun , 并不是单纯的ai^aj
     */
    public static long sum2(int[] A) {
        long ans = 0;
        for (int i = 0; i < 32; i++) { // 计算a1与a2的异或和
            long cnt0 = 0, cnt1 = 0;
            long curr;
            for (int a : A) {
                if (((a >> i) & 1) != 0) {
                    cnt1++;
                    curr = cnt0;
                } else {
                    cnt0++;
                    curr = cnt1;
                }
                ans = ans + (curr * (1 << i));
            }
        }
        return ans;
    }

    /**
     求 sum{ a[i1]^a[i2]+a[i3]^a[i4] | 1 <= i1 < i2 < i3 < i4 <= n }
     = sum{ a[i1]^a[i2] | 1 <= i1 < i2 < i3 < i4 <= n } + sum{ a[i3]^a[i4] | 1 <= i1 < i2 < i3 < i4 <= n }
     = sum{ a[i1]^a[i2] * C(n-i2-1,2) | 1 <= i1 < i2 <= n } + sum{ a[i3]^a[i4] * C(i3,2) | 1 <= i3 < i4 <= n }
     所以从前往后计算一遍异或组合之和,再从后往前算一遍即可
     */
    public static long sum3(int[] A) {
        int n = A.length;
        long ans = 0;
        for (int i = 0; i < 32; i++) { // 计算a1与a2的异或和
            long cnt0 = 0, cnt1 = 0, curr;
            for (int j = 0; j < n; j++) {
                if (((A[j] >> i) & 1) != 0) {
                    cnt1++;
                    curr = cnt0;
                } else {
                    cnt0++;
                    curr = cnt1;
                }

                long res = (long) (n - j - 1) * (n - j - 2) / 2;// C(n-i2-1,2)
                ans = (ans + ((curr * (1 << i))) * res);
            }
        }
        for (int i = 0; i < 32; i++) {
            long cnt0 = 0, cnt1 = 0, curr;
            for (int j = n - 1; j >= 0; j--) { // 计算a3与a4的异或和
                if (((A[j] >> i) & 1) != 0) {
                    cnt1++;
                    curr = cnt0;
                } else {
                    cnt0++;
                    curr = cnt1;
                }
                long res = (long) j * (j - 1) / 2;// C(i3,2)
                ans = (ans + ((curr * (1 << i))) * res);
            }
        }
        return ans;
    }
    /**
     全体子数组的异或和之和
     sum{[L,R], sum{a[i]^a[j]}}
     {@link F_异或世界}
     */


}
