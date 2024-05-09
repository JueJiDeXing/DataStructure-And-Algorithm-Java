package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.HashMap;
import java.util.Scanner;

/**
 已AC
 */
public class C左移右移 {
    /*
    一个数组初始为[1,2,3,...,n]
    执行M次操作,每次会对数x进行左移或右移,即移动到数组最左边或者最右边

    输入:
    第一行: N:数组长度; M:操作次数
    第k行: 形式为 L x 或 R x,表示左移或右移数字x; 例如:L 3 将3移动到最左侧[3,1,2,...]

    输出N个数表示操作后的数组
    1<=x<=N,M<=2*10^5
     */

    /**
     使用双向链表+哈希表实现O(1)操作
     元素x->哈希表中节点x->从链表中移除节点x->将节点放在链表头或尾
     */
    public static void main(String[] args) {
        main_enter();
    }

    private static void main_enter() {
        //初始化链表和哈希表
        HashMap<Integer, Node> map = new HashMap<>();
        LinkedList list = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            Node value = new Node(i);
            map.put(i, value);
            list.addToRight(value);
        }
        //M次操作
        int M = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < M; i++) {
            String operation = sc.nextLine();
            int x = Integer.parseInt(operation.substring(2));
            Node node = map.get(x);
            list.remove(node);
            if (operation.charAt(0) == 'L') {
                list.addToLeft(node);
            } else {
                list.addToRight(node);
            }
        }
        System.out.println(list);
    }

    static class Node {
        Node prev, next;
        int val;

        public Node(int val) {
            this.val = val;
        }
    }

    static class LinkedList {
        Node head = new Node(-1), tail = new Node(-1);

        public LinkedList() {
            head.next = tail;
            head.prev = tail;
            tail.prev = head;
            tail.next = head;
        }

        public void addToLeft(Node node) {
            Node n = head.next;
            node.prev = head;
            node.next = n;
            head.next = node;
            n.prev = node;
        }

        public void addToRight(Node node) {
            Node p = tail.prev;
            node.prev = p;
            node.next = tail;
            p.next = node;
            tail.prev = node;
        }

        public void remove(Node node) {
            Node p = node.prev;
            Node n = node.next;
            p.next = n;
            n.prev = p;
        }

        @Override
        public String toString() {
            Node p = head.next;
            StringBuilder ans = new StringBuilder();
            while (p.next != tail) {
                ans.append(p.val).append(" ");
                p = p.next;
            }
            ans.append(p.val);
            return ans.toString();
        }
    }

}
