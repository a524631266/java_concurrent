package zhangll.learningplayground.concurrent.cap2;

class Runable1 implements Runnable {
    private int id = 1;
    private static int max = 50;

    /**
     * 可执行单元与控制单元分离的一种体现 thread是控制单元，而可执行单元是被继承的可runnable的子类或者任务
     */
    @Override
    public void run() {
        while (id < max) {
            System.out.println(Thread.currentThread() + ":" + (id++));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

public class TestRunable {
    public static void main(String[] args) {
        Runnable run = new Runable1();
        Thread a = new Thread(run, "a");
        Thread b = new Thread(run, "b");
        Thread c = new Thread(run, "c");
        a.start();
        b.start();
        c.start();
    }
}