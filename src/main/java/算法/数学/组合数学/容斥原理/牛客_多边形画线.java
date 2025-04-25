package 算法.数学.组合数学.容斥原理;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class 牛客_多边形画线 {
    /*
    一个凸多边形, 每个边上有a[i]个点.
    现在两个人分别选择两个点画线, 画出的线段不能在多边形的边上
    求有多少种方案使他们两条线段恰有一个交点
     */

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 10_0000_0007;

    /*
    ① 选择了4个不同的点, 画出的线段有交点:
        自动分配有交点的情况(对角相连), 甲乙可交换线段, 方案数乘以2
        ans = 2 * C(S,4)

    ② 在①中, 如果四个点在同一条边上,需要排除
       C(a[i],4), 选择一条边, 边上选4个点的方案数
       自动分配有交点的情况(外线段覆盖内线段), 甲乙可交换线段, 方案数乘以2
       ans -= 2 * C(a[i],4)

    ③ 在①中, 如果三个点在同一条边上,需要排除
       C(a[i],3) * (S-a[i]) , 选择一条边, 边上选3个点, 另一点在边外取
       自动分配有交点的情况(外点连接中心点), 甲乙可交换线段, 方案数乘以2
       ans -= 2 * C(a[i],3) * (S-a[i])

    ④ 如果两个点在同一条边上重合,这种情况在①中未被统计, 需要加入
        a[i] * C(S-a[i],2) 选择边上一重合点, 另外两点不重合
        自动分配有交点的情况(外点连接重合点), 甲乙可交换线段, 方案数乘以2
        ans += 2 * a[i] * C(S-a[i],2)

    (若两对点重合,交点有无数个,而非1个交点)

       MOD = 10**9+7
       n = int(input())
       arr = list(map(int, input().split()))
       s = sum(arr) % MOD
       ans = s*(s-1)*(s-2)*(s-3)//12                            # C(S,4), 任取4个不重合点的方案, 自动分配有交点情况, 甲乙可交换线段 ,乘2
       ans -= sum(a*(a-1)*(a-2)*(a-3)//12 % MOD for a in arr)   # 排除四点在同一边的情况, 选边,边上选4点
       ans -= sum(a*(a-1)*(a-2)//3 * (s-a) % MOD for a in arr)  # 排除三点在同一边的情况,选边,在边上取三个点另一个点在边外取
       ans += sum(a*(s-a)*(s-a-1) % MOD for a in arr)           # 加上有一个端点重合的情况, 选边, 边上选重合点, 另外两点任意选不重合的
       print(ans % MOD)
        */
    public static void main(String[] args) throws Exception {
        int n = I();
        int[] arr = new int[n];
        long sum = 0;// 总点数
        for (int i = 0; i < n; i++) {
            arr[i] = I();
            sum = (sum + arr[i]) % MOD;
        }
        long inv12 = pow(12, MOD - 2);// 12对于MOD的乘法逆元
        //long ans = sum * (sum - 1) % MOD * (sum - 2) % MOD * (sum - 3) % MOD * inv12 % MOD;// 2 * C(S,4)
        long ans = mul(sum, sum - 1, sum - 2, sum - 3, inv12);// 2 * C(S,4)
        for (long a : arr) {
            //ans = (ans - a * (a - 1) % MOD * (a - 2) % MOD * (a - 3) % MOD * inv12 % MOD + MOD) % MOD; // 2 * C(a[i],4)
            ans = sub(ans, mul(a, a - 1, a - 2, a - 3, inv12));
        }
        long inv3 = pow(3, MOD - 2);
        for (long a : arr) {
            //ans = (ans - a * (a - 1) % MOD * (a - 2) % MOD * inv3 % MOD * (sum - a) % MOD + MOD) % MOD;// 2 * C(a[i],3) * (S-a[i])
            ans = sub(ans, mul(a, a - 1, a - 2, inv3, sum - a));// 2 * C(a[i],3) * (S-a[i])
        }
        for (long a : arr) {
            //ans = (ans + a * (sum - a) % MOD * (sum - a - 1) % MOD) % MOD;// 2 * a[i] * C(S-a[i],2)
            ans = add(ans, mul(a, sum - a, sum - a - 1));// 2 * a[i] * C(S-a[i],2)
        }
        System.out.println(ans);
    }

    static long mul(long... nums) {
        long ans = 1;
        for (long num : nums) {
            ans = (ans * num) % MOD;
        }
        return ans;
    }

    static long sub(long num1, long num2) {
        return (num1 - num2 + MOD) % MOD;
    }

    static long add(long num1, long num2) {
        return (num1 + num2) % MOD;
    }

    static long pow(long x, long n) {
        x %= MOD; // 防传入 int*int 忘取模
        long ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) ans = ans * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }
        return ans;
    }

}
