package 算法.数学.组合数学;

import 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组.J组合数问题;

public class 卢卡斯定理 {
    /*
     C(n,m) % p = C( n/p, m/p ) * C( n%p, m%p ) % p
     其最后可以表示为 MUL( C(ai,bi) ) % p
     其中 ai按序拼接为n的p进制表示 , bi同理
     */

    /**
     求满足 C(i,j) % k == 0 的数对(i,j)的个数<br>
     其中 1 <= i <= n , 0 <= j <= m<br>
     k是质数<br>
     {@link J组合数问题}<br>
     */
    public static void main(String[] args) {

    }
}
