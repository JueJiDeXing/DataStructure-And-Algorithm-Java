package 算法OJ.蓝桥杯.算法赛.小白入门赛.第8场;

import java.util.*;
/**
 已AC
 */
public class _2二进制王国 {
    /*
    n个二进制字符串进行排列,求字典序最小的排列
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] x = new String[n];
        for (int i = 0; i < n; i++) x[i] = sc.next();
        Arrays.sort(x, ((o1, o2) -> (o1 + o2).compareTo(o2 + o1)));
        for (String i : x) {
            System.out.print(i);
        }
    }
}
