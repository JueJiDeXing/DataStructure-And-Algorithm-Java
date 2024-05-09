package 算法OJ.蓝桥杯.算法赛.算法季度赛.第1场;

import java.util.*;
/**
 已AC
 */
public class _3空间复杂度 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int x = sc.nextInt();
            String y = sc.next();
            int p = sc.nextInt();
            solve(x, y, p);
        }
    }

    static void solve(long x, String y, int p) {
        switch (y) {
            case "KB" :{
                x = x * 1024;break;
            }
            case "MB" :{
                x = x * 1024 * 1024;break;
            }
        }
        System.out.println(x / p);
    }

}
