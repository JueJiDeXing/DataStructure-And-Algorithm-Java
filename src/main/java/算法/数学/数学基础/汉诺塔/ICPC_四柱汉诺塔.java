package 算法.数学.数学基础.汉诺塔;

import java.math.BigInteger;

/**
 {@link  算法OJ.ICPC.江西2021.F汉诺塔}
 */
public class ICPC_四柱汉诺塔 {
    /*
    汉诺塔,但是4根柱子,求n个盘的最少操作次数
     */
    /*
    (1) 选择A上面的x块, 借助C和D,移动到B
    (2) 选择A上面的n-x-1块,借助D,移动到C
    (3) 选择A的最后一块, 移动到D
    (4) 将C上的n-x-1块,借助A,移动到B
    (5) 将B上的x块,借助A和C,移动到D
    定义: f(n)为四柱情况下的n块移动最少次数, g(n)为三柱情况下的n块移动最少次数
    则有 f(n) = 2 * f(x) + 2 * g(n-x-1) + 1

    对于三柱情况:
    (1) 选择A上的n-1块, 借助C, 移动到B
    (2) 将A的第n块, 移动到C
    (3) 将B还是那个的n-1块, 借助A, 移动到C
    g(n) = 2 * g(n-1) + 1
    可得g的通项公式 g(n) = 2^n - 1

    则f公式可化为 f(n) = 2 * f(x) + 2^(n-x) - 1 , 由于是最少操作次数, 需要取min

    这是一个n^2的做法

    打印出前面一些项:
        0  1   3   5   9   13   17  25   33  41   49    65 ...
         +1  +2  +2  +4  +4  +4   +8  +8   +8   +8   +16
    可得规律: 从0开始, 加1,1次 、加2,2次 、加4,3次 、 加8,4次 、 加16,5次...
     */

    static BigInteger[] ans = new BigInteger[10001];

    static {
        ans[0] = BigInteger.ZERO;
        int flag = 1, cnt = 0;
        BigInteger B2 = BigInteger.valueOf(2);
        BigInteger f = BigInteger.ONE;
        for (int i = 1; i <= 10000; i++) {
            ans[i] = ans[i - 1].add(f);
            cnt++;
            if (cnt == flag) {
                flag++;
                cnt = 0;
                f = f.multiply(B2);
            }
        }
    }

}
