package 算法OJ.牛客.小白月赛.小白月赛94;

import java.io.*;
/**
 已AC
 */
public class D_gcd排列构造 {
    /*
    给出前缀gcd数组,复原数组, 原数组是一个长度为n的排列
     */
    static int N = (int) 2e5 + 1;
    static int[] a = new int[N];//a[i] = gcd(ans[1],...asn[i])
    static int[] ans = new int[N];
    static int n;
    static boolean[] used = new boolean[N];//used[i]:数字i是否被使用了

    public static void main(String[] args) throws Exception {
        n = I();
        if (!solve()) {
            System.out.println(-1);
        } else {
            for (int i = 1; i <= n; i++) System.out.print(ans[i] + " ");
        }
    }

    private static boolean solve() throws IOException {
        for (int i = 1; i <= n; i++) {
            a[i] = I();
            /*
            a[i] = gcd(ans[1],...ans[i]) = gcd(a[i-1],ans[i])
            ⇒ a[i-1] % a[i] == 0
             */
            if (a[i - 1] % a[i] != 0) return false;
        }
        for (int i = 1; i <= n; i++) {
            if (a[i - 1] != a[i]) {// eg: i=2, a[1]=4, a[2]=2 -> 填2
                ans[i] = a[i];
            } else {// eg: i=2, a[1]=4, a[2]=4  -> 可填 8 12 16... 
                ans[i] = find(i);
                if (ans[i] == 0) return false;// 找最小的未填的a[i]倍数,找不到
            }
            used[ans[i]] = true;
        }
        return true;
    }

    /**
     查找a[i]的未使用的倍数, 且满足:
     gcd(ans[1],...ans[i-1],ans[i]) == a[i]
     ⇔ gcd(a[i-1], ans[i]) == a[i]
     */
    static int find(int i) {
        // ans[i] > ans[i-1], ans[i-1]也是a[i]的倍数
        for (int f = ans[i - 1] + a[i]; f <= n; f += a[i]) {
            if (!used[f] && gcd(a[i - 1], f) == a[i]) {
                return f;
            }
        }
        return 0;
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

}

