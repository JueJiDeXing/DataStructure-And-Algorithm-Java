package 算法OJ.洛谷.普及down;

import java.util.*;

/**
 已AC
 */
public class P1015回文数 {
    /*
    给定两个数n和m,n为进制,n∈[1,10]+{16}
    现在对m进行操作
    每次操作将 m 和 按位颠倒的m 相加得到新的m
    求需要操作多少次才能将m变为回文数, 输出STEP={?}
    如果m初始就是回文数,则{?}为0
    如果次数大于30, 输出"Impossible!"
     */
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        char[] m = sc.next().toCharArray();
        List<Integer> num = new ArrayList<>();
        for (char c : m) {
            num.add(toInt(c, n));
        }
        int ans = 0;
        while (!is(num)) {
            num = add(num);
            ans++;
            if (ans > 30) {
                System.out.println("Impossible!");
                return;
            }
        }
        System.out.println("STEP=" + ans);
    }

    /**
     ans = num + reverse(num)
     num为n进制数
     */
    static List<Integer> add(List<Integer> num) {
        int len = num.size();
        List<Integer> ans = new ArrayList<>();
        int i = len - 1, j = 0;
        int carry = 0;
        while (i >= 0) {
            int sum = num.get(i) + num.get(j) + carry;
            carry = sum / n;
            sum %= n;
            ans.add(sum);
            i--;
            j++;
        }
        if (carry != 0) ans.add(carry);
        return ans;
    }

    static int toInt(char ch, int n) {
        if (n != 16) return ch - '0';
        if (ch == 'A') return 10;
        if (ch == 'B') return 11;
        if (ch == 'C') return 12;
        if (ch == 'D') return 13;
        if (ch == 'E') return 14;
        if (ch == 'F') return 15;
        return ch - '0';
    }

    static boolean is(List<Integer> m) {
        int len = m.size();
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            if (!m.get(i).equals(m.get(j))) {
                return false;
            }
        }
        return true;
    }
}
