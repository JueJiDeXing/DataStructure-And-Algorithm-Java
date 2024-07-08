package 算法.数学.计算几何;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class 牛客_最大平行四边形 {
    /*
    给定n个不重合的点,求最大平行四边形面积
    n<=1000
     */

    public static void main(String[] args) throws Exception {
        int n = I();
        long[][] points = new long[n][2];
        for (int i = 0; i < n; i++) {
            long x = I(), y = I();
            points[i] = new long[]{x, y};
        }

        HashMap<Line, List<int[]>> map = new HashMap<>();
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long[] p1 = points[i], p2 = points[j];
                //p1和p2组成直线
                long a = p2[1] - p1[1], b = p1[0] - p2[0];
                Line line = new Line(a, b, a * a + b * b);// 斜率, 长度相等的存一起
                map.computeIfAbsent(line, kk -> new ArrayList<>()).add(new int[]{i, j});
            }
        }

        long ans = maxArea(map, points);
        if (ans == 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans + ".0");
        }
    }

    private static long maxArea(HashMap<Line, List<int[]>> map, long[][] points) {
        long ans = 0;
        for (List<int[]> e : map.values()) {
            for (int i = 1; i < e.size(); i++) {
                for (int j = 0; j < i; j++) {
                    // 直线1: p1->p2, 直线2: p3->p4
                    long[] p1 = points[e.get(i)[0]], p2 = points[e.get(i)[1]];
                    long[] p3 = points[e.get(j)[0]], p4 = points[e.get(j)[1]];
                    ans = Math.max(ans, cross(p1, p2, p1, p3));// p1->p2 × p1->p3 与 p1->p2 × p1->p4是大小相等的
                }
            }
        }
        return ans;
    }

    //向量 A1->A2 × B1->B2
    static long cross(long[] A1, long[] A2, long[] B1, long[] B2) {
        return Math.abs((A2[0] - A1[0]) * (B2[1] - B1[1]) - (A2[1] - A1[1]) * (B2[0] - B1[0]));
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static int I() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static class Line {
        long a, b; //k= a/b
        long length;// 长度

        public Line(long a, long b, long length) {
            this.length = length;
            if (a == 0) {//斜率为0
                this.a = 0;
                this.b = 0;
                return;
            }
            long g = gcd(a, b);
            if (g == 0) {// 斜率为无穷大
                this.a = 1;
                this.b = 0;
                return;
            }
            a /= g;
            b /= g;
            if (b < 0) {//保证分母为正, (ab是需要参与哈希运算的)
                a = -a;
                b = -b;
            }
            this.a = a;
            this.b = b;
        }

        long gcd(long a, long b) {
            if (b == 0) return a;
            return gcd(b, a % b);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return a == line.a && b == line.b && length == line.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, length);
        }

        @Override
        public String toString() {
            return "K{" + a + "/" + b + ", len=" + length + "}";
        }
    }
}
