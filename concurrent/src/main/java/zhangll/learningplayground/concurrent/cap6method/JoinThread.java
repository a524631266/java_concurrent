package zhangll.learningplayground.concurrent.cap6method;

import java.util.stream.IntStream;

public class JoinThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            IntStream.range(0, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));
        });
        Thread t2 = new Thread(() -> {
            IntStream.range(0, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));
        });
        t1.start();
        t2.start();
        // join等待线程执行完之后
        // t1 与 t2 仍然是并行触发的,join只针对main线程
        // 看源码有个wait(0)
        // while (isAlive()) { 当前线程是活着的情况下 A thread is alive if it has been started and
        // has not yet died. 所以一直while循环
        // wait(0); 这样就能够保证主线程能够堵塞了
        // }
        t1.join();
        t2.join();
        IntStream.range(0, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));
    }
}