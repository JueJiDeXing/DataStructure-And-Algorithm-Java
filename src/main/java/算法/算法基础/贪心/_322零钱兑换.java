package 算法.算法基础.贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

class _322零钱兑换ERROR {
    /*
    给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
    请你计算并返回可以凑成总金额的最少硬币个数。如果任何硬币组合都无法凑出总金额，返回 -1 。
    假设每一种面额的硬币有无限个。
    题目数据保证结果符合 32 位带符号整数。
 */
    public static void main(String[] args) {
        System.out.println(change2(new int[]{5, 2, 1}, 18));
    }

    static int min = -1;

    /**
     <h1>暴力递归</h1>
     */
    public static int change(int[] coins, int amount) {
        coins = Arrays.stream(coins)
                .boxed() // 将int数组中的元素包装为Integer对象
                .sorted(Comparator.reverseOrder()) // 使用Comparator.reverseOrder()反转排序顺序
                .mapToInt(Integer::intValue) // 将Integer对象重新转换为int
                .toArray();
        System.out.println(Arrays.toString(coins));
        rec(0, coins, amount, new AtomicInteger(-1), new LinkedList<>(), true);
        return min;
    }

    /**
     求凑成剩余金额的解的个数<br>
     从索引0开始,分路暴力枚举,索引i只能选择i~coins.length-1的硬币,0~i-1的硬币已被处理过

     @param index     未处理的硬币索引
     @param coins     硬币
     @param remainder 剩余金额
     @param count     某一组合钱币的种数
     @param stack     报存组合的硬币信息
     @param first     是否为第一次调用函数
     */
    private static void rec(int index, int[] coins, int remainder, AtomicInteger count, LinkedList<Object> stack, boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        count.incrementAndGet();
        if (remainder == 0) {//
            System.out.println("有解:" + stack);
            if (min == -1) {
                min = count.get();
            } else {
                min = Math.min(min, count.get());//最少硬币数
            }

        } else if (remainder > 0) {//剩余金额>0 继续递归
            for (int i = index; i < coins.length; i++) {
                // 剩余金额-当前硬币 进入下次递归
                rec(i, coins, remainder - coins[i], new AtomicInteger(-1), stack, false);
            }
        }
        count.decrementAndGet();
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    /**
     <h1>贪心</h1>
     每次选择可选的最大面额的硬币<br>
     注:可能无法得到最优解
     */
    public static int change2(int[] coins, int amount) {
        coins = Arrays.stream(coins)
                .boxed() // 将int数组中的元素包装为Integer对象
                .sorted(Comparator.reverseOrder()) // 使用Comparator.reverseOrder()反转排序顺序
                .mapToInt(Integer::intValue) // 将Integer对象重新转换为int
                .toArray();
        int remainder = amount;
        int count = 0;
        for (int coin : coins) {
            while (remainder >= coin) {
                remainder -= coin;
                count++;
            }
            if (remainder == 0) {
                break;
            }
        }
        if (remainder > 0) {
            return -1;
        }
        return count;
    }


}
