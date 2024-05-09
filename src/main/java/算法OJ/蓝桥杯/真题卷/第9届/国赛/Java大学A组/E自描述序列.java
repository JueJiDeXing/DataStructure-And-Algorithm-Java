package 算法OJ.蓝桥杯.真题卷.第9届.国赛.Java大学A组;

import java.util.*;
import java.util.List;

/**
 看不懂,反函数太绕了
 */
public class E自描述序列 {
    /*
    序列 1 2 2 3 3 4 4 4 5 5 5 6 6 6...
    n     1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
    G(n)  1 2 2 3 3 4 4 4 5 5  5  6  6  6  6  7  7  7  7  8  8  8  8
    G(n)表示序列中有多少个n,而G(n)自身就构成了这个序列
    给定一个n,求G(n)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        solve2(n);

    }

    /**
     G(n) = ?n
     n = ?G(n)
     求G(n)的反函数,G(n)的相同项保留n的最大的一个
     G(n)  1 2 3 4 5  6  7  8
     n     1 3 5 8 11 15 19 23 ...
     */
    private static void solve2(long n) {
        List<Long> list = new ArrayList<>();// list[i]:G(n)=i时n的最大值
        list.add((long) 0); // G(n)=0 , n无解
        list.add((long) 1); // G(n)=1, n=1
        list.add((long) 3); // G(n)=2, n=3
        int j = 2;
        for (int i = 2; list.get(i) < 1000000; i++) {
            if (list.get(j) < i + 1) {
                j++;
            }
            list.add(j + list.get(i));
        }
        System.out.println(list);
        //计算G(n)
        j = 1;//当前是哪个数字
        long yn = 0, gn = 0;
        for (long i = 1; i < 2000000000000000L; i++) {//i:j的出现个数
            //System.out.println("yn:"+yn+", gn"+gn);
            if (i > list.get(j)) j++;// i超过i的出现区域,变为下一个数字
            yn += i * j;//当前计算到的n
            gn += j;//上个数字的结束索引
            if (yn >= n) {
                System.out.println(gn - (yn - n) / i);
                return;
            }
        }
    }
}
