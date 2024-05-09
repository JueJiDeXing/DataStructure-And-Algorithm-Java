package 算法OJ.蓝桥杯.算法赛.小白入门赛.第8场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
/**
 已AC
 */
public class _5美丽圆环 {
    /*
    一个长度为n的环形数组A
    需要进行操作,使其满足任意连续的三个数都是 (非严格) 递增/递减 的
     操作: 将数组的任意一个数变为任意大小
     可以随意交换数组的元素,不计次数
     求最少的操作数
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int T = Int();
        for (int i = 0; i < T; i++) {
            int n = Int();
            int[] A = new int[n], count = new int[n + 1];
            for (int j = 0; j < n; j++) {
                A[j] = Int();
                count[A[j]]++;
            }
            Arrays.sort(A);
            if (A[0] == A[n - 1]) {//数都相等,不需要操作
                System.out.println(0);
                continue;
            }
            if (n == 2) {//两个不相等的数,操作1次
                System.out.println(1);
                continue;
            }
            if (n == 3) {//3个数
                if (A[0] != A[1] && A[1] != A[2] && A[0] != A[2]) {//等不相等,需要改成全部相等,2次
                    //1 2 3 -> 1 1 1 或 2 2 2 或 3 3 3
                    System.out.println(2);
                    continue;
                }
                //其中两个相等,把另一个改一下,1次
                // 1 1 2 ->  1 1 1
                System.out.println(1);
                continue;
            }
            if (count[A[0]] == 1 && count[A[n - 1]] == 1) {//排序后,两端都是1个的
                // 当下一项有连续时,可以将这一项改变后移到另一端配对,这样只需要1次
                // 否则两端都要变
                if (count[A[1]] > 1 || count[A[n - 2]] > 1) {//有连续,1次
                    // 1 2 2 3 5 -> 将1改成5,移动到最右边, 1次
                    System.out.println(1);
                } else {//没有连续,将两端都变为它的下一项
                    // 1 2 3 4 5 -> 将1改成2 将5改成4, 2次
                    System.out.println(2);
                }
                continue;
            }

            if (count[A[0]] == 1 || count[A[n - 1]] == 1) {  //排序后,有1端不连续,1次
                // 1 2 3 4 4  -> 将1变为2
                System.out.println(1);
            } else {//排序后两端都连续,不需要改变
                //1 1 2 3 4 4
                System.out.println(0);
            }

        }
    }
}
