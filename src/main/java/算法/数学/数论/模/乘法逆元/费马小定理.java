package 算法.数学.数论.模.乘法逆元;

public class 费马小定理 {
    /*
    费马小定理:
     若p为质数,且a%p!=0 则a^(p-1) mod p = 1

     推论:
     a * a^(p-2) mod p = 1
     x = a^(p-2) 为a在模p下的乘法逆元

     注意: p需要是质数,且a不是p的倍数才能使用费马小定理
     */
    public int inv(int a, int m) {
        return pow(a, m - 2, m);
    }

    public int pow(int a, int n, int m) {
        int res = 1;
        while (n != 0) {
            if ((n & 1) == 1) res = (res * a) % m;
            a = (a * a) % m;
            n >>= 1;
        }
        return res;
    }
}
