package 算法OJ.蓝桥杯.题单.第15届第2期模拟;
/**
 已AC
 */
public class F数位和相等 {
    /*
    转二进制和转八进制的数位和相等,求第23个这样的正整数
    1 8 9 64...
     */
    public static void main(String[] args) {
        int count = 0, num = 0;
        while (count != 23) {
            num++;
            if (check(num)) count++;
        }
        System.out.println(num);
    }

    static boolean check(int num) {
        int sum1 = get(num, 2);
        int sum2 = get(num, 8);
        return sum1 == sum2;
    }

    static int get(int n, int r) {
        int sum = 0;
        while (n > 0) {
            sum += n % r;
            n /= r;
        }
        return sum;
    }
}
