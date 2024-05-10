package 算法.进阶数据结构算法.并查集;

import java.util.*;

/**
 第 123 场周赛 Q2
 难度分:
 */
public class _990等式方程的可满足性 {
    /*
    给定一个由表示变量之间关系的字符串方程组成的数组，
    每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。
    在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。

    只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。
     */
    public boolean equationsPossible(String[] equations) {
        //将==表示的项进行分量连通,然后检查!=的项,他们不能连通
        DisjointSet2 set = new DisjointSet2();
        List<String> notUnion = new ArrayList<>();
        for (String equation : equations) {
            if (equation.charAt(1) == '=') {
                set.union(equation.charAt(0), equation.charAt(3));
            } else {
                notUnion.add(equation);
            }
        }
        for (String equation : notUnion) {
            if (set.isConnect(equation.charAt(0), equation.charAt(3))) {
                return false;
            }
        }
        return true;
    }

    /**
     使用HashMap
     */
    static class DisjointSet1 {
        Map<Character, Character> fa = new HashMap<>();

        char find(char ch) {
            if (ch == fa.get(ch)) return ch;
            char c = find(fa.get(ch));
            fa.put(ch, c);
            return c;
        }

        void union(char ch1, char ch2) {
            if (!fa.containsKey(ch1)) fa.put(ch1, ch1);
            if (!fa.containsKey(ch2)) fa.put(ch2, ch2);

            char x1 = find(ch1), x2 = find(ch2);
            fa.put(x1, x2);
            fa.put(ch1, x2);
            fa.put(ch2, x2);
        }

        boolean isConnect(char ch1, char ch2) {
            if (!fa.containsKey(ch1)) fa.put(ch1, ch1);
            if (!fa.containsKey(ch2)) fa.put(ch2, ch2);

            return find(ch1) == find(ch2);
        }
    }

    /**
     因为都是小写字母，所以可以用26位数组来代替
     */
    static class DisjointSet2 {
        int[] fa = new int[26];

        public DisjointSet2() {
            for (int i = 0; i < 26; i++) fa[i] = i;
        }

        int find(int x) {
            if (x == fa[x]) return x;
            return fa[x] = find(fa[x]);
        }

        void union(char x, char y) {
            int rootX = find(x - 'a'), rootY = find(y - 'a');
            if (rootX != rootY) fa[rootX] = rootY;
        }

        boolean isConnect(char x, char y) {
            return find(x - 'a') == find(y - 'a');
        }
    }
}
