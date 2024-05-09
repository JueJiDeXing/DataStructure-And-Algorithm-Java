package 算法.数学.数运算模拟;

public class _415字符串相加 {
    public String addStrings(String num1, String num2) {
        int index = 0, carry = 0;
        int len1 = num1.length(), len2 = num2.length();
        StringBuilder sb = new StringBuilder();
        while (index < len1 || index < len2) {
            int n1 = index >= len1 ? 0 : num1.charAt(len1 - index - 1) - '0';
            int n2 = index >= len2 ? 0 : num2.charAt(len2 - index - 1) - '0';
            int n = n1 + n2 + carry;
            if (n >= 10) {
                n %= 10;
                carry = 1;
            } else {
                carry = 0;
            }
            sb.append(n);
            index++;
        }
        if (carry == 1) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }
}
