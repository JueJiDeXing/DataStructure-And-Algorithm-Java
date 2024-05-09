package 算法.数学.数论.阶乘;

public class 阶乘 {//factorial

    public static void main(String[] args) {
        System.out.println( fact2(20));
    }


    public static int fact1(int n) {
        if (n < 0 || n > 12) {
            throw new RuntimeException();
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * fact1(n - 1);
    }

    public static long fact2(int n) {
        if(n>20){
            throw new RuntimeException("太大了,long也会溢出的");
        }
        long dp = 1;
        for (int i = 2; i <= n; i++) {
            dp *= i;
        }
        return dp;
    }
    /*
    Γ(s) =∫ 0->+∞ t^(s-1) e^(-t) dt
    float n>1: n! = n(n-1)(n-2)...(1+n-int(n)) * (n-int(n))!
     */

}
