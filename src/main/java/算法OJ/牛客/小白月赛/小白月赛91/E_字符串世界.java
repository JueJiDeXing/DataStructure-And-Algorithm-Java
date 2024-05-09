package 算法OJ.牛客.小白月赛.小白月赛91;

import java.util.*;

/**
 已AC
 */
public class E_字符串世界 {
    /*
    给定长度为n的字符串S
    求满足条件的子串个数
    条件:
    子串包含子序列"ACCEPT",不含子序列"WA",且长度大于等于k
     */
    static char[] ac = "ACCEPT".toCharArray(), wa = "WA".toCharArray();
    static int n;
    static int[][] next;

    /**
     next[i][j]:从第i位开始(含),第一个字符j的出现位置
     */
    private static void getNext(char[] ss) {
        next = new int[n + 1][26];
        Arrays.fill(next[n], n);
        for (int i = n - 1; i >= 0; i--) {
            System.arraycopy(next[i + 1], 0, next[i], 0, 26);
            next[i][ss[i] - 'A'] = i;
        }
    }

    static int find(int l, char[] s) {
        l = next[l][s[0] - 'A'];
        for (int i = 1; i < s.length; i++) {
            if (l == n) return l;
            l = next[l + 1][s[i] - 'A'];
        }
        return l;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        char[] ss = s.toCharArray();
        getNext(ss);
        //for (int[] nxt : next) System.out.println(Arrays.toString(nxt));
        long ans = 0;
        for (int left = 0; left < n; left++) {
            //找到第一个ac位置,和第一个wa位置(没找到为n),它们中间的位置就是可行的
            int r1 = find(left, ac), r2 = find(left, wa);
            if (r1 == n) continue;//没找到ac
            r1 = Math.max(r1, left + k - 1);
            if (r2 > r1) ans += r2 - r1;
        }
        System.out.println(ans);
    }


}

