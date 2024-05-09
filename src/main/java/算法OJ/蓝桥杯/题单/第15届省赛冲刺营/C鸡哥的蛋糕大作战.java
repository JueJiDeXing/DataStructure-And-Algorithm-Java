package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.Scanner;
/**
 已AC
 */
public class C鸡哥的蛋糕大作战 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt(), b = sc.nextInt();
        int maxCnt = 0, maxNum = 0;
        for (int i = a; i <= b; i++) {
            int cnt = 0;
            int t = i;
            while (t > 0) {
                int tt = t % 10;
                if (tt == 0 || tt == 6 || tt == 9) {
                    cnt++;
                } else if (tt == 8) {
                    cnt += 2;
                }
                t /= 10;
            }
            if (cnt > maxCnt) {
                maxNum = i;
                maxCnt = cnt;
            }
        }
        System.out.println(maxNum);
    }


}
