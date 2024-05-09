package 算法OJ.蓝桥杯.算法赛.算法双周赛.第3场;

import java.util.*;
/**
 已AC
 */
public class _6鲜花之海 {
    /*
    n^2朵鲜花,排成一排,每朵花有唯一编号(a,b) 1<=a,b<=N
     假设第X朵花的编号为(Xa,Xb),第Y朵花的编号为(Ya,Yb)
     规则如下:
     如果 Xa+Xb < Ya+Yb 则X应该在前面
     如果 相等 则a更小的在前面

     求第k朵鲜花的编号
     */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            long n = sc.nextLong(), k = sc.nextLong();
            if (k > (n + 1) * n / 2) {
                k = n * n - k + 1;//倒数第k个
                long t = (long) Math.sqrt(2 * k);
                k -= t * (t - 1) / 2;
                if (k > t) {
                    k -= t;
                    t++;
                }
                System.out.println((n - k + 1) + " " + (n - t + k));
            } else {
                long t = (long) Math.sqrt(2 * k);//k在第几行
                k -= t * (t - 1) / 2;
                if (k > t) {
                    k -= t;
                    t++;
                }
                System.out.println(k + " " + (t + 1 - k));
            }
        }
    }

}
