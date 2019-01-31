package zhangll.learningplayground.concurrent.cap6method;

import java.util.Optional;

public class MethodThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("hello").ifPresent(System.out::println);
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t.start();
        // getName 获取线程的名字
        Optional.of(t.getName()).ifPresent(System.out::println);
        // getName 获取线程的id
        Optional.of(t.getId()).ifPresent(System.out::println);
        // getName 获取线程的优先级 默认为 5
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}