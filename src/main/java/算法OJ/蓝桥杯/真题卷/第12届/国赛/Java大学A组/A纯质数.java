package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

/**
 已AC
 */
public class A纯质数 {
    //纯质数:每位数都由质数组成的质数
    //求[1,20210605]中纯质数个数
public static void main(String[] args) {
    int count = 0;
    for (int i = 2; i <= 20210605; i++) {
        if (is(i)) count++;
    }
    System.out.println(count);//1903
}

static boolean is(int n) {
    int temp = n;
    while (n > 0) {
        if (!isDigitPrime(n % 10)) return false;
        n /= 10;
    }
    return isPrime(temp);
}

private static boolean isDigitPrime(int t) {
    return t == 2 || t == 3 || t == 5 || t == 7;
}

static boolean isPrime(int n) {
    int s = (int) Math.sqrt(n);
    for (int i = 2; i <= s; i++) {
        if (n % i == 0) return false;
    }
    return true;
}
}
