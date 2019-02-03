package zhangll.learningplayground.concurrent.cap9selflock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MyLock implements Lock {
    private Collection<Thread> threadpool = new ArrayList<>();
    private boolean locked = false;
    // 修复bug
    private Thread currentThread;

    /**
     * 就是为了锁住所有的线程，但是此时所有线程都需要调用这个方法以
     * 
     * @throws InterruptedException
     */
    @Override
    public synchronized void lock() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (locked) {
            System.out.println("置入" + currentThread.getName());
            threadpool.add(currentThread);
            this.wait();
        }
        System.out.println("开启当前线程" + currentThread.getName());
        threadpool.remove(currentThread);
        locked = true;
        // bug修复
        this.currentThread = currentThread;
    }

    @Override
    public synchronized void unlock() {
        if (currentThread == Thread.currentThread()) {
            locked = false;
            // 使用notify或者notifyall的时候需要在同步快中，否者会报错
            this.notify();
            // this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getThreadPool() {
        // 这个是为了保证获取集合的时候不能被修改
        return Collections.unmodifiableCollection(threadpool);
    }

}
