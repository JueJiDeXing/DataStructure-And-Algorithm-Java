package 算法OJ.南昌大学OJ.南昌大学第7届校赛;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 已AC
 */
public class E_四舍五入 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in), 65535);
    static StreamTokenizer st = new StreamTokenizer(bf);

    static String S() throws IOException {
        String res = bf.readLine();
        while (res.isEmpty()) res = bf.readLine();
        return res;
    }

    /*
1
99
1980
1985
9450
44395
58319
59454
123444
666666
911111

     */
    public static void main(String[] args) throws Exception {
        System.out.println(solve());
    }

    static String solve() throws IOException {
        String n = S();
        int len = n.length();
        char[] num = n.toCharArray();
        int first = 0;//找第一个大于等于5的位置
        for (; first < len; first++) {
            if (num[first] >= '5') break;
        }
        if (first == len) return n;//没有,无法"入", n已是最大
        Arrays.fill(num, first, len, '0');// 后面全部舍入
        if (first == 0) return "1" + new String(num);// 第1位入
        // 从第first位开始, 模拟向前依次做舍入操作
        for (int j = first - 1; j >= 0; j--) {
            num[j] += 1;
            if (num[j] < '5') break; //无法再入, 停止
            if (j != 0) num[j] = '0'; //首位特判
        }
        if (num[0] >= '5') {// 首位在后面经过舍入后大于等于5,可以入
            num[0] = '0';
            return "1" + new String(num);
        }
        //首位入不了
        return new String(num);
    }

}
