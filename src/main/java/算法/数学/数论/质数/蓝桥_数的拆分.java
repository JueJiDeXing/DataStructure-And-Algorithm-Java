package 算法.数学.数论.质数;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 蓝桥_数的拆分 {
    /*
     T个询问, 每次询问a能否表示为x1^y1 * x2^y2形式
     其中x1和x2为正整数, y1和y2为大于1的正整数

    T < 1e5, a<1e18
     */
/*
### 结论: a能拆分  当且仅当  a的质因子分解中不含1次幂

a = 2^p[1] * 3^p[2] * 5^p[3] * ....

若存在p[i]=1, 那么它无法进行合并, 而y1和y2要求大于等于2

若不存在p[i]=1, 即任意p[i]>=2:

可以将a拆分为 x1^2 * x2^3

对于p[i]为偶数, 将其放入x1^2中

对于p[i]为奇数, 可以拆为3次幂和p[i]-3次幂, 3次幂放入x2^3中, p[i]-3次幂放入x1^2中

例如: a = 2^5 * 3^6 = 2^2 * 2^3 * 3^2 * 3^2 * 3^2 = (2*3*3*3)^2 * 3^2

那么对a做质因子分解, 判断是否存在1次幂系数即可, 若存在返回false, 不存在返回true

### 超时处理

a的范围为1e18, 如果a中存在大质数, 则无法枚举a的所有质因子, 否则会超时

设枚举的质数在1~N内, 对a质因子分解后剩余的部分为x

考虑x可以是多少个大于N的质数的乘积:

设N = 1e4, N^k < 1e18 , k<5

如果将N设为1e4, x最多是4个大质数的乘积

x中不含1次幂质因子的情况有p^2、p^3、p^4、p1^2 * p2^2

发现x一定是`平方数 或 立方数`

---

N能否继续减小, 如果N减小使x可以是5的大质数的乘积

那么x的合法情况会引入p1^2 * p2^3, 这个情况是不好判断的

那么N最小可以是多少?

N^5>1e18 , N > pow(1e18,1/5) = pow(1e15,1/5) * pow(1e3,1/5) ≈ 3981

N最小可以取4000左右, 此时用素数筛筛出素数,有550个质数, 1e5 * 550


 */
    // 质数
    static List<Integer> prime = new ArrayList<>();

    static {
        int N = 4000;
        boolean[] notPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            if (!notPrime[i]) prime.add(i);
            for (int j = 0; j < prime.size() && i * prime.get(j) <= N; j++) {
                notPrime[i * prime.get(j)] = true;
                if (i % prime.get(j) == 0) break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(prime.size());
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            long a = sc.nextLong();
            System.out.println(judge(a) ? "yes" : "no");
        }
    }

    /**
     判断a能否拆分为 x1^y1 * x2^y2
     y1,y2 > 1
     */
    static boolean judge(long a) {
        // 质因子分解
        for (int j = 0; j < prime.size(); j++) {
            long i = prime.get(j);
            if (i > a) break;
            if (a % i != 0) continue;
            int cnt = 0;// 幂次
            while (a % i == 0) {
                cnt++;
                a /= i;
            }
            // 不能存在1次幂
            if (cnt == 1) return false;
        }
        // 剩余部分必须是平方数或立方数
        return isp2(a) || isp3(a);
    }

    static boolean isp2(long a) {
        long x = (long) Math.sqrt(a);
        return x * x == a || (x + 1) * (x + 1) == a;
    }

    static boolean isp3(long a) {
        long x = (long) Math.cbrt(a);
        return x * x * x == a || (x + 1) * (x + 1) * (x + 1) == a || (x + 2) * (x + 2) * (x + 2) == a;
    }

}
