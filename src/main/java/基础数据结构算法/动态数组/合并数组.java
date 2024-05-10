package 基础数据结构算法.动态数组;

/**
 合并两个有序数组,数组只有一个,但给出两个有序区间
 */
public class 合并数组 {

    /**
     <div color=rgb(155,200,80)>
     <h1>方法一:递归</h1>
     每次把较小的元素放在新数组中</div>

     @param a1     原始数组
     @param a2,k   结果数组与索引,初始为与a1长度相等的空数组
     @param i,iEnd 第一个有序区间的起点和终点
     @param j,jEnd 第二个有序区间的起点和终点
     */
    public void merge(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2, int k) {
        if (i > iEnd) {
            //拷贝a1剩余的j~jEnd区间至a2
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
            return;
        }
        if (j > jEnd) {
            //拷贝a1剩余的i~iEnd区间至a2
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
            return;
        }

        if (a1[i] < a1[j]) {
            a2[k] = a1[i];
            merge(a1, i + 1, iEnd, j, jEnd, a2, k + 1);
        } else {
            a2[k] = a1[j];
            merge(a1, i, iEnd, j + 1, jEnd, a2, k + 1);
        }

    }

    /**
     <div color=rgb(155,200,80)>
     <h1>方法二:非递归</h1>
     放在原数组中</div>

     @param a1     原始数组
     @param a2     结果数组与索引,初始为与a1长度相等的空数组
     @param i,iEnd 第一个有序区间的起点和终点
     @param j,jEnd 第二个有序区间的起点和终点
     */
    public void merge2(int[] a1, int i, int iEnd, int j, int jEnd, int[] a2) {
        int k = 0;
        while (i <= iEnd && j <= jEnd) {
            if (a1[i] < a1[j]) {
                a2[k] = a1[i];
                i++;
            } else {
                a2[k] = a1[j];
                j++;
            }
            k++;
        }
        if (i > iEnd) {
            System.arraycopy(a1, j, a2, k, jEnd - j + 1);
        }
        if (j > jEnd) {
            System.arraycopy(a1, i, a2, k, iEnd - i + 1);
        }

    }
}
