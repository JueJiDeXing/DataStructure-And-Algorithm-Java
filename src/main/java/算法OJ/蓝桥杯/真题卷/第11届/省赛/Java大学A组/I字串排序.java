package 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组;

import java.util.*;

/**
 已AC,但是有个部分没看懂
 */
public class I字串排序 {
    /*
    找到一个最短的字符串,它恰好需要V次冒泡排序的交换才能排好
    如果有多个最短字符串，输出字典序最小的一个
    V<1e4
     */

    /**
     贪心<br>
     首先确定最短长度<br>
     再从前往后依次填入字符,填入字符从最小的开始尝试,不能填入再检查更大的字符,确保字典序最小<br>
     <p>
     1.如何确定长度
     从len=1开始枚举,判断长度为len的序列,是否存在逆序数大于V的排列,最小的满足条件的len就是最短长度<br>
     2.如何判断一个字符能否填入<br>
     设当前已填字符串的逆序数为now, 计算填入字符x的增加量, 以及当前字符串状态下后面n个位置可提供的最大逆序数增加量<br>
     三者相加如果大于等于V,说明字符x是可填入的,将其填入即可<br>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int len = getLen(V);//符合交换次数V，字符串长度最小值
        //枚举每个位置上的字母,字典序小
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < 26; j++) {
                //检查能否放字母j,如果可以sum[j]++
                if (check(j, len - i, V)) {
                    ans.append((char) (j + 'a'));
                    break;
                }
            }
        }
        System.out.println(ans);
    }

    private static int getLen(int V) {
        int len;
        for (int i = 1; ; i++) { //从1开始枚举长度(二分?),直到len可以产生的最大逆序对大于V
            if (getMax(i) >= V) {
                len = i;
                break;
            }
        }
        return len;
    }

    /**
     使用26个字母,组成长度为len的排列中,最大逆序数
     */
    static int getMax(int len) {
        int t = len / 26;
        int r = len % 26;
        return ((len - (t + 1)) * (t + 1) * r + (26 - r) * t * (len - t)) / 2;
    }

    static int now; //当前已经构造好的那一部分字符串逆序对个数
    static int[] cnt = new int[26];
    static int[] sum = new int[26];//当前构造的部分字符串包含字母a~z个数

    //当前放字母x,后边还有n个位置，看是否满足V
    static boolean check(int x, int n, int V) {
        int add1 = 0;//在当前位置放x,新增的逆序对个数
        for (int j = 25; j >= x + 1; j--) add1 += sum[j];
        sum[x]++;//当前位置放x,sum[x]++

        //后边剩余n个位置
        int add2 = 0;//在当前位置放x,后边的逆序对个数最大值
        Arrays.fill(cnt, 0);//当前位置后续部分包含字母a~z个数
        for (int L = 0; L < n; L++) {
            int max = -1;//后续L位置上，放字母新增逆序对个数最大值
            int pos = 0;//放置的字母
            int num = 0;//已经放置的大于第j个字母的字符个数
            for (int j = 25; j >= 0; j--) {
                //L:在字母x后面当前放置了L个字符; cnt[j]:x~L(小于?)等于字母j的个数; num:x前面大于字母j的字符个数
                // L-cnt[j]:因为这L个字符的顺序不影响和x前面字符的逆序数,所以可以随意调整这L个字符的顺序
                // ==> c:L前面大于字母j的字符个数(填入字母j的新增逆序数)
                int c = (L - cnt[j]) + num;
                if (c > max) {
                    max = c;
                    pos = j;
                }
                num += sum[j];
            }
            add2 += max;
            cnt[pos]++;
        }
        if (now + add1 + add2 >= V) {//当前 + 放x新增 + 后面的最大生成
            now += add1;
            return true;
        }
        sum[x]--;//放弃在当前位置放置x
        return false;
    }
}
