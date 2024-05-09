package 算法.数学.数论.平方;

public class 完全平方数判定 {
    /*
    对于完全平方数n^2
    (1) 平方数的个位数字:0,1,4,5,6,9
    (2) (2k)^2 = 4k^2 是4的倍数
        (2k+1)^2 = 4k^2+4k+1 是4的倍数+1 (这里还可以进一步推导 4k^2+4k+1=8k(k+1)/2+1 是8的倍数)
        所以有:
        x % 4 = 0 => x=(2k)^2
        x % 4 = 1 => x=(2k+1)^2
        x % 4 = 2或3 => x不是完全平方数
     */
    public static void main(String[] args) {
        //测试
        for (int i = 1; i <= 100; i++) {
            boolean perfectSquare = isPerfectSquare(i);
            if (perfectSquare) {
                System.out.println(i);
            }
        }


    }

    public static boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int L = num % 10;
        if (L == 2 || L == 3 || L == 7 || L == 8) return false;
        L = num % 4;
        if (L == 2 || L == 3) return false;
        //判定不了,还是得去开方
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }
}
