package zhangll.learningplayground.level2.cap2waitset;

public class WaitSetTest {
    public static final Object LOCK = new Object();

    public static void work() {
        synchronized (LOCK) {
            System.out.println("开始等待");
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName());
                LOCK.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("唤醒1111");
        }
    }

    public static void main(String[] args) {
        // new Thread() {
        // @Override
        // public void run() {
        // work();
        // }
        // }.start();
        work();
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}