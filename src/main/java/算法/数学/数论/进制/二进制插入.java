package 算法.数学.数论.进制;

/**
 把bin(a)插入到bin(b)的0上
 */
public class 二进制插入 {

    public static long insert(int a, int b) {
        long ans = b;
        int i = 0;
        while (a > 0) {
            long bit = (ans >> i) & 1;
            if (bit == 0) { // b 的第 i 个比特值是 0
                // 填入
                ans |= (long) (a & 1) << i;
                a >>= 1;
            }
            i++;
        }
        return ans;
    }
}
