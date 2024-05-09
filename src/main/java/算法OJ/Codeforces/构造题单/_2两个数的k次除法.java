package 算法OJ.Codeforces.构造题单;

import java.util.*;

/**
 已AC
 */
public class _2两个数的k次除法 {
    /*
    给定a,b,k
    要求k次操作后,a=b
    每次操作:
    可以将a变为a/c,其中c整除a,且c>1
    或 将b变为b/c,其中c整除b,且c>1
    (二者在每次操作中仅发生一个,c的条件取决于选择的数是a还是b)

    如果可以做到,输出Yes,否则输出No

    t组询问(t<=1e4)
    1<=a,b,k<=1e9
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            System.out.println(solve() ? "Yes" : "No");
        }
    }

    static boolean solve() {
        int a = sc.nextInt(), b = sc.nextInt(), k = sc.nextInt();
        if (a > b) {
            int t = a;
            a = b;
            b = t;
        }
        int max = split(a) + split(b);// 最大操作次数求质因子指数之和
        int min;//最小操作次数
        if (a == b) min = 0;// 相等,不需要操作
        else if (b % a == 0) min = 1;  // 整数倍关系,操作1次
        else min = 2;// 不成倍数, a和b都需要操作1次
        if (k < min || k > max) return false;// k不在操作区间,一定不行
        if (k == 1) return min == 1;// 特判点: k如果为1, ab需要成不相等的倍数关系,否则不能操作1次得到相等
        return true;
    }

    static List<Integer> prime = new ArrayList<>();

    static {
        int N = (int) Math.sqrt(1e9) + 1;
        boolean[] isCom = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (!isCom[i]) prime.add(i);
            for (int p : prime) {
                int t = p * i;
                if (t > N) break;
                isCom[t] = true;
                if (i % p == 0) break;
            }
        }
    }

    static int split(int x) {
        int cnt = 0;
        for (int p : prime) {
            if (p * p > x) break;
            if (x % p != 0) continue;
            while (x % p == 0) {
                x /= p;
                cnt++;
            }
        }
        if (x != 1) cnt++;
        return cnt;
    }
}
