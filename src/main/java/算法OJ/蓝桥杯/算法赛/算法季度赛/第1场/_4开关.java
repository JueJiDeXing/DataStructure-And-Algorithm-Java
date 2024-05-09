package 算法OJ.蓝桥杯.算法赛.算法季度赛.第1场;

import java.io.*;

/**
 已AC
 */
public class _4开关 {
    /*
    有n*n个开关,初始为关闭状态
    执行n次操作
    每次操作执行两步:
    (1) ai/bi的概率触发该步, 将第i行开关状态翻转
    (2) ci/di的概率触发该步, 将第i列开关状态翻转
    求n次操作后,点亮的灯泡的期望个数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     对于(x,y)位置的灯泡:<br>
     在i=x时可能触发行翻转,在i=y时可能触发列翻转<br>
     如果它要点亮,这两个事件只能发生一个<br>
     则概率为 ax/bx * (1-cy/dy) + (1-ax/bx) * cy/dy<br>
     令 p[x] = ax/bx, q[x] = cx/dx<br>
     则灯泡(x,y)的点亮概率为 p[x]*(1-q[y]) + (1-p[x])*q[y]<br>
     则期望为 sum{ p[x]*(1-q[y]) + (1-p[x])*q[y] }<br>
     = sum{  sum{ p[x]+q[y]-2p[x]q[y] | y } | x  }<br>
     = sum{  sum{ p[x] | y } + sum{ q[y] | y } - 2p[x]sum{ q[y] | y } | x}<br>
     令s1 = sum(p), s2 = sum(q)<br>
     = sum{  np[x]           + s2              - 2p[x]s2 | x }<br>
     = sum{ np[x] | x } + sum{ s2 | x } - 2s2*sum{ p[x] | x }<br>
     = ns1 + ns2 - 2s1*s2<br>
     所以求s1和s2即可<br>
     */
    public static void main(String[] args) {
        int n = nextInt();
        int[] a = new int[n], b = new int[n], c = new int[n], d = new int[n];
        for (int i = 0; i < n; i++) a[i] = nextInt();
        for (int i = 0; i < n; i++) b[i] = nextInt();
        for (int i = 0; i < n; i++) c[i] = nextInt();
        for (int i = 0; i < n; i++) d[i] = nextInt();
        long s1 = 0, s2 = 0;
        for (int i = 0; i < n; i++) {
            s1 = (s1 + (a[i] * pow(b[i], MOD - 2))) % MOD;
            s2 = (s2 + (c[i] * pow(d[i], MOD - 2))) % MOD;
        }
        System.out.println((n * (s1 + s2) % MOD - 2 * (s1 * s2) % MOD + MOD) % MOD);
    }

    static int MOD = 998244353;

    static long pow(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = res * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return res;
    }
}
