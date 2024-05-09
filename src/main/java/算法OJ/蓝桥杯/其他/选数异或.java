package 算法OJ.蓝桥杯.其他;

import java.util.*;
/**
 已AC
 */
public class 选数异或 {
    /*
    给定一个长度为n的数组,和非负整数x
    有m次查询,每次询问[l,r]上是否存在a^b=x
     */

    /**
     [L,R]上存在a^b=x  等价于  有一个[L,R]上的位置j,[j,R]上存在a^b=x <br>
     令 MaxJ[r]: 最大的j(j < r), 其中j满足[j,r]上存在a^b=x <br>
     则处理出MaxJ后, 询问时只需要回答 MaxJ[r] 是否大于等于 l <br>
     <br>
     MaxJ的更新:<br>
     对于A[i], 如果它前面没有与它异或为x的数, 则MaxJ[i]=MaxJ[i-1]<br>
     如果它前面的A[max_j]与它异或为x, 则 MaxJ[i] = max_j<br>
     二者取max,MaxJ[i]=max{MaxJ[i-1],max_j}<br>
     <br>
     对于位置i如何找到max_j (i左侧最近的与A[i]异或为x的元素):<br>
     使用哈希表不断更新和查询最远位置即可
     由 a^b=x, 则 a=b^x
     哈希表存储 A[j]^x -> max_j, 则查找A[i]^A[j]=x时,只需要在哈希表里查找key=A[i]即可<br>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        long x = sc.nextLong();
        HashMap<Long, Integer> xorMap = new HashMap<>();//A[j]^x -> max_j
        int[] MaxJ = new int[n + 1];// MaxJ[r]: 最大的j(j < r), 其中j满足[j,r]上存在a^b=x
        for (int i = 1; i <= n; i++) {
            long a = sc.nextLong();
            MaxJ[i] = Math.max(MaxJ[i - 1], xorMap.getOrDefault(a, 0));
            xorMap.put(a ^ x, i);
        }

        for (int i = 0; i < m; i++) {
            int l = sc.nextInt(), r = sc.nextInt();
            System.out.println(MaxJ[r] >= l ? "yes" : "no");
        }
    }
}
