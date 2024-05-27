package 算法.数学.数论.质数;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 素因子筛 {
    public static void main(String[] args) {
        int n = 100000000;
        int[] s = new int[n + 1];// s[i]表示i的素数因子个数(当遍历到i时,如果s[i]为0表示i是素数)
        List<Integer> prime = new ArrayList<>();
        init1(n, s, prime);
    }

    /**
     <h1>统计个数</h1>
     统计[2,n]中各数的素数因子个数s[i],以及素数表prime
     */
    private static void init1(int n, int[] s, List<Integer> prime) {
        for (int i = 2; i <= n; i++) {
            if (s[i] == 0) {// i是素数
                s[i] = 1;
                prime.add(i);// 加入素数表
            }
            for (int x : prime) {
                if (x * i > n) break;
                s[x * i] = s[i] + 1;// x是x*i的一个素数因子,i的素数因子有s[i]个
            }
        }
    }

    /**
     <h1>给定一组数的质因子</h1>
     */
    public static List<Integer>[] init2(int[] a) {
        int n = a.length;
        List<Integer>[] factor = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= a[i] / j; j++) {
                if (a[i] % j == 0) {
                    while (a[i] % j == 0) a[i] /= j;
                    factor[i].add(j);
                }
            }
            if (a[i] != 1) factor[i].add(a[i]);
        }
        return factor;
    }

    /**
     <h1>1~n的质因子</h1>
     */
    public static List<Integer>[] init3(int n) {
        List<Integer>[] factor = new List[n + 1];
        List<Integer> prime = new ArrayList<>();
        boolean[] isCom = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            if (!isCom[i]) prime.add(i);
            for (int j = 0; j < prime.size(); j++) {
                int t = prime.get(j);
                if (i * t > n) break;
                isCom[i * t] = true;
                if (i % t == 0) break;
            }
        }
        Arrays.setAll(factor, i -> new ArrayList<>());
        for (int i = 2; i <= n; i++) {
            int x = i;
            for (int j = 0; j < prime.size(); j++) {
                int v = prime.get(j);
                if (v * v > x) break;
                if (x % v == 0) {
                    factor[i].add(v);
                    while (x % v == 0) x /= v;
                }
            }
            if (x != 1) factor[i].add(x);
        }
        return factor;
    }
}
