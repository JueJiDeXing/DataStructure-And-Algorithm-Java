package 数据结构实现.设计数据结构;

import lombok.Getter;

import java.util.*;

public class _355推特 {
    /*
    设计一个简化版的推特(Twitter)，
    可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。

    实现 Twitter 类：

    Twitter()
         初始化简易版推特对象
    void postTweet(int userId, int tweetId)
         根据给定的 tweetId 和 userId 创建一条新推文。
         每次调用此函数都会使用一个不同的 tweetId 。
    List<Integer> getNewsFeed(int userId)
         检索当前用户新闻推送中最近 10 条推文的 ID 。
         新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。
         推文必须 按照时间顺序由最近到最远排序 。
    void follow(int followerId, int followeeId)
         ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
    void unfollow(int followerId, int followeeId)
         ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。
     */
    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.follow(1, 5);
        List<Integer> res = twitter.getNewsFeed(1);
        System.out.println(res);
    }
}

class Twitter {

    @Getter
    static class Tweet {//文章,链表型
        int id, time;
        Tweet next;

        public Tweet(int id, int time, Tweet next) {
            this.id = id;
            this.time = time;
            this.next = next;
        }

        public Tweet() {
        }
    }

    static class User {//用户
        int id;

        public User(int id) {
            this.id = id;
        }

        Set<Integer> followees = new HashSet<>();//用户的关注列表
        Tweet head = new Tweet();//用户的文章链表
    }

    Map<Integer, User> userMap = new HashMap<>();// 用户id -> 用户对象
    private static int time = 0;//全局时间戳

    public Twitter() {

    }

    /**
     用户发一篇文章

     @param userId  用户id
     @param tweetId 文章id
     */
    public void postTweet(int userId, int tweetId) {
        User user = userMap.computeIfAbsent(userId, User::new);//根据id获取用户对象
        user.head.next = new Tweet(tweetId, time++, user.head.next);//向用户文章链表头插入一篇文章
    }

    /**
     获取最新的10篇文章,文章必须是用户和用户关注的人发的

     @param userId 用户id
     @return List<Integer>10篇文章对应的id
     */
    public List<Integer> getNewsFeed(int userId) {
        User user = userMap.get(userId);
        if (user == null) return new ArrayList<>();//没有这个用户,没有文章

        PriorityQueue<Tweet> queue = new PriorityQueue<>(
                Comparator.comparingInt(Tweet::getTime).reversed()
        );//优先级队列,按时间排序
        if (user.head.next != null) {
            queue.offer(user.head.next);//添加用户发的文章
        }
        for (Integer id : user.followees) {//添加用户的关注列表发的文章
            User followee = userMap.get(id);
            if (followee.head.next != null) {
                queue.offer(followee.head.next);
            }
        }
        //合并k个有序链表
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty() && count < 10) {//选择时间最靠前的十篇
            Tweet max = queue.poll();//抛出最早的一篇
            res.add(max.id);
            if (max.next != null) {
                queue.offer(max.next);//选择一篇文章后,将下一篇文章加入
            }
            count++;
        }
        return res;
    }

    /**
     用户关注某人

     @param followerId 用户id
     @param followeeId 关注人
     */
    public void follow(int followerId, int followeeId) {
        User user = userMap.computeIfAbsent(followerId, User::new);
        userMap.computeIfAbsent(followeeId, User::new);//没有则需要创建关注对象
        user.followees.add(followeeId);
    }

    /**
     用户取关某人

     @param followerId 用户id
     @param followeeId 关注人
     */
    public void unfollow(int followerId, int followeeId) {
        User user = userMap.computeIfAbsent(followerId, User::new);
        user.followees.remove(followeeId);//直接remove,元素不存在不会报错
    }
}
