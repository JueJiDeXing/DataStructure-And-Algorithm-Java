package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学C组;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 已AC
 */
public class D平均 {
    /**
     长度为n的数组,n为10的倍数,每个数都在[0,9]上
     更改第i数的代价为bi
     求将10种数的出现次数都等于n/10的最小代价
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, Queue<Integer>> map = new HashMap<>();//数字->代价,代价升序排列
        int[] count = new int[10];//计数
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt(), b = sc.nextInt();
            map.computeIfAbsent(num, k -> new PriorityQueue<>()).offer(b);
            count[num]++;
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            int more = count[i] - n / 10;//数字i的多余个数
            Queue<Integer> queue = map.get(i);
            for (int j = 0; j < more; j++) {//修改p个数字i
                ans += queue.poll();//挑最小代价进行修改
            }
        }
        System.out.println(ans);
        sc.close();
    }
}
