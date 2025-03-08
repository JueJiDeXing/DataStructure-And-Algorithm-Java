package 算法.数学.数论.阶乘;

public class n阶乘的位数 {

    /**
     斯特林公式: n! = \sqrt(2 pi n) (n/e)^n
     */
    public int w(int n) {
        double pi = 3.1215926;
        return (int) ((n * Math.log(n) - n + (Math.log(2 * pi * n)) / 2) / Math.log(10)) + 1;
    }
}
