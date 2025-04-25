package 算法.字符串.KMP;

import java.util.Arrays;

public class KMP算法 {
    public static void main(String[] args) {
        KMP算法 test = new KMP算法();
        System.out.println(Arrays.toString(test.getNext("aaacaaaaac".toCharArray())));
        System.out.println(Arrays.toString(test.getNext("ababaca".toCharArray())));
    }

    /*
    在模式串找最长的相同前后缀数组
    next[j]:使用前j-1个字符时,最长前后缀的长度,表示匹配失败时j的跳转位置

    例: 模式串为 a b a b a ...
    若前5个字符都匹配
                ↓
    (a b a) b a ...
    a b (a b a) ...
    最长相同前后缀的长度为3, next[6]=3
    在匹配第6个字符时,如果不匹配,则回退到索引3处
    括号内为匹配的部分
     */
    public int strStr(String haystack, String needle) {
        char[] origin = haystack.toCharArray();//原始
        char[] pattern = needle.toCharArray();//模式(在origin中查找pattern)
        int[] next = getNext(pattern);
        int i = 0, j = 0;
        //while (i < origin.length)
        while (pattern.length - j <= origin.length - i) {
            if (pattern[j] == origin[i]) {//匹配成功,匹配下一位
                i++;
                j++;
                if (j == pattern.length) return i - j;//全部匹配, i-j为origin匹配的开始位置
            } else if (j == 0) {//第0位不匹配
                i++;
            } else {//在某处不匹配跳过最长前后缀
                j = next[j - 1];
            }
        }
        return -1;
    }

    /*
    i:初始为1, 表示后缀, 前缀需要以pattern[i]结尾
    j:初始为0, 表示前缀, 后缀需要以pattern[j]开头
    0 ~ j  与  i-j ~ i 是相同的

    ...

   next[0, 1, 2,                   ]
                 j   //相同则填入 长度为j+1
           a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                 i

   next[0, 1, 2,                  ]
                 j  //遇到不同字符,前面有相同部分,j往回找,j=next[j-1] (j=1)
              a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                 i

   next[0, 1, 2,                  ]
                 j  //遇到不同字符,前面有相同部分,j往回找,j=next[j-1] (j=0)
                 a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                 i

   next[0, 1, 2, 0                 ]
                 j  //遇到不同字符,前面没有相同部分(j=0),该位为0,i后移
                 a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                 i

   next[0, 1, 2, 0  1, 2, 3         ]
                             j//相同则填入 长度为j+1
                    a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                             i

   next[0, 1, 2, 0  1, 2, 3, 3      ]
                             j  //遇到不同字符,前面有相同部分,j往回找,j=next[j-1] (j=2)
                       a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                             i

   next[0, 1, 2, 0  1, 2, 3, 3,     ]
                                j
                       a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                                i

   next[0, 1, 2, 0  1, 2, 3, 3, 3   ]
                                j
                          a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                                i

   next[0, 1, 2, 0  1, 2, 3, 3, 3, 4]
                                   j
                          a  a  a  c  a  a  a  a  a  c
        a  a  a  c  a  a  a  a  a  c
                                   i
     */
    private int[] getNext(char[] pattern) {
        int[] next = new int[pattern.length];
        int i = 1, j = 0;
        while (i < pattern.length) {
            if (pattern[i] == pattern[j]) {
                next[i] = j + 1;
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j - 1];
            }
        }
        return next;
    }
}
