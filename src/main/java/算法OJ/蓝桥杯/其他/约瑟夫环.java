package 算法OJ.蓝桥杯.其他;

import java.util.*;

/**
 已AC
 */
public class 约瑟夫环 {
    /*
    n个人围成环,编号1~n,从第k个人开始从1报数,报到m的出队
    求出队顺序
     */
    static class Node {
        Node next;
        int v;

        public Node(int val) {
            v = val;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), k = sc.nextInt() % n, m = sc.nextInt();
        k %= n;
        if (k == 0) k = n;
        Node head = new Node(k);//第k个人开始
        Node prev = head, curr;
        for (int i = 1; i < n; i++) {
            int val = (k + i) % n;
            curr = new Node(val == 0 ? n : val);
            prev.next = curr;
            prev = curr;
        }
        prev.next = curr = head;
        while (curr.next != curr) {
            for (int i = 1; i < m; i++) {//数到m,走m-1步
                prev = curr;
                curr = curr.next;
            }
            System.out.println(curr.v);
            prev.next = curr.next;
            curr = curr.next;
        }
        System.out.println(curr.v);//最后一个人
    }

}
