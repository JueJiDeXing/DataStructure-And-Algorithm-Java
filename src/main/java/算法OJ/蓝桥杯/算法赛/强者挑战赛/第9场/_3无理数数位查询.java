package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 已AC
 */
public class _3无理数数位查询 {
    /*
    10进制下,将正整数按序拼接到小数点后:
    0.123456789101112131415161718192021...
    现在是m进制,求小数点后的第n位是多少
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) solve();
    }

    static long fastPow(long x, long n) {
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x;
            x = x * x;
            n >>= 1;
        }
        return ans;
    }

    static void solve() {
        long n = sc.nextLong();
        int m = sc.nextInt();
        //km^k - m^(k-1) - m^(k-2) -...-m <= n ,n为k+1位数
        long preBit = 0;//前缀位数
        int ki;//n是ki位数
        for (int i = 1; ; i++) {
            // 1~m-1 是一位数, 有1*(m^1-m^0)位
            // 10~(m-1)(m-1) 是两位数, 有 2*(m^2 - m^1) 位 -> 0~(m-1)(m-1)减去一位数
            // 100~(m-1)(m-1)(m-1) 是三位数, 有 3*(m^3 - m^2) -> 0~(m-1)(m-1)(m-1)减去一位和两位数
            // 所以1~ki位数 一共有 sum{i*[m^i-m^(i-1)]}位
            long e = (fastPow(m, i) - fastPow(m, i - 1)) * i + preBit;
            if (e >= n) {
                ki = i;
                break;
            }
            preBit = e;
        }
        n -= preBit;//从ki位开始的偏移位数
        long b = n / ki;//n前面有几个ki位数
        int c = (int) (n % ki);//n是ki位数的第几位
        if (c == 0) {//0表示是前一个数的最后一位
            c = ki;
            b--;
        }
        List<Long> x = new ArrayList<>();
        long resNum = fastPow(m, ki - 1) + b;//fastPow(m, ki - 1):ki位数的开始值; b:n前面有几个ki位数
        while (resNum > 0) {//b的部分转为m进制, b是偏移个数,是十进制的
            x.add(resNum % m);
            resNum /= m;
        }
        Long ans = x.get(x.size() - c);//第c位,因为转换进制时是倒序的,所以x.size() - c
        System.out.println(ans);
    }
}
