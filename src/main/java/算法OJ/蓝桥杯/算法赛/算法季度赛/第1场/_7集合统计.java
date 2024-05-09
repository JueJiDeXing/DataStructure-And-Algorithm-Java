package 算法OJ.蓝桥杯.算法赛.算法季度赛.第1场;

import java.util.*;
/**
 已AC
 */
public class _7集合统计 {
    /*
    [l,r]上有多少个非空集合 A = { x | x ∈ [l,r], kx ∉ A }
     */
    static Scanner sc = new Scanner(System.in);
    static int mod = 998244353;

    public static void main(String[] args) {
        int t = sc.nextInt();
        init();
        for (int i = 0; i < t; i++) {
            solve();
        }
    }

    static int[] fib = new int[101];//斐波那契数列

    static void init() {
        fib[1] = 2;
        int a, b = 1;
        for (int i = 2; i <= 100; i++) {
            a = b;
            b = fib[i - 1];
            fib[i] = a + b;
        }
    }

    static void solve() {
        long L = sc.nextLong(), R = sc.nextLong(), k = sc.nextLong();
        if (k == 1) {// x∈A -> kx∉A , k=1 -> x∉A 矛盾 -> A=∅, 与题干矛盾 -> A无解
            System.out.println(0);
            return;
        }
        // 将[L,R]按k的幂划分大组
        List<Long> board = getBoard(k, R, L);

        // 假设当前为第i个大组, 则当前大组有 diff(i) - diff(i-1) 个小组
        // 其中diff(i)表示第i大组的左边界值与右边界值之差 board(i) - board(i-1)
        // 小组内的数字数量为 board.size() - i + 1
        // 每小组的只能选择不相邻的数字, 对于小组的选择数字方案:可以发现结果根据小组大小成斐波那契数列
        long ans = 1;
        long d = 0, preDiff = 0;
        int bSize = board.size();
        for (int i = 1; i < bSize; i++) {
            if (Objects.equals(board.get(i), board.get(i - 1))) continue;
            long groupSize = board.get(i) - board.get(i - 1) - preDiff;//该大组内的小组数量
            int cntInGroup = bSize - i + 1;// 每个小组的数字个数
            ans = (ans * pow(fib[cntInGroup], groupSize)) % mod;
            preDiff = board.get(i) - board.get(i - 1); // 更新差值
            d += groupSize * cntInGroup;// 统计已使用的数的个数
        }
        ans = (ans * pow(2L, R - L + 1 - d)) % mod;//剩余的都是一个数字一组的
        System.out.println((ans - 1 + mod) % mod);//减去空集
    }

    private static List<Long> getBoard(long k, long R, long L) {
        //统计k的幂
        long temp = k;
        List<Long> powerK = new ArrayList<>();
        while (temp > 0 && temp <= R) {
            powerK.add(temp);
            temp *= k;
        }
        // 按k的幂划分大组,大组内的每个小组的大小会相同
        /*
         例如:k=2, L=1,R=20
          20/16,20/8    20/4      20/2
        0    1,2         5        10
        ↓     ↓          ↓         ↓
         -----------------------------------------
        |  1  ||  3   5   |  7  9   |  11 13 14 17 19
        |  2  ||  6   10  |  14 18  |
        |  4  ||  12  20  |
        |  8  ||
        |  16 ||
          第一大组有1个小组{1,2,4,8,16}
          第二大组有2个小组{3,6,12}和{5,10,20}
          ...
          大组的划分按 R / k^? 向下取整
         */
        List<Long> board = new ArrayList<>();
        board.add(L - 1);
        for (int i = powerK.size() - 1; i >= 0; i--) {
            board.add(Math.max(L - 1, R / powerK.get(i)));
        }
        return board;
    }

    static long pow(long base, long n) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            n >>= 1;
        }
        return res;
    }
}
