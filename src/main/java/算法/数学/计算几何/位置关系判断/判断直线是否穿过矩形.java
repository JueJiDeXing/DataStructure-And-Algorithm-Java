package 算法.数学.计算几何.位置关系判断;

public class 判断直线是否穿过矩形 {
    /**
     将四个矩形的四个点代入直线方程,判断点是在直线下面还是上面(刚好落在直线上不计数)<br>
     case1:四个点都在直线下方,down=4,up=0,没有穿过<br>
     case2:四个点都在直线上方,down=0,up=4,穿过<br>
     case3:一部分在上方,一部分在下方,down和up均不为0<br>
     */
    public boolean isLinePassingRectangle(int a, int b, int c, int[][] rect) {
        int up = 0, down = 0;// 记录上下个数
        int pos;
        for(int[]r:rect){
            int x=r[0],y=r[1];
            pos = a * x + b * y + c;
            if (pos > 0) up++;
            if (pos < 0) down++;
        }
        return up > 0 && down > 0;// 有正有负说明当前格子被直线穿过
    }
}
