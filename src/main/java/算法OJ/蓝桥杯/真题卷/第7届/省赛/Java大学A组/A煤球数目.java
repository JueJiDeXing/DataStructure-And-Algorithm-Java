package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;
/**
 已AC
 */
public class A煤球数目 {
    public static void main(String[] args) {
        long ans = 0;
        long cur = 1, add = 2;
        for (int i = 1; i <= 100; i++) {
            ans += cur;
            cur += add;
            add++;
        }
        System.out.println(ans);//171700
    }
}
