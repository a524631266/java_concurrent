package zhangll.learningplayground.concurrent.cap1;

class Thread01 extends Thread {
    public int max = 50;
    // 1. 各玩个的
    // public int id = 1;
    // 2. 共享一个变量
    private volatile int id = 1;
    final String name;

    public Thread01(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (id < max) {
            System.out.println("thread [" + name + "]" + (id++));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class TestThread {
    public static void main(String[] args) {
        Thread a = new Thread01("a");
        Thread b = new Thread01("b");
        Thread c = new Thread01("c");
        a.start();
        b.start();
        c.start();
        System.out.println("111111");
    }
}