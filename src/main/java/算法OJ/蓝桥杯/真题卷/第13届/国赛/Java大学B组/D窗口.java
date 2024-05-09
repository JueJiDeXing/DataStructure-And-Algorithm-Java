package 算法OJ.蓝桥杯.真题卷.第13届.国赛.Java大学B组;

import java.util.*;

/**
 已AC
 */
public class D窗口 {
    /*
    桌面长M,宽N,左上角坐标(0,0),右下角坐标(N-1,M-1)

    现有5种操作:
    1. new [PID] [top] [left] [height] [width] 创建编号为PID的窗口,窗口置顶
    2. move [PID] [vertical] [horizontal] 移动窗口,负数表示向左/上移动,窗口置顶
    3. resize [PID] [height] [width] 保持窗口左上角坐标不变,改变大小,窗口置顶
    4. close [PID] 关闭窗口
    5. active [PID] 激活窗口,窗口置顶

    输入:
    第一行: N,M:窗口大小
    第二行: K:操作序列
    第k行: 每行一个操作

    输出:画出窗口图
    第1~N行,每行M个字符
    . 背景   + 窗口角  - | 窗口的横边与竖边   窗口内部为空白
7 10
8
new 1 0 3 2 5
new 2 4 4 2 5
new 3 3 3 4 6
resize 3 3 6
move 1 0 5
close 2
new 4 1 1 3 5
active 3

     */

    static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine();
        LinkedList list = new LinkedList();
        HashMap<Integer, Node> mapToNode = new HashMap<>();//pid->node->node.window
        for (int i = 0; i < K; i++) {
            String[] operate = sc.nextLine().split(" ");
            doOperate(operate, mapToNode, list);
        }
        //目前list顺序存储了从顶层到底层的窗口对象
        char[][] ans = Image(list);//通过list生成窗口图像
        for (char[] a : ans) {//打印
            System.out.println(new String(a));
        }
    }

    /**
     处理一个操作operate
     */
    private static void doOperate(String[] operate, HashMap<Integer, Node> mapToNode, LinkedList list) {
        String method = operate[0];
        int pid = Integer.parseInt(operate[1]);
        if (method.equals("new")) {//新增
            Node node = new Node(new Window(operate));
            mapToNode.put(pid, node);
            list.addFirst(node);
            return;
        }
        //其他操作
        Node node = mapToNode.get(pid);
        list.remove(node);//移除后放到链表头即为置顶
        if (method.equals("move")) {//移动左上角坐标
            node.window.top += Integer.parseInt(operate[2]);
            node.window.left += Integer.parseInt(operate[3]);
        } else if (method.equals("resize")) {//关闭窗口宽高
            node.window.height = Integer.parseInt(operate[2]);
            node.window.width = Integer.parseInt(operate[3]);
        }
        if (!method.equals("close")) {//除了close,其余的都要置顶
            list.addFirst(node);
        }
    }


    /**
     打印窗口
     */
    private static char[][] Image(LinkedList list) {
        char[][] ans = new char[N][M];
        for (char[] a : ans) {
            Arrays.fill(a, '.');//"."表示背景
        }
        Node p = list.tail.prev;//自底而上打印,保证覆盖关系正确
        while (p != list.head) {
            //对窗口的三个区域分别处理
            Window window = p.window;
            checkCenter(ans, window);
            checkCorner(ans, window);
            checkBorder(ans, window);
            p = p.prev;
        }
        return ans;
    }

    private static void checkBorder(char[][] ans, Window window) {
        int left = Math.max(0, window.left + 1), right = Math.min(M, window.left + window.width - 1);
        for (int j = left; j < right; j++) {
            if (isInRange(window.top, N - 1)) ans[window.top][j] = '-';
            if (isInRange(window.top + window.height - 1, N - 1)) ans[window.top + window.height - 1][j] = '-';
        }
        int top = Math.max(0, window.top + 1), bottom = Math.min(N, window.top + window.height - 1);
        for (int i = top; i < bottom; i++) {
            if (isInRange(window.left, M - 1)) ans[i][window.left] = '|';
            if (isInRange(window.left + window.width - 1, M - 1)) ans[i][window.left + window.width - 1] = '|';
        }
    }

    private static void checkCenter(char[][] ans, Window window) {
        int top = Math.max(0, window.top + 1), bottom = Math.min(N, window.top + window.height - 1),
                left = Math.max(0, window.left + 1), right = Math.min(M, window.left + window.width - 1);
        for (int i = top; i < bottom; i++) {
            for (int j = left; j < right; j++) {
                ans[i][j] = ' ';
            }
        }
    }

    private static void checkCorner(char[][] ans, Window window) {
        if (isInRange(window.top, N - 1)) {
            if (isInRange(window.left, M - 1)) ans[window.top][window.left] = '+';
            if (isInRange(window.left + window.width - 1, M - 1))
                ans[window.top][window.left + window.width - 1] = '+';
        }
        if (isInRange(window.top + window.height - 1, N - 1)) {
            if (isInRange(window.left, M - 1)) ans[window.top + window.height - 1][window.left] = '+';
            if (isInRange(window.left + window.width - 1, M - 1))
                ans[window.top + window.height - 1][window.left + window.width - 1] = '+';
        }
    }

    /**
     判断x是否在[0,R]内
     */
    private static boolean isInRange(int x, int R) {
        return 0 <= x && x <= R;
    }

    static class Window {
        int pid, top, left, height, width;

        public Window(String[] data) {
            this.pid = Integer.parseInt(data[1]);
            this.top = Integer.parseInt(data[2]);
            this.left = Integer.parseInt(data[3]);
            this.height = Integer.parseInt(data[4]);
            this.width = Integer.parseInt(data[5]);
        }
    }

    static class Node {
        Node prev, next;
        Window window;

        public Node() {
        }

        public Node(Window window) {
            this.window = window;
        }
    }

    static class LinkedList {
        Node head = new Node(), tail = new Node();

        public LinkedList() {
            head.next = tail;
            head.prev = tail;
            tail.prev = head;
            tail.next = head;
        }

        public void addFirst(Node node) {
            Node n = head.next;
            node.prev = head;
            node.next = n;
            head.next = node;
            n.prev = node;
        }


        public void remove(Node node) {
            Node p = node.prev;
            Node n = node.next;
            p.next = n;
            n.prev = p;
        }
    }
}
