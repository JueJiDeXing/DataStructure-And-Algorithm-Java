package 算法OJ.牛客.五一集训1;

import java.util.Arrays;
import java.util.Scanner;

public class E希尔伯特排序 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //int[] a1 = {2, 3};
        //int[] a2 = {2, 4};
        //System.out.println(compare(a1, a2, 2));
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] A = new int[n][2];
        for (int i = 0; i < n; i++) {
            A[i][0] = sc.nextInt();
            A[i][1] = sc.nextInt();
        }
        Arrays.sort(A, ((o1, o2) -> compare(o1, o2, k)));
        for (int[] a : A) {
            System.out.println(a[0] + " " + a[1]);
        }
    }

    static int compare(int[] a, int[] b, int k) {
        if (a[0] == b[0] && a[1] == b[1]) return 0;
        int id1 = get(a, k), id2 = get(b, k);
        if (id1 != id2) return id1 - id2;
        if (id1 == 1) {
            int[] na = new int[]{a[1], a[0]};
            int[] nb = new int[]{b[1], b[0]};
            return -compare(na, nb, k - 1);
        }
        int kk = 1 << (k - 1);
        if (id1 == 2) {
            int[] na = new int[]{a[0] - kk, a[1]};
            int[] nb = new int[]{b[0] - kk, b[1]};
            return compare(na, nb, k - 1);
        }
        if (id1 == 3) {
            int[] na = new int[]{a[0] - kk, a[1] - kk};
            int[] nb = new int[]{b[0] - kk, b[1] - kk};
            return compare(na, nb, k - 1);
        }
        int[] na = new int[]{2 * kk - a[1] + 1, a[0]};
        int[] nb = new int[]{2 * kk - b[1] + 1, b[0]};
        return -compare(na, nb, k - 1);
    }


    static int get(int[] a, int k) {
        int kk = 1 << (k - 1);
        if (a[0] <= kk) {
            if (a[1] <= kk) {
                return 1;
            } else {
                return 4;
            }
        } else {
            if (a[1] <= kk) {
                return 2;
            } else {
                return 3;
            }
        }
    }
}
