package 算法OJ.牛客.五一集训1;

import java.util.HashSet;
import java.util.Scanner;

public class D韩信点兵 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long m = sc.nextLong();

        long x = 1;//x[i]:满足前i个约束的最小x
        long lcm = 1;//lcm[i]:前i个约束的最小公倍数
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            set.clear();
            while (x % a != b) {
                if (!set.add((int) (x % a))) {
                    System.out.println("he was definitely lying");
                    return;
                }
                x += lcm;// x[i] = x[i-1] + t*lcm => x[i] % i == map[i]
            }
            lcm = lcm(lcm, a);
        }
        if (x > m) {
            System.out.println("he was probably lying");
            return;
        }
        System.out.println(x);//2022040920220409
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
