package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
/**
 已AC
 */
public class H异或数列 {
    /*
    A和B的初始值为0
    有长度为n公共序列X
    A先手
    每次可以选择一个数X[i],将A的值或B的值异或上X[i]
    最后判断A和B的值,大的赢
    A赢输出1,B赢输出-1,平局输出0

    游戏进行T次 T<2e5
    每行第一个整数为序列x的长度n,后面n个数为序列x
    n<2e5,x[i]<20e20
    */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static long Long() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (long) st.nval;
    }

    /**
     最终的比较大小一定是看最高位,一个为1,一个为0,这样才比较出大小
     假设序列X中有t个x[i]最高位是第k位(二进制)
     <p>
     如果t有偶数个:最终状态的第k位一定是(0,0)或(1,1),无法从该位判断大小
     先看2个的情况,假设a选了一个,对于令一个它一定会使用,不管对a还是对b使用,ab的第k位都为0,这一位无法比较出ab的大小
     扩展到全体偶数的情况,偶数的数一定都会使用,最后要么ab在第k位都为0,要么都为1
     所以,在第k位上,如果有偶数个1,则无法从这位判断大小,需要去判断下一个位k-1
     <p>
     如果t有奇数个:最终状态的第k位一定是(1,0)或(0,1),可以从该位比较出大小
     如果只有1个:a会把它选走,b赢不了
     如果n为偶数:
     那么在第k位就有奇数个1和奇数个0
     如果a选了一个1,那么b有必胜策略,可以选0,这样如果a选1会减小a或者增大b,所以a也只能选0
     0有奇数个,b会选完最后一个0,a再选1,现在情况变为ab在第k位相同,还有奇数个1,没有0,b先手
     那么最后b一定在第k位高于a
     如果n为奇数:
     那么在第k位就有奇数个1和偶数个0
     ab拿走t-1个1后(如果中途b选了0,那a跟着选0即可),剩余1个1,情况变为ab在第k位相同,还有1个1,偶数个0(也可能没有0了),a先手
     a拿走最后一个1,ab首位为0给a异或,ab首位为1给b异或,a赢
     特殊情况:每位都有偶数个1,平局, 全体数异或为0=>平局
     */
    public static void main(String[] args) {
        int T = Int();
        for (int i = 0; i < T; i++) {
            int n = Int();
            int[] count = new int[32];
            long all = 0;
            for (int j = 0; j < n; j++) {
                long x = Long();
                all ^= x;
                for (int k = 0; k < 32; k++) {
                    if ((x & (1 << k)) != 0) {
                        count[k]++;
                    }
                }
            }
            if (all == 0) {
                System.out.println(0);
                continue;
            }
            //从最高位枚举
            for (int k = 31; k >= 0; k--) {
                if (count[k] % 2 == 0) continue;//偶数判断不了大小
                //奇数
                if (count[k] == 1) {
                    System.out.println(1);
                    break;
                }
                if (n % 2 == 0) {//奇数个1,奇数个0,b有必胜策略
                    System.out.println(-1);
                } else {//奇数个1,偶数个0,a有必胜策略
                    System.out.println(1);
                }
                break;
            }
        }
    }
}
