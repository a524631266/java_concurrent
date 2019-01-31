package zhangll.learningplayground.concurrent.cap6method;

class MyselfThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "->" + "");
        }
    }
}

class ProxyMyselfThread extends Thread {
    public void run() {
        Thread inner = new MyselfThread();
        inner.setDaemon(true);
        inner.start();
        // 等待自己死
        // 不管父亲有没有死掉我都不死,除非明确指出自己是Daemon的
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

public class JoinMyselfThread {
    public static void main(String[] args) {
        Thread t = new ProxyMyselfThread();
        // 设置为true会里面死掉，即使里面是等待它自己的
        // t.setDaemon(true);
        t.start();
    }
}