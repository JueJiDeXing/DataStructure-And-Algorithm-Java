package 算法.数学.数论.平方;

public class 平方根倒数 {
    void dir() {
        Q_rsqrt(1.0F);//源码
        Q_rsqrt2();//修改后代码 C标准
        Q_rsqrt3();//修改后代码 C++20
        //TODO 寻找java版本
        算法解析();
    }

    /**
     <h1>使用说明:</h1>
     该算法已经是很久远的算法了,现在用的是64位操作系统，很多long不是32位而是64位，跟32位的float对不齐<br>
     所以需要进行修改才能使用
     */
    public float Q_rsqrt(float number) {
        long i;
        float x2, y;
        float threehalfs = 1.5F;

        x2 = number * 0.5F;
        y = number;
        i = (long) y;                       // evil floating point bit level hacking
        i = 0x5f3759df - (i >> 1);               // what the fuck?
        y = (float) i;
        y = y * (threehalfs - (x2 * y * y));   // 1st iteration
        //  y  = y * ( threehalfs - ( x2 * y * y ) );   // 2nd iteration, this can be removed
        return y;
    }

    public void Q_rsqrt2() {
        /*
        #include <stdint.h> // uint32_t

        //现在用的是64位操作系统，很多long不是32位而是64位，跟32位的float对不齐；所以第一步应当改用uint32_t。
        float Q_rsqrt2(float number) {
            union {
                float f;
                uint32_t i;
            } conv = {.f = number};
            conv.i = 0x5f3759df - (conv.i >> 1);
            conv.f *= 1.5F - (number * 0.5F * conv.f * conv.f);
            return conv.f;
        }
         */
        /*
        如果不导入stdint库,可以自定义无符号32位long型
        #if 2147483647L+1L == -2147483648L
        typedef long long32;
        typedef unsigned long ulong32;
        #else
        typedef int long32;
                typedef unsigned int ulong32;
        #endif
         */
    }

    public void Q_rsqrt3() {
        /*
        //注意需要C++20标准

        #include <iostream>
        #include <bit>
        #include <limits>

        constexpr float Q_rsqrt(float number) noexcept {
            float const y = std::bit_cast<float>(0x5f3759df - (std::bit_cast<unsigned int>(number) >> 1));
            return y * (1.5f - (number * 0.5f * y * y));
        }
         */
    }

    public void 算法解析() {
    /*
    前置知识:浮点数的二进制存储
        0          0000_0000    0000_0000_0000_0000_0000_000
        1位符号位   8位指数位     23位尾数位
        s           e              m

        例如:
        (8.25)_10 = (1000.01)_2 = 1.00001 * 2^3 = (1+m) * 2^e
        指数e为3 , 因为二进制的科学计数法首位一定为1,所以"1."不用存储,尾数m为0.00001
        用E表示8位指数的整型形式,由于指数可以是负数,所以存储时0~255映射到-128~127,e需要加上127再存储,即E=e+127
        用M表示23位尾数的整型形式,M=2^23 * m
        所以8.25的物理存储如下:0 0000_0010 0000_1000_0000_0000_0000_000

        其中重要的是 E = e + B (B=127) 和 M = L * m (L=2^23)

        回到正题:
        输入x,求x的平方根倒数y=1/√x
        y=x^(-1/2) 取log以2为底
        log _{2}{y}= -1/2 * log _{2}{x}
        由浮点数存储可知 n = (1+m) * 2^e
        则log _{2}{(1+m_y)*2^e_y} =-1/2 log _{2}{(1+m_x)*2^e_x}
        log _{2}{1+m_y} + e_y =-1/2 ( log _{2}{1+m_x} + e_x )
        由于函数f(x)=log _{2}{1+x}在0<x<1时可以用直线f(x)=x+b拟合,其中b可通过不断测试寻找优近似,一个优近似b=0.0450465
        则 m_y + b + e_y ≈ -1/2 ( m_x + b + e_x)
        将E=e+127,M=L*m代入
        化简得: M_y + L*E_y ≈ 3/2 L*(B-b) - 1/2 (M_x + L*E_x)
        回到浮点数存储
         0        0000_0000    0000_0000_0000_0000_0000_000
        1位符号位   8位指数位     23位尾数位
        s           e              m
        M+L*E等于指数与尾数拼接的31位整型,记为I
        则I_y ≈ 3/2 L*(B-b) - 1/2 I_x
        I_y ≈ 0x5f3759df - (I_x >> 1)
        即可得到y的初始近似值

        要得到更精确的结果可以在这个初始近似值基础上使用牛顿迭代法
        牛顿迭代公式:x_{n+1} = x_n - f(x_n) / f`(x_n)
        y = y * (threehalfs - (x2 * y * y))
        一般迭代一次精度就够了,如果追求更高的精度可以选择继续迭代
        y = y * (threehalfs - (x2 * y * y))
     */
    }
}
