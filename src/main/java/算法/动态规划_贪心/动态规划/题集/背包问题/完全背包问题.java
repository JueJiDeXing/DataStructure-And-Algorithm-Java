package 算法.动态规划_贪心.动态规划.题集.背包问题;

import java.util.Arrays;

class 完全背包问题 {
    static class Item {
        int id;
        int weight;
        String name;
        int value;

        @Override
        public String toString() {
            return "Item(" + id + ")";
        }

        public Item(int id, int weight, String name, int value) {
            this.id = id;
            this.weight = weight;
            this.name = name;
            this.value = value;
        }


    }

    //每件物品都有无限多
    public static void main(String[] args) {
        Item[] items = new Item[]{
                new Item(1, 2, "青铜", 3),//c
                new Item(2, 3, "白银", 4),//s
                new Item(3, 4, "黄金", 7)//a
        };
        int total = 6;
        System.out.println(select(items, total));
        /*
                0   1   2   3   4   5   6
            1   0   0   c   c   cc  cc  ccc
            2   0   0   c   s   cc  sc  ccc
            3   0   0   c   s   a   a   ac
         */
    }

    /**
     <h1>动态规划_贪心</h1>
     二维数组,dp[i][j]表示装第i种物品最大容量j克<br>
     其中dp[i][0]=0 , dp[物品种数-1][最大容量]为所求结果<br>
     每次在最大容量j尝试装入物品i:<br>
     1.如果不能装下,保留上一级, dp[i][j] = dp[i-1][j]<br>
     2.如果能装下,比较价值, dp[i][j] = max( value + dp[i][j-weight] , dp[i-1][j] )<br>
     与01背包不同点:value + dp[i][j-weight] 因为物品无限,所以依然在本行寻找最大价值
     */
    public static int select(Item[] items, int total) {
        int[][] dp = new int[items.length][total + 1];
        Item firstItem = items[0];//第一个物品单独处理
        for (int i = 0; i <= total; i++) {
            if (i >= firstItem.weight) {//装不下
                dp[0][i] = firstItem.value + dp[0][i - firstItem.weight];//物品无限,能放入多个同一物品
            }
        }
        System.out.println(Arrays.deepToString(dp));
        System.out.println("--------------");
        for (int i = 1; i < items.length; i++) {
            Item item = items[i];
            for (int j = 0; j <= total; j++) {
                if (j < item.weight) {//装不下
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(item.value + dp[i][j - item.weight], dp[i - 1][j]);
                }
            }
            System.out.println(Arrays.deepToString(dp));
        }
        return dp[items.length - 1][total];
    }

    //降维
    public static int select2(Item[] items, int total) {
        int[] dp = new int[total + 1];

        //Item firstItem = items[0];//第一个物品单独处理
        //for (int i = 0; i <= total; i++) {
        //    dp[i] = total / firstItem.weight * firstItem.value;
        //}//与后面遍历的循环重复了
        System.out.println(Arrays.toString(dp));
        System.out.println("--------------");
        for (Item item : items) {//不特殊处理第一个物品,索引从0开始
            for (int j = 0; j <= total; j++) {//从左向右处理,因为处理后面的元素需要用到本次前面的元素
                if (j >= item.weight) {
                    dp[j] = Math.max(item.value + dp[j - item.weight], dp[j]);
                }
                //装不下则不变
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[total];
    }

}
