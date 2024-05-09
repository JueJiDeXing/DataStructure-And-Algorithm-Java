package 数据结构.设计数据结构;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class _535短网址 {
    /*
    TinyURL 是一种 URL 简化服务，
    比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
    它将返回一个简化的URL http://tinyurl.com/4e9iAk 。
    请你设计一个类来加密与解密 TinyURL 。

    加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。

    实现 Solution 类：

    Solution() 初始化 TinyURL 系统对象。
    String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
    String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
     */
}

class Codec {
    /*
    思路:只需要让短网址和长网址一一对应即可
    生成id的三种方法:
        1.随机数
        2.hash码
        3.递增数
    TODO 解决多线程使用和分布式使用
     */

    // 使用map集合对应
    private Map<String, String> longToShort = new HashMap<>();
    private Map<String, String> shortToLong = new HashMap<>();
    String SHORT_PREFIX = "http://tinyurl.com/";//前缀

    public String encode1(String longUrl) {
        String shortUrl = longToShort.get(longUrl);
        if (shortUrl != null) {
            return shortUrl;
        }

        //生成
        while (true) {
            int id = ThreadLocalRandom.current().nextInt();//1.随机数
            shortUrl = SHORT_PREFIX + toBase62(id);
            if (!shortToLong.containsKey(shortUrl)) {//有冲突则重新生成
                longToShort.put(longUrl, shortUrl);
                shortToLong.put(shortUrl, longUrl);
                break;
            }
        }
        return shortUrl;
    }

    private static final char[] toBase62 = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    public static String toBase62(int num) {
        if (num == 0) {
            return String.valueOf(toBase62[0]);
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int r = num % 62;
            sb.append(toBase62[r]);
            num /= 62;
        }
        return sb.toString();
    }

    public String encode2(String longUrl) {
        String shortUrl = longToShort.get(longUrl);
        if (shortUrl != null) {
            return shortUrl;
        }
        //生成
        int id = longUrl.hashCode(); //2.hash码
        while (true) {
            shortUrl = SHORT_PREFIX + id;
            if (!shortToLong.containsKey(shortUrl)) {//有冲突则重新生成
                longToShort.put(longUrl, shortUrl);
                shortToLong.put(shortUrl, longUrl);
                break;
            }
            id++;//2.hash码
        }
        return shortUrl;
    }

    private static int increaseID = 0;

    public String encode3(String longUrl) {
        String shortUrl = longToShort.get(longUrl);
        if (shortUrl != null) {
            return shortUrl;
        }
        //生成
        //3.递增数
        shortUrl = SHORT_PREFIX + increaseID;
        longToShort.put(longUrl, shortUrl);
        shortToLong.put(shortUrl, longUrl);
        increaseID++;
        return shortUrl;
    }

    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl);
    }
}
