package 算法.数学.数论.模;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class 能否被两个数模相等 {
    /*
    T个询问
    每次询问给出一个整数n,和一个范围m
    判断在[1,m]上是否能找到 x!=y && n%x==n%y
     */
    static StreamTokenizer tokenizer = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            tokenizer.nextToken();
        } catch (Exception ignore) {
        }
        return (int) tokenizer.nval;
    }

    /**
     <h1>暴力</h1>
     */
    public static void main(String[] args) {
        int T = Int();
        Set<Integer> set = new HashSet<>();//n%k
        out:
        for (int i = 0; i < T; i++) {
            int n = Int(), m = Int();
            set.clear();
            for (int j = 1; j <= m; j++) {//查看j模n是否已存在
                if (!set.add(n % j)) {
                    System.out.println("Yes");//找到了
                    continue out;
                }
            }
            System.out.println("No");//没找到
        }
    }

    /**
     <h1>中国剩余定理</h1>
     如果找不到n mod x = n mod y
     说明 n % i 为 1~m-1上的排列    i∈[1,m]
     ∵ n%1 = 0
     => n%2 = 1    --- n%2只有0/1两个取值,0被n%1占了,值只能取1
     => n%3 = 2    --- 同理
     => ...
     => n%i = i-1
     即: 只要判断 n%i = i-1 对 i∈[1,m] 是否都成立即可
     如果都成立,说明找不到,输出No; 如果某一个不成立,说明可以找到,输出Yes
     */
    private static void solve2() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        out:
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt(), m = sc.nextInt();
            for (int j = m; j > 0; j--) {
                if (n % j != (j - 1)) {
                    System.out.println("Yes");
                    continue out;
                }
            }
            System.out.println("No");
        }
    }

}
