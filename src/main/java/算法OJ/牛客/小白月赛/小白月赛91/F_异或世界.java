package 算法OJ.牛客.小白月赛.小白月赛91;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 已AC
 */
public class F_异或世界 {
    /*
    长度为n的数组,求所有非空子数组的权值之和
    数组arr的权值 = sum{ a[i]^a[j] | i,j ∈ arr}  // 有重复
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static int MOD = 10_0000_0007;

    /**
     <h1>拆位贡献法</h1>
     <p>
     根据题意: a[i]与a[j]的贡献 = 包含它的子数组个数 * a[i] ^ a[j]
     ans = sum{ a[i]与a[j]的贡献 * 2 | i,j ∈ a 且 i < j }
     <p>
     拆位: 分成32位分别计算<br>
     <p>
     如果现在固定a[i]:
     [L,R]需要包含a[i]<br>
     对于R: R有 n-i+1 个选法<br>
     对于L:
     如果a[i]为0, 那么只有1可以做出贡献<br>
     若存在a[j]=1, 则L有j个选择, 若存在多个a[j],则L有sum{j}个选择<br>
     a[i]为1时同理<br>
     那么对于a[i]的贡献就等于 sum{j} * (n-i+1) * 位权, j < i<br>
     */
    public static void main(String[] args) throws IOException {
        int n = I();
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = I();
        long ans = 0;
        for (int i = 0; i < 20; i++) {//枚举20位进制(a<1e6)
            int weight = 1 << i;// 该位位权
            long cnt0 = 0, cnt1 = 0;// cnt(x) = sum{j | a[j] = x}
            for (int j = 1; j <= n; j++) {
                int curBit = (a[j] >> i) & 1;
                int R_choose = n - j + 1;
                long L_choose;
                if (curBit == 0) {
                    L_choose = cnt1;
                    cnt0 = (cnt0 + j) % MOD;
                } else {
                    L_choose = cnt0;
                    cnt1 = (cnt1 + j) % MOD;
                }
                ans = (ans + L_choose * R_choose % MOD * weight) % MOD;
            }
        }
        System.out.println(ans * 2 % MOD);//题目的数对是有重复的
    }

}
