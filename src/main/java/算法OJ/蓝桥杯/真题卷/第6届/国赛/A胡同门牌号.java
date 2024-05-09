package 算法OJ.蓝桥杯.真题卷.第6届.国赛;

/**
 已AC
 */
public class A胡同门牌号 {
    /*
    ans = k
    (a+...+a+k) - k =100

    (2a+k)(k+1) - 2k=200
     */
    public static void main(String[] args) {
        for (int k = 1; k < 100; k++) {
            if ((200 + 2 * k) % (k + 1) != 0) continue;
            int a_2 = (200 + 2 * k) / (k + 1) - k;
            if (a_2 <= 0) continue;
            if (a_2 % 2 == 0 && k > a_2 / 2) {
                System.out.println(k);
                break;
            }
        }
    }
}
