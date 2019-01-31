package zhangll.learningplayground.concurrent.cap6method;

public class SetPriorityThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":do!");
        }, "t1");
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":do!");
        }, "t2");
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":do!");
        }, "t3");
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}