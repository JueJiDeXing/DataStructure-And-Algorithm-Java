package 算法OJ.蓝桥杯.其他;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 已AC
 */
public class 与s互质的第r个正整数 {
    /*
    暴力枚举 + 分解s的质因数
    枚举数ans,检查其是否与s互质,互质则count++,输出第r个
    检查ans与s互质:只需要ans中不含有s的质因子即可
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt(), r = sc.nextInt();
        //分解s的质因数
        for (int i = 2; i * i <= s; i++) {
            if (s % i == 0) {
                factor_s.add(i);
                while (s % i == 0) s /= i;
            }
        }
        if (s > 1) factor_s.add(s);

        int ans = 1;//枚举数
        int count = 1; //第几个有效互质数字
        while (count < r) {
            ans++;
            if (check(ans)) {//检查ans与s是否互质
                count++;
            }
        }
        System.out.println(ans);
    }

    static boolean check(int x) {
        //x是否与s互质
        for (int p : factor_s) {
            if (x % p == 0) return false;//x不能含有s的质因数
        }
        return true;
    }

    static List<Integer> factor_s = new ArrayList<>(); // s的质因数
}
