package zhangll.learningplayground.concurrent.cap6method;

/**
 * interrupt 方式可以给线程的sleep以及 wait方式监听到
 */
public class InterruptCase {
    public static void main(String[] args) {
        ThreadService ts = new ThreadService();
        // ts.execute(() -> {
        // // a) 模拟一个很长的连接
        // while (true) {

        // }
        // });
        ts.execute(() -> {
            // b) 模拟一个5秒
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        ts.shutdown(10_000);
    }
}

class ThreadService {
    // 1. 启动一个主执行线程
    private Thread mainThread;
    // 3. 是指内部inner是否执行完
    private boolean innerfinished = false;

    public void execute(Runnable runnable) {
        mainThread = new Thread(() -> {
            Thread inner = new Thread(runnable);
            // 2. 设置一个守护进程，当mainthread结束之后会直接挂掉
            inner.setDaemon(true);
            inner.start();
            try {
                // 4.等待内部线程结束
                inner.join();
                innerfinished = true;
            } catch (InterruptedException e) {
                // 一旦结束就会触发join或者主执行进程结束之后会受到这个消息
                System.out.println("内部线程已经结束");
            }
        });
        mainThread.start();
    }

    public void shutdown(long maxtime) {
        long start = System.currentTimeMillis();
        // 5.当启动任务的时候想关闭时候需要确保内部没有执行结束
        // 总不想结束之后还要循环遍历吧
        while (!innerfinished) {
            if (System.currentTimeMillis() - start > maxtime) {
                mainThread.interrupt();
                break;
            }
            // 6.一旦mainThread.interrupt();触发之后,谁会收到信息呢？保证能退出
            try {
                mainThread.sleep(1000);
                System.out.println("等待" + (System.currentTimeMillis() - start));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("被打断+++++++++++++");
                break;
            }
            // try {
            // Thread.sleep(1000);
            // System.out.println("等待" + (System.currentTimeMillis() - start));
            // } catch (InterruptedException e) {
            // // TODO Auto-generated catch block
            // e.printStackTrace();
            // System.out.println("被打断");
            // }
        }
        System.out.println("shuttdown完成关闭 耗时" + (System.currentTimeMillis() - start));
    }
}