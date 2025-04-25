package 算法.数学.数论.gcd和lcm;

import java.util.Scanner;

public class 蓝桥15C_宝石组合 {
    /*
    给定一个数组H, 从中选择下标不同的三个数
    S = abc * lcm(a,b,c) / [lcm(a,b) * lcm(a,c) * lcm(b,c)]
    求S最大时, 选择的方案, 按字典序输出选择的三个数
    如果有多个方案, 选择字典序最小的
     */

    /**
     S = gcd(a,b,c)
     反向枚举S, 统计S的倍数有没有3个
     有3个时输出这三个数
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] H = new int[n];
        int maxH = 0;
        for (int i = 0; i < n; i++) {
            H[i] = sc.nextInt();
            maxH = Math.max(maxH, H[i]);
        }
        //cnt[i]: i有多少个
        int[] cnt = new int[maxH + 1];
        for (int h : H) cnt[h]++;

        // S = gcd(a,b,c), 取最大
        int[] nums = new int[3];// 结果数组
        for (int S = maxH; S >= 1; S--) {// S从最大开始枚举
            // 找S的倍数, 看有没有3个
            int p_nums = 0;
            find:
            for (int i = S; i <= maxH; i += S) {//i为S的倍数, 有cnt[i]个
                // 把i放入结果数组
                for (int k = 0; k < cnt[i]; k++) {
                    nums[p_nums++] = i;
                    if (p_nums == 3) break find;
                }
            }
            if (p_nums == 3) {
                for (int h : nums) System.out.print(h + " ");
                return;
            }
        }
    }


}
