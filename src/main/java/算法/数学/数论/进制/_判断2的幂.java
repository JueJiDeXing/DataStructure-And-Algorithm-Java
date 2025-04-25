package 算法.数学.数论.进制;

public class _判断2的幂 {
    /*
    判断一个数是否为2的幂次
     */
    /*
    若x = 2^k = 0b 1000...0
    x - 1  = 0b 0111...1
    则 x & (x-1) == 0


    若x != 2^k
    x = 0b 1???1000...0
    x - 1 = 0b 1???0111...1

    x & (x-1) != 0

     */
    static boolean isPow2(int x) {
        return (x & (x - 1)) == 0;
    }

}
