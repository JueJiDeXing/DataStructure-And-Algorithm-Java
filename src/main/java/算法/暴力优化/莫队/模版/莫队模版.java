package 算法.暴力优化.莫队.模版;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 莫队模版 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    public static void main(String[] args) throws Exception {
        In_arr();
        In_questions();
        Sort_questions();
        int[] ans = getAns();
        printAns(ans);
    }

    static int[] a;
    static int n;

    private static void In_arr() throws IOException {
        n = I();//长度为n的数组
        a = new int[n + 1];//数组
        for (int i = 1; i <= n; i++) a[i] = I();
    }

    static Q[] questions;
    static int q;

    private static void In_questions() throws IOException {
        // q个询问
        q = I();
        questions = new Q[q];
        for (int i = 0; i < q; i++) {
            int l = I(), r = I();
            questions[i] = new Q(l, r, i);
        }
    }

    private static void Sort_questions() {
        //按左端点块号排序,块号相同,按右端点排序
        int size = (int) Math.sqrt(n);//分块,每块的大小
        Arrays.sort(questions, (a1, a2) -> {
            if (a1.l / size != a2.l / size) return a1.l - a2.l;
            return (a1.l - a2.l) % 2 == 0 ? (a1.r - a2.r) : (a2.r - a1.r);
        });
    }

    private static int[] getAns() {
        int[] ans = new int[q];
        int l = 1, r = 0;//当前处理的区间
        for (int i = 0; i < q; i++) {
            while (l > questions[i].l) Add(--l);
            while (r < questions[i].r) Add(++r);
            while (l < questions[i].l) Sub(l++);
            while (r > questions[i].r) Sub(r--);
            ans[questions[i].id] = res;
        }
        return ans;
    }

    static int res = 0;

    /**
     把x位置的贡献加入res
     */
    static void Add(int x) {
        // ...
    }

    /**
     把x位置的贡献减去
     */
    static void Sub(int x) {
        // ...
    }

    private static void printAns(int[] ans) {
        for (int i = 0; i < q; i++) {
            System.out.println(ans[i]);
        }
    }

}
