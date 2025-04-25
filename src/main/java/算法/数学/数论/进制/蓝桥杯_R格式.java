package 算法.数学.数论.进制;

import java.util.Scanner;

public class 蓝桥杯_R格式 {
    /*
    给定 int n, String<double> R
    求 R * 2^n, 四舍五入到整数为多少

    例:
    输入 2 3.14
    输出 13
    说明 3.14 * 4 = 12.56 -> 13
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder R = new StringBuilder(sc.next());
        R.reverse();// 反转, 方便*2时进位
        int idx = R.indexOf(".");// 记录一下小数点位置
        R.deleteCharAt(idx);// 删掉小数点,防止影响*2
        for (int i = 0; i < n; i++) {// R * 2^n
            // R * 2
            int carry = 0;
            for (int j = 0; j < R.length(); j++) {
                int sum = 2 * (R.charAt(j) - '0') + carry;
                R.setCharAt(j, (char) (sum % 10 + '0'));
                carry = sum / 10;
            }
            if (carry == 1) R.append('1');
        }
        // 四舍五入
        int dotNext = R.charAt(idx - 1) - '0';
        StringBuilder rInt = new StringBuilder(R.substring(idx));
        if (dotNext >= 5) {
            // R + 1
            int carry = 1;
            for (int i = 0; i < rInt.length(); i++) {
                int sum = rInt.charAt(i) - '0' + carry;
                rInt.setCharAt(i, (char) (sum % 10 + '0'));
                carry = sum / 10;
                if (carry == 0) break;// 没进位了, 退出
            }
            if (carry == 1) rInt.append('1');
        }
        rInt.reverse();// 转回来
        System.out.println(rInt);

    }
}
