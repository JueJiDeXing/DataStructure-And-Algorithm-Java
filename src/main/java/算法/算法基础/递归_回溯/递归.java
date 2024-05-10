package 算法.算法基础.递归_回溯;

import java.util.Arrays;
import java.util.LinkedList;

public class 递归 {


    /**
     <div color=rgb(155,200,80)>
     <h1>斐波那契递归:优化</h1>
     使用Memoization(记忆法,备忘录法)优化<br>
     用数组存储计算出的值,这样就可以免去大量重复的计算(空间换时间)

     </div>
     */
    public static int fibonacci(int n) {
        //原:return f(n-1)+f(n-2);//时间复杂度O(1.618^n)
        //改进后:时间复杂度O(n)
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);//将数组以-1填充,表示该位置的值未计算出来
        cache[0] = 0;
        cache[1] = 1;
        return fi(n, cache);
    }

    public static int fi(int n, int[] cache) {
        if (cache[n] != -1) {//不等于-1,说明该位置已经计算过了,可以直接使用
            return cache[n];
        }
        int x = fi(n - 1, cache);
        int y = fi(n - 2, cache);
        cache[n] = x + y;//存入数组

        return cache[n];

    }

    /**
     <div color=rgb(155,200,80)>
     <h1>递归:爆栈问题</h1>
     求和n+...+1,使用累加器<br>
     注:只有部分编译器支持尾递归优化,解决爆栈问题的根本在于把递归改成循环
     </div>

     @param n           递归求和的第n项
     @param accumulator 累加器
     */
    public static int sum(int n, int accumulator) {
        //尾调用:一个函数的返回值调用另一个函数,部分编译器可以对其做优化,使该函数提前释放内存,再去调用函数
        if (n == 1) {
            return 1 + accumulator;
        }
        return sum(n - 1, n + accumulator);//尾递归,tail rec

        /*递归的时间复杂度计算公式:(部分不能使用该公式,比如求和递归)
        T(n)=aT(n/b)+f(n)
        Tn:问题运行的总时间,n为数据规模
        a:子问题的个数,如:青蛙跳台阶每次1级或2级,a=2
        T(n/b):子问题运行的时间,b为数据规模子问题相较于上问题的缩小倍数
        f(n):递归以外的计算
        x=log_b a
             ┌Θ(n^x)        f(n)=O(n^x)且c<x     //比较x与c
        T(n)=┤Θ(n^x logn)   f(n)=Θ(n^x)
             └Θ(n^c)        f(n)=Ω(n^c)且c>x
        */
    }

    public static LinkedList<Integer> a;
    public static LinkedList<Integer> b;
    public static LinkedList<Integer> c;

    public static void init(int n) {
        for (int i = n; i >= 1; i--) {
            a.add(i);
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>递归:汉诺塔问题</h1>
     时间复杂度O(2^n)<br>
     </div>

     @param n 圆盘的个数
     @param a 圆盘的初始位置
     @param b 借助的位置
     @param c 最终移动到的位置
     */
    public static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b, LinkedList<Integer> c) {
        if (n == 0) {
            return;
        }
        //将上面的n-1一个从a移动到b,再把最下面的一个移到c,再把b的n-1个移到c
        move(n - 1, a, c, b);
        c.addLast(a.removeLast());
        move(n - 1, b, a, c);
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 递归:杨辉三角优化1</h1>
     二维数组记忆法<br>
     </div>

     @param triangle 二维记忆数组
     @param i,j      求[i][j]的元素
     @return
     */
    public static int element1(int[][] triangle, int i, int j) {
        if (triangle[i][j] > 0) {
            //已经计算过了,直接返回
            return triangle[i][j];
        }
        if (j == 0 || j == i) {
            triangle[i][j] = 1;
            return 1;
        }
        triangle[i][j] = element1(triangle, i - 1, j) + element1(triangle, i - 1, j - 1);
        return triangle[i][j];
    }

    public static void print1(int n) {
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", element1(triangle, i, j));
            }
            System.out.println();
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 递归:杨辉三角优化2</h1>
     一维(滚动)数组记忆法<br>
     </div>

     @param row 每一行的数据
     @param i   第i行
     */
    public static void createRow(int[] row, int i) {
        if (i == 0) {
            row[0] = 1;
            return;
        }
        for (int j = i; j > 0; j--) {
            row[j] = row[j] + row[j - 1];
        }
    }

    public static void print2(int n) {
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            createRow(row, i);
            for (int j = 0; j <= i; j++) {
                System.out.printf("%-4d", row[j]);
            }
            System.out.println();
        }
    }


}
