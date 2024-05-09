package 算法OJ.蓝桥杯.算法赛.强者挑战赛.第9场;

import java.io.*;
import java.util.*;

/**
 已AC
 */
public class _4贝贝的集合 {
    /*
    大小为n的可重集合 {2^a1,..2^an}
    一次操作:
    (1) 选择2个数, x < y ,将他们删除
    (2) 将2x插入到集合
    (3) 如果x!=y,则将y-x插入到集合
    求集合大小的最小可能值
     */
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    static int nextInt() {
        try {
            st.nextToken();
        } catch (Exception ignored) {

        }
        return (int) st.nval;
    }

    /**
     首先相同的项先进行合并
     排序后,假设是 2^a1,2^a2,...,2^max (a1 < a2 < ... < max)
     如果a1与max合并,合并1或多次后到达a2,可以与a2合并,再继续合并可以到a3,...
     这样只会剩下两项: 不断合并的a1,以及与a1合并的amax
     如果a1合并到大于max,那么后面肯定没有数了,而max合并之后是2^y-2^x形式,一定合并不了
     所以肯定会剩下两项
     <p>
     如果全部合并后只剩下一项,那就是1
     <p>
     先排序,每次取前两个,如果有不同,那么一定会剩下两项,输出2,如果相同,则合并,入队合并结果
     如果合并完了,没有不同的情况出现,那么就是剩下一项
     */
    public static void main(String[] args) {
        int n = nextInt();
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) queue.offer(nextInt());
        while (queue.size() >= 2) {
            int x = queue.poll(), y = queue.poll();
            if (x != y) {//发现不同,说明是与max合并,最终合并剩余两项
                System.out.println(2);
                return;
            }
            queue.offer(x + 1);
        }
        // 不需要与max合并,只剩下1项
        System.out.println(1);
    }
}
