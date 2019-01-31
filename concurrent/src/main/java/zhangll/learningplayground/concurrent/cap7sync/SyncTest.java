package zhangll.learningplayground.concurrent.cap7sync;

/**
 * javap -c SyncTest.class 查看汇编代码
 */
public class SyncTest {
    private static final Object LOCK = new Object();

    public static void main(String[] args) {

        Runnable run = () -> {
            synchronized (LOCK) {
                try {
                    Thread.sleep(20_000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        };
        Thread t1 = new Thread(run, "111");
        Thread t2 = new Thread(run, "22");
        Thread t3 = new Thread(run, "3333");
        t1.start();
        t2.start();
        t3.start();
    }
}
