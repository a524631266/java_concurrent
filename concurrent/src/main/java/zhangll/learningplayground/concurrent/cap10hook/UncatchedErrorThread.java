package zhangll.learningplayground.concurrent.cap10hook;

import java.util.Arrays;

/**
 * 由于run方法无法正常获取异常方法
 * 
 * 即使有也是在run方法内部捕获异常,并不能
 * 
 * 抛出异常,所以thread也提供了一种方法能够
 * 
 * 统一处理那些未捕获异常的注入接口
 * 
 * @FunctionalInterface UncaughtExceptionHandler
 *                      这是一个函数接口,因此可以用java8新特性的lamda表达式写 void
 *                      uncaughtException(Thread t, Throwable e);
 */
public class UncatchedErrorThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int a = 1 / 0;
            System.out.println("捕获异常");
        });
        t1.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("当前名称" + thread.getName());
            System.out.println("异常信息" + ex.getMessage());
            Arrays.asList(Thread.currentThread().getStackTrace()).stream().forEach(e -> {
                System.out.println(e.toString());
            });
            ;
        });
        t1.start();
    }
}