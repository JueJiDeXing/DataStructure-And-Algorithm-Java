package 算法OJ.蓝桥杯.算法赛.算法双周赛.第4场;

import java.util.*;
/**
 已AC
 */
public class _4乱纪元 {
    /*
    如果 T = x^a + y^b + z^c 则T为乱纪元年, 其余情况均为恒纪元年
    其中 x,y,z已知, a,b,c为非负整数, 1<=x,y,z<=1e5,且xyz不同时为1
    给出q个询问
    每个询问给出一个年份S(S为乱纪元年), 求下一个恒纪元年是多少, 并且求它的持续时间
     */
    static Scanner sc = new Scanner(System.in);
    static long x, y, z;
    static TreeSet<Long> messDates;
    static long N = (long) 1e15;

    public static void main(String[] args) {
        messDates = new TreeSet<>();
        x = sc.nextLong();
        y = sc.nextLong();
        z = sc.nextLong();
        init();
        long q = sc.nextLong();
        for (int i = 0; i < q; i++) {
            solve();
        }
    }

    private static void solve() {
        long s = sc.nextLong();
        //从s+1向后找第一个恒纪元
        for (long date = s + 1; date <= N; date++) {
            if (messDates.contains(date)) continue;//乱纪元跳过,找恒纪元
            long len = messDates.ceiling(date) - date; // ceil(date)为大于date的第一个乱纪元
            System.out.println(date + " " + len);
            break;
        }
    }

    private static void init() {
        //枚举x^a,y^b和z^c, 将乱纪元日期添加到有序集合中
        for (long xa = 1; xa <= N; xa *= x) {//xa=x^a
            for (long yb = 1; yb <= N; yb *= y) {
                for (long zc = 1; zc <= N; zc *= z) {
                    messDates.add(xa + yb + zc);
                    if (z == 1) break;//是1的话,指数不重要,只需要执行一遍循环
                }
                if (y == 1) break;
            }
            if (x == 1) break;
        }
    }
}
