package zhangll.learningplayground.concurrent.cap5daemon;

/**
 * 内嵌一个守护进程
 */
public class DaemonThreadNested {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Thread inner = new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep(1_000);
                        System.out.println("心跳心跳");
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
            inner.setDaemon(true);
            inner.start();
            System.out.println("t 线程关闭");
        });
        t.start();
        t.setDaemon(true);
        System.out.println("main 线程关闭");
        // 总结,daemon设置的线程逻辑只关注本线程的行为，不管父亲还是爷爷线程设置了没有，当前线程都会进行daemon规则
        // 规则为1. 设置true，就会伴随着父线程的结束而结束
        // 2.默认是不会结束的
        // 3.守护意味着主人死了,其也死去
    }
}