package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第10场;

import java.util.*;
/**
 已AC
 */
public class A加减法 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int A = sc.nextInt(), B = sc.nextInt();
        long ans = sc.nextLong();
        Queue<Long> p = new PriorityQueue<>(Long::compare),//正数, 小->大
                q = new PriorityQueue<>((a, b) -> Long.compare(b, a));//负数,大->小
        for (int i = 0; i < n - 1; i++) {
            long x = sc.nextLong();
            if (x >= 0) {
                p.add(x);
            } else {
                q.add(x);
            }
            ans += Math.abs(x);
        }
        int cntP = p.size(), cntQ = q.size();
        if (cntP > A) {
            for (int i = 0; i < cntP - A; i++) {
                ans -= p.poll() * 2;
            }
        } else {
            for (int i = 0; i < cntQ - B; i++) {
                ans -= -q.poll() * 2;
            }
        }
        System.out.println(ans);

    }


}
