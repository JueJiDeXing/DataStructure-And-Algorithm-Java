package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第10场;

import java.util.*;

public class D众数区间 {
    /*
    分块
    出现次数大于sqrt(n)的数不超过sqrt(n)个,这些数O(n)暴力
    出现次数小于sqrt(n)的数,长度不超过2*sqrt(n)
     */

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt();

        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }
        HashMap<Long, LinkedList<Integer>> map = new HashMap<>();
        long ans = n;
        for (int r = 0; r < n; r++) {
            if (map.containsKey(a[r])) {
                List<Integer> list = map.get(a[r]);
                int pre = r;
                for (int j = 0; j < list.size(); j++) {
                    //cnt=j+2
                    Integer l = list.get(j);
                    int need = (r - l + 1) / 2 + 1;
                    if (need == j + 2) {
                        ans++;
                    }
                    if (j + 2 - need >= 0 && j + 2 - need <= 1 && pre - l > 1) {
                        ans++;
                    }
                    pre = l;
                }
            }
            map.computeIfAbsent(a[r], k -> new LinkedList<>()).addFirst(r);
        }
        System.out.println(ans);
    }
}
