package 算法OJ.蓝桥杯.真题卷.第14届.省赛.Java大学A组;

import java.util.*;

/**
 已AC
 */
public class B与或异或 {
    /**
     三种门电路
     对于长度为k的数组arr,你可以选择k-1个门,第i个门对arr[i]和arr[i+1]进行相应操作
     求初始输入arr=[1,0,1,0,1]最后结果为[1]的门的选择方案数

     例如:
     <pre>
     1 0 1 0 1
      | ^ ^ &
      1 1 1 0
       | & ^
       1 1 1
        | ^
        1 0
         |
         1
     </pre>
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 1, 0, 1};
        int ans = dfs(arr);
        System.out.println(ans);//30528
    }

    static int dfs(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0] == 1 ? 1 : 0;
        //生成长度为n-1的门的情况枚举
        List<List<Integer>> oper = new ArrayList<>();
        All(oper, 0, n - 1, new Stack<>());
        int res = 0;
        for (List<Integer> op : oper) {
            //每个排列生成一个长度为n-1的新数组
            int[] newArr = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                int o = op.get(i);
                if (o == 0) {
                    newArr[i] = arr[i] & arr[i + 1];
                } else if (o == 1) {
                    newArr[i] = arr[i] | arr[i + 1];
                } else {
                    newArr[i] = arr[i] ^ arr[i + 1];
                }
            }
            res += dfs(newArr);//dfs(arr)=Sum(dfs(newArr))
        }
        return res;
    }

    /**
     对门进行全情况枚举,0表示与门,1表示或门,2表示异或门

     @param oper  结果存储
     @param start 当前要枚举的门编号
     @param end   要枚举的门数量
     @param stack 存储当前枚举的情况
     */
    static void All(List<List<Integer>> oper, int start, int end, Stack<Integer> stack) {
        if (start == end) {
            oper.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < 3; i++) {
            stack.push(i);
            All(oper, start + 1, end, stack);
            stack.pop();
        }
    }
}
