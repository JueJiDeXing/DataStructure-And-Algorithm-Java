package 算法.数学.概率;

import 数据结构实现.链表.ListNode;

import java.util.Random;

public class 蓄水池抽样 {
    /*
    有一未知长度的数据流, 你每次需要从中随机地返回1个数据
     */

    /**
     第k次加入一个新数据ak
     有1/k的概率将ak保留至下一轮, 有(1-1/k)=(k-1)/k的概率舍弃ak,保留上次的数至下一轮
     <p>
     可以算出, 假设有n个数据, 第k个数据最终被保留的概率为 1/k * k/(k+1) * (k+1)/(k+2)*...= 1 / n
     这样就实现了随机地返回1个数据
     */
    static class Solution {
        ListNode head;
        Random random;

        public Solution(ListNode head) {
            this.head = head;
            this.random = new Random();
        }

        public int getRandom() {
            int reserve = 0;
            ListNode cur = head;
            int count = 0;
            while (cur != null) {
                count++;
                int r = this.random.nextInt(count) + 1;
                if (r == count) {
                    reserve = cur.val;
                }
                cur = cur.next;
            }
            return reserve;
        }
    }
}
