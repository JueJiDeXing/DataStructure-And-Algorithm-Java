package 算法OJ.蓝桥杯.其他;

import java.util.*;
/*
### Nim游戏
n堆石子, 轮流选择任意一堆取任意正整数个石子, 每堆都取完\
求最后的胜者(取到最后一颗石子的人胜,无法再取的人输)

### 结论(附证明):
    (1) 最终状态: 每堆都为0, 此时是"输"状态
    (2) 如果各堆石子的异或和为0, 那么无论怎么操作,都会变为非0
    (3) 如果各堆石子的异或和不为0, 一定存在一个操作, 能将其变为0

基于以上三点:
    (1) 如果初始异或和不为0, 那么先手可以将其操作为0, 并且每次后手操作后将其重置为0,这样最后会到达最终状态,后手会输
    (2) 如果初始异或和为0, 与(1)相反, 后手是必胜的

所以,只需要判断初始异或和是否为0即可

### 结论证明:
(2) 异或和为0时, 操作会变为非0
    设当前为 a[1]^a[2]^...^a[n]=0
    假如操作了a[k], (a[k]!=0) , 将其变为x, (x<a[k]), 并且异或和依然为0
    因为 a[1]^a[2]^...^a[n]=0
    则 a[1]^a[2]^..^a[k-1]^a[k+1]^..^a[n] = a[k]
    则 a[1]^a[2]^...^a[k-1]^x^a[k+1]..^a[n]  = a[k]^x
    左式为操作后的结果, 操作假设是异或和依然为0, 所以左式为0
    0=a[k]^x , 推出x=a[k],与x<a[k]矛盾
(3) 异或和不为0时, 一定有操作可以变为0
    设当前 a[1]^a[2]^...^a[n] = x (x!=0)
    设x的二进制最高位是第k位, 记为x(k)=1
    因为x是a异或出的结果,a中一定有奇数个第k位为1的数
    设其中一个为a[i], a[i](k)=1
    所以 x^a[i]<a[i] // 因为x前面都是0,异或后不变,第k位x和a[i]都是1,异或后变为0, 变小了
    那么, 可以让a[i]变为x^a[i]
    则操作后 a[1]^a[2]^...x^a[i]...^a[n] = x^x = 0

```java
import java.util.*;

public class 糖果的游戏 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int v = 0;
        for (int i = 0; i < n; i++) v ^= sc.nextInt() - 1;// 留一个
        if (v == 0) {
            System.out.println("Qiao");
        } else {
            System.out.println("Lan");
        }
    }
}
```
 */
public class 糖果的游戏 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int v = 0;
        for (int i = 0; i < n; i++) v ^= sc.nextInt() - 1;
        if (v == 0) {
            System.out.println("Qiao");
        } else {
            System.out.println("Lan");
        }
    }
}
