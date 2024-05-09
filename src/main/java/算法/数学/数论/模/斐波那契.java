package 算法.数学.数论.模;

import java.math.BigInteger;
import java.util.*;

public class 斐波那契 {
    /*
    斐波那契数列性质:
    (1) f(n) = f(n-1) + f(n-2)
    (2) f(n+m) = f(n+1)f(m) + f(n)f(m-1)
     从f(n)往后偏移m项, 里面有 m个f(n+1)项 和 m-1个f(n)项
    (3) f(n)^2 = f(n-1)f(n+1) + (-1)^(n+1)
    (4) n>=1&&r>=2时,有 f(n)f(n+r-1) - f(n+1)f(n+r-2) = (-1)^(n+1)f(r-2)
    */

    /*
    例题1:
    求 f(n) % f(m)
    m较小,n非常大
     */


    /*
    f(n) % f(m)
    = f(n-m+m) % f(m)
    = ( f(n-m+1)f(m) + f(n-m)f(m-1) ) % f(m)
    = f(n-m)*f(m-1) % f(m)
    = ...
    = f(n%m)*f(m-1)^(n/m) % f(m)

    对于f(m-1)^2 % f(m)
    = (f(m-2)f(m)+(-1)^m) % f(m)
    = (-1)^m % f(m)
     */
    final static int N = 1005;
    static BigInteger[] F = new BigInteger[N];

    static void Init() {
        F[0] = BigInteger.ZERO;
        F[1] = BigInteger.ONE;
        for (int i = 2; i < N; i++) F[i] = F[i - 1].add(F[i - 2]);
    }

    public static void solve1() {
        Init();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- != 0) {
            long n = sc.nextLong();
            int m = sc.nextInt();
            BigInteger ans = F[(int) (n % m)];//f(n%m)*f(m-1)^(n/m) % f(m)
            long y = n / m;
            if (isOdd(y)) ans = ans.multiply(F[m - 1]);// n/m是奇数,拆出一项单独乘
            y >>= 1;
            if (isOdd(m) && isOdd(y)) {
                // f(m-1)^2y % f(m) = ( (-1)^m )^y % f(m)
                // m是奇数且y也是奇数时乘-1
                // -ans % f = (ans*f-ans) % f = ans * (f-1) % f
                ans = ans.multiply(F[m].subtract(BigInteger.ONE));
            }
            System.out.println(ans.mod(F[m]));
        }
    }

    static boolean isOdd(long num) {
        return (num & 1) != 1;
    }
    //-------------------------------------------------------------------------------------------------------------
    /*
    例题2:
    求 sum{f(1~n)} % f(m) % p
    n,m都很大
     */

    /*
    由 f(n) = f(n+1) - f(n-1)
    sum{f(1~n)}
    = f(1) + f(3)-f(1) + f(4)-f(2) + ... + f(n+1) - f(n-1)
    = f(n) + f(n+1) - f(2)
    = f(n+2) - 1

    问题模型是 f(n) % f(m) % p

    化简:
    f(n) % f(m)  = f(n%m)*f(m-1)^(n/m) % f(m)
    f(m-1)^2 % f(m)  = (-1)^m % f(m)
    (1) m为偶:
        n/m为偶: f(n)%f(m) = f(n%m)
        n/m为奇: f(n)%f(m) = f(n%m)*f(m-1)
    (2) m为奇:
        n/m为偶,n/2m为偶: f(n)%f(m) = f(n%m)
        n/m为偶,n/2m为偶: f(n)%f(m) = f(m) - f(n%m)
        n/m为奇,n/2m为偶: f(n)%f(m) = f(n%m)f(m-1) % f(m)
        n/m为奇,n/2m为奇: f(n)%f(m) = (f(m) - f(n%m)f(m-1)) % f(m)

    目前难点在于f(n%m)f(m-1) % f(m)的计算
    由性质: n>=1&&r>=2时,有 f(n)f(n+r-1) - f(n+1)f(n+r-2) = (-1)^(n+1)f(r-2)
    令k=n%m,....TODO 服了
     */
}
