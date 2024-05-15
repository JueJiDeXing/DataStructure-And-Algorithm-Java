package 算法.数学.数论.模;

import 算法OJ.蓝桥杯.真题卷.第13届.省赛.Java大学A组.B寻找整数;

public class 除数与余数推导被除数 {
    /*
    问题描述:
    某个数x,除以a1,a2,a3...的余数分别是r1,r2,r3....
    求正整数x的最小值
     */

    /**
     性质: 如果 a % b = c, 那么对a加上b的任意倍数,a % b 的结果不变
     <p>
     令 x[i] 为满足前i个约束的解的最小值, X[i] 为满足前i个约束的解的通解 <br>
     只考虑前i个约束的通解: X[i] = x[i] + k1 * lcm(m1,m2,m3,...,mi) <br>
     考虑第i+1个约束: X[i+1] = x[i+1] + k2 * lcm(m1,m2,m3,...,mi+1) <br>
     递推式1: x[i+1] = x[i] + t*lcm(m1,..,mi) && x[i+1] % i == map[i] // t可以直接从0开始枚举<br>
     递推式2: lcm(m1,m2,...mi+1) = lcm( lcm(m1,m2,...,mi), i+1) <br>
     例题: {@link B寻找整数}
     */
    public static void main(String[] args) {
        //map[i]: x mod i的值
        int[] map = new int[]{0, 0, 1, 2, 1, 4, 5, 4, 1, 2, 9, 0, 5, 10, 11, 14, 9, 0, 11, 18, 9, 11, 11, 15, 17, 9, 23, 20, 25, 16, 29, 27, 25, 11, 17, 4, 29, 22, 37, 23, 9, 1, 11, 11, 33, 29, 15, 5, 41, 46};

        long x = 1;//x[i]:满足前i个约束的最小x
        long lcm = 1;//lcm[i]:前i个约束的最小公倍数
        for (int i = 2; i < map.length; i++) {
            while (x % i != map[i]) {
                x += lcm; // x[i] = x[i-1] + t*lcm => x[i] % i == map[i]
            }
            lcm = lcm(lcm, i);
        }
        System.out.println(x);//2022040920220409
    }

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }


}
