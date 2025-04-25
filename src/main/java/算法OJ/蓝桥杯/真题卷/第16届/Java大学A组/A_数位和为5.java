package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

/**
 AC
 5分
 */
public class A_数位和为5 {
    /*
    求1~202504中有多少个数数位和为5
     */
    public static void main(String[] args) throws Exception {
        int ans = 0;
        for (int i = 1; i <= 202504; i++) {
            if (getCnt(i) % 5 == 0) ans++;
        }
        System.out.println(ans);//40500
    }

    static int getCnt(int n) {
        int c = 0;
        while (n > 0) {
            c += n % 10;
            n /= 10;
        }
        return c;
    }

}
