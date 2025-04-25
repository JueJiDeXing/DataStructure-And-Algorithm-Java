package 算法.数学.数学基础.数列;

/**
 难度:困难
 */
public class _3495使数组元素都变为零的最少操作次数 {
    public static void main(String[] args) throws Exception {
        _3495使数组元素都变为零的最少操作次数 s = new _3495使数组元素都变为零的最少操作次数();
        long l = s.f2(8);
        System.out.println(l);
    }
/*
给定一个queries数组
每次询问 [l,r]这个数组最少需要多少次操作使数组元素都变为零

操作:
选择数组中的两个数a,b, 将其替换为a/4和b/4

求查询的次数总和

len(queries),1e5
1<=l<r<1e9
 */

    /**
     除以4 ⇔ 右移2位
     a变为0的操作次数 = a的二进制长度 / 2
     <p>
     将 [l,r] 转为 次数数组:
     <p>
     arr = [1,2,3,4,5]
     → arr = [1,1,1,2,2]
     <p>
     整个数组需要操作的次数为 ceil{ sum{arr}/2 }
     <p>
     对于 二进制长度为k的数:
     需要 ceil{ k/2 } 次操作
     <p>
     设r有m个比特位
     若 k < m, 则这些数有 2^(k-1) 个
     若 k = m, 这些数有 r - 2^(k-1) + 1 个
     <p>
     则 sum{arr} =  sum{ ceil{k/2} * 2^(k-1) | k∈[1,m) } + r + 1 - 2^(m-1)
     */
    public long minOperations(int[][] queries) {
        long ans = 0;
        for (int[] query : queries) {
            int l = query[0], r = query[1];
            ans += (f1(r) - f1(l - 1) + 1) / 2;
        }
        return ans;
    }

    long f1(int n) {// [1,n]
        long m = 32 - Integer.numberOfLeadingZeros(n);
        long ans = 0;
        for (long k = 1; k < m; k++) {
            ans += (k + 1) / 2 << (k - 1);
        }
        ans += (m + 1) / 2 * (n + 1 - (1L << m >> 1));
        return ans;
    }

    /**
     <h1>数列求和优化</h1>
     若 k为小于m的最大偶数
     长度为 1和2的数: 需要操作1次, 有 2^2-2^0个
     长度为 3和4的数: 需要操作2次, 有 2^4-2^2个
     长度为 5和6的数: 需要操作3次, 有 2^6-2^4个
     ...
     长度为 k-1和k的数: 需要操作k/2次, 有 2^k - 2^(k-2) 个
     <p>
     (2^2-2^0) * 1 +  (2^4-2^2) * 2 + ... + (2^k - 2^(k-2)) * k/2
     = 3 * 2^0 * 1 + 3 * 2^2 * 2 + ... + 3 * 2^(k-2) * k/2
     = 3S
     <p>
     S = 1 * 4^0 + 2 * 4^1 + 3 * 4^2 + ... + k/2 * 4^(k/2-1)
     4S - S = 4 * k/2 * 4^(k/2-1) - ( 4^0 + 4^1 + ... + 4^(k/2-1) )
     3S = (k/2) * 4^(k/2) - [4^(k/2)-1]/3
     <p>
     原式 = k/2 * 2^k - (2^k - 1) / 3
     (2^k - 1) / 3为下取整, 这里减1可以省略
     = k/2 * 2^k -  2^k / 3
     <p>
     剩余的部分为长度[k+1,m]的数, 有 n - 2^k + 1个, 每个数需要操作ceil{ m/2 }次
     */
    long f2(int n) {// [1,n]
        if (n == 0) return 0;
        long m = 32 - Integer.numberOfLeadingZeros(n);
        long k = (m - 1) / 2 * 2;// k是偶数
        long p1 = (k / 2 << k) - (1L << k) / 3;
        long p2 = (m + 1) / 2 * (n + 1 - (1L << k));
        return p1 + p2;
    }
}
