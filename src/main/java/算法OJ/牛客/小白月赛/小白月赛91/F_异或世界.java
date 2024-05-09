package 算法OJ.牛客.小白月赛.小白月赛91;

import java.io.*;

/**
 已AC
 */
public class F_异或世界 {
    /*
    长度为n的数组,求所有非空子数组的权值之和,数组a的权值为a的异或和sum{a[i]^a[j]}
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int MOD = 10_0000_0007;

    /**
     <h1>拆位贡献法</h1>
     <p>
     a[i]与a[j]的贡献 = 包含它的子数组个数*a[i]^a[j]
     <p>
     拆位:分成32位分别计算<br>
     如果现在固定a[i], [L,R]包含A[i],R有n-i+1个选法<br>
     如果a[i]为0,那么只有1可以做出贡献,a[j]=1,对应的,L有j个选择<br>
     a[i]为1时同理<br>
     那么对于a[i]的贡献就等于 sum{j} * (n-i+1) * 位权<br>
     */
    public static void main(String[] args) {
        int n = nextInt();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = nextInt();
        long ans = 0;
        for (int i = 0; i < 20; i++) {//枚举20位进制(a<1e6)
            long cnt0 = 0, cnt1 = 0;
            for (int j = 1; j <= n; j++) {
                if (((a[j] >> i) & 1) == 0) {
                    ans = (ans + cnt1 * (n - j + 1) % MOD * (1 << i)) % MOD;
                    cnt0 = (cnt0 + j) % MOD;
                } else {
                    ans = (ans + cnt0 * (n - j + 1) % MOD * (1 << i)) % MOD;
                    cnt1 = (cnt1 + j) % MOD;
                }
            }
        }
        System.out.println(ans * 2 % MOD);//题目的数对是有重复的
    }

}
