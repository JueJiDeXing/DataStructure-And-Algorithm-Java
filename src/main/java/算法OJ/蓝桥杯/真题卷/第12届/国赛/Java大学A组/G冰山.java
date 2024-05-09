package 算法OJ.蓝桥杯.真题卷.第12届.国赛.Java大学A组;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 测试通过:7/10 3TLE
 */
public class G冰山 {
    static BigInteger zero = BigInteger.ZERO, one = BigInteger.ONE;
    static BigInteger mod = new BigInteger("998244353");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 初始冰山数量
        int m = scanner.nextInt(); // 观察的天数
        int k = scanner.nextInt(); // 冰山的最大限制

        //  冰山体积 -> 对应的体积的冰山数量
        Map<Integer, BigInteger> VToCount = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            VToCount.put(a, VToCount.getOrDefault(a, zero).add(one));
        }

        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt(), y = scanner.nextInt(); // 每天冰山的变化量x每天漂来的冰山体积y
            VToCount = changeV(VToCount, x, y, k);//模拟操作
            BigInteger sum = new BigInteger("0");
            // 求每一天的体积之和
            Set<Integer> keys = VToCount.keySet();
            for (int j : keys) {
                sum = sum.add(BigInteger.valueOf(j).multiply(VToCount.get(j)));//v*count
                sum = sum.divideAndRemainder(mod)[1];
            }
            System.out.println(sum);
        }
        scanner.close();
    }

    public static Map<Integer, BigInteger> changeV(Map<Integer, BigInteger> VToCount, int x, int y, int k) {
        Map<Integer, BigInteger> map = new HashMap<>();//新键一个map,存放操作后的冰山情况
        Set<Integer> Vs = VToCount.keySet();
        for (int v : Vs) {
            BigInteger count = VToCount.get(v);//体积v有count个
            long vNext = v + x;//变化后的体积
            if (vNext <= 0) continue; //vNext<=0,冰山消失,不存入map

            if (vNext <= k) {// 未超出k的限制
                BigInteger countNext = map.getOrDefault((int) vNext, zero).add(count);
                map.put((int) vNext, countNext);
            } else if (vNext > k) {  // 超出k的限制,分裂
                BigInteger countKNext = map.getOrDefault(k, zero).add(count);//保留1块体积k的冰山
                map.put(k, countKNext);

                BigInteger bi = BigInteger.valueOf(vNext - k);//每个冰山的其余体积分裂为a-k个体积1的冰山
                BigInteger countOneNext = map.getOrDefault(1, zero).add(bi.multiply(count));
                map.put(1, countOneNext);
            }
        }
        if (y != 0) {
            map.put(y, map.getOrDefault(y, zero).add(one));
        }
        return map;
    }

}
