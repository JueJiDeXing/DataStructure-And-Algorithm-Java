package 算法OJ.蓝桥杯.真题卷.第10届.省赛.Java大学A组;

import java.io.*;
import java.util.HashSet;

/**
 已AC
 */
public class H修改数组 {
    /*
    给出一个可能存在重复值的长度为n的数组A
    从前往后依次修改A[i],A[0]不变
    每次给A[i]加1,如果加完A[i]还是在前面出现过,则继续加1,直到没有重复
    一直到A[n-1]修改完毕
    输出修改后的数组

     */static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     使用并查集
     并查集的每个数如果是未出现的,则指向自己
     如果出现过,则指向下一个数

     在添加一个数时,在并查集中从自己的位置开始进行查询,返回最小的未出现的值,并且因为这个数使用了,将其指向下一个数即可
     */
    public static void main(String[] args) {
        int n = Int();
        int[] A = new int[n];
        DisSet set = new DisSet();
        for (int i = 0; i < n; i++) {
            int a = Int();
            A[i] = set.add(a);
        }
        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    private static void solve1(int[] A, int n) {//朴素哈希表模拟,8AC 2TLE
        HashSet<Integer> set = new HashSet<>();
        A[0] = Int();
        set.add(A[0]);
        for (int i = 1; i < n; i++) {
            int a = Int();
            while (set.contains(a)) {
                a++;
            }
            A[i] = a;
            set.add(a);
        }
        for (int a : A) {
            System.out.print(a + " ");
        }
    }

    static class DisSet {
        int M = 10_0000;
        int[] fa;

        public DisSet() {
            fa = new int[M + 1];
            for (int i = 1; i <= M; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (x == fa[x]) return x;
            return fa[x] = find(fa[x]);
        }

        public int add(int x) {
            int rx = find(x);
            fa[rx] = rx + 1;
            return rx;
        }
    }
}
