package 算法.深搜_广搜.深度优先;

import java.util.Scanner;

public class _牛牛 {
    static int n = 5;
    static String[] strs;
    static int[] nums = new int[n];

    public static void main(String[] args) {
        //接收数据
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            strs = sc.nextLine().split(" ");
            for (int i = 0; i < n; i++) {
                switch (strs[i]) {
                    case "A":
                        nums[i] = 1;
                        break;
                    case "J":
                    case "Q":
                    case "K":
                        nums[i] = 10;
                        break;
                    default:
                        nums[i] = Integer.parseInt(strs[i]);
                }
            }
            //判断是否有牛
            boolean c = dfs(nums, 0, 0, 0);
            if (!c) {
                System.out.println("没有牛");
            } else {
                //有牛,判断牛几
                int sum = 0;
                for (int num : nums) {
                    sum += num;
                }
                int r = sum % 10;
                if (r == 0) {
                    System.out.println("牛牛");
                } else {
                    System.out.println("牛" + r);
                }
            }
        }
    }

    private static boolean dfs(int[] nums, int start, int count, int sum) {
        if (count == 3 && sum % 10 == 0) {
            return true;
        }
        for (int i = start; i < n; i++) {
            if (dfs(nums, i + 1, count + 1, sum + nums[i])) {
                return true;
            }
        }
        return false;
    }
}
