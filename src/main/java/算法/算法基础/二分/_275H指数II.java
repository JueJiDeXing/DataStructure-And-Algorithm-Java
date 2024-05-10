package 算法.算法基础.二分;

/**
 难度:中等
 附274H指数I
 */
public class _275H指数II {
    /*
    给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，
    citations 已经按照 升序排列 。计算并返回该研究者的 h 指数。

    h 指数的定义：h 代表“高引用次数”（high citations），
    一名科研人员的 h 指数是指他（她）的 （n 篇论文中）至少 有 h 篇论文分别被引用了至少 h 次。

    请你设计并实现对数时间复杂度的算法解决此问题。
     */

    /**
     <h1>二分:LeftMost</h1>
     此情景下,二分查找比较条件:<br>
     0 1 2 5 6 -> 引用次数<br>
     ↑ ↑ ↑ ↑ ↑<br>
     5 4 3 2 1 -> 右侧(含自身)论文数<br>
     F F F T T -> 比较结果<br>
     <p>
     有 h 篇论文分别被引用了至少 h 次 : 当高引用论文数大于等于引用次数时这个h值是可行的<br>
     需要找到最小的可行h值<br>
     */
    public int hIndex(int[] citations) {
        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int high = citations.length - mid;
            if (high <= citations[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return citations.length - left;
    }

    /**
     <h1>H指数I--计数思想</h1>
     与II的不同点:传入的数组未排序
     令cnt[a]=b表示引用次数为a次的论文有b篇
     让h从n往前找,第一个满足的h即为答案
     //h 指数:有 h 篇论文被引用了至少 h 次
     根据定义h要满足: 引用次数>=h次 的论文数 >= h
     即SUM( cnt[>=h] ) >= h
     */
    public int hIndexI(int[] cs) {
        int n = cs.length;
        int[] cnt = new int[n + 1];//计数
        for (int c : cs) cnt[Math.min(c, n)]++;//大于n的全部映射到n
        for (int h = n, total = 0; h >= 0; h--) {
            total += cnt[h];
            if (total >= h) return h;
        }
        return -1; // never
    }
}
