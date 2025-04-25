package 算法.字符串.其他;

import java.util.*;


public class 哈希 {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        String s = "bacbwa";
        //int length = noPeatStr(s);
        String[] strs = new String[]{"tan", "nat", "eat", "atr", "ate"};
        System.out.println(groupStr2(strs));
        ;
    }

    /**
     <h1>最长不重复子串</h1>
     从左向右查看每个字符
     没遇到重复字符,end右移
     遇到重复字符,调整begin至与end的重复字符
     将当前字符放入哈希表<br>
     示例:abba
     a
     ab
     b
     ba
     输出2
     */
    private static int noPeatStr(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int begin = 0;
        int maxLen = 0;
        for (int end = 0; end < s.length(); end++) {
            char ch = s.charAt(end);
            if (map.containsKey(ch)) {//哈希表存储ch字符和它的索引位置
                //重复
                // 调整begin至与end的重复字符位置
                begin = Math.max(begin, map.get(ch) + 1);//max函数保证begin不往回走
                map.put(ch, end);
            } else {
                map.put(ch, end);
            }
            maxLen = Math.max(maxLen, end - begin - 1);//寻找最长不重复子串
        }
        return maxLen;
    }

    /**
     <h1>判断字母异位词</h1>
     */
    private static boolean heterotopiaStr(String str1, String str2) {
        return Arrays.equals(getKey2(str1), getKey2(str2));
    }

    private static int[] getKey1(String s) {
        int[] array = new int[26];
        for (int i = 0; i < 26; i++) {
            array[s.charAt(i) - 'a']++;
        }
        return array;
    }

    private static int[] getKey2(String s) {
        int[] array = new int[26];
        for (char ch : s.toCharArray()) {//优化
            array[ch - 'a']++;
        }
        return array;
    }

    /**
     <h1>字母异位词分组:解1</h1>
     遍历数组,字符串中的字符排序后加入哈希表,返回分组结果
     */
    private static List<List<String>> groupStr1(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();//以字符串作为key
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.computeIfAbsent(
                    key, k -> new ArrayList<>()
            );//查看是否有key,如果没有则创建
//            if (list == null) {//如果没有,则创建该组后添加到组内
//                list = new ArrayList<>();
//                map.put(key, list);
//            }
            //如果有,则直接添加到组内
            list.add(str);

        }
        return new ArrayList<>(map.values());
    }

    /**
     <h1>字母异位词分组:解2</h1>
     strs只包含小写字母,所以使用26位数组存储一个字符串的信息
     */
    private static List<List<String>> groupStr2(String[] strs) {
        HashMap<ArrayKey, List<String>> map = new HashMap<>();//以数组作为key
        for (String str : strs) {
            ArrayKey key = new ArrayKey(str);
            List<String> list = map.computeIfAbsent(
                    key, k -> new ArrayList<>()
            );
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    static class ArrayKey {
        int[] key = new int[26];

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArrayKey arrayKey = (ArrayKey) o;
            return Arrays.equals(key, arrayKey.key);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(key);
        }

        public ArrayKey(String str) {//传入一个字符串,返回其对应26位数组
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                key[ch - 'a']++;
            }
        }
    }

    /**
     <h1>数组重复元素</h1>
     */
    private static boolean peatArrayNum1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            if (map.containsKey(key)) {
                return true;
            }
            map.put(key, key);
        }
        return false;
    }

    private static boolean peatArrayNum2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int key : nums) {
            if (!set.add(key)) {
                //性能好
                //Map实现多出了一次containsKey方法
                return true;
            }
        }
        return false;
    }

    private static boolean peatArrayNum3(int[] nums) {
        //优化
        HashMap<Integer, Object> map = new HashMap<>(nums.length * 2);//设置初始容量,减少扩容次数
        Object value = new Object();//用一个固定对象作为值存储
        for (int key : nums) {
            if (map.put(key, value) != null) {//模拟Set集合
                return true;
            }
        }
        return false;
    }

    /**
     <h1>找不重复数字</h1>
     数组中某个元素只出现一次,其余元素都出现两次
     */
    private static int findNoPeatArrayNum1(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                //遇到重复元素,则删除,因为重复元素只能出现1两次
            }
        }

        return set.toArray(new Integer[0])[0];//最后剩余的元素即为不重复数字
    }

    //使用异或
    // 相同数字异或为0,任意数字与0异或为数字本身
    private static int findNoPeatArrayNum2(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    /**
     <h1>找第一个不重复字符</h1>
     字符串仅包含小写字符
     */
    private static int findFirstNoPeatChar(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            array[ch - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (array[ch - 'a'] == 1) {
                return i;//找到第一个不重复字符,返回索引
            }
        }
        return -1;//没有找到不重复字符
    }

    /**
     <h1>重复次数最多的单词</h1>
     不区分大小写<br>
     限制:单词不出现在禁用词列表中
     */
    private static String findMaxPeatWord(String paragraph, String[] banned) {
        HashMap<String, Integer> map = new HashMap<>();
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        //截取段落为单词
//        String[] words = paragraph.toLowerCase().split("[^A-Za-z]+");//以除字母外的字符分隔
        char[] chars = paragraph.toLowerCase().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : chars) {
            if ('a' <= ch && ch <= 'z') {
                stringBuilder.append(ch);
            } else {
                String key = stringBuilder.toString();
                if (!bannedSet.contains(key)) {
                    map.compute(key, (k, v) -> v == null ? 1 : v + 1);
                }
//                stringBuilder = new StringBuilder();
                stringBuilder.setLength(0);
            }
        }
        if (stringBuilder.length() > 0) {
            String key = stringBuilder.toString();
            if (!bannedSet.contains(key)) {
                map.compute(key, (k, v) -> v == null ? 1 : v + 1);
            }
        }
///将单词加入集合,单词:次数  (过滤禁用词)
///      for (String key : words) {
///          if (!bannedSet.contains(key)){
///              map.compute(key, (k,v)->v==null?1:v+1);
///            Integer value = map.get(key);
///            if (value==null){
///                value=0;
///            }
///            map.put(key,value+1);
///              }
///          }

        //找到最大的value,返回key
        int max = 0;
        String maxKey = null;
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            Integer value = e.getValue();
            if (value > max) {
                max = value;
                maxKey = e.getKey();
            }
        }
//        Optional<Map.Entry<String, Integer>> optional =
//                map.entrySet().stream().max(Map.Entry.comparingByValue());
//
//        return optional.map(Map.Entry::getKey)
//                .orElse(null);
        return maxKey;

    }
}
