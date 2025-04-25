package 算法.数学.数论.质数;

import java.util.ArrayList;
import java.util.List;

public class 素数分布估计 {
    /*
    1~N内的素数个数约为 N / (lnN-1) 个
     */
    static int cnt(int N) {
        return (int) (N / (Math.log(N) - 1));
    }
    /*
    N       质数个数     估计
    1e1     4           7
    1e2     25          27
    1e3     168         169
    1e4     1229        1217
    1e5     9592        9512
    1e6     78498       78030
    1e7     664579      661458
     */

    static int cnt_true(int N) {
        List<Integer> prime = new ArrayList<>();
        boolean[] notPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (!notPrime[i]) prime.add(i);
            for (int j = 0; j < prime.size() && i * prime.get(j) <= N; j++) {
                notPrime[i * prime.get(j)] = true;
                if (i % prime.get(j) == 0) break;
            }
        }
        return prime.size();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(cnt(10));
        System.out.println(cnt(100));
        System.out.println(cnt(1000));
        System.out.println(cnt(10000));
        System.out.println(cnt(100000));
        System.out.println(cnt(1000000));
        System.out.println(cnt(10000000));
    }


}
