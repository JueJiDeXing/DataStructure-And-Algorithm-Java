package 算法.动态规划_贪心.贪心;

import java.util.Arrays;
import java.util.Comparator;

public class 背包问题 {

}

class 分数背包 {
    /*
    有n个液体,给出总重量以及总价值
    现在要取10升液体
    每次可以不取,全取,取一部分,问最高价值是多少

       编号     重量     价值
        0   ,    4    ,   24      水
        1   ,    8    ,   160     牛奶
        2   ,    2    ,   4000    五粮液
        3   ,    6    ,   108     可乐
        4   ,    1    ,   4000    茅台
    */
    static class Item {
        int id;
        int weight;
        int value;

        @Override
        public String toString() {
            return "Item(" + id + ")";
        }

        public Item(int id, int weight, int value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
        }

        public int unitValue() {
            return value / weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(0, 4, 24),
                new Item(1, 8, 160),
                new Item(2, 2, 4000),
                new Item(3, 6, 108),
                new Item(4, 1, 4000)
        };
        int total = 10;
        System.out.println(select(items, total));
    }

    /**
     <h1>贪心算法</h1>
     每次选择最大单位价值的物品
     */
    private static int select(Item[] items, int total) {
        //按单位价值排序
        Arrays.sort(items, Comparator.comparingInt(Item::unitValue).reversed());
        int max = 0;
        for (Item item : items) {
            if (total >= item.weight) {//可以拿完
                total -= item.weight;
                max += item.value;
            } else {//拿不完
                max += total * item.unitValue();
                break;
            }
        }
        return max;

    }
}

class _01背包 {
    /*
    有n个固体,给出总重量以及总价值
    现在要取不超过10克的物体
    每次可以不取,全取,问最高价值是多少

   编号     重量     价值
    0   ,   1    , 1000000   钻戒
    1   ,   4    , 1600      黄金
    2   ,   8    , 2400      红宝石
    3   ,   5    , 30        白银
    */
    static class Item {
        int id;
        int weight;
        int value;

        @Override
        public String toString() {
            return "Item(" + id + ")";
        }

        public Item(int id, int weight, int value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
        }

        public int unitValue() {
            return value / weight;
        }
    }

    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(0, 4, 24),
                new Item(1, 8, 160),
                new Item(2, 2, 4000),
                new Item(3, 6, 108),
                new Item(4, 1, 4000)
        };
        int total = 10;
        System.out.println(select(items, total));
    }

    /**
     <h1>贪心算法</h1>
     每次选择最大单位价值的物品<br>
     注意:可能无法达到最优解
     */
    private static int select(Item[] items, int total) {
        //按单位价值排序
        Arrays.sort(items, Comparator.comparingInt(Item::unitValue).reversed());
        int max = 0;
        for (Item item : items) {
            if (total >= item.weight) {//可以拿完
                total -= item.weight;
                max += item.value;
            }
            //拿不完,进入下次循环

        }
        return max;

    }
}
