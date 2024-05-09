package 算法OJ.蓝桥杯.真题卷.第12届.省赛.Java大学A组;


import java.util.ArrayList;
import java.util.List;
/**
 已AC
 */
public class B直线 {
    /*
    0<=x<=19 , 0<=y<=20 的整点
    两点确定一条直线,有多少条不重复的直线
     */
    public static void main(String[] args) {
        List<Pair> set = new ArrayList<>();
        for (int x1 = 0; x1 <= 19; x1++) {
            for (int y1 = 0; y1 <= 20; y1++) {
                for (int x2 = 0; x2 <= 19; x2++) {
                    out:
                    for (int y2 = 0; y2 <= 20; y2++) {
                        if (x1 == x2 || y1 == y2) continue;
                        Pair cal = cal(x1, y1, x2, y2);
                        for (Pair p : set) {
                            if (p.equals(cal)) continue out;
                        }
                        set.add(cal);
                    }
                }
            }
        }
        System.out.println(set.size() + 41);//40257
        //System.out.println(set);
    }

    static Pair cal(int x1, int y1, int x2, int y2) {
        //y=kx+b
        //(y2-y1)/(x2-x1)=k   y-kx=y1-x1(y2-y1)/(x2-x1)=(y1(x2-x1)-x1(y2-y1))/(x2-x1)=(y1*x2-x1*y2)/(x2-x1)=b
        return new Pair((double) (y2 - y1) / (x2 - x1), (double) (y1 * x2 - x1 * y2) / (x2 - x1));
    }


    static class Pair {
        double k, b;

        public Pair(double k, double b) {
            this.k = k;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Math.abs(k - pair.k) <= 1e-6 && Math.abs(b - pair.b) <= 1e-6;
        }
    }
}
