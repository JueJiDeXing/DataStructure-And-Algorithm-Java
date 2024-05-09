package 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场;

import java.util.*;

/**
 已AC
 */
public class _4取余 {
    /*
    求 a%b的结果 在[S,T]的范围内的数对(a,b)个数
    且 a<=A, b<=B
     */

    /**
     令f(A,B,C)表示 a%b <= C的数对(a,b)个数, 且 a<=A, b<=B
     则 ans = f(A,B,T) - f(A,B,S-1)
     <p>
     a%b <= C
     可以分两种情况
     (1) b<=C
     由a%b < b 得 a%b < C
     则a取值任意, 数量为A
     (2) b>C
     对于a∈[kb,kb+C] 有 a%b < C
     关于k的取值: a<=A -> k_max = A/b -> 0 <= k < A/b
     所以:
     count1 = (A/b) * (C+1) 完整段
     count2 = (A%b,C) 最后一段
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt(), B = sc.nextInt(), S = sc.nextInt(), T = sc.nextInt();
        long ans = f(A, B, T) - f(A, B, S - 1);
        System.out.println(ans);

    }

    /**
     count{ a%b <= C | 1<=a<=A && 1<=b<=B }
     */
    static long f(int A, int B, int C) {
        if (C < 0) return 0;
        long ans = 0;
        for (int b = 1; b <= B; b++) {
            if (b <= C) {// a%b < b && b<=C => a%b < C 则a取值任意
                ans += A;
            } else {
                // a∈[kb,kb+C] -> a%b < C
                // a<=A -> k_max = A/b -> 0 <= k < A/b
                // count1 = (A/b) * (C+1) 完整段
                // count2 = (A%b,C) 最后一段
                ans += (long) (A / b) * (C + 1) + Math.min(A % b, C);
            }
        }
        return ans;
    }
}
