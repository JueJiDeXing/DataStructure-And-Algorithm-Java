package 算法.数学.数论.莫比乌斯;

public class 莫比乌斯函数 {
/*
莫比乌斯函数:
        1       n=1
f(n) =  0       n有平方因子
        (-1)^k  k为n的不同质因子个数
解释: n为1时取1, 当n不为1时, n可以质因子分解,如果某种质因子个数超过1个则为0,否则函数值为-1的质因子个数

结论:
(1) sum{ f(d) | n%d=0 } 仅当n为1时取1,其余情况为0
(2) 反演结论: [gcd(i,j)=1] = sum{ f(d) | gcd(i,j)%d=0 }
            当gcd(i,j)=1时为1,其余为0
 */

}
