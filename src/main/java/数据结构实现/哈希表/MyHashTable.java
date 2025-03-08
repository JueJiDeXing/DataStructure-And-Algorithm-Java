package 数据结构实现.哈希表;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 哈希表<br>
 基于数组与链表实现
 */
public class MyHashTable {
    MyEntry[] table = new MyEntry[16];//2^n大小
    int size = 0;
    float loadFactor = 0.75F;//负载因子,当 元素个数/数组长度=0.75时 扩容数组
    int threshold = (int) (loadFactor * table.length);//阈值

    /**
     <div color=rgb(155,200,80)>
     <h1>根据hash码获取value</h1>
     </div>
     */
    Object get(int hash, Object key) {
        int index = hash & (table.length - 1);
        //hash mod length = hash & (length-1) 当且仅当 length=2^n
        //  2进制除10、100、1000,余数为被除数的后1、2、3位
        if (table[index] == null) {
            return null;
        }
        MyEntry p = table[index];
        while (p != null) {//遍历链表
            if (p.key.equals(key)) {//比较
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>向hash表存入键值对</h1>
     如果key重复则更新value</div>
     */
    void put(int hash, Object key, Object value) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            //无元素,直接添加
            table[index] = new MyEntry(hash, key, value);
        } else {
            //有元素
            MyEntry p = table[index];
            while (true) {
                //遍历链表比较是否有key相同元素,如果有则更新
                if (p.key.equals(key)) {
                    p.value = value;//有key相同元素,更新元素值
                    return;
                }
                if (p.next == null) {//已到达最后一个节点,跳出循环
                    break;
                }
                p = p.next;
            }
            //没有重复的key,新建一个节点
            p.next = new MyEntry(hash, key, value);
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>扩容</h1>
     </div>
     */
    private void resize() {
        MyEntry[] newTable = new MyEntry[table.length << 1];//扩容两倍
        for (int i = 0; i < table.length; i++) {
            MyEntry p = table[i];//链表头
            if (p == null) continue;
            //拆分链表到新数组
            //拆分规律:一个链表最多拆分为两个,按hash & length是否为0
            //  2^n 对于后n位与后n+1位相同的index,扩容后位置不变
            //  例如 8 0100 扩容后还是原位置  ; 9 1001 扩容后 位置改变
            //  所以 检查倒数第n+1位是否为0即可
            MyEntry aHead = null, bHead = null;//拆分的两个新链表
            MyEntry a = null, b = null;
            while (p != null) {
                if ((p.hash & table.length) == 0) {
                    if (a != null) {
                        a.next = p;//先指向下一个节点
                    } else {
                        aHead = p;//第一次记录头指针
                    }
                    a = p;//再更新位置
                } else {
                    if (b != null) {
                        b.next = p;
                    } else {
                        bHead = p;
                    }
                    b = p;
                }
                p = p.next;
            }
            //循环后将两个新链的尾节点指向null
            if (a != null) {
                a.next = null;
                newTable[i] = aHead;//a表位置不变
            }
            if (b != null) {
                b.next = null;
                newTable[i + table.length] = bHead;//b表为索引加数组长
            }

        }
        table = newTable;
        threshold = (int) (loadFactor * table.length);
    }

    /**
     <div color=rgb(155,200,80)>
     <h1>根据hash码删除</h1>
     </div>
     */
    Object remove(int hash, Object key) {
        int index = hash & (table.length - 1);
        if (table[index] == null) {
            //无元素,直接添加
            return null;
        }
        MyEntry p = table[index];
        MyEntry prev = null;
        while (p != null) {//遍历链表
            if (p.key.equals(key)) {
                //找到元素,删除
                if (prev != null) {
                    prev.next = p.next;
                } else {
                    table[index] = p.next;//删除的是链表的第一个节点
                }
                size--;
                return p.value;
            }
            prev = p;
            p = p.next;
        }
        return null;
    }

    Object get(Object key) {
        int hash = getHash(key);
        return get(hash, key);
    }


    void put(Object key, Object value) {
        int hash = getHash(key);
        put(hash, key, value);
    }

    Object remove(Object key) {
        int hash = getHash(key);
        return remove(hash, key);
    }

    private static int getHash(Object key) {
        int hash = 0;
        String s = key.toString();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            hash = (hash << 5) - hash + c;//字符串的每位字符转ASCII码后拼接,乘以一个大质数31使冲突的几率更小
            //优化 hash*31 --> hash*32 - hash --> (hash << 5 ) - hash
        }
        return hash ^ (hash >>> 16);//高16位与低16位进行异或,异或结果放在低16位上
    }

    public boolean containsKey(Object key) {
        int hash = getHash(key);
        int index = (hash & 0x7FFFFFFF) % table.length;
        for (MyEntry e = table[index]; e != null; e = e.next) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }


    //打印链表长度,按长度分组,长度为1有xx个...
    public void printLength() {
        int[] sums = new int[table.length];
        for (int i = 0; i < table.length; i++) {
            MyEntry p = table[i];
            while (p != null) {
                sums[i]++;
                p = p.next;
            }
        }
        //System.out.println(Arrays.toString(sums));
        Map<Integer, Long> collect = Arrays.stream(sums).
                boxed().
                collect(Collectors.groupingBy(
                        e -> e, Collectors.counting()
                ));
        System.out.println(collect);
    }

    //思考1:put时使用了尾插法,在JDK1.8以前使用头插法,但是多线程可能死循环,所以改为尾插
    //思考2:
    // HashTable源码中使用了 return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    // 高位与低位进行异或运算可以减少冲突
    //思考3:很多方法都基于2的n次幂这个长度,但是其分散性不好,也有长度为质数的实现(如:初始长度11),其分散性较好,但是运算效率不高
    //思考4:链表过长时,链表会转为红黑树(正常情况下链表长度很短,但是会有用户恶意存储的情况使链表长度非常长)
}
