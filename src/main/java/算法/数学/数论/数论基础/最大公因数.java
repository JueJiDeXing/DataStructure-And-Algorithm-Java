package 算法.数学.数论.数论基础;

public class 最大公因数 {
    /* 证明 gcd(a,b)=gcd(a-b,b)

      设 d = gcd(a,b) , a=md, b=nd

      1. 证明d是a-b与b的公因数
      a-b=(m-n)d   -> d是a-b的因数
      b=nd         -> d是b的因数

      2. 证明d是a-b与b的最大公因数
      假设存在k(k>d)是a-b与b的公因数
      则 k|(a-b)  k|b
      得 k|a
      所以k是a与b的公因数
      由于 k>d 与 d是a,b的最大公因数 矛盾
      故 假设不成立
      即 不存在k>d使得k是a-b与b的最大公因数
      所以d是a-b与b的最大公因数

      由1和2
      d是a和b的最大公因数 && d是a-b和b的最大公因数 ->  gcd(a,b)=gcd(a-b,b)
     */
    /* 证明 gcd(a,b)=gcd(b,a%b)

     设 (k+1)b > a >= kb >=0

     则 gcd(a,b) = gcd(a-b,b) = gcd(a-2b,b) = ... = gcd(a-kb,b)

     由 kb + r = a --> r = a - kb = a % b
     所以 gcd(a,b) = gcd(a-kb,b) = gcd(a%b,b) = gcd(b,a%b)

     // gcd(a,0)=a
     */
    //欧几里得（辗转相除法）
    public static int gcd(int a, int b) {//当上一轮余数为0时(b==0),返回上一轮的除数(a),否则继续迭代
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int gcd2(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
