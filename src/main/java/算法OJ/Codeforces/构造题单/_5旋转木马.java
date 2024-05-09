package 算法OJ.Codeforces.构造题单;

import java.io.*;
import java.util.HashSet;
import java.util.function.Function;

/**
 已AC
 */
public class _5旋转木马 {
    /*
    长度为n的环,如果a[i]!=a[i+1]则他们需要涂为不同的颜色
    输出最小颜色数,和涂色方案,颜色从1开始
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {
        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int t = nextInt();
        while (t-- > 0) {
            solve();
            pw.println();
        }
        pw.flush();
        pw.close();
    }

    static PrintWriter pw = new PrintWriter(System.out);

    static void solve() {
        int n = nextInt();
        int[] a = new int[n];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(a[i] = nextInt());
        //(1) 全部相同,只需要一种颜色,其余情况需要2或3种

        if (set.size() == 1) {
            pw.println(1);
            for (int i = 0; i < n; i++) pw.print(1 + " ");
            return;
        }
        //(2) 只有两种颜色,种类1填颜色1,种类2填颜色2
        if (set.size() == 2) {
            pw.println(2);
            for (int i = 0; i < n; i++) pw.print((a[i] == a[0] ? 1 : 2) + " ");
            return;
        }
        //(3) 长度为偶数,1和2交替即可
        if (n % 2 == 0) {
            pw.println(2);
            int k = 2;
            for (int i = 0; i < n; i++) pw.print((k = reverse.apply(k)) + " ");
            return;
        }
        //(4) 有3种及以上颜色, 且长度为奇数
        // 情况可以表示为: len1 a[x] len2 ,其中len1和len2都是偶数
        // 如果此时a[x]与a[x+1]相同, 那么a[x+1]可以作为长度为偶数情况(3)的开头
        // 所以检查中间有没有相邻相同的,如果有则需要2种颜色,否则需要3种颜色
        int sameIdx = -1;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] == a[i + 1]) {
                sameIdx = i;
                break;
            }
        }
        if (sameIdx == -1 && a[n - 1] == a[0]) sameIdx = n - 1; //环形
        //没有相邻相同的
        if (sameIdx == -1) {
            pw.println(3);
            int k = 2;
            for (int i = 0; i < n - 1; i++) pw.print((k = reverse.apply(k)) + " ");
            pw.print(3);
            return;
        }
        //有相邻相同的
        pw.println(2);
        int k = 1;
        for (int i = 0; i < n; i++) {
            pw.print(k + " ");
            if (i != sameIdx) k = reverse.apply(k);//a[i+1]是(3)的开头,此时a[i]填好后不reverse
        }
    }

    static Function<Integer, Integer> reverse = x -> x ^ 3;

}

