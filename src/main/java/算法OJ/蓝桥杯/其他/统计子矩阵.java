package 算法OJ.蓝桥杯.其他;

import java.io.*;

public class 统计子矩阵 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = nextInt(), m = nextInt(), k = nextInt();
        int[][] a = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = nextInt();
                //列前缀和数组
                a[i][j] += a[i - 1][j];
            }
        }
        long ans = 0;
        for (int up = 1; up <= n; up++) {//遍历上边界
            for (int down = up; down <= n; down++) {//遍历下边界
                //寻找可行的列区域[l,r]
                int l = 1;//滑动窗口的左右端点
                int sum = 0;//[l,r](二维)区间的累计和
                for (int r = 1; r <= m; r++) {//遍历右端点，根据区间和调整左端点
                    sum += a[down][r] - a[up - 1][r];//加上右端点处的和
                    while (sum > k) {//大了,左端点右移
                        sum -= a[down][l] - a[up - 1][l];
                        l++;
                    }
                    ans += r - l + 1;//以[down,up]为上下边界,以r为右端点的区间有r-l+1个
                }
            }
        }
        System.out.println(ans);
    }
}
