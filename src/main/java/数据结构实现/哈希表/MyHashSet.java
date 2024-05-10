package 数据结构实现.哈希表;


import java.util.HashMap;
import java.util.LinkedHashMap;

public class MyHashSet<E> {


    private transient HashMap<E, Object> map;
    private static final Object PRESENT = new Object();


    public MyHashSet() {
        map = new HashMap<>();
    }

    public MyHashSet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    public MyHashSet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }


    MyHashSet(int initialCapacity, float loadFactor, boolean dummy) {
        map = new LinkedHashMap<>(initialCapacity, loadFactor);
    }


    public int size() {
        return map.size();
    }


    public boolean isEmpty() {
        return map.isEmpty();
    }


    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    public void clear() {
        map.clear();
    }

    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }


    @SuppressWarnings("unchecked")
    public Object clone() {
        try {
            MyHashSet<E> newSet = (MyHashSet<E>) super.clone();
            newSet.map = (HashMap<E, Object>) map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }


}
