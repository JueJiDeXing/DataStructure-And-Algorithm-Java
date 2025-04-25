package 算法.数学.数论.模;

public class 费马平方和 {
/*

若 c可以表示为两个数的平方和 c = a^2 + b^2
则 对于质数p=4k+3, c的质因子分解中p的幂次一定为偶数

若 对于质数p=4k+3, c的质因子分解中p的幂次均为偶数
则 c可以表示为两个数的平方和 c = a^2 + b^2
 */

    public boolean judgeSquareSum(int c) {
        for (int base = 2; base * base <= c; base++) {
            if (c % base != 0) {
                continue;
            }

            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }

            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }
        return c % 4 != 3;
    }
}
