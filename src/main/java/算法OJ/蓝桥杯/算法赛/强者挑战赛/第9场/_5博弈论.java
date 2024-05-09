package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场;

import java.util.*;

public class _5博弈论 {
    /*
    n个硬币
    给定k
    每次操作需要选择一个左端点为正面朝上的长度为k的硬币段
    将它们翻面
    在不能操作时判输
    求最后的获胜者,以及必胜方案数
    如果是先手必胜,输出第一步方案数
    如果平局,输出"Equal"
    如果先手必输,输出考虑先手第一步所有情况下的后手第一步必胜方案的最小值和最大值
     */

    /**
     首先证明一定不会平局:
     将正面朝上看做1,否则为0, 记硬币表示的数为num,则当num<2^k-1时无法再操作
     而每次操作都会选择一个左端点1,所以每次操作都会变小
     所以一定不会平局
     <p>
     必胜规律:
     n个硬币,将为k的倍数的位置作为一个集合U={k,2k,3k,...}
     因为操作区间长度为k,U的元素相隔也未k,所以无论选择哪一个左端点,都恰好会操作U中的某一个元素
     令f表示U中的位置正面朝上(1)的个数
     则每次操作都会使f恰好改变1, f+1 or f-1
     当f变为0时无法再操作,必败
     当f=1,选择U中仅剩的1进行操作, f变为0,必胜
     f要么转移到f+1,要么转移到f-1,其奇偶性一定是不变的
     所以当初始f为奇数时,一定会转移到f=1,是先手必胜的
     当初始f为偶数时,一定会转移到f=2,是先手必败的
     <p>
     所以,必胜规律仅与U的奇偶性有关(U有n/k个数),与操作无关
     当n/k为奇数时,先手必胜,第一步操作有n-k+1个位置可操作
     当n/k为偶数时,后手必胜,后手的第一步操作:
     最多是先手操作了倒数第k个位置,剩余n-k个位置可操作
     最少是先手操作了倒数第(k-1)+k的位置,剩余n-2k+1个位置可操作
     */

    public static void main(String[] args) {
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) solve();
        sc.close();
    }

    static Scanner sc = new Scanner(System.in);

    static void solve() {
        long n = sc.nextLong(), k = sc.nextLong();
        if ((n / k) % 2 == 1) {
            System.out.println("BeiBei");
            System.out.println(n - k + 1);
        } else {
            System.out.println("NingNing");
            System.out.println((n - 2 * k + 1) + " " + (n - k));
        }
    }

}
