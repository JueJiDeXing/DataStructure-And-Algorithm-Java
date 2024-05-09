package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学C组;

import java.io.*;

/**
 做不了,得读文件,评测那里超时
 */
public class B小蓝做实验 {
    public static void main(String[] args) throws IOException {
        //读取文件中的数,判断统计质数个数
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("primes.txt")));
        long ans = 0;
        while (true) {
            String s = in.readLine();
            if (s == null) break;
            long val = Long.parseLong(s);
            if (val > N) {
                if (isPrime(val)) ans++;
            } else {
                if (!isComposite[(int) val]) ans++;
            }
        }
        System.out.println(ans);//342693

    }

    static boolean isPrime(long x) {
        int s = (int) Math.sqrt(x);
        for (int i = 2; i <= s; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }

    static int N = 10000_0000;
    static boolean[] isComposite = new boolean[N + 1];

    static {
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isComposite[j] = true;
                }
            }
        }
    }
}
