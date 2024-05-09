package 算法OJ.蓝桥杯.其他;

import java.util.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/**
 已AC
 */
public class 最大比例 {
    /*
    缺项的乱序正整数等比数列
    求可能的最大等比
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Set<Long> X = new HashSet<>();
        for (int i = 0; i < n; i++) X.add(sc.nextLong());
        List<Long> arr = new ArrayList<>(X);
        arr.sort((a, b) -> a > b ? 1 : -1);
        List<long[]> pairs = new ArrayList<>();
        for (int i = 0; i < arr.size() - 1; i++) {
            long a = arr.get(i), b = arr.get(i + 1);
            long t = gcd(a, b);
            pairs.add(new long[]{a / t, b / t});
        }
        int minId = 0;
        for (int i = 0; i < pairs.size(); i++) {
            long[] pair = pairs.get(i);
            long[] minPair = pairs.get(minId);
            if (compare(minPair, pair)) {
                minId = i;
            }
        }
        long[] min = pairs.get(minId);
        System.out.println(min[1] + "/" + min[0]);
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static boolean compare(long[] a, long[] b) {
        return a[1] * b[0] > b[1] * a[0];
    }
}
