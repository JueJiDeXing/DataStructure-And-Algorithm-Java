package 算法.数学.数论.模;

import 算法.数学.组合数学.组合数;

public class 模上的除法提取 {
    /**
     <pre>
    求 (a / k) mod b



    法1: (a mod kb) / k
     令 (a / k) mod b = x
     设 a / k = bt + x  --> x < b
     ∴ a = kbt + kx
       a mod kb = kx  <-- kx < kb
       (a mod kb) / k = x
       (a mod kb) / k = (a / k) mod b
     所以 (a / k) mod b = (a mod kb) / k
     即将除法提取到了模外



    法2: 乘法逆元,使用乘法代替除法
    示例: {@link 组合数#C4(int, int)}
     </pre>

     */
    public static void main(String[] args) {

    }
}
