package 算法OJ.蓝桥杯.算法赛.小白入门赛.第3场;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class _6小蓝的反击 {
    /*
    长度为N的数组arr
    给定正整数A和B
    求arr上有多少个区间[L,R], m=MUL{ arr[i] | L<=i<=R}, 满足 m%A=0 && m%B!=0
     */

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static int n;
    static int[] a = new int[n];

    static class Pair {
        int factor, cnt;

        public Pair(int factor, int cnt) {
            this.factor = factor;
            this.cnt = cnt;
        }
    }

    /**
     求 m%A=0的区间数c1 和 m%A==0&&m%B==0的区间数c2
     ans = c1 - c2
     问题转变为在arr上统计 乘积为num的倍数的区间个数
     <p>
     如果直接枚举L和R,时间O(n^2)超时
     可以发现,如果[L,R]满足要求, 则[L,R+k]均满足要求
     那么只需要枚举左边界L,再二分一个最小的R即可
     <p>
     如果直接存储前缀积,数字太大,可以对数字进行质因子分解
     */
    public static void main(String[] args) {
        n = Int();
        int A = Int(), B = Int();
        a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Int();
        HashMap<Integer, Integer> map = new HashMap<>();
        compose(map, A);//分解A的因子到map
        long c1 = count(map);
        compose(map, B / gcd(A, B));//分解lcm(A,B)的因子,因为A的已经分解过了,所以分解B中的非公共因子
        long c2 = count(map);
        System.out.println(c1 - c2);
    }

    /**
     分解x的因子到map
     */
    static void compose(HashMap<Integer, Integer> map, long x) {
        for (int i = 2; i <= x / i; i++) {
            if (x % i != 0) continue;
            while (x % i == 0) {
                map.put(i, map.getOrDefault(i, 0) + 1);
                x /= i;
            }
        }
        if (x != 1) map.put((int) x, map.getOrDefault((int) x, 0) + 1);
    }

    static List<Pair> cnt = new ArrayList<>();
    static int[][] preSum;

    static long count(HashMap<Integer, Integer> map) {
        cnt.clear();
        map.forEach((k, v) -> cnt.add(new Pair(k, v)));
        int m = cnt.size();
        //计算数组a的因子前缀和,用于二分时的check
        preSum = new int[n + 1][m];//preSum[i][j]:a的前i个数的cnt[j]因子和
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                int k = cnt.get(j).factor;
                int x = a[i - 1];
                preSum[i][j] = preSum[i - 1][j];
                while (x % k == 0) {
                    x /= k;
                    preSum[i][j]++;
                }
            }
        }
        // 符合要求的区间总数
        long res = 0;

        for (int L = 1; L <= n; L++) {//枚举区间的左边界1~n
            //二分搜索最小的右边界R
            if (!check(L, n, m)) break;
            int left = L - 1, right = n + 1;//left:x  right:√
            while (left + 1 != right) {
                int mid = (left + right) / 2;
                if (check(L, mid, m)) right = mid;
                else left = mid;
            }
            //累加结果
            res += n - right + 1;//right=n+1时为0
        }
        return res;
    }

    static boolean check(int l, int r, int m) {
        for (int j = 0; j < m; j++) {
            Pair p = cnt.get(j);
            if (preSum[r][j] - preSum[l - 1][j] < p.cnt) return false;
        }
        return true;
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

}
