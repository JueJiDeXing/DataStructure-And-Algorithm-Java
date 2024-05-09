package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class I围栏 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] pos = new int[n][2];
        for (int i = 0; i < n; i++) {
            pos[i][0] = sc.nextInt();
            pos[i][1] = sc.nextInt();
        }
        long ans = 0;
        int[][] tempPos = new int[n - 2][2];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int kk = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j) tempPos[kk++] = pos[k];
                }
                //不使用(i,j)这两个点,求剩下的n-2个点围成的凸多边形面积
                long area = getArea(tempPos);
                ans = Math.max(ans, area);
            }
        }
        System.out.println(ans);
    }

    /**
     求凸多边形面积

     @param pos pos[i]=[xi,yi]点的坐标
     */
    private static long getArea(int[][] pos) {
        int n = pos.length;
        long area = 0;
        int[] p = pos[0];
        for (int i = 1; i < n - 1; i++) {
            int[] p1 = pos[i], p2 = pos[i + 1];
            area += cross(p1[0] - p[0], p1[1] - p[1], p2[0] - p[0], p2[1] - p[1]);
        }
        return area;
    }

    private static long cross(int x1, int y1, int x2, int y2) {
        return Math.abs((long) x1 * y2 - (long) x2 * y1);
    }

}
