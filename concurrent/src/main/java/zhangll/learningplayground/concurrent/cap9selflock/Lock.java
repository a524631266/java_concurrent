package zhangll.learningplayground.concurrent.cap9selflock;

import java.util.Collection;

/**
 * 实现锁的接口的类中都有一个集合，用来存储所有线程
 */
public interface Lock {
    public static class TimeOutException extends Exception {

        private static final long serialVersionUID = 1L;

    }

    // 用来锁住锁，结合while和wait用来存储锁
    public void lock() throws InterruptedException;

    // 释放集合中的休眠线程
    public void unlock();

    // 获取线程所有
    public Collection<Thread> getThreadPool();
}