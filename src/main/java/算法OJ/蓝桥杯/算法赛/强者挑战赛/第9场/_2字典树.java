package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC
 */
public class _2字典树 {
    /*
    长度为n的数组A
    求 sum{ f(A[i]&A[j]) }
    其中f(x)表示x的二进制1的个数
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
     32位数组,前缀1计数

     */
    public static void main(String[] args) {
        int n = nextInt();
        int[] preFix = new int[32];
        long count = 0;
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            for (int j = 0; j < 32 && x > 0; j++) {
                if ((x & 1) == 1) {
                    count += preFix[j];
                    preFix[j]++;
                }
                x >>= 1;
            }
        }
        System.out.println(count);
    }

}
