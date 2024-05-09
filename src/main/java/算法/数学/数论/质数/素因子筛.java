package 算法.数学.数论.质数;

import java.util.ArrayList;
import java.util.List;

public class 素因子筛 {
    public static void main(String[] args) {
        int n = 100000000;
        int[] s = new int[n + 1];// s[i]表示i的素数因子个数(当遍历到i时,如果s[i]为0表示i是素数)
        List<Integer> prime = new ArrayList<>();
        primeFactorization(n, s, prime);
    }

    /**
     统计[2,n]中各数的素数因子个数s[i],以及素数表prime
     */
    private static void primeFactorization(int n, int[] s, List<Integer> prime) {
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
}
