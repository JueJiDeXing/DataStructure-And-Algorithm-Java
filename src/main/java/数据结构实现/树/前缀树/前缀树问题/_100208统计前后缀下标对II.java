package 数据结构实现.树.前缀树.前缀树问题;

import java.util.HashMap;
import java.util.Objects;

public class _100208统计前后缀下标对II {
    /*
    给你应该字符串数组words
    定义布尔函数 isPrefixAndSuffix,接收两个字符串str1和str2
    当str1同时是str2的前缀和后缀时,返回true

    求words数组中isPrefixAndSuffix的下标对数
     */

    /**
     对于一个字符串s,将它的前后对pair(s[i],s[n-i-1])存储到字典树,匹配时只需要查看前后对是否一致即可
     <p>
     例如:       abc      abczabc
     存储到字典树 (a,c)     (a,c)
     (b,b)     (b,b)
     (c,a)     (c,a)
     (z,z)
     ...
     这样匹配前缀数对即可判断一个字符串是否同时是另一个字符串的前缀和后缀
     */
    public int count(String[] words) {
        Trie trie = new Trie();
        int ans = 0;
        for (String word : words) {
            ans += trie.queryAndInsert(word);
        }
        return 0;
    }

    static class Trie {
        static class Node {
            int count = 0;
            HashMap<Pair, Node> next = new HashMap<>();
        }

        static class Pair {
            Character ch1, ch2;//前后缀同时存储

            public Pair(Character ch1, Character ch2) {
                this.ch1 = ch1;
                this.ch2 = ch2;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return Objects.equals(ch1, pair.ch1) && Objects.equals(ch2, pair.ch2);
            }

            @Override
            public int hashCode() {
                return Objects.hash(ch1, ch2);
            }
        }

        Node root = new Node();

        /**
         查询word是否有同时是前缀和后缀的子串,有多少个
         查询后将word插入到trie中
         */
        public int queryAndInsert(String word) {
            int n = word.length();
            char[] wordChar = word.toCharArray();
            Node p = root;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                Pair pair = new Pair(wordChar[i], wordChar[n - i - 1]);
                p = p.next.computeIfAbsent(pair, k -> new Node());
                ans += p.count;//统计同时是前缀和后缀的子串有多少个
            }
            p.count++;//将word插入到trie中
            return ans;
        }
    }

    /**
     如果 s 同时是t的前缀和后缀,对于t来说,t的长为len(s)的前后缀需要相同
     定义Z函数,z[i] = LCP( s[i,n) , s) = n-i 字符串s的
     */
    public int count2(String[] words) {
        return 0;//TODO
    }
}
