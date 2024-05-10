package 数据结构实现.队列.阻塞队列;

public class 阻塞队列 {
    //多线程队列安全问题--消费者与生产者
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue2<String> queue = new BlockingQueue2<>(10);
        queue.offer("3");
        queue.offer("5");
        new Thread(() -> {
            try {
                String poll = queue.poll();
                System.out.println("poll_1 :" + poll);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "POLL_1").start();
        new Thread(() -> {
            try {
                String poll = queue.poll();
                System.out.println("poll_2 :" + poll);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }, "POLL_2").start();
        new Thread(() -> {
            try {
                queue.offer("元素");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "线程3").start();
    }
}

