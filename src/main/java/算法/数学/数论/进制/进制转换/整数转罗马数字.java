package 算法.数学.数论.进制.进制转换;

public class 整数转罗马数字 {
    //特殊的进制?
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int[] div = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] ss = {"M", "CM", "D", "CD",  "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        for (int i = 0; i < div.length; i++) {
            int count= num / div[i];
            sb.append(ss[i].repeat(Math.max(0, count)));
            num = num % div[i];
        }
        return sb.toString();
    }
}
