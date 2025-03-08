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
        while (n > 0) {
            if ((n & 1) == 1) res = (res * a) % m;
            a = (a * a) % m;
            n >>= 1;
        }
        return res;
    }
    /*
    证明:
    构造集合S={a,2a,3a,...(p-1)a}

    ① S中的任一元素模p不为0
    因为p是质数,ia不是p的倍数
    ② S中的元素模p的值不重复
    设 ia % p = ja % p
    则 (i-j)a % p = 0
    因为 a%p!=0
    则 i=j与假设矛盾
    ③ S中的元素模p的值构成1~p-1的排列


    a * 2a * ... * (p-1)a %p = a%p * 2a%p * ... * (p-1)a%p %p
    左式 = a^(p-1) * (p-1)! %p
    右式基于③ = (p-1)! %p
    则  a^(p-1) * (p-1)! %p = (p-1)! %p
    因为 (p-1)!与p互质  ==> 两边同乘(p-1)!对p的逆元
    得: a^(p-1) % p = 1

     */
}
