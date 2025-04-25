package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

/**
 赛时代码错误,赛后AC
 */
public class B_含有2025 {
    /*
    求1~20250412中有多少个数 至少含有1个0,2个2,1个5
     */
    public static void main(String[] args) throws Exception {
        int ans = 0;
        for (int i = 1; i <= 20250412; i++) {
            if (have(i)) ans++;
        }
        System.out.println(ans);//506754
    }

    static boolean have(int n) {
        int c0 = 0, c2 = 0, c5 = 0;
        while (n > 0) {
            int t = n % 10;
            if (t == 0) c0++;
            else if(t == 2) c2++;
            else if(t == 5) c5++;
            n /= 10;
        }
        return c0>=1&&c2>=2&&c5>=1;
    }

}
