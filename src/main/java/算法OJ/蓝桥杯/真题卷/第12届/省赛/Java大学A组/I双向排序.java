package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;

import java.io.*;
import java.util.*;
/**
 已AC
 */
public class I双向排序 {
    /*
    初始数组[1,n]
    m次操作
    每次操作给出一个操作类型p, p=0 将 [1,q]降序 , p=1 将[q,n]升序
    求最后的数组
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int Int() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     (1) 第一个有效操作是降序,初始的升序操作可以删除
     (2) 连续的同种操作,只需要保留最长的一个,其余的可以删除
     (3) 由(2)得现在是交替操作,对于交替操作,如果遇到更大的操作,那么前一对交替可删除
     降序   a
     升序      b
     降序         c   -> 前一对可以删除
     (4) (交替操作)每次操作会翻转一段区间,另一段区间不变;而且翻转的区间一定在向中间缩小,如果区间变大,前面的两次都会在(3)删除
     原始 1 2 3 4 5 6 7 8 9
     降序           x
     结果 6 5 4 3 2 1|7 8 9  -> [1,6]翻转,[7,9]不变
     升序     x
     结果 6 5|1 2 3 4|7 8 9  -> [1,2]不变,[3,6]翻转,[7,9]不变
     降序         x
     结果 6 5|3 2 1|4|7 8 9  -> [1,2]不变,[3,5]翻转,[6,9]不变
     ->  可以发现[7,9]区间是从n开始的,[1,2]区间紧挨着[7,9]区间,[6,6]区间紧挨着[1,2]区间

     所以从n开始可以填充[7,9]区间,再填充[1,2]区间,,[6,6]对于剩余[3,5]区间看它翻转了几次即可(上图翻转奇数次,[3,5]升序,从左侧开始填充)
     如下:
     1 2 3 4 5 6 7 8 9  k=9
     l               r
     翻转[1,6],[7,9]后面都不会改变,从9位置开始填充
     6 5 4 3 2 1        k=6
     l         r
     翻转[3,6],[1,2]后面都不会改变,从1位置开始填充
         1 2 3 4        k=4
         l     r
     翻转[3,5],[6,6]后面都不会改变,从6位置开始填充
         3 2 1          k=3
         l   r
     最后剩余的区间,因为它翻转了奇数次,是降序,所以从左侧填充

             lr
     */
    public static void main(String[] args) {
        int n = Int(), m = Int();
        LinkedList<Data> stack = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int type = Int(), q = Int();
            if (type == 0) {
                while (!stack.isEmpty() && stack.peek().type == type) {
                    Data pop = stack.pop();
                    q = Math.max(q, pop.q);//同种操作保留最大的一个
                }
                while (stack.size() >= 2) {//交替操作时,如果遇到更大的操作,那么前一对交替可删除
                    Data first = stack.pop(), peek = stack.peek();
                    if (peek.q <= q) {
                        stack.pop();
                    } else {
                        stack.push(first);
                        break;
                    }
                }
            } else {
                if (stack.isEmpty()) continue;//第一个有效操作一定是降序的
                while (!stack.isEmpty() && stack.peek().type == type) {
                    Data pop = stack.pop();
                    q = Math.min(q, pop.q);//同种操作保留最大的一个
                }
                while (stack.size() >= 2) {//交替操作时,如果遇到更大的操作,那么前一对交替可删除
                    Data first = stack.pop(), peek = stack.peek();
                    if (peek.q >= q) {
                        stack.pop();
                    } else {
                        stack.push(first);
                        break;
                    }
                }
            }
            stack.push(new Data(type, q));
        }
        //现在栈内是有效操作
        //每次操作是翻转一个区间,令一个区间不动,且翻转的区间一直在向内缩减,所以两端可以交替填出数字
        int k = n, l = 1, r = n;
        int[] ans = new int[n + 1];
        int s = stack.size();
        while (!stack.isEmpty() && l <= r) {
            Data data = stack.pollLast();
            if (data.type == 0) {
                while (r > data.q && l <= r) ans[r--] = k--;
            } else {
                while (l < data.q && l <= r) ans[l++] = k--;
            }
        }
        //剩余中间的被翻转区间未填充
        if (s % 2 == 1) {//第一个操作是降序,s为奇数,那么最后一个操作也是降序
            while (l <= r) ans[l++] = k--;
        } else {
            while (l <= r) ans[r--] = k--;
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    static class Data {
        int type, q;//type=0:降序[1,q]; type=1:升序[q,n]

        Data(int type, int q) {
            this.type = type;
            this.q = q;
        }
    }

    private static void solve1(int n, int m) {//直接排序法 6AV 4TLE
        Integer[] arr = new Integer[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = i;

        for (int i = 0; i < m; i++) {
            int type = Int(), q = Int();
            if (type == 0) {
                Arrays.sort(arr, 1, q + 1, (o1, o2) -> o2 - o1);
            } else {
                Arrays.sort(arr, q, n + 1);
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + " ");
        }
    }


}
