package 算法.暴力优化.莫队;

import 算法.暴力优化.莫队.模版.Q;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 平方和 {
    /*
    长度为 n 的数组, a[i] ∈ [1,k]
    q个询问, 每次询问[l,r]上的 sum{ c[i]^2 | c[i]为数字i出现的次数, i ∈ [1,k] }
     */

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    static int[] a;
    static int n, k;

    /**
     用一个cnt数组维护一下出现次数, 在移动l,r时, 只需要将原位置的平方减去,再加移动后的即可
     */
    public static void main(String[] args) throws Exception {
        n = I();//长度为n的数组
        k = I();
        cnt = new int[k + 1]; //cnt[i]:数字i出现的次数
        a = new int[n + 1];//数组
        for (int i = 1; i <= n; i++) a[i] = I();
        int q = I();//q个询问
        Q[] questions = new Q[q];
        for (int i = 0; i < q; i++) {
            int l = I(), r = I();
            questions[i] = new Q(l, r, i);
        }
        //按左端点块号排序,块号相同,按右端点排序
        int size = (int) Math.sqrt(n);//分块,每块的大小
        Arrays.sort(questions, (a1, a2) -> {
            if (a1.l / size != a2.l / size) return a1.l - a2.l;
            return (a1.l - a2.l) % 2 == 0 ? (a1.r - a2.r) : (a2.r - a1.r);
        });
        int[] ans = new int[q];
        int l = 1, r = 0;//当前处理的区间
        for (int i = 0; i < q; i++) {
            Q qs = questions[i];
            while (l > qs.l) Add(--l);
            while (r < qs.r) Add(++r);
            while (l < qs.l) Sub(l++);
            while (r < qs.r) Sub(r--);
            ans[qs.id] = res;
        }
        for (int i = 0; i < q; i++) {
            System.out.println(ans[i]);
        }
    }

    static int res = 0;
    static int[] cnt;

    /**
     把x位置的贡献加入res
     */
    static void Add(int x) {
        res -= cnt[a[x]] * cnt[a[x]];
        cnt[a[x]]++;
        res += cnt[a[x]] * cnt[a[x]];

    }

    /**
     把x位置的贡献减去
     */
    static void Sub(int x) {
        res -= cnt[a[x]] * cnt[a[x]];
        cnt[a[x]]--;
        res += cnt[a[x]] * cnt[a[x]];
    }
}
