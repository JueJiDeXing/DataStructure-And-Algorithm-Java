package 算法.数学.数论.质数;

import java.util.ArrayList;
import java.util.List;

public class 欧拉函数 {
    /*
    E(k):[1,k]上与k互质的个数
    1. 若k是质数, 则E(k)=k-1
    2. 积性: 若a和b都是质数, 则E(ab) = E(a) * E(b) = (a-1)(b-1)
        2.1  若a是质数, 则 E(a^k) = a^(k-1) * E(a) = (a-1) * a^(k-1)
        2.2  k= p1^q1 * p2^q2 * ...
             E(k) = E(p1^q1) * E(p2^q2) * ...
                  = (p1-1) * 2^(q1-1) * (p2-1) * p2^(q2-1) * ...
                  = mul{ pi - 1 } * mul{ pi^(qi-1) }
                  = mul{ pi - 1 } * mul{ pi^qi / pi }
                  = mul{ pi - 1 } * x / mul{ pi }
                  = x * mul{ 1 - 1/pi }
    3. 若 a % b = 0, 则E(a*b) = E(a) * b


     !重要结论: E(x) = x * mul{ 1 - 1/pi | pi为x的质因子}
     */


    /**
     <h1>欧拉函数模版-求单个正整数的欧拉函数值</h1>
     E(x) = x * mul{1-1/pi} 其中pi是N的质因子
     O(sqrt(x))
     */
    public static int euler(int x) {
        int res = x;
        for (int i = 2; i * i <= x; i++) {
            if (x % i != 0) continue;//找质因子
            res = res / i * (i - 1);
            while (x % i == 0) x /= i;//去除重复因数
        }
        if (x > 1) res = res * (x - 1) / x;//处理剩余的质因数（若存在）
        return res;
    }

    /**
     <h1>欧拉函数模版-求[1,n]的欧拉函数</h1>
     */
    int[] get_phi(int n) {
        int[] phi = new int[n];
        for (int i = 0; i < n; i++) phi[i] = i;
        for (int i = 2; i < n; i++) {
            if (phi[i] == i) {
                //i是质数,i的倍数j含有质因子i,将其筛掉 E(x) = x * (1 - 1/pi)
                for (int j = i; j < n; j += i) {// j为i的倍数, E(j) = j * (1 - 1/i) * ...
                    phi[j] = phi[j] * (i - 1) / i;
                }
            }
        }
        return phi;
    }

    /**
     <h1>欧拉函数模版-欧拉&素数筛</h1>
     */
    static int N = 2000007;
    static List<Integer> prime = new ArrayList<>();
    static int[] phi = new int[N];

    static {
        boolean[] isCom = new boolean[N];
        phi[1] = 1;
        for (int i = 2; i < N; i++) {
            if (!isCom[i]) {
                prime.add(i);
                phi[i] = i - 1;// 与质数i互质的数有i-1个
            }
            for (int j = 0; j < prime.size(); j++) {
                int p = prime.get(j);
                int mul = i * p;
                if (mul >= N) break;
                isCom[mul] = true;
                if (i % p == 0) { // 性质: a % b = 0, 则E(a*b) = E(a) * b
                    phi[mul] = phi[i] * p;
                    break;
                } else {
                    phi[mul] = phi[i] * (p - 1);// E(N) = E(N/pi) * (pi-1) 其中pi是N的质因子
                }
            }
        }
    }


    /**
     <h1>求a^b在[1,a^b]上的互质数个数</h1>
     E(a^b) = E(a) * a^(b-1)<br>
     {@link 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组.E互质数个数}
     */
    void link1() {
    }

    /**
     <h1>欧拉降幂</h1>
     a^k mod m = a^( k mod phi(m) + phi(m) ) mod m<br>
     {@link 算法.数学.数论.模.欧拉降幂}
     */
    void link2() {
    }
}
