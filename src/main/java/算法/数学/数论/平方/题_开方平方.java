package 算法.数学.数论.平方;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 ???
 */
public class 题_开方平方 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double ac = sc.nextDouble();
        int max = 0;
        for (int x = 2; x <= n; x++) {
            int k = sqrtK(x, ac);
            System.out.println(x + "   " + k);
            max = Math.max(max, k);
        }
        System.out.println(max);
    }

    /**
     对x进行K次开方,再平方K次,结果与原值相差大于ac
     */
    static int sqrtK(int x, double ac) {
        //预开方
        double[] sqrtMap = new double[101];
        sqrtMap[0] = x;
        for (int i = 1; i <= 100; i++) {
            sqrtMap[i] = Math.sqrt(sqrtMap[i - 1]);
        }
        //二分
        int left = 0, right = 100;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >>> 1;//开方再平方mid次
            double rX = sqrtMap[mid];
            for (int i = 0; i < mid; i++) {
                rX = Math.pow(rX, 2);
            }

            if (Math.abs(rX - x) > ac) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    /**
     对x进行K次开方,再平方K次,结果与原值相差大于ac
     */
    static int sqrtK2(int x, double ac) {
        //预开方
        BigDecimal[] sqrtMap = new BigDecimal[101];
        sqrtMap[0] = BigDecimal.valueOf(x);
        for (int i = 1; i <= 100; i++) {
            sqrtMap[i] = sqrtMap[i - 1].sqrt(MathContext.DECIMAL64);
        }
        //二分
        int left = 0, right = 100;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >>> 1;//开方再平方mid次
            BigDecimal rX = sqrtMap[mid];
            for (int i = 0; i < mid; i++) {
                rX = rX.pow(2);
            }
            BigDecimal compare = rX.add(BigDecimal.valueOf(-x)).abs().add(BigDecimal.valueOf(ac));
            if (compare.compareTo(BigDecimal.ZERO) > 0) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


}
