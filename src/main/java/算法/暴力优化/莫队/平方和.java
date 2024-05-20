package 算法.暴力优化.莫队;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class 平方和 {
    /*
    长度为n的数组, a[i] ∈ [1,k]
    q个询问, 每次询问[l,r]上的 sum{ c[i]^2 | 1 ≤ i ≤ k, c[i]为数字i出现的次数 }

     */

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }


    static class Q {
        int l, r, k;// 询问区间, 第几个询问

        public Q(int l, int r, int k) {
            this.l = l;
            this.r = r;
            this.k = k;
        }
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
        int size = (int) Math.sqrt(n);//分块,每块的大小
        a = new int[n + 1];//数组
        int[] block = new int[n + 1];// block[i]: a[i]所在块序号
        for (int i = 1; i <= n; i++) {
            a[i] = I();
            block[i] = i / size;
        }
        int q = I();//q个询问
        Q[] question = new Q[q];
        for (int i = 0; i < q; i++) {
            int l = I(), r = I();
            question[i] = new Q(l, r, i);
        }
        //按左端点块号排序,块号相同,按右端点排序
        Arrays.sort(question, (a1, a2) -> {
            if (block[a1.l] == block[a2.l]) {
                return a1.r - a2.r;
            }
            return a1.l - a2.l;
        });
        int[] ans = new int[q];
        int l = 1, r = 0;//当前处理的区间
        for (int i = 0; i < q; i++) {
            while (l > question[i].l) add(--l);
            while (r < question[i].r) add(++r);
            while (l < question[i].l) sub(l++);
            while (r < question[i].r) sub(r--);
            ans[question[i].k] = res;
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
    static void add(int x) {
        res -= cnt[a[x]] * cnt[a[x]];
        cnt[a[x]]++;
        res += cnt[a[x]] * cnt[a[x]];

    }

    /**
     把x位置的贡献减去
     */
    static void sub(int x) {
        res -= cnt[a[x]] * cnt[a[x]];
        cnt[a[x]]--;
        res += cnt[a[x]] * cnt[a[x]];
    }
}
