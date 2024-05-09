package 算法OJ.蓝桥杯.题单.第15届省赛冲刺营;

import java.util.HashMap;
import java.util.Scanner;
/**
 已AC
 */
public class F七彩之城的独特序列 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> mapCnt = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            mapCnt.put(x, mapCnt.getOrDefault(x, 0) + 1);
        }
        long ans = 1;
        for (int cnt : mapCnt.values()) {
            ans = (ans * (cnt + 1)) % 10_0000_0007;
        }
        System.out.println(ans - 1);
    }

}
