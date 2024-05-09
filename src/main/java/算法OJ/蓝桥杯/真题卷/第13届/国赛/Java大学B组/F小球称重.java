package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class F小球称重 {
    /*
    N个球(从1开始编号),有一个次品较轻,现在称了M次
    第k次称重,左边放a1,a2,...号球,右边放b1,b2,...号球,称重返回一个结果"<","=",">"
     求还有几个球有次品嫌疑
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();
        HashSet<String> set = new HashSet<>();
        boolean first = true;
        Set<String> equal = new HashSet<>();
        for (int i = 0; i < M; i++) {
            sc.nextLine();
            String[] A = sc.nextLine().split(" "), B = sc.nextLine().split(" ");
            char compare = sc.nextLine().charAt(0);
            if (compare == '=') {
                equal.addAll(Arrays.asList(A));
                equal.addAll(Arrays.asList(B));
            } else if (compare == '<') {//轻的一侧一定有小球,第一将其加入怀疑区,后面取交集
                if (first) {
                    first = false;
                    set.addAll(Arrays.asList(A));
                } else {//与A取交集
                    set.retainAll(Arrays.asList(A));
                }
            } else {
                if (first) {
                    first = false;
                    set.addAll(Arrays.asList(B));
                } else {
                    set.retainAll(Arrays.asList(B));
                }
            }
        }
        set.removeAll(equal);
        System.out.println(set.isEmpty() ? N - equal.size() : set.size());
    }


}
