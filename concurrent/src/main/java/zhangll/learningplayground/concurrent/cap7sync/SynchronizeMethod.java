package zhangll.learningplayground.concurrent.cap7sync;

/**
 * 同步方法
 */
public class SynchronizeMethod {
    public static void main(String[] args) {
        Thread1 tt = new Thread1();
        Thread n1 = new Thread(tt, "一号");
        Thread n2 = new Thread(tt, "二号");
        Thread n3 = new Thread(tt, "三号");
        n1.start();
        n2.start();
        n3.start();
    }
}

class Thread1 implements Runnable {

    private int max = 500;
    private int index = 0;
    private final Object LOCK = new Object();

    @Override
    public void run() {

        while (true) {
            if (ticket())
                break;
        }
    }

    public synchronized boolean ticket() {
        // 同步方法相当于 对this进行锁
        // synchronized (this) {
        if (index > max) {
            System.out.println("结束");
            return true;
        }
        try {
            Thread.sleep(5); // 在这里是block了,所会执行下去
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "::" + index++);
        // }
        return false;
    }
}