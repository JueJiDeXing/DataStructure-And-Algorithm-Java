package 算法OJ.蓝桥杯.真题卷.第7届.国赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class B凑平方数 {
    /*
    0~9分成多组,每组都是平方数
    例如 {0,36,5948721}
     */
    public static void main(String[] args) {
        //将 没有重复数字的完全平方数 转为bit 存入sq中
        for (long i = 0; i < 100000; i++) {
            check(i * i);//相乘可能溢出
        }
        //dfs枚举sq的组和
        dfs(0, 0);
        System.out.println(ans);//300
    }

    static void check(long n) {
        int bin = 0;
        if (n == 0) bin = 1;
        while (n > 0) {
            int t = (int) (n % 10);
            if ((bin & (1 << t)) != 0) return;
            bin |= (1 << t);
            n /= 10;
        }
        sq.add(bin);
    }

    static List<Integer> sq = new ArrayList<>();
    static int ans = 0;

    static void dfs(int start, int bin) {
        if (bin == (1 << 10) - 1) {//全部集齐
            ans++;
            return;
        }
        for (int i = start; i < sq.size(); i++) {
            int b = sq.get(i);
            if ((b & bin) == 0) {//需要与前面组没有重复数字
                dfs(i + 1, bin | b);
            }
        }
    }

}
