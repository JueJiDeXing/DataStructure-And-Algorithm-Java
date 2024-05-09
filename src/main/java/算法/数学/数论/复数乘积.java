package 算法.数学.数论;

public class 复数乘积 {
    /**
     s1 = a+bi, s2 = c+di
     ans = (a+bi)*(c+di) = ac-bd + (bc+ad)i
     */
    static int[] mul(int[] s1, int[] s2) {
        int a = s1[0], b = s1[1];
        int c = s2[0], d = s2[1];
        int ans1 = a * c - b * d;
        int ans2 = b * c + a * d;
        return new int[]{ans1, ans2};
    }
}
