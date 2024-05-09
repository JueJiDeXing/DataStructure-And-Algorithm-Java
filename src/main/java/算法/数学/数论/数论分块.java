package 算法.数学.数论;

import 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组.H因数平方和;

public class 数论分块 {
    /**
     <pre>
     数论分块可以快速计算一些含有除法向下取整的和式
     即形如 Sum(  F(i)*G(floor(n/i)) )  的和式

     当可以在 O(1) 内计算 F(r)-F(l) 或 已经预处理出 F 的前缀和时，
     数论分块就可以在 O(logn) 的时间内计算上述和式的值。

     它主要利用了富比尼定理, 将  floor(n/i) 相同的数打包同时计算。

     结论:
     对于常数n, 使 floor(n/l) = floor(n/r) 的最大r为floor( n/floor(n/l) )
     即 值floor(n/l)所在块的右端点r=floor( n/floor(n/l) )

     例如:n=12                 l1   r1   l2                  r2
     i     1   2   3   4   5   6    7    8    9   10   11   12
     n/i   12  6   4   3   2   2    1    1    1    1    1    1
     </pre>
     例题:{@link H因数平方和}
     */
    public static void main(String[] args) {

    }
}
