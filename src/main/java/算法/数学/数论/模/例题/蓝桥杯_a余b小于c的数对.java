package 算法.数学.数论.模.例题;

import 算法OJ.蓝桥杯.算法赛.小白入门赛.第2场._4取余;

public class 蓝桥杯_a余b小于c的数对 {
    /**
     count{ a%b <= C | 1<=a<=A && 1<=b<=B }<br>
     {@link _4取余}
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
