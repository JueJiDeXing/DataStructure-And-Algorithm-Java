package 算法.数学.数论.质数;

import java.util.Scanner;

public class 素数筛 {
    //求[0,N]的质数数量、对[0,N]的质数求和、求[0,N]的第k个质数

    /**
     <h1>传统做法</h1>
     */
    public int count1(int N) {
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime1(i)) {
                sum++;
            }
        }
        return sum;
    }

    public boolean isPrime1(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isPrime2(int n) {
        int m = (int) Math.sqrt(n);//优化: 假如a<b,a*b=n,那么a<=sqrt(n)<=b
        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     <h1>埃氏筛法</h1>
     */
    public int count2(int N) {
        if (N < 2) return 0;
        boolean[] isComposite = new boolean[N + 1];//是否为合数
        for (int i = 2; i <= N / i; i++) {
            if (!isComposite[i]) {
                //是质数,把后面的合数筛掉
                for (int j = i * i; j <= N; j += i) {
                    //从i^2开始是因为:假设前面质数是ai,这次质数是b,则筛ai的时候把ai*b都已经筛掉了,所以从b^2开始即可
                    isComposite[j] = true;
                }
            }
        }
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) sum++;
        }
        return sum;
    }

    /**
     <h1>欧拉筛法</h1>
     埃氏筛的改进
     */
    public int count3(int N) {
        if (N < 2) return 0;
        boolean[] isComposite = new boolean[N + 1];//是否为合数
        int[] prime = new int[N + 1];//存储素数
        int sum = 0;
        for (int i = 2; i <= N; i++) {
            if (!isComposite[i]) {
                prime[sum++] = i;//是素数
            }
            //把后面的合数( i与现有质数的倍数(含自身) )筛掉
            for (int j = 0; prime[j] * i <= N && j < sum; j++) {
                isComposite[prime[j] * i] = true;
                if (i % prime[j] == 0) break;//防止重复筛
            }
        }
        return sum;
    }

    /*
    定义函数f(x)为x不同的素因子个数，
    对于一个正整数a，请找到一个最小的正整数b，使得a<b,f(a)<f(b) 。

    题目描述给定一个数q，表示询问数，
    以及q个正整数，表示题目背景中的a，
    对每个a，你需要求出相应的b。

    格式输入格式第一行一个正整数q，表示询问数第2∼q+1行每行一个正整数，表示题目背景中的a
    输出格式每行一个正整数b
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        int N = 40000;//3万过不了
        //素数筛
        boolean[] isComposite = new boolean[N];
        isComposite[0] = isComposite[1] = true;
        int[] prime = new int[N];
        int pNum = 0;
        int[] count = new int[N];
        //求素因子统计
        for (int i = 2; i < N; i++) {
            if (!isComposite[i]) {
                prime[pNum++] = i;
                for (int j = 1; i * j < N; j++) {
                    count[i * j]++;
                }
            }
            for (int j = 0; j < pNum; j++) {
                if (j > i || i * prime[j] >= N) break;
                isComposite[i * prime[j]] = true;
            }
        }
        //q个询问
        for (int i = 0; i < q; i++) {
            int n = sc.nextInt();
            for (int j = n + 1; j < N; j++) {
                if (count[j] > count[n]) {
                    System.out.println(j);
                    break;
                }
            }
        }
    }

}
