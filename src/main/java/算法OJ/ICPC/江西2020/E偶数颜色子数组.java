package 算法OJ.ICPC.江西2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
/**
 已AC(简单,前缀异或)
 */
public class E偶数颜色子数组 {
    /*
    长度为n的数组, c[i]表示第i个位置的颜色, 求有多少个子数组满足以下条件:
    子数组内各颜色的出现次数为偶数
    c[i]<=20
     */

    /**
     [l,r]满足要求
     <=> [l,r]的异或和 = 0
     <=> [1,r]的异或和 ^ [1,l-1]的异或和 = 0
     <=> [1,r]的异或和 = [1,l-1]的异或和
     所以求前缀异或和数组, 然后用哈希表滚动记录异或和, 每滑到一个位置, 直接取哈希表中的计数即可
     */
    public static void main(String[] args) throws Exception {
        int n = I();
        int[] c = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            c[i] = I();
        }
        int[] preFix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preFix[i] = preFix[i - 1] ^ (1 << c[i]);
        }
        HashMap<Integer, Integer> preCnt = new HashMap<>();
        preCnt.put(0, 1);
        long ans = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = preCnt.getOrDefault(preFix[i], 0);
            ans += cnt;
            preCnt.put(preFix[i], cnt + 1);
        }
        System.out.println(ans);
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    } 
}
