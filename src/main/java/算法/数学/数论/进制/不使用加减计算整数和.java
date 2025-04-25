package 算法.数学.数论.进制;

public class 不使用加减计算整数和 {
    /*
    异或:不进位加法

     */
    public int getSum(int a, int b) {
        int carry;
        while (b != 0) {
            carry = (a & b) << 1;// ab相加的进位
            a = a ^ b; // ab相加的不进位
            b = carry; // ab相加 = ab相加的不进位 + ab相加的进位 = newA + newB
            // 当carry=0时, 不进位和进位完全错开,此时做异或就是a+b
        }
        return a;
    } 
}
