package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学C组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 已AC
 */
public class C取模 {
    static StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            tokenizer.nextToken();
        } catch (Exception ignore) {
        }
        return (int) tokenizer.nval;
    }

    public static void main(String[] args) {
        int T = Int();
        Set<Integer> set = new HashSet<>();//n%k
        out:
        for (int i = 0; i < T; i++) {
            int n = Int(), m = Int();
            set.clear();
            for (int j = 1; j <= m; j++) {
                if (!set.add(n % j)) {
                    System.out.println("Yes");
                    continue out;
                }
            }
            System.out.println("No");
        }
    }

    private static void solve2() {//13/20 7TL
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        out:
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt(), m = sc.nextInt();
            //如果找不到n mod x = n mod y,那么一定有n%1=0, n%2=1, n%3=2,...n%m=m-1
            for (int j = m; j > 0; j--) {
                if (n % j != (j - 1)) {
                    System.out.println("Yes");
                    continue out;
                }
            }
            System.out.println("No");
        }
    }

    private static void solve() {// 16/20 4TL(必须加数据输入流优化)
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        Set<Integer> set = new HashSet<>();//n%k
        out:
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt(), m = sc.nextInt();
            set.clear();
            for (int j = 1; j <= m; j++) {
                if (!set.add(n % j)) {
                    System.out.println("Yes");
                    continue out;
                }
            }
            System.out.println("No");
        }
    }
}
