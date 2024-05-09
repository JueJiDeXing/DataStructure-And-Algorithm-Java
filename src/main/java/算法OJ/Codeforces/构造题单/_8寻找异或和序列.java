package 算法OJ.Codeforces.构造题单;

import java.util.*;

/**
 已AC
 */
public class _8寻找异或和序列 {
    /*
    给定u和v,寻找一个最短的数组a,使得a的逐位异或为u,a的和为v
     */

    /**
     令d=v-u
     因为数组的异或为u, 考虑数组的一个数为u, 那么再来两个相同的数t
     t^t^u=u, t+t+u=v推出t=d/2
     则可行解为[t,t,u]

     考虑更优解:
     上述数组为[t,t,u], 考虑将t和u先行异或,[t,t^u], 异或仍满足t^t^u=u, 在t+t^u=v当t^u=t+u时成立

     证明长度为2的唯一性:
     假设解为[a,b],a+b=v,a^b=u
     令 a=u+x
     则解可以表示为[u+x,d-x], 其中d=v-u
     (u+x)^(d-x)=u
     由异或性质: a+b=a^b+2(a&b)
     得 (u+x+d-x)-2((u+x)&(d-x))=u
     (u+x)&(d-x)=d/2  -> 有 d/2<=d-x, x<=d/2
     由与或性质: a&b + a^b = a|b
     得 (u+x)|(d-x) - (u+x)^(d-x) = d/2
     (u+x)|(d-x) = d/2 + u  -> 有 u+x>=d/2+u, x>=d/2
     所以 仅有x=d/2一个值
     代入x验证: (u+d/2)^(d-d/2)=u
     (u+d/2)^d/2=u
     u+d/2 = u^(d/2)
     所以当长度为2的解仅可能有一个[u+d/2,d/2], 且需要满足条件 u+d/2 = u^(d/2)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long u = sc.nextLong(), v = sc.nextLong();
        if (v < u || u % 2 != v % 2) {//进位加法应该大于不进位加法,且奇偶性应该相同{a+b=a^b+2(a&b)}
            System.out.println("-1");
            return;
        }
        if (u == v) {//相等,直接填u即可
            if (u == 0) System.out.println(0);//特别的,u=v=0时,输出0
            else System.out.println("1\n" + u);// 不为0时,1个数字,输出1再输出u
            return;
        }
        long t = (v - u) >> 1;
        if ((t ^ u) == (t + u)) {
            // t ^ (t ^ u) = u, t + t ^ u = t + t + u = v - u + u = v
            System.out.println(2);
            System.out.println(t + " " + (t ^ u));
        } else {// t ^ t ^ u = u, t + t + u = v - u + u = v
            System.out.println(3);
            System.out.println(t + " " + t + " " + u);
        }
    }
}
