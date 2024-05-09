package 算法.数学.数论.质数;

public class 西尔维斯特定理 {
    /*
    对于互质的两个正整数a,b
    ax + by = ab - a - b无非负整数解
     */
    /*
    反证法:
    假设 ax + by = ab - a - b
    则 ab = a(x+1) + b(y+1)
    因为 a 乘以某个数 等于右边
    所以右边可以整除 a ,记为 a(x+1)+b(y+1) / a
    所以 b(y+1) / a
    由于条件 a , b 互质
    所以 (y+1) / a
    那么 y >= a - 1
    同理 x >= b - 1
    则 ax + by >= 2ab - a - b > ab - a - b
    所以 ax + by = ab - a - b无非负整数解
     */
}
