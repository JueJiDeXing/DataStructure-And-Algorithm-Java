package 算法.数学.数论;

public class 最小公倍数 {
    //lcm(a,b) = (a * b) / gcd(a,b)
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
}
