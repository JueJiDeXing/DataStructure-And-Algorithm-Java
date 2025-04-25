package 算法.数学.博弈论;

/**
 难度:中等
 */
public class _1927求和游戏 {
    /*
    给定一个偶数长度数串num, 其中的某些位置为'?'

    Alice先手,Bob后手, 每次选择一个'?'替换为0~9的一个数字, 直到没有'?'

    Bob胜利条件: num的前半部分之和 == num的后半部分之和
    Alice胜利条件: num的前半部分之和 != num的后半部分之和

    最优策略下,若Alice必胜返回true,否则返回false
     */
    public boolean sumGame(String num) {
        char[] arr = num.toCharArray();
        int n = arr.length, sum1 = 0, cnt1 = 0, sum2 = 0, cnt2 = 0;
        // 统计左右
        for (int i = 0; i < n / 2; ++i) {
            sum1 += (arr[i] == '?' ? 0 : (arr[i] - '0'));
            cnt1 += (arr[i] == '?' ? 1 : 0);
        }
        for (int i = n / 2; i < n; ++i) {
            sum2 += (arr[i] == '?' ? 0 : (arr[i] - '0'));
            cnt2 += (arr[i] == '?' ? 1 : 0);
        }
        // 奇数个问号, 最后一步由Alice走, 此时最多有一个数字可以让左边==右边,Alice不选那个数就行
        if ((cnt1 + cnt2) % 2 == 1) {
            return true;
        }
        // 令 d = sum1 - sum2
        // (1) Alice增大d(使最终d>0), 在左侧放a个9,右侧放(cnt1+cnt2)/2 - a个0
        // Bob为了减小d, 在右侧最多放cnt2-((cnt1+cnt2)/2 - a)=(cnt2-cnt1)/2+a个9, 左侧放cnt1-a个0
        // d = sum1 - sum2 + 9a - 9[(cnt2-cnt1)/2+a] = sum1 - sum2 + 9(cnt1-cnt2)/2
        // 当d>0时表示Bob无法拉回差距, Alice必胜
        // (2) Alice增大d(使最终d<0), 同理 d' = sum1 - sum2 - 9[(cnt1+cnt2)/2-a] + 9(cnt1-a) =
        // sum1 - sum2 + 9(cnt1-cnt2)/2
        // 当d'<0时Alice必胜
        // 由于d'=d, 即d>0和d<0时Alice必胜
        // 即 sum1 - sum2 + 9(cnt1-cnt2)/2 不为0 时Alice必胜
        return 9 * (cnt1 - cnt2) / 2 + sum1 - sum2 != 0;
    }
}
