package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第10场;

import java.util.Arrays;
import java.util.Scanner;
/**
 已AC(但错解)
 */
public class C买彩票 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int n = sc.nextInt(), x = sc.nextInt();
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        a[n] = x;
        int m = sc.nextInt(), y = sc.nextInt();
        int[] b = new int[m + 1];
        b[m] = y;
        for (int i = 0; i < m; i++) b[i] = sc.nextInt();
        int v1 = cal(a), v2 = cal(b);
        if (v1 == v2) {
            System.out.println("Draw");
        } else if (v1 > v2) {
            System.out.println("Clrlss");
        } else {
            System.out.println("Yaya");
        }
    }

    static int cal(int[] arr) {
        Arrays.sort(arr);
        int k = 1;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] >= k) {
                k++;
                arr[i] -= k;
            }
        }
        return k;
    }
}
