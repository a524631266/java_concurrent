package zhangll.learningplayground.concurrent.cap5daemon;

public class DaemonThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("111111");
        });
        // 概念1 设置成false(default),代表着t不是一个守护进程,那么就不会随着main线程的结束而结束
        // t.setDaemon(false);
        // true为守护进程,也就是弟们,土地
        t.setDaemon(true);
        t.start();
        System.out.println("main out");
    }
}