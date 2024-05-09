package 数据结构.图.拓扑排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _936戳印序列 {
    /*
    你想要用小写字母组成一个目标字符串 target。
    开始的时候，序列由 target.length 个 '?' 记号组成。
    你有一个小写字母印章 stamp。 在每个回合，你可以将印章放在序列上，并将序列中的每个字母替换为印章上的相应字母。
    你最多可以进行 10 * target.length  个回合。

    举个例子，如果初始序列为 "?????"，而你的印章 stamp 是 "abc"，那么在第一回合，你可以得到 "abc??"、"?abc?"、"??abc"。
    （请注意，印章必须完全包含在序列的边界内才能盖下去。）

    如果可以印出序列，那么返回一个数组，该数组由每个回合中被印下的最左边字母的索引组成。
    如果不能印出序列，就返回一个空数组。

    例如，如果序列是 "ababc"，印章是 "abc"，那么我们就可以返回与操作 "?????" -> "abc??" -> "ababc" 相对应的答案 [0, 2]；
    另外，如果可以印出序列，那么需要保证可以在 10 * target.length 个回合内完成。任何超过此数字的答案将不被接受。
     */
    public int[] movesToStamp(String stamp, String target) {
        char[] s = stamp.toCharArray();
        char[] t = target.toCharArray();
        int m = s.length, n = t.length;
        int[] indegree = new int[n - m + 1];//入度(错点个数)
        Arrays.fill(indegree, m);
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i <= n - m; i++) {//i开头
            for (int j = 0; j < m; j++) {//i~i+m的子串
                if (t[i + j] == s[j]) {//比对印章
                    if (--indegree[i] == 0) {//能对上,错点-1
                        queue.offer(i);//都对得上,入队
                    }
                } else {
                    //错点位置->开头位置
                    graph.get(i + j).add(i);
                }
            }
        }
        boolean[] visit = new boolean[n];//记录是否已取消该位置错点,防止重复取消
        LinkedList<Integer> path = new LinkedList<>();//收集盖章位置(逆序+栈->正序)
        while (!queue.isEmpty()) {
            int curr = queue.poll();//抛出一个错点为0的开头
            path.push(curr);
            for (int i = 0; i < m; i++) {//拿到curr~curr+m的子串
                //将子串里每一个字符对应的开头的错点减掉
                if (!visit[curr + i]) {//visit防止重复减
                    visit[curr + i] = true;
                    for (int next : graph.get(curr + i)) {//拿到开头
                        if (--indegree[next] == 0) {//错点减1
                            queue.offer(next);//减到0入队
                        }
                    }
                }
            }
        }
        if (path.size() != n - m + 1) {//点数量对不上,怎么盖都不行
            return new int[0];
        }
        return path.stream().mapToInt(Integer::intValue).toArray();
    }
}
