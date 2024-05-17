package 算法OJ.牛客.练习;

import java.util.Scanner;

/**
 已AC
 */
public class 译码 {
    /*
    aaa=0,aab=1,...
    给出数字求字符串
     */
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int len = sc.nextInt();
            String s = sc.next();
            for (int i = 0; i < len; i += 5) {
                String ans = cal(Integer.parseInt(s.substring(i, i + 5)));
                System.out.print(ans);
            }
            System.out.println();
        }
    }

    static String cal(int n) {
        char s1 = (char) (n % 26 + 'a');
        n /= 26;
        char s2 = (char) (n % 26 + 'a');
        n /= 26;
        char s3 = (char) (n % 26 + 'a');
        return s3 + "" + s2 + s1;
    }
}
