package 基础数据结构算法.链表;

import 数据结构实现.链表.ListNode;

/**
 两个/K个 升序链表合并
 难度:中等/困难
 */
public class 合并链表 {
    /**
     <div color=rgb(155,200,80)>
     <h1> 方法一:双指针</h1>
     双指针遍历链表,每次添加较小的元素至新链表尾部<br>
     </div>
     */
    public ListNode merge1(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode n = s;
        //取较小的节点添加到新链表尾部
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                n.next = p1;
                p1 = p1.next;
            } else {
                n.next = p2;
                p2 = p2.next;
            }
            n = n.next;
        }
        //把不为null的剩余链表接到新链表尾部
        if (p1 != null) {
            n.next = p1;
        }
        if (p2 != null) {
            n.next = p2;
        }
        return s.next;
    }

    /**
     <div color=rgb(155,200,80)>
     <h1> 方法二:递归</h1>
     每次递归返回较小的节点,并让剩余节点与另一个链表再次递归<br>
     串起所有返回值
     </div>
     */
    public ListNode merge2(ListNode p1, ListNode p2) {
        if (p2 == null) return p1;
        if (p1 == null) return p2;

        if (p1.val < p2.val) {
            p1.next = merge2(p1.next, p2);//p1指向后续比较中的较小值
            return p1;
        } else {
            p2.next = merge2(p1, p2.next);
            return p2;
        }
    }
    //合并k个有序链表--------------------------------------------------------------------

    /**
     <h1>归并</h1>
     */
    public ListNode mergeK(ListNode[] listNodes) {
        if (listNodes == null || listNodes.length == 0) return null;

        return split(listNodes, 0, listNodes.length - 1);
    }

    /**
     @param listNodes 要合并的链表
     @param i,j       左右边界
     @return 返回合并后的链表
     */
    private ListNode split(ListNode[] listNodes, int i, int j) {
        if (i == j) {
            return listNodes[i];//数组内只有1个链表时退出
        }
        //有多个链表就分隔
        int m = (i + j) >>> 1;
        ListNode left = split(listNodes, i, m), right = split(listNodes, m + 1, j);
        return merge1(left, right);//合并两个链表
    }

    /**
     <h1>堆</h1>
     把节点存储到小顶堆(优先级队列),每次抛出的堆顶就是当前最小的节点,将它连接到新链上
     最后把这个节点的下一个节点入堆
     */
    public ListNode mergeKLists(ListNode[] lists) {
        MinHeap heap = new MinHeap(lists);
        ListNode s = new ListNode(-1, null);//哨兵
        ListNode t = s;//尾指针
        while (!heap.isEmpty()) {
            //从堆顶移除元素,并添加到新链尾
            ListNode min = heap.poll();
            t.next = min;
            t = min;
            if (min.next != null) {
                heap.offer(min.next);//把移除的节点的下一个节点添加到堆
            }
        }
        return s.next;
    }

    /**
     存储节点的小顶堆
     */
    static class MinHeap {
        public ListNode[] array;
        public int size;

        public MinHeap(ListNode[] arr) {
            this.array = arr.clone();
            this.size = arr.length;
            build();
        }

        /**
         建堆
         */
        public void build() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                down(i);
            }
        }

        /**
         添加节点
         */
        public boolean offer(ListNode offered) {
            if (isFull()) return false;

            int child = size;//从堆末开始寻找位置
            int parent = (child - 1) / 2;
            while (child > 0 && offered.val < array[parent].val) {
                //比父节点小,将父节点向下移,offered取代父的位置
                array[child] = array[parent];
                child = parent;
                parent = (parent - 1) / 2;//继续寻找,直到parent比offered小或到达堆顶
            }
            array[child] = offered;//插入
            size++;
            return true;
        }


        public ListNode poll() {
            if (isEmpty()) return null;

            swap(0, size - 1);//交换头元素与尾元素再将尾元素抛出
            size--;
            ListNode e = array[size];
            array[size] = null;

            down(0);//下潜
            return e;
        }

        /**
         小顶堆,大元素下潜
         */
        private void down(int parent) {
            int left = 2 * parent + 1;
            int right = left + 1;
            int min = parent;//寻找 父,左,右 三者较小的
            if (left < size && array[left].val < array[min].val) {
                min = left;
            }
            if (right < size && array[right].val < array[min].val) {
                min = right;
            }
            if (min != parent) {//如果子节点比父节点优先级大
                swap(parent, min);
                down(min);//递归,直到min==parent,父节点小于左右子节点时停止
            }
        }

        /**
         交换索引i与j的元素
         */
        private void swap(int i, int j) {
            ListNode e = array[i];
            array[i] = array[j];
            array[j] = e;
        }

        /**
         判断是否堆空
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         判断是否堆满
         */
        public boolean isFull() {
            return size == array.length;
        }
    }
}

