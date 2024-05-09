package 算法OJ.Codeforces.构造题单;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashMap;

/**
 不懂
 */
public class _6无用数组 {
    static int n, M = 2001;
    static int[] a = new int[M], l = new int[M], r = new int[M];
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int T = nextInt();
        while (T-- > 0) solve();

    }

    static int cnt = 0;

    static void solve() {
        HashMap<Integer, Integer> map = new HashMap<>();
        n = nextInt() * 2;
        for (int i = 1; i <= n; i++) {
            a[i] = nextInt();
            map.put(a[i], map.getOrDefault(a[i], 0) + 1);
        }
        Arrays.sort(a, 1, n + 1);
        boolean flag = false;
        int i = 1, j = 0;
        for (; i < n; i++) {
            if (check(i, map) && cnt <= n) {
                j = i;
                flag = true;
                break;
            }
        }
        if (flag) {
            System.out.println("Yes");
            System.out.println(a[n] + a[j]);
            for (int k = 1; k <= cnt; k++) System.out.println(l[i] + " " + r[i]);
        } else
            System.out.println("No");

    }

    static boolean check(int j, HashMap<Integer, Integer> map) {
        l[1] = a[n];
        r[1] = a[j];
        cnt = 1;
        int now = a[n], num = n - 2;
        sub(map, a[n]);
        sub(map, a[j]);
        for (int i = n - 1; num > 0; i--) {
            if (!map.containsKey(a[i])) continue;
            if (!map.containsKey(now - a[i]) || a[i] * 2 == now
                    && map.getOrDefault(a[i], 0) <= 1)
                return false;
            cnt++;
            l[cnt] = a[i];
            r[cnt] = now - a[i];
            sub(map, a[i]);
            sub(map, now - a[i]);
            now = a[i] - 2;
        }
        return (num == 0 && cnt * 2 == n);
    }

    static void sub(HashMap<Integer, Integer> map, int x) {
        if (!map.containsKey(x)) return;
        int v = map.get(x);
        if (v == 1) {
            map.remove(x);
        } else {
            map.put(x, v - 1);
        }
    }


}
