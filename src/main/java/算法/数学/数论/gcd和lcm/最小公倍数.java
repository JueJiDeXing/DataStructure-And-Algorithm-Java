package 算法.数学.数论.gcd和lcm;

public class 最小公倍数 {
    // 两个数的lcm: lcm(a,b) = (a * b) / gcd(a,b)
    /*
    设a,b的质因数分解为
    a = p1^m1 * p2^m2 * p3^m3 ...
    b = p1^n1 * p2^n2 * p3^n3 ...
    则 gcd(a,b) = p1^min(m1,n1) * p2^min(m2,n2) * p3^min(m3,n3)
    lcm(a,b) = p1^max(m1,n1) * p2^max(m2,n2) * p3^max(m3,n3)
    由于 mi+ni = min(mi,ni)+max(mi,ni)
    所以 a*b = gcd(a,b)*lcm(a,b)
    lcm(a,b) = a*b/gcd(a,b)
     */

    // 三个数的lcm: lcm(a,b,c) = (a * b * c * gcd(a,b,c)) / (gcd(a,b)*gcd(a,c)*gcd(b,c))
    /*
    x + y + z
    = min(x,y,z) + mid(x,y,z) + max(z,y,z)
    = 2 * min(x,y,z) + mid(x,y,z) + max(z,y,z) - min(x,y,z)
    = min(x,y) + min(x,z) + min(y,z) + max(x,y,z) - min(x,y,z)
     */
}
