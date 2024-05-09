package 算法OJ.蓝桥杯.真题卷.第11届.国赛.Java大学A组;
/**
 已AC
 */
public class A合数个数 {
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 1; i <= 2020; i++) {
            if (isPrime(i)) continue;
            ans++;
        }
        System.out.println(ans);//1713
    }

    static boolean isPrime(int x) {
        int s = (int) Math.sqrt(x);
        for (int i = 2; i <= s; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}
