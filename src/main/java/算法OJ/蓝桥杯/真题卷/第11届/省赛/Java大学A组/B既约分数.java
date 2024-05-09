package 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组;
/**
 已AC
 */
public class B既约分数 {
    /*
    分子分母的互质
    求[1,2020]的分子分母既约分数的个数

     */
    public static void main(String[] args) {
        int ans = 0;
        for (int i = 1; i <= 2020; i++) {
            for (int j = 1; j <= 2020; j++) {
                if (gcd(i, j) == 1) {
                    ans++;
                }
            }
        }
        System.out.println(ans);//2481215
    }

    static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
