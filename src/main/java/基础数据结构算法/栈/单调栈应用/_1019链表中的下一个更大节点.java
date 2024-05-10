package 基础数据结构算法.栈.单调栈应用;

import 数据结构实现.链表.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 第 130 场周赛 Q3
 难度分:1571
 */
public class _1019链表中的下一个更大节点 {
    /*
    给定一个长度为 n 的链表 head

    对于列表中的每个节点，查找下一个 更大节点 的值。
    也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。

    返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
    如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
     */
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> ans = new ArrayList<>();//也可先求出链表长度
        ListNode p = head;
        Stack<int[]> stack = new Stack<>();
        int curr = 0;
        while (p != null) {
            while (!stack.isEmpty() && stack.peek()[1] < p.val) {
                int idx = stack.pop()[0];
                while (ans.size() <= idx) {
                    ans.add(0);
                }
                ans.set(idx, p.val);
            }
            stack.push(new int[]{curr, p.val});
            curr++;
            p = p.next;
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop()[0];
            while (ans.size() <= idx) {
                ans.add(0);
            }
            ans.set(idx, 0);
        }
        int[] res = new int[curr];
        for (int i = 0; i < curr; i++) {
            res[i] = ans.get(i);
        }
        return res;

    }
}
