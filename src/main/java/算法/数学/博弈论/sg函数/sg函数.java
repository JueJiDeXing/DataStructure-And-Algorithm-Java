package 算法.数学.博弈论.sg函数;

import java.util.Arrays;
import java.util.BitSet;
import java.util.Scanner;
import java.util.Set;

public class sg函数 {

    /*
    定义mex运算: mex(set)为set中最小的未出现的非负整数
    */
    static int mex(Set<Integer> set) {
        for (int i = 0; ; i++) {
            if (!set.contains(i)) return i;
        }
    }

    static int mex(BitSet set) {
        return set.nextClearBit(0);
    }
    /*
    sg函数:
    sg(x) = mex{ sg(y) | y是x的后继状态 }

    性质:
    (sg=0为必败态)
    1. 没有出边的sg值为0, 因为它没有后继状态
    2. 对于sg值为0的节点, 它的后继节点sg不为0
    3. 对于sg值不为0的节点, 一定存在一个后继节点sg为0

    sg定理:
    合游戏的sg = 各后继的sg异或和
    sg(x) = sg(y1) ^ sg(y2) ...
     */

    /**
     <h1>模版</h1>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sg = new int[n + 1];
        Arrays.fill(sg, -1);
        sg[0] = 0; // 定义必败态
        dfs(n);
        System.out.println(sg[n] != 0 ? "Alice" : "Bob");
    }

    static int[] sg;

    static void dfs(int n) {
        if (sg[n] != -1) return;
        BitSet set = new BitSet();
        for (int i = 1; i < n; i++) {
            dfs(i);// 搜索后继状态
            set.set(sg[i]);//得到后继状态的sg值
        }
        sg[n] = mex(set);
    }
}
