package 算法OJ.蓝桥杯.真题卷.第11届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class G子串分值 {
    /*
    f(str)表示str中只出现一次的字母的个数,str由小写字母组成
    给定字符串S,求S的所有子串s的f(s)之和
     */

    /**
     对每个字母求贡献<br>
     S[i]的贡献为包含S[i]且不存在两个以上S[i]的子串个数<br>
     S[i],假设左边离它left距离没有S[i],右边离它right距离没有S[i]<br>
     a ...  a  ...  a
     left   i    right
     对于仅包含一个S[i]的子串, S[i]左边可选择长度为[0,left],有left+1个选择, 右侧同理有right+1个选择<br>
     根据组合计数,ans+=(left+1)*(right+1)<br>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.next().toCharArray();
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            //寻找左右的s[i],可预处理优化
            int left = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (s[j] == s[i]) break;
                left++;
            }
            int right = 0;
            for (int j = i + 1; j < s.length; j++) {
                if (s[j] == s[i]) break;
                right++;
            }
            ans += (left + 1) * (right + 1);//s[i]的贡献
        }
        System.out.println(ans);

    }

    private static void solve(char[] s) {//5AC 5TLE
        int ans = 0;
        for (int i = 0; i < s.length; i++) {
            HashSet<Character> setOne = new HashSet<>(), setTwo = new HashSet<>();
            int sum = 0;
            for (int j = i; j < s.length; j++) {
                if (setOne.contains(s[j])) {
                    setTwo.add(s[j]);
                    setOne.remove(s[j]);
                } else if (!setTwo.contains(s[j])) {
                    setOne.add(s[j]);
                }
                sum += setOne.size();
            }
            ans += sum;
        }
        System.out.println(ans);
    }
}
