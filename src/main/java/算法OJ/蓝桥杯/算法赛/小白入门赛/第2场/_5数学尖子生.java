package 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场;

import java.util.Scanner;
/**
 已AC
 */
public class _5数学尖子生 {
    /*
    f(x): 不是 x因子 的 最小正整数
    给定 a 和 n
    求 [1,n] 上 f(x)=a 的x 个数
     */

    /**
     令 g(a) 表示 [1,n]上 [1,a]都是x的因子 的 x数量
     则 ans = g(a-1) - g(a)
     <p>
     对于f(x)=a有:
     (1) x % a != 0
     (2) x % i == 0 && i∈[1,a-1]
     对于(2), 有 x % lcm([1,a-1]) == 0
     <p>
     令p[a]=lcm([1,a])
     在[1,n]上, 如果t能整除p[a], 则[1,a]都是t的因子, 则g(a)的一个值为t
     [1,n]上能整除p[a]的数个数为n/p[a]
     则g(a) = n/p[a]
     <p>
     p[a]=lcm(p[a-1],a)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int a = sc.nextInt();
            long n = sc.nextLong();//别用StreamTokenizer,会损失精度
            if (a == 1) {
                System.out.println(0);
                continue;
            }
            System.out.println(g(a - 1, n) - g(a, n));
        }
    }

    static long[] p = new long[1000010];

    static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static {
        p[1] = 1;
        for (int i = 2; i <= 1000000; i++) {
            p[i] = lcm(p[i - 1], i);
            if (p[i] > 1e16) {
                p[i] = 0;
                break;
            }
        }
    }

    static long g(int a, long n) {
        if (p[a] == 0) return 0;
        return n / p[a];
    }

}
