package 数据结构.栈.栈问题.单调栈应用;

import java.util.LinkedList;

public class _1944从队列中可以看到的人数 {

    /**
     <h1>暴力</h1>
     对每个人求他能看到的人数
     i能看到的人数为,从i+1开始,比他低且构成递增序列的长度(还要加上最后一个比他高的)
     */
    public int[] canSeePersonsCount1(int[] heights) {
        int n = heights.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {//对每个人求他能看到的人数
            int h = heights[i];
            int j = i + 1;//从i+1开始寻找递增序列
            int currH = 0;//记录当前高度最大值
            while (j < n) {
                if (heights[j] >= h) {//高度比i高,停止寻找,后面的都看不见
                    ans[i]++;//再加上这个高的人
                    break;
                }
                if (heights[j] > currH) {//如果高度没有currH高说明看不见,就不加
                    ans[i]++;
                }
                currH = Math.max(currH, heights[j]);//记录当前最大高度
                j++;
            }
        }
        return ans;
    }

    static class Pair {
        int key;
        int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     <h1>单调栈</h1>
     单调递减栈,此题本质是求右侧第一个比他大的元素
     case 1: 遇到低的,直接入栈
     case 2: 当遇到高的,抛出栈中低的,因为低的碰到高的说明后面已经看不到了
     此时低的+1,因为他能看到这个高的,低的前面一个比他高的+1,因为这个高的能看到刚抛出的低的
     */
    public int[] canSeePersonsCount2(int[] heights) {
        int n = heights.length;
        LinkedList<Pair> stack = new LinkedList<>();//单调递减栈,index,value
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (!stack.isEmpty() && stack.peek().value < h) {//遇到高的
                Pair pop = stack.pop();
                ans[pop.key]++;//抛出的加->因为当前高的可以被他看到
                if (stack.peek() != null) {
                    ans[stack.peek().key]++;//抛出的那个的前面一个加->前面那个可以看见抛出的那个
                }
            }
            stack.push(new Pair(i, h));
        }
        //最后剩余一个递减序列,每个人都只能再看一个
        if (!stack.isEmpty()) {//最后一个人右边没有,看不到
            stack.pop();
        }
        while (!stack.isEmpty()) {//每个人看一个
            Pair pop = stack.pop();
            ans[pop.key]++;
        }
        return ans;
    }

    //使用数组替换栈进行改写
    public int[] canSeePersonsCount3(int[] heights) {
        int n = heights.length;
        //单调递减栈,index,value
        Pair[] stack = new Pair[n];
        int stackLen = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int h = heights[i];
            while (stackLen > 0 && stack[stackLen - 1].value < h) {//遇到高的
                Pair pop = stack[--stackLen];
                ans[pop.key]++;//抛出的加->因为当前高的可以被他看到
                if (stackLen > 0) {
                    ans[stack[stackLen - 1].key]++;//抛出的那个的前面一个加->前面那个可以看见抛出的那个
                }
            }
            stack[stackLen++] = new Pair(i, h);
        }
        //最后剩余一个递减序列,每个人都只能再看一个
        if (stackLen > 0) {//最后一个人右边没有,看不到
            stackLen--;
        }
        while (stackLen > 0) {//每个人看一个
            Pair pop = stack[--stackLen];
            ans[pop.key]++;
        }
        return ans;
    }
}
