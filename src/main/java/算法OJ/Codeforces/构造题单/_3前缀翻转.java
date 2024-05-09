package 算法OJ.Codeforces.构造题单;

import java.util.*;

/**
 已AC
 */
public class _3前缀翻转 {
    /*
    给出两个长度为n的二进制串a和b
    现在要将a翻转为b
    每次操作可以选择a的一个前缀,将0变为1,1变为0,并且子串前后翻转
    最多操作2n次
    输出操作

    t次询问(t<1000)
    每次询问给出n<1e5和a,b
    每次询问输出k表示操作次数,后面k个数表示翻转a的前缀长度
     */
    static Scanner sc = new Scanner(System.in);

    /**
     题目没有要求最优解,只要操作次数小于等于2n均可
     可以发现:
     (1) 将一个二进制串翻转为全0或全1的次数一定小于n次
     (2) 两次相邻的操作如果在同位置翻转会抵消, 也就是说如果a能翻转为b,则b可以通过逆操作翻转为a
     <p>
     所以:
     如果将a翻转为全0或者全1,操作集合为S
     将b也翻转为全0或全1,操作集合为T
     如果a和b翻转后相同(全0或全1), 则有a->b = S + reverse(T)
     如果不同,则再翻转一次整串即可, a->b = S + {n} + reverse(T)
     <p>
     如何翻转为全0或全1:
     如果前i-1位为000...0,当前第i位为1,则翻转前i-1位,则前i位可变为全1
     如果前i-1位为111...1,当前第i位为0,则翻转前i-1位,则前i位可变为全0
     也就是说,从前往后扫描,只要某一位与后一位不同,即翻转
     这样的翻转最多n-1次,因为相邻对只有n-1个
     */
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            solve();
        }
    }

    static char[] a, b;
    static int n;
    static LinkedList<Integer> S = new LinkedList<>(), T = new LinkedList<>();

    static void solve() {
        n = sc.nextInt();
        a = sc.next().toCharArray();
        b = sc.next().toCharArray();
        S.clear();
        T.clear();
        for (int i = 1; i < n; i++) {
            if (a[i - 1] != a[i]) S.addLast(i);
            if (b[i - 1] != b[i]) T.addFirst(i);
        }
        if (a[n - 1] != b[n - 1]) S.addLast(n);
        //S正序打印,T逆序打印
        int k = S.size() + T.size();
        System.out.print(k + " ");
        for (int i : S) System.out.print(i + " ");
        for (int i : T) System.out.print(i + " ");

        System.out.println();
    }


}
