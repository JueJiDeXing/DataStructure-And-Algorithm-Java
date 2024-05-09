package 算法OJ.蓝桥杯.真题卷.第8届.省赛.Java大学A组;

import java.io.*;

/**
 已AC
 */
public class G分巧克力 {
    /*
    k个人,N块巧克力,第i块大小为H[i]*W[i]
    需要从N块里切K个大小相同的正方形,并且尽可能大
    6*5的巧克力可以切出6块2*2的,或者2块3*3的
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    static class Node {
        int h, w;

        Node(int h, int w) {
            this.h = h;
            this.w = w;
        }
    }

    static int n, k;

    /**
     二分秒了
     */
    public static void main(String[] args) {
        n = Int();
        k = Int();
        Node[] rect = new Node[n];
        int right = 0;//二分上界 -> 能切出来的最大的正方形边长
        for (int i = 0; i < n; i++) {
            int h = Int(), w = Int();
            rect[i] = new Node(h, w);
            right = Math.max(right, Math.min(h, w) + 1);
        }

        int left = 1;//left:最后一个√;right:第一个x
        while (left + 1 != right) {
            int mid = (left + right) >>> 1;
            if (check(mid, rect)) {
                left = mid;//能切出K个mid*mid的巧克力
            } else {
                right = mid;//不能
            }
        }
        System.out.println(left);
    }

    /**
     判断rect能否k个切出len*len的正方形
     */
    static boolean check(int len, Node[] rect) {
        int t = k;
        for (Node node : rect) {
            if (node.h < len || node.w < len) continue;
            t -= (node.h / len) * (node.w / len);
            if (t <= 0) return true;
        }
        return false;
    }

}
