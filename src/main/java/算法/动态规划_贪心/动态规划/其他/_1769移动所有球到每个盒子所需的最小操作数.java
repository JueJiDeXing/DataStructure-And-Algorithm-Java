package 算法.动态规划_贪心.动态规划.其他;

public class _1769移动所有球到每个盒子所需的最小操作数 {
    /*
    移动所有球到每个盒子所需的最小操作数
    有n个盒子。给你一个长度为 n 的二进制字符串 boxes,
    其中boxes[i]的值为'0'表示第i个盒子是空的,而 boxes[i] 的值为 '1' 表示盒子里有 一个 小球。
    在一步操作中，你可以将一个小球从某个盒子移动到一个与之相邻的盒子中。
    注意，操作执行后，某些盒子中可能会存在不止一个小球。
    返回一个长度为 n 的数组 answer,其中answer[i]是将所有小球移动到第 i 个盒子所需的 生成树 操作数。
    每个answer[i]都需要根据盒子的 初始状态 进行计算。
     */

    /**
     @param boxes 盒子,二进制字符串形式,0表示空,1表示有一个球
     @return int[]res 答案从初始状态,将球都移到索引i位置的操作次数
     */
    public int[] move(String boxes) {
        int length = boxes.length();
        int[] res = new int[length];
        int left_num = 0, right_num = 0;//左右侧小球的数量(不含自身)
        //遍历一次,计算移动到第一个盒子的操作数,并记录小球的数量
        for (int i = 1; i < length; i++) {
            if (boxes.charAt(i) == '1') {
                right_num++;
                res[0] += i;
            }
        }
        //左、右操作数,初始左操作数为0,右操作数为移动到索引0的盒子的总操作数
        int left_count = 0, right_count = res[0];
        /* 公式
        指针右移后:每个左侧小球的距离+1,每个右侧小球的距离-1

        第j个左操作数=第j-1个左操作数+第j个左侧小球数量   =>left_count += left_num;
        第j个右操作数=第j-1个右操作数-第j-1个右侧小球数量  =>right_count -= right_num;

        第j个操作数=第j个左操作数＋第j个右操作数     =>res[j] = left_count + right_count;

        每一次循环,指针向右移动一位
        如果第j-1位是1,左侧小球数+1;如果第j位是1,计算过后出操作数后右侧小球-1
         */
        for (int j = 1; j < length; j++) {
            if (boxes.charAt(j - 1) == '1') {//前一位是1,当前盒子需要移动的左边球数+1
                left_num++;
            }
            left_count += left_num;//left_num的自增放在前面,需要减掉第j-1位为1的距离
            right_count -= right_num;//right_num的自减放在后面,需要减掉第j位为1的距离
            res[j] = left_count + right_count;
            if (boxes.charAt(j) == '1') {
                right_num--;
            }
        }
        return res;
    }
}
