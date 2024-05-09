package 算法.数学.数论.进制;

public class 不使用加减计算整数和 {
    /*
    异或:不进位加法

     */
    public int getSum(int a, int b) {
        int carry;
        while (b != 0) {
            carry = (a & b) << 1;//都为1则说明要进位
            a = a ^ b;//存储不需要进位的和
            b = carry;//下次循环计算:未进位的结果+进位值
        }
        return a;
    }
}
