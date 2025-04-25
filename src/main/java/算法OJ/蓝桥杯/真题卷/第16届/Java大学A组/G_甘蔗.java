package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

import java.util.HashSet;
import java.util.Scanner;

/**
 40% = 8分
 */
public class G_甘蔗 {
    /*
     给定数组a和b
     求至少做多少次操作,才能使 ∀i,都有abs(a[i]-a[i+1]) ∈ b
     操作:
     将a[i]变为[0,a[i]-1]中的任意数字
     */
    static int n, m;
    static int[] a, b;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        a = new int[n];
        b = new int[m];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            b[i] = sc.nextInt();
            setB.add(b[i]);
        }
        if (n * m <= 64) {
            for (int f = a[0]; f >= 0; f--) {
                dfs(1, f, f == a[0] ? 0 : 1);
            }
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }

    static HashSet<Integer> setB = new HashSet<>();
    static int ans = 501;

    static void dfs(int i, int preH, int cut) {
        if (cut >= ans) return;
        if (i == n) {
            ans = cut;
            return;
        }
        for (int bi : setB) {
            int h1 = preH - bi;
            int h2 = preH + bi;
            if(h1>a[i])break;
            if (0 <= h1 && h1 <= a[i]) {
                dfs(i + 1, h1, cut + (h1 == a[i] ? 0 : 1));
            }
            if (h2 <= a[i]) {
                dfs(i + 1, h2, cut + (h2 == a[i] ? 0 : 1));
            }
        }
    }

}
