package 算法OJ.蓝桥杯.真题卷.第10届.国赛.Java大学A组;

import java.util.*;
/**
 已AC
 */
public class I最优旅行 {
    /*
    节点:
    上海、广州、长沙、西安、杭州、
    济南、成都、南京、昆明、郑州、
    天津、太原、武汉、重庆、南昌、
    长春、沈阳、贵阳、福州

    从北京出发,每个节点至少待24h,每个节点只能去一次,最后回到北京
    出发时间是中午12点,每天的车次表固定,求最少的遍历时间
车次   出发站  到达站  出发时间  到达时间
北京   上海   16:40   22:35
北京   上海   19:08   23:40
上海   北京   17:55   22:36
广州   北京   11:13   21:10
北京   广州   12:13   22:19
上海   广州   15:25   23:38
广州   上海   08:00   14:50
广州   长沙   21:00   23:36
长沙   广州   17:55   20:39
长沙   北京   07:36   14:24
北京   长沙   14:41   21:14
上海   长沙   15:37   20:59
长沙   上海   09:00   13:41
西安   上海   08:49   14:45
上海   西安   16:12   22:54
北京   西安   14:00   18:20
西安   北京   13:30   17:55
西安   广州   09:57   17:39
广州   西安   11:24   20:09
广州   杭州   15:56   23:25
杭州   北京   07:48   12:20
北京   杭州   19:04   23:22
上海   杭州   21:30   22:28
杭州   上海   07:06   08:12
济南   上海   06:50   11:40
北京   济南   19:55   21:55
济南   北京   07:45   09:33
广州   济南   08:05   18:34
济南   广州   10:14   20:49
成都   北京   07:00   14:46
北京   成都   06:53   14:38
成都   南京   11:28   22:00
上海   南京   10:05   11:29
南京   上海   08:00   09:39
南京   杭州   16:19   17:40
杭州   南京   12:09   13:30
昆明   南京   10:20   21:14
南京   昆明   09:05   19:40
成都   昆明   08:51   14:29
昆明   成都   12:16   17:57
昆明   郑州   08:46   18:48
郑州   昆明   10:38   20:49
郑州   西安   07:52   10:24
西安   郑州   08:10   10:29
西安   重庆   17:06   22:56
重庆   西安   07:05   12:37
重庆   成都   06:50   08:07
成都   重庆   22:12   23:29
天津   重庆   08:05   19:39
重庆   天津   10:49   22:45
北京   天津   22:10   22:45
天津   北京   19:08   19:46
天津   太原   10:40   14:15
太原   天津   14:43   18:12
太原   上海   12:26   21:17
上海   太原   08:10   17:28
郑州   太原   13:17   17:16
太原   郑州   17:38   21:38
太原   杭州   12:50   21:10
杭州   太原   07:14   15:50
北京   太原   08:40   11:07
太原   北京   08:33   11:00
太原   武汉   16:37   22:29
武汉   太原   09:48   16:00
武汉   上海   08:00   11:53
上海   武汉   13:51   17:50
西安   武汉   15:18   19:48
武汉   西安   09:17   14:27
天津   武汉   14:56   20:41
武汉   天津   14:30   20:32
长沙   天津   08:47   16:56
天津   长沙   10:58   18:50
长沙   太原   09:23   17:55
太原   长沙   10:46   18:18
杭州   昆明   11:43   22:53
昆明   杭州   09:06   20:18
昆明   南昌   16:00   22:54
南昌   昆明   08:25   15:38
南昌   杭州   12:24   15:28
杭州   南昌   12:30   15:26
济南   长春   07:42   15:07
长春   济南   15:33   22:35
沈阳   长春   06:42   08:40
长沙   长春   07:21   21:09
长春   长沙   08:47   22:08
长春   北京   08:32   14:48
北京   长春   15:20   21:45
南京   成都   08:07   17:54
南京   长沙   09:27   14:10
长沙   南京   15:53   20:40
贵阳   南京   07:58   18:02
南京   贵阳   12:07   21:58
长沙   贵阳   13:17   16:55
贵阳   长沙   08:11   11:26
郑州   成都   07:10   13:19
成都   郑州   16:57   23:04
上海   郑州   14:15   18:12
郑州   上海   07:33   12:02
武汉   沈阳   07:23   19:03
沈阳   武汉   07:32   19:20
重庆   昆明   07:43   11:55
昆明   重庆   14:52   19:09
重庆   上海   08:48   20:56
上海   重庆   11:39   23:29
南昌   重庆   07:08   14:45
重庆   南昌   15:12   22:23
南京   南昌   13:00   17:21
南昌   南京   09:04   13:25
南昌   福州   08:13   11:09
福州   南昌   18:30   21:25
长春   上海   11:53   22:54
上海   长春   09:08   20:05
沈阳   成都   07:02   21:47
成都   沈阳   09:06   23:13
北京   沈阳   13:30   17:15
沈阳   北京   08:11   11:58
沈阳   太原   15:34   23:00
太原   沈阳   07:44   15:14
贵阳   成都   19:15   22:35
成都   贵阳   11:11   14:31
贵阳   广州   14:03   20:26
广州   贵阳   07:27   13:43
武汉   贵阳   08:01   13:25
贵阳   武汉   14:23   19:33
福州   广州   08:16   14:15
广州   福州   14:55   21:05
昆明   福州   11:11   22:02
福州   昆明   08:41   19:28
福州   上海   12:26   16:55
上海   福州   07:54   12:15
福州   杭州   14:45   18:32
杭州   福州   18:55   22:38

41613
     */
    static class Data {
        String endPos;
        String startTime, endTime;

        public Data(String endPos, String startTime, String endTime) {
            this.endPos = endPos;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Data data = (Data) o;
            return Objects.equals(endPos, data.endPos) && Objects.equals(startTime, data.startTime) && Objects.equals(endTime, data.endTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(endPos, startTime, endTime);
        }
    }

    static HashMap<String, List<Data>> graph = new HashMap<>();
    static Set<String> set = new HashSet<>();
    static long res = 0x3f3f3f;

    public static void main(String[] args) {
        String[] strings = new String[]{"上海", "广州", "长沙", "西安", "杭州", "济南", "成都", "南京", "昆明", "郑州", "天津", "太原", "武汉", "重庆", "南昌", "长春", "沈阳", "贵阳", "福州"};
        set.addAll(new ArrayList<>(Arrays.asList(strings)));
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 132; ++i) {
            String[] info = in.nextLine().split("   ");
            String startPos = info[0], endPos = info[1];
            String startTime = info[2], endTime = info[3];

            List<Data> edges = graph.computeIfAbsent(startPos, k -> new ArrayList<>());
            edges.add(new Data(endPos, startTime, endTime));
        }
        dfs("北京", 0, "12:00");//从北京12:00开始,当前走了0分钟,最后回到北京
        System.out.println(res + strings.length * 1440);//41613
    }

    private static void dfs(String start, long sum, String time) {
        if (sum >= res) return;
        if (set.isEmpty() && start.equals("北京")) {
            res = sum;
            System.out.println(res);//最后14253
            return;
        }
        for (Data edge : graph.get(start)) {
            int t = cnt(edge.startTime, edge.endTime) + cnt(time, edge.startTime);//从s[0]->s[1],花费时间=乘车时间+等待发车时间
            if (set.isEmpty() && edge.endPos.equals("北京")) {//最后回到北京
                dfs(edge.endPos, sum + t, edge.endTime);
            }
            if (set.contains(edge.endPos)) {//不到北京
                set.remove(edge.endPos);
                dfs(edge.endPos, sum + t, edge.endTime);
                set.add(edge.endPos);
            }
        }
    }

    private static int cnt(String startTime, String endTime) {
        String[] aa = startTime.split(":");
        String[] bb = endTime.split(":");
        int t = (Integer.parseInt(bb[0]) * 60 + Integer.parseInt(bb[1])) -
                (Integer.parseInt(aa[0]) * 60 + Integer.parseInt(aa[1]));
        return t < 0 ? 1440 + t : t;
    }
}
