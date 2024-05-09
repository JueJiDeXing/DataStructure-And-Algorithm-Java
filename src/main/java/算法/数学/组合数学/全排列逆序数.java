package 算法.数学.组合数学;

import 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组.F全排列的价值;

public class 全排列逆序数 {

    /**
     sum(n) = n*(n-1)*n! / 4
     证明:
     对于某个排列 a1 a2 a3 ... ak ... an 和它的反排列 an, an-1,...ak,...a2,a1<br>
     现在考虑这两次排列ak的总价值,设ak=v<br>
     设 a[1,k-1]中 小于v的有x个数, a[k+1, n] 小于v的有y个数<br>
     因为序列由[1,n]组成,那么总共小于ak=v的数就有v-1个<br>
     所以x+y=v-1 即 正排列+反排列ak的贡献为v-1<br>
     全体元素的贡献为 Sum(ak - 1) ,等于 [0,n-1]的和, n(n-1)/2<br>
     也就是说一对正反排列的价值为n(n-1)/2<br>
     排列数A(n,n)=n!, 总共有 n!/2对正反排列<br>
     所以价值为 n(n-1)/2 * n!/2<br>
     {@link F全排列的价值}
     */
    public static void main(String[] args) {

    }
}
