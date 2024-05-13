package 算法OJ.洛谷.普及_提高down;

import java.util.Scanner;

/**
 已AC
 */
public class P1246编码 {
    /*
    按a,b,...z,ab,ac,...az,abc,abd,...编码
    a=1, b=2...
    字符串为升序, len<7
    给出一个字符串,求它的编码,如果字符串不在编码表中,则输出0
     */

    /**
     记给出的字符串为s,长度为n
     如果有k个字符串比s小,那么它的编码就是k+1
     <p>
     长度为1的字符串有26个
     长度为2的字符串有C(26,2)个
     ...
     如果s长度为n,则长度为1~n-1的字符串都比s小, 一共sum{ C(26,i) | 1<=i<=n-1 }
     对于同样长度为n的字符串:
     (1) 第一位不同, 第一位有 [a,s[1])这些选择
     如果选择了字符序为x的字符,则剩余的n-1位需要比x大,C(26-x,n-1)
     (2) 第一位相同, 第一位只能选择s[1]才可能达成比s小
     那么考虑第二位又是相同的问题
     所以枚举每一位进行判断
     对于第i位(前面i-1位字符相同), 第i位如果严格小于,则字符的选择范围是(s[i-1],s[i]), 方案数为C(26-x,n-i)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) >= s.charAt(i + 1)) {
                System.out.println(0);
                return;
            }
        }
        long ans = 0;
        for (int i = 1; i < n; i++) ans += c(26, i);//计算出位数比它小的单词数
        for (int i = 0; i < n; i++)//枚举每一位
            for (char j = i == 0 ? 'a' : (char) (s.charAt(i - 1) + 1); j < s.charAt(i); j++)//注意考虑边界
                ans += c('z' - j, n - i - 1);//因为字符串下标从0开始，所以是n-i-1而不是n-i
        System.out.println(ans + 1);
    }

    static int c(int n, int m) {
        if (m == 0) return 1;
        int mut = 1;
        for (int i = n; i > n - m; i--) mut *= i;
        for (int i = m; i > 1; i--) mut /= i;
        return mut;
    }

}
