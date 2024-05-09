package 数据结构.哈希表.哈希表实现;

public class MyHashMap {
    /*
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    private Object getNode(Object key) {
        MyEntry[] tab = table;
        if (tab == null) {
            return null;
        }
        int n = tab.length;
        if (n == 0) {
            return null;
        }
        int hash = getHash(key);
        MyEntry first = tab[n - 1 & hash];
        if (first == null) {
            return null;
        }
        // always check first node
        if (first.hash == hash) {
            Object k = first.key;
            if (k == key || key.equals(k)) {
                return first;
            }
        }
        MyEntry e = first.next;
        if (e == null) {
            return null;
        }
        Object k = e.key;
        do {
            if (e.hash == hash && (k == key || key.equals(k))) {
                return e;
            }
            e=e.next;
        } while (e != null);
        return null;
    }

     */
}
