package 算法.深搜_广搜.深度优先.排列组合问题;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 不重复非空子集数 {
    //每个字符只能使用一次,重复字符可用次数为它的个数
    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (char t : tiles.toCharArray()) {
            count.put(t, count.getOrDefault(t, 0) + 1);//统计各类字符个数
        }
        Set<Character> tile = new HashSet<>(count.keySet());//可用字符
        return dfs(tiles.length(), count, tile) - 1;
    }

    /**
     @param i     当前可用字符种数
     @param count 字符::剩余数量
     @param tile  字符种类
     */
    public int dfs(int i, Map<Character, Integer> count, Set<Character> tile) {
        if (i == 0) {//已无字符
            return 1;
        }
        int res = 1;
        for (char t : tile) {
            if (count.get(t) > 0) {//还有字符t可用
                count.put(t, count.get(t) - 1);
                res += dfs(i - 1, count, tile);

                count.put(t, count.get(t) + 1);
            }
        }
        return res;
    }
    /* 示例:AABC
             /--B
         /--A--C
        |
     /--A --B--A--C
     |  |    \--C--A
     |  |
     |   \--C--A--B
     |       \--B--A
    null--B...
     \--C...

    */
}
