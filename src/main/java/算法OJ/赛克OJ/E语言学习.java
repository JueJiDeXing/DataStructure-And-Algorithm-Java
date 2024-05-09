package 算法OJ.赛克OJ;

import java.util.Scanner;
/**
 已AC
 */
public class E语言学习 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] S = sc.next().toCharArray(), T = sc.next().toCharArray();
        int lenS = S.length, lenT = T.length;
        //求前缀H和X的数量
        int[][] countS = new int[lenS + 1][2], countT = new int[lenT + 1][2];//countS[i][0]:S[0,i)上的H数,countS[i][1]:S[0,i)上的X数
        for (int i = 1; i <= lenS; i++) {
            countS[i][0] = countS[i - 1][0];
            countS[i][1] = countS[i - 1][1];
            if (S[i - 1] == 'H') {
                countS[i][0]++;
            } else {
                countS[i][1]++;
            }
        }
        for (int i = 1; i <= lenT; i++) {
            countT[i][0] = countT[i - 1][0];
            countT[i][1] = countT[i - 1][1];
            if (T[i - 1] == 'H') {
                countT[i][0]++;
            } else {
                countT[i][1]++;
            }
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int si = sc.nextInt(), sj = sc.nextInt();
            int ti = sc.nextInt(), tj = sc.nextInt();
            int countHS = countS[sj][0] - countS[si - 1][0];
            int countXS = countS[sj][1] - countS[si - 1][1];
            int countHT = countT[tj][0] - countT[ti - 1][0];
            int countXT = countT[tj][1] - countT[ti - 1][1];
            countXS += 2 * countHS;//每个H可以换2个X
            countXT += 2 * countHT;
            int diffX = Math.abs(countXS - countXT);
            if (diffX % 3 == 0) {//差距需要3的倍数,XXX可以随意插入
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
