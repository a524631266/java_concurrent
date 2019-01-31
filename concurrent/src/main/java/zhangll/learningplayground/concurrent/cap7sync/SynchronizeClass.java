package zhangll.learningplayground.concurrent.cap7sync;

/**
 * 同步方法在静态方法以及静态代表块中
 */
public class SynchronizeClass {
    public static void main(String[] args) {
        new Thread("m1") {
            @Override
            public void run() {
                StaticClass.m1();
            }
        }.start();
        new Thread("m2") {
            @Override
            public void run() {
                StaticClass.m2();
            }
        }.start();
        new Thread("m3") {
            @Override
            public void run() {
                StaticClass.m3();
            }
        }.start();
    }
}

class StaticClass {
    // 只会在类初始化的时候调用一次
    static {
        // 类也是对象
        synchronized (StaticClass.class) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void m3() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}