package 数据结构.树.树问题.其他;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 之字型标号 {
    /*
    在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
    如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
    而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
    给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
    示例:
        输入：label = 14
        输出：[1,3,4,14]
     */
    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> path = new ArrayList<>();

        int row = 1;//统计行数
        int i = 1;
        while (i * 2 <= label) {
            row++;
            i *= 2;
        }

        if (row % 2 == 0) {
            label = reverse(label, row);//初始label在偶数行,为反转标号,再次反转后正常
        }
        while (row > 0) {
            if (row % 2 == 0) {//偶数行,反转标号
                path.add(reverse(label, row));
            } else {
                path.add(label);
            }
            row--;
            label /= 2;//满二叉树性质,父节点索引等于子节点索引除以2
        }
        Collections.reverse(path);
        return path;


    }

    /*
    获取反转标号(从左到右<-->从右到左)
    对于第row行,,每行都从左到右标号label的节点,在"之"字型标号为下的标号为2^(row-1) +  2^row -label-1
    */
    public int reverse(int label, int row) {
        return (1 << (row - 1)) + (1 << row) - 1 - label;
    }
}
