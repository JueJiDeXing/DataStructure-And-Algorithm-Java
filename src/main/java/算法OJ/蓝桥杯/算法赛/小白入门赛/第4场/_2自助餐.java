package 算法OJ.蓝桥杯.算法赛.小白入门赛.第4场;

import java.util.*;
/**
 已AC
 */
public class _2自助餐 {
    /*
    餐盘种类         价格
    yuanxing         1
    zhengfangxing    2
    changfangxing    3
    sanjiaoxing      4
    tuoyuanxing      5
    liubianxing      6
    输入n个字符串,求价格总和
     */
    static HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("yuanxing", 1);
        map.put("zhengfangxing", 2);
        map.put("changfangxing", 3);
        map.put("sanjiaoxing", 4);
        map.put("tuoyuanxing", 5);
        map.put("liubianxing", 6);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int sum = 0;
        String[] strs = sc.nextLine().split(" ");
        for (String s : strs) {
            sum += map.get(s);
        }
        System.out.println(sum);
    }
}
