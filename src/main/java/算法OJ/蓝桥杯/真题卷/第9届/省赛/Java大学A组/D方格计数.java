package 算法OJ.蓝桥杯.真题卷.第9届.省赛.Java大学A组;

/**
 已AC
 */
public class D方格计数 {
    /*
    原点,半径50000的圆
    有多少个1*1的方格被覆盖
     */

    public static void main(String[] args) {
        long ans = 0;
        //对称性,只要算1/4
        for (int x = 1; x < r; x++) {//枚举格子的右上角(x,y)
            for (int y = 1; y < r; y++) {
                if (check(x, y)) ans++;
            }
        }
        System.out.println(ans * 4);//7853781044
    }

    static long r = 50000;

    /**
     检查以(x,y)为右上角的1*1方格是否被覆盖
     */
    static boolean check(long x, long y) {
        return x * x + y * y <= r * r;
    }
}
