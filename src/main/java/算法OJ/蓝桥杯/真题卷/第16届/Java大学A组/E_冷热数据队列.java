package 算法OJ.蓝桥杯.真题卷.第16届.Java大学A组;

import java.util.HashMap;
import java.util.Scanner;

/**
 AC
 15分
 */
public class E_冷热数据队列 {
    /*
    热数据队列q1,容量n1
    冷数据队列q2,容量n2
    初始均为空
    有m次查询操作:
    查询x
    (1) 若x不在队列中, 将x加入到q2头部
        加入前,若q2满则淘汰q2尾部
    (2) 若x在队列中, 将x取出加入到q1头部
        加入前, 若q1满则淘汰q1尾部至q2头部
        若q2也是满的,则淘汰q2尾部
     */
    static class Node {
        int val;
        Node pre, next;

        Node() {}

        Node(int v) {val = v;}
    }

    static Node head1 = new Node(), tail1 = new Node(), head2 = new Node(), tail2 = new Node();
    static HashMap<Integer, Node> map1 = new HashMap<>(), map2 = new HashMap<>();

    static void delete(Node p) {
        Node pre = p.pre, next = p.next;
        pre.next = next;
        next.pre = pre;
    }

    static void addFirst(Node p, Node head) {
        Node next = head.next;
        head.next = p;
        next.pre = p;
        p.pre = head;
        p.next = next;
    }

    static Node pollLast(Node tail) {
        Node poll = tail.pre;
        Node pre = poll.pre;
        pre.next = tail;
        tail.pre = pre;
        return poll;
    }

    public static void main(String[] args) throws Exception {
        head1.next = tail1;
        head1.pre = tail1;
        tail1.next = head1;
        tail1.pre = head1;
        head2.next = tail2;
        head2.pre = tail2;
        tail2.next = head2;
        tail2.pre = head2;
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            if (map1.containsKey(x)) {
                Node p = map1.get(x);
                delete(p);
                addFirst(p, head1);
                // size不变
            } else if (map2.containsKey(x)) {
                Node p = map2.get(x);
                delete(p);
                map2.remove(x);

                addFirst(p, head1);
                map1.put(x, p);

                if (map1.size() == n1 + 1) {
                    Node poll1 = pollLast(tail1);
                    map1.remove(poll1.val);

                    addFirst(poll1, head2);
                    map2.put(poll1.val, poll1);

                    if (map2.size() == n2 + 1) {
                        Node poll2 = pollLast(tail2);
                        map2.remove(poll2.val);
                    }
                }
            } else {
                Node add = new Node(x);
                map2.put(x, add);
                addFirst(add, head2);
                if (map2.size() == n2 + 1) {
                    Node poll = pollLast(tail2);
                    map2.remove(poll.val);
                }
            }
        }
        Node p = head1.next;
        while (p != tail1) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
        p = head2.next;
        while (p != tail2) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

}
