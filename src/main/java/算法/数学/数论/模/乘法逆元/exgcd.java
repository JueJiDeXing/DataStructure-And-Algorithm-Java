package 算法.数学.数论.模.乘法逆元;

public class exgcd {
    /*
    裴蜀定理:
    1. 对于任意正整数a,b,对于任意正整数x,y 都有 ax+by % gcd(a,b) = 0
    2. 存在整数x,y, 使ax+by=gcd(a,b)成立.
    3. a,b互质 <=> gcd(a,b)=1 <=> 存在整数x,y,使ax+by=1.

    ax+by=c有解 <==> gcd(a,b)|c
    ax % b = c  <==> ax+by=c
    */

    /*
     exgcd:
     对于不定方程 ax + by = gcd(a,b) = d
     ∵ gcd(a,b) = gcd(b,a%b)
     ∴ bx1 + (a%b)y1 = d
     又∵ a%b = a - (a/b)b
     ∴ bx1 + (a-(a/b)b)y1 = d
        bx1 + ay - b(a/b)y1 = d
        ay1 + b(x1-(a/b)y1) = d
      与ax+by=d对比得: x=y1, y=x1-(a/b)y1
      这样可以递推求解ax+by = gcd(a,b)的解
     */


    static int x, y;

    /**
     <h1>求解 ax + by = gcd(a,b)</h1>
     解由static变量存储

     @param a,b 方程参数
     */
    private void doExgcd(int a, int b) {
        if (b == 0) {
            x = 1;
            y = 0;
            return;
        }
        doExgcd(b, a % b);
        int x1 = x, y1 = y;
        x = y1;
        y = x1 - (a / b) * y1;
    }

    /*
    <h1>exgcd求逆元</h1>
    ax % b = 1 则 x为模p下a的逆元
    由裴蜀定理得 ax + by = 1
    使用exgcd(a,b)求得x,y, 则 x为模b下a的逆元
     */
    public int inv(int a, int b) {
        x = 0;
        y = 0;
        doExgcd(a, b);
        return (x + b) % b;
    }
}
