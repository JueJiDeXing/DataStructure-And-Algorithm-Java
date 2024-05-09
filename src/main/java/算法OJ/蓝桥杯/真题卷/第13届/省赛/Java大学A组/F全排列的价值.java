package 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class F全排列的价值 {
    static int mod = 998244353;

    /**
     sum(n) = n * (n-1) * n! / 4
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
     <p>
     法1: 不乘就是除了
     ans = sum(n) % mod
     = n * (n-1) / 2 % mod * mul(3,n) % mod
     法2: 模上的除法提取到模外
     ans = n * (n-1) * n! / 4 % mod
     = n * (n-1) * n! % (4mod) / 4
     = n * (n-1) % (4mod) * n! % (4mod) / 4
     法3: 乘法逆元,乘法代替除法
     ans = n * (n-1) * n! / 4 % mod
     = n * (n-1) * n! * inv(4) % mod // inv(4)表示在mod下的4的乘法逆元
     = n * (n-1) % mod  * n! % mod  * inv(4) % mod
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long ans = n * (n - 1) / 2 % mod;
        for (int i = 3; i <= n; i++) {//2不乘,相当于结果除以2
            ans = (ans * i) % mod;
        }
        System.out.println(ans);
    }


}
