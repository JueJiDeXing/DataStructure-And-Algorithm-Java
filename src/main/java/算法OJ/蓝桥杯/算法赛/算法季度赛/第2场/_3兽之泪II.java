package 算法OJ.蓝桥杯.算法赛.算法季度赛.第2场;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class _3兽之泪II {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    public static void main(String[] args) {
        int t = nextInt();
        while (t-- > 0) {
            solve();
        }
    }

    static class Node {
        long x, y;

        public Node(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    static void solve() {
        int k = nextInt(), n = nextInt();
        Node[] A = new Node[k - 1];
        long sumA = 0;
        long min = Integer.MAX_VALUE;
        //(1)不考虑最后一只
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < k - 1; i++) {
            long x = nextInt(), y = nextInt();
            A[i] = new Node(x, y);
            sumA += x;
            min = Math.min(min, y);
        }
        for (int i = 0; i < k - 1; i++) {

        }


        //(2)打最后一只
        long x = nextInt(), y = nextInt();
        sumA += x;
        min = Math.min(min, y);
        if (n - k > 0) {
            ans = Math.min(ans, sumA + (long) (n - k) * min);
        }
        System.out.println(ans);

    }

    /**
     查找第一个大于target的值
     */
    static int binarySearch(Node[] A, long target) {
        int left = 0, right = A.length;//left:小于等于的
        while (left + 1 != right) {
            int middle = (left + right) >>> 1;//使用位运算防止溢出问题
            if (target < A[middle].x) {// 目标在左边
                right = middle - 1;
            } else if (A[middle].x < target) {// 目标在右边
                left = middle + 1;
            } else {//找到目标
                return middle;
            }
        }//未找到目标
        return left;

    }


}
