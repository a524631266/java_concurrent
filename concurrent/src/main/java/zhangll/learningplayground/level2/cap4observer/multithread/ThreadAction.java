package zhangll.learningplayground.level2.cap4observer.multithread;

public class ThreadAction extends Thread {
    // 1. 改类被设置为subject,所以需要注入观察者(方便起见,只注入一个了,不用多个)
    private ThreadObserver to;

    public ThreadAction() {

    }

    // 3. 注入观察者ThreadObserver,行为为注入观察者的时候马上开启监控模式
    public void setTo(ThreadObserver to) {
        this.to = to;
        this.start();
    }

    @Override
    public void run() {
        try {
            while (true) {

                this.published(new ThreadEvent(Thread.currentThread(), ThreadState.RUNNABLE, null));
                Thread.sleep(1000);
                System.out.println("compulte");
                int a = 1 / 0;
                this.published(new ThreadEvent(Thread.currentThread(), ThreadState.RUNNING, null));
            }
        } catch (Exception e) {
            this.published(new ThreadEvent(Thread.currentThread(), ThreadState.DEAD, e));
        }

    }

    // 2. 发布事件给观察者
    public void published(ThreadEvent te) {
        to.onEvent(te);
    }

    public enum ThreadState {
        RUNNABLE, RUNNING, BLOCK, DEAD
    }

    public class ThreadEvent {
        private final Thread t;
        private final ThreadState ts;
        private final Throwable tw;

        public ThreadEvent(Thread t, ThreadState ts, Throwable tw) {
            this.t = t;
            this.ts = ts;
            this.tw = tw;
        }

        public Thread getT() {
            return this.t;
        }

        public ThreadState getTs() {
            return this.ts;
        }

        public Throwable getTw() {
            return this.tw;
        }

        @Override
        public String toString() {
            return "{" + " t='" + getT() + "'" + ", ts='" + getTs() + "'" + ", tw='" + getTw() + "'" + "}";
        }
    }
}