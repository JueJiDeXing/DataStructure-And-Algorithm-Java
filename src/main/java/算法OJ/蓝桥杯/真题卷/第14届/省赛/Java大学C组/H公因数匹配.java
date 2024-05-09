package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 已AC
 */
public class H公因数匹配 {
    static int inf = 0x3f3f3f3f;//int型的极大值
    static HashMap<Integer, Integer> map = new HashMap<>();//质因子->第一次出现的下标

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        int ans_i = n, ans_j = 0;

        for (int k = 0; k < n; k++) {
            int pos = cal_factor(A[k], k);
            if (pos != -1 && ans_i > pos) {//找到了更小的下标pos
                ans_i = pos;//i更新为更小的pos
                ans_j = k;
            }
        }
        System.out.println((ans_i + 1) + " " + (ans_j + 1));
    }

    /**
     拆解x的质因子并记录,同时返回之前已经出现的质因子的最小下标

     @return 如果x的所有质因子都是第一次出现, 返回-1. 如果有重复出现的质因子,返回一个最小下标
     */
    static int cal_factor(int x, int idx) {//计算x的质因子,x在数组中的下标为idx
        int pos = inf;//pos存储在x之前出现的最早的重复质因子的下标,初始化为极大值
        for (int i = 2; i * i <= x; i++) {//遍历x所有可能的质因子i
            if (x % i != 0) continue;//不能整除,跳过
            // 是质因子
            while (x % i == 0) {
                x = x / i;
            }
            if (map.containsKey(i)) {//该质因子之前已存在
                if (map.get(i) != idx) pos = Math.min(pos, map.get(i));  //该质因子首次出现的位置不是当前元素的位置,则用pos记录之(pos不断以较小值更新)
            } else {
                map.put(i, idx);//该质因子是首次出现,记录位置并插入
            }
        }
        if (x > 1) {//最后可能还是剩下一个更大的质因子
            if (map.containsKey(x)) {
                if (map.get(x) != idx) pos = Math.min(pos, map.get(x));
            } else {
                map.put(x, idx);
            }
        }
        if (pos < inf) return pos;//找到了重复出现的质因子,返回最小的下标

        return -1;//否则返回0
    }


    private static void main2() {//3/9 6WA
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        int[] map = new int[1000001];
        Arrays.fill(map, -1);
        int ans_i = n, ans_j = n;
        for (int i = 0; i < n; i++) {
            if (map[A[i]] != -1) {
                if (ans_i > map[A[i]]) {
                    ans_i = map[A[i]];
                    ans_j = i;
                }
                continue;
            }
            for (int k = 1; k * A[i] <= 1000000; k++) {
                if (map[k * A[i]] == -1) {
                    map[k * A[i]] = i;
                }
            }
        }
        System.out.println((1 + ans_i) + " " + (1 + ans_j));
    }

    private static void main1() {//4/9 5TL
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) A[i] = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (gcd(A[i], A[j]) != 1) {
                    System.out.println((i + 1) + " " + (j + 1));
                    return;
                }
            }
        }
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
