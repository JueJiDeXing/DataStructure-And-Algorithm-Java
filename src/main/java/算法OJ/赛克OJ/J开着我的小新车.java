package 算法OJ.赛克OJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class J开着我的小新车 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int n = Int();
        int[] x = new int[n + 2];
        for (int i = 1; i <= n; i++) x[i] = Int();
        int L = Int(), Q = Int();
        x[0] = x[1] - L;
        x[n + 1] = x[n] + L;
        int[] toRight = getToRight(n, x, L);//toRight[i]:从x[0]开到x[i]的最少天数(x[i]的出发时间)
        int[] toLeft = getToLeft(n, x, L);//toLeft[i]:从x[n+1]开到x[i]的最少天数(x[i]的出发时间)
        System.out.println();
        for (int i = 0; i < Q; i++) {
            int left = Int(), right = Int();
            int ans;
            if (left > right) {
                ans = toLeft[right] - toLeft[left];
            } else {
                ans = toRight[right] - toRight[left];
            }
            System.out.println(ans == 0 ? 1 : ans);//ans==0
        }
    }

    private static int[] getToRight(int n, int[] x, int L) {
        int[] toRight = new int[n + 2];
        int day = 1;
        int curr = 0;
        for (int i = 1; i <= n; i++) {
            if (x[curr] + L < x[i]) {
                if (x[i] - x[i - 1] > L) {
                    day += 3;
                } else {
                    day++;
                }
                curr = i - 1;
            }
            toRight[i] = day;
        }
        return toRight;
    }

    private static int[] getToLeft(int n, int[] x, int L) {
        int[] toLeft = new int[n + 2];
        int day = 1;
        int curr = n + 1;
        for (int i = n; i > 0; i--) {
            if (x[curr] - L > x[i]) {
                if (x[i + 1] - x[i] > L) {
                    day += 3;
                } else {
                    day++;
                }
                curr = i + 1;
            }
            toLeft[i] = day;
        }
        return toLeft;
    }
}
