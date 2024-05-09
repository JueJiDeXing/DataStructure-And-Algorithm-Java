package 算法.数学.数论.质数;

import 算法.数学.数论.模.欧拉降幂;
import 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组.E互质数个数;

import java.util.Arrays;

public class 欧拉函数 {
    /*
    求数k在[1,k]上与k互质的个数,E(k)
    1. E(a^b) = E(a) * a^(b-1)
    2. E(k) = k * (1 - 1/pi) 其中pi是k的质因子
    3. E(N) = E(N/pi) * (pi-1) 其中pi是N的质因子
     */

    /**
     <h1>欧拉函数模版-求单个正整数的欧拉函数值</h1>
     E(x) = E(x/pi) * (pi-1) 其中pi是N的质因子
     O(sqrt(x))
     */
    static int euler(int x) {
        int n = (int) Math.sqrt(x);
        int res = x;
        for (int i = 2; i <= n; i++) {
            if (x % i != 0) continue;//找因数
            res = res / i * (i - 1);
            while (x % i == 0) x /= i;//去除重复因数
        }
        if (x > 1) res = res / x * (x - 1);//处理剩余的质因数（若存在）
        return res;
    }

    /**
     <h1>质数线性筛加快欧拉函数</h1>
     */
    long euler(int a, int b) {//求[a,b]的欧拉函数值之和
        //线性筛,筛质数
        boolean[] isPrime = new boolean[b + 1];
        Arrays.fill(isPrime, true);//先置为true,后面再筛掉
        for (int i = 2; i * i <= b; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= b; j += i) isPrime[j] = false;
        }
        //求和
        long sum = 0;
        for (int i = a; i <= b; i++) sum += phi(i, isPrime);
        return sum;
    }

    //求n的欧拉函数值
    int phi(int n, boolean[] isPrime) {
        if (n == 1) return 1;
        int res = n;
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) continue;
            if (n % i != 0) continue;
            res -= res / i;
            while (n % i == 0) n /= i;
        }
        if (n > 1) res -= res / n;
        return res;
    }

    /**
     <h1>求区间上每个数的欧拉函数</h1>
     */
    int[] get_phi(int n) {
        int[] phi = new int[n];
        for (int i = 0; i < n; i++) phi[i] = i;
        for (int i = 2; i < n; i++) {
            if (phi[i] == i) {
                //i是质数,i的倍数含有质因子i,将其筛掉 E(x) = x * (1 - 1/pi)
                for (int j = i; j < n; j += i) phi[j] = phi[j] / i * (i - 1);
            }
        }
        return phi;
    }

    /**
     <h1>求a^b在[1,a^b]上的互质数个数</h1>
     E(a^b) = E(a) * a^(b-1)<br>
     {@link E互质数个数}
     */
    void link1() {
    }

    /**
     <h1>欧拉降幂</h1>
     a^k mod m = a^( k mod phi(m) + phi(m) ) mod m<br>
     {@link 欧拉降幂}
     */
    void link2() {
    }
}
