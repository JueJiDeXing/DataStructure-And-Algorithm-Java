package 算法OJ.蓝桥杯.算法赛.算法双周赛.第2场;

import java.util.Scanner;

/**
 已AC
 */
public class _2铺地板 {
    /*
    大小2*3的地板,需要铺n*m的大小,问能否铺满
     */

    /**
     问题拓展 a*b 的地板 能否铺满 n*m
     前提: (n*m) % (a*b) == 0 是铺满地板的必要条件
     (1) n%a==0 && m%b==0:
     a边平行n,b边平行m即可贴满
     (2) n%b==0 && m%a==0:
     同(1)
     (3) n%a==0 && n%b==0:
     对于m边:
     由定理, a和b的非负整数系数线性组合, ta+kb (t>=0,k>=0)
     仅有{x | x==ab-a-b || x < min(a,b) }不能被组合
     所以 m = ta + kb
     那么, 上面t层用a边平行m,下面k层用b边平行m
     而 n%a==0 && n%b==0 也是贴满的
     示例:
     m=7时 m=2*2+1*3,上面两层用2
     */
    public static void main(String[] aaaaa) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt(), m = sc.nextInt();
            if (n == 1 || m == 1) {
                System.out.println("NO");
                continue;
            }
            if (n * m % 6 != 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }

    }
}
