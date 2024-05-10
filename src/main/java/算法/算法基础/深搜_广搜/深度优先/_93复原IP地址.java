package 算法.算法基础.深搜_广搜.深度优先;

import java.util.*;

public class _93复原IP地址 {
    List<String> res = new ArrayList<>();
    int[] ip = new int[4];
    int len = 0;

    public List<String> restoreIpAddresses(String s) {
        len = s.length();
        dfs(s.toCharArray(), 0, 0);
        return res;
    }

    //curr:当前是第几段(0~3)
    public void dfs(char[] s, int start, int curr) {
        if (curr == 4) {//4段找完了
            if (start == len) {//并且刚好到了最末尾
                //添加答案
                StringBuilder ipAddr = new StringBuilder();
                for (int i = 0; i < 4; ++i) {
                    ipAddr.append(ip[i]);
                    if (i != 3) {
                        ipAddr.append('.');
                    }
                }
                res.add(ipAddr.toString());
            }
            return;
        }
        if (start == len) return;//没找完4段就到最末尾了
        if (s[start] == '0') {//该位为0,0只能单独放或与前面的结合
            ip[curr] = 0;
            dfs(s, start + 1, curr + 1);
            return;
        }
        int num = 0;
        for (int end = start; end < len; end++) {//枚举end点
            num = num * 10 + s[end] - '0';
            if (0 < num && num <= 255) {//需要在范围内
                ip[curr] = num;
                dfs(s, end + 1, curr + 1);
            } else {
                break;
            }
        }
    }
}
