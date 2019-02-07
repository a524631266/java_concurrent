package zhangll.learningplayground.level2.cap4observer.multithread;

import java.util.List;

import zhangll.learningplayground.level2.cap4observer.multithread.ThreadAction.ThreadEvent;

public class OneObserver implements ThreadObserver {
    private final static Object LOCK = new Object();

    /**
     * 这个是核心,我装我自己
     * 
     * @param ta
     */
    public OneObserver(ThreadAction ta) {
        ta.setTo(this);
    }

    /**
     * 2. 这是是对观察这分发给多个线程处理 这里已经失去代理观察者的含义,更多的是以线程作为观察者
     */
    public OneObserver(List<Integer> ids) {
        ids.stream().forEach((i) -> new ThreadAction(this) {
            @Override
            public void run() {
                try {
                    while (true) {
                        this.published(new ThreadEvent(Thread.currentThread(), ThreadState.RUNNABLE, null));
                        Thread.sleep(1000);
                        System.out.println("compulte:ids" + i);
                        // int a = 1 / 0;
                        this.published(new ThreadEvent(Thread.currentThread(), ThreadState.RUNNING, null));
                    }
                } catch (Exception e) {
                    this.published(new ThreadEvent(Thread.currentThread(), ThreadState.DEAD, e));
                }
            }
        }.start());
    }

    @Override
    public void onEvent(ThreadEvent te) {
        synchronized (LOCK) {

            if (te.getTw() != null) {
                System.out.println(te.getT().getName() + "线程的状态:" + te.getTs() + "::::" + te.getTw().getMessage());
            } else {
                System.out.println(te.getT().getName() + "线程的状态:" + te.getTs() + "::::");
            }
        }
    }
}