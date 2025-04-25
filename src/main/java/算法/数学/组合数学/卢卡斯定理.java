package 算法.数学.组合数学;

import 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组.J组合数问题;

public class 卢卡斯定理 {
    /*
     C(n,m) % p = C( n/p, m/p ) * C( n%p, m%p ) % p

     令 n的p进制表示为 a0,a1,a2,...
        m的p进制表示为 b0,b1,b2,...
     则 C(n,m) % p 最后可以表示为 MUL( C(ai,bi) ) % p

     */

    /*
     给定n,m,k
     求满足 C(i,j) % k == 0 的数对(i,j)的个数<br>
     其中 1 <= i <= n , 0 <= j <= m<br>
     k是质数<br>
     */

    /**
     {@link J组合数问题}<br>
     */
    public static void main(String[] args) {

    }
}
