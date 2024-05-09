package 算法OJ.蓝桥杯.真题卷.第7届.省赛.Java大学A组;
/**
 已AC
 */
public class B生日蜡烛 {
    /*
    a + .. + a+t =236
    (2a+t) * (t+1) = 472
     */
    public static void main(String[] args) {
        for (int t = 0; t < 472; t++) {
            if (472 % (t + 1) != 0) continue;
            int aaa = 472 / (t + 1) - t;
            if (aaa < 0) break;
            if (aaa % 2 == 0) {
                System.out.println(aaa / 2);// 236(舍) 或 26
            }
        }
    }
}
