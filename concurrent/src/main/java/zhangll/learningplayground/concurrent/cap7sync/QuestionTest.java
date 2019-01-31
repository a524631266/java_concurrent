package zhangll.learningplayground.concurrent.cap7sync;

public class QuestionTest {
    public static void main(String[] args) {
        ThreadT tt = new ThreadT();
        Thread n1 = new Thread(tt, "一号");
        Thread n2 = new Thread(tt, "二号");
        Thread n3 = new Thread(tt, "三号");
        n1.start();
        n2.start();
        n3.start();
    }
}

class ThreadT implements Runnable {
    private int max = 500;
    private int index = 0;
    private final Object LOCK = new Object();

    @Override
    public void run() {

        while (true) {
            synchronized (LOCK) {
                if (index > max) {
                    System.out.println("结束");
                    break;
                }
                try {
                    Thread.sleep(5); // 在这里是block了,所会执行下去
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "::" + index++);
            }
        }
    }

}