package 算法.数学.其他;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class 回文数生成 {
    /*
    生成正确排序的回文数列表,自然生成序列,不另外使用排序
     */
    public static void main(String[] args) {
        int n = 10000;//半区的最大值, 9999 9 9999 半区为9999<n
        List<Integer> ans = create(n);
        ans.add(10_0000_0001);//哨兵
        System.out.println(ans);
    }

    @NotNull
    private static List<Integer> create(int n) {
        List<Integer> ans = new ArrayList<>();
        int base = 1;
        while (base <= n) {//len(base):回文串半区长度
            for (int i = base; i < base * 10; i++) {
                //奇数翻转
                //i 拼接 倒i, 例如i=123 -> 生成12321
                int x = i;
                int t = i / 10;
                while (t > 0) {
                    x = x * 10 + t % 10;
                    t /= 10;
                }
                ans.add(x);
            }
            if (base <= n / 10) {
                //偶数翻转
                for (int i = base; i < base * 10; i++) {
                    int x = i, t = i;
                    while (t > 0) {
                        x = x * 10 + t % 10;
                        t /= 10;
                    }
                    ans.add(x);
                }
            }
            base *= 10;
        }
        return ans;
    }
}
