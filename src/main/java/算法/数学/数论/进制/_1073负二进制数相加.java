package 算法.数学.数论.进制;

import java.util.ArrayList;
import java.util.List;

public class _1073负二进制数相加 {
    /*
    给出基数为 -2 的两个数 arr1 和 arr2，返回两数相加的结果。

    数字以 数组形式 给出：数组由若干 0 和 1 组成，按最高有效位到最低有效位的顺序排列。
    例如，arr = [1,1,0,1] 表示数字 (-2)^3 + (-2)^2 + (-2)^0 = -3。
    数组形式 中的数字 arr 也同样不含前导零：即 arr == [0] 或 arr[0] == 1。

    返回相同表示形式的 arr1 和 arr2 相加的结果。两数的表示形式为：不含前导零、由若干 0 和 1 组成的数组。
     */

    /**
     <h1>模拟加法</h1>
     加数arr1[i],被加数arr2[i],进位carry,借位flag,当前位k<br>
     add=arr1[i]+arr2[i]+carry <br>
     若该位有借位(flag=1)则add需要减1
     <ul>
     <li>case 1: add=-2<br>
     add*(-2)^k = 0*(-2)^k + (-2)^(k+1)  <br>
     add=0 carry=1 <br>
     # 导致add=-2情况:两加数都为0,借位为-1且进位为-1<br>
     </li>
     <li>case 2: add=-1<br>
     向前面的第二位借位, add*(-2)^k + (-2)^(k+2) = (add+4)*(-2)^k <br>
     add+=4  carry不变  flag=2 <br>
     // 借位标识flag=2表示距离借位点距离,每次循环flag--,减到0时add--<br>
     再执行case 3<br>
     <p>
     # 导致add=-1情况:0+0-1(无借位) 0+0+0-1(有借位) 1+0-1-1(有借位)<br>
     </li>
     <li>case 3: add>=0<br>
     <ul>
     <li>case 3.1: add=2或3<br>
     add*(-2)^k = (add-2)*(-2)^k + 2*(-2)^(k+1) = (add-2)*(-2)^k - (-2)^(k+1)<br>
     add-=2 carry=-1<br>
     <p>
     # 导致add=3情况:1+1+1(无借位) 或 add=-1导致向前借位add=add+4=3<br>
     # 导致add=2情况:略
     </li>
     <li>case 3.2: add=0或1<br>
     没有进位,carry=0<br>
     <p>
     # 导致add=0或1情况:略
     </li>
     </ul>
     </li>
     </ul>
     */
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        if (arr1.length < arr2.length) return addNegabinary(arr2, arr1);//选一个长的做while循环条件
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        int carry = 0;
        int flag1 = -1, flag2 = -1;//挂载的借位任务标记,借位是当前位向前面的第二位借,flag减到0表示到借位点了
        while (i < arr1.length || flag1 >= 0 || flag2 >= 0 || carry != 0) {//当arr有数相加,或有借位,或有进位时继续运行
            //对应位相加,加低位进位,减借位
            int add = carry;
            if (i < arr1.length) add += arr1[arr1.length - i - 1];
            if (i < arr2.length) add += arr2[arr2.length - i - 1];
            if (flag1 == 0 || flag2 == 0) add--;//有借位任务,减去1,(借位任务在某一位只可能有一个)
            //分析相加后的情况
            //add最大为3 --> 两加数都为1,进位为1
            //add最小为-2 --> 两加数都为0,借位为-1和进位为-1
            if (add == -2) {//(-2)*(-2)^k=(-2)^(k+1) 前位+1
                carry = 1;
                add = 0;
            } else {
                if (add == -1) {//需要借位
                    //创建一个借位标记
                    if (flag1 > 0) flag2 = 2;
                    else flag1 = 2;
                    add += 4;//4*(-2)^k
                }

                if (add >= 2) {// add*(-2)^k = (add-2)*(-2)^k + 2*(-2)^(k+1) = (add-2)*(-2)^k - (-2)^(k+1) 前位-1
                    carry = -1;
                    add -= 2;
                } else {//add=0或1,不进位
                    carry = 0;
                }
            }
            ans.add(0, add);
            i++;
            flag1--;//flag减到0表示到借位点,没有借位任务时,flag为负数
            flag2--;
        }
        //去除前导0
        while (ans.get(0) == 0) {
            ans.remove(0);
            if (ans.isEmpty()) return new int[]{0};//移空了->相加后为0
        }
        //拷贝到数组中返回
        int[] res = new int[ans.size()];
        for (int j = 0; j < ans.size(); j++) {
            res[j] = ans.get(j);
        }
        return res;
    }
}
