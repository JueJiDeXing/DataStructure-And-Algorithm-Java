package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.math.BigInteger;
import java.util.Scanner;

/**
 已AC
 */
public class G六六大顺 {
    /*
    A=(a1,...an)  ai由i个6组成,例如:a1=6 a2=66 a3=666
    B=ai^2
    求数组B的前n项和,1<=n<=10^7
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(Sum2(n));
    }

    /**
     an = an-1 * 10 + 6
     an+2/3=10(an-1 + 2/3)
     令cn=an+2/3
     则cn=20/3 * 10^(n-1)
     所以an=2/3 * (10^n -1)
     bn=an^2=4/9 * (10^n-1)^2=4/9 * (100^n - 2* 10^n +1)
     所以Sn=[400(100^n -1) - 880(10^n -1) + 396n]/891
     测试通过:6/10
     */
    private static BigInteger Sum(int n) {
        BigInteger first = BigInteger.valueOf(400).multiply(BigInteger.TEN.pow(2 * n).subtract(BigInteger.ONE));
        BigInteger second = BigInteger.valueOf(880).multiply(BigInteger.TEN.pow(n).subtract(BigInteger.ONE));
        BigInteger third = BigInteger.valueOf(396).multiply(BigInteger.valueOf(n));
        return first.subtract(second).add(third)
                .divide(BigInteger.valueOf(891));
    }

    /*
   bn=4/9 * (100^n - 2* 10^n +1)
   则Sn=4/9 * (An-Bn+n) = (4*An - 4*Bn + 4n) / 9
   其中An=Sum(100^n),Bn=Sum(2*10^n)
   An的数字形式是101010...10100,An共2n+1位,其中奇数位除最低位都为1,偶数位都为0
   构造4*An可通过n个"40"+1个"0"
   Bn的数字形式是222...220,Bn共n+1位,前n位都为2,最低位为0
   构造4*Bn可通过n个"8"+1个"0"
   测试通过:5/10
     */
    private static BigInteger Sum2(int n) {
        return new BigInteger("40".repeat(n) + "0")
                .subtract(new BigInteger("8".repeat(n) + "0"))
                .add(BigInteger.valueOf(4L * n))
                .divide(BigInteger.valueOf(9));
    }

    /**
     bi = i-1个4 接 1个3 接i-1个5 接1个6
     i:2n-1      210
     `````````````36   b1
     ```````````4356   b2
     `````````443556   b3
     ```````44435556   .
     ``````.........   .
     ```4...435...56   bn
     第i位的3个数 = i <= n ? 1 : 0   // i>0,第0位单独考虑
     第i位的4个数 = Math.min((i - 1) / 2, all) // 4的个数规律:0 0 1 1 2 2 3 3 4 4 ...,不超过all(all表示总共有数字的位置数)
     第i位的5个数 = all-3个数-4个数
     测试通过:10/10
     */
    private static String Sum3(int n) {
        StringBuilder answer = new StringBuilder();
        int num3, num4, num5;
        int current = 6 * n;//当前位和,初始为个位之和n个6
        answer.append(current % 10);
        int remainder = current / 10;
        for (int k = 1; k < 2 * n; k++) {//从低位第二位开始计算该位和
            int all = n - k / 2;//总共有数字的位置
            num3 = k <= n ? 1 : 0;//bk = k-1个4 接 1个3 接k-1个5 接1个6,所以最低位为6,然后向高位是n-1个5,然后是3在第n+1位,因为i是从第二位开始的,所以i<=n时会有一项有1个
            num4 = Math.min((k - 1) / 2, all);
            num5 = all - num3 - num4;
            current = 3 * num3 + 4 * num4 + 5 * num5 + remainder;
            answer.append(current % 10);
            remainder = current / 10;
        }
        while (remainder > 0) {
            answer.append(remainder % 10);
            remainder /= 10;
        }
        return answer.reverse().toString();
    }
}
