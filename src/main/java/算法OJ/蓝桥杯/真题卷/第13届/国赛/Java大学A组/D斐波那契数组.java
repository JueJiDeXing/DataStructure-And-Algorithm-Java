package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学A组;

import java.util.Scanner;

/**
 已AC
 */
public class D斐波那契数组 {
    /*
    如果A=(a0,a1,..an-1)满足:
    1. n>=2
    2. a0=a1
    3. ai=ai-1+ai-2 (i>=2)
    则A为斐波那契数组
    输入一个数组A,问至少修改几个元素(大于0),A会变为斐波那契数组
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] split = sc.nextLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        System.out.println(minChange(arr));
    }

    private static int minChange(int[] arr) {
        int ans = arr.length;
        for (int a = 0; a <= 1000000; a++) { //a0->a  a1->a
            ans = Math.min(ans, check(arr, a));
        }
        return ans;
    }

    /**
     在前两项为first的情况下要修改多少次后面的数
     */
    private static int check(int[] arr, int first) {
        int success = 0;//匹配成功的数
        if (arr[0] == first) success++;//查看前两项是否匹配
        if (arr[1] == first) success++;
        int currVal;//当前项
        int p1 = first, p2 = first;//当前值的前两项
        for (int i = 2; i < arr.length; i++) {
            currVal = p1 + p2;
            if (currVal > 1000000) break;
            if (arr[i] == currVal) success++;//成功匹配
            p1 = p2;
            p2 = currVal;
        }
        return arr.length - success;
    }
}
