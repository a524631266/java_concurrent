package zhangll.learningplayground.concurrent.cap12pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class SimpleThreadPool {
    private final static int DEFAULT_SIZE = 10;
    private final int size;
    // 1.先定义一个用来从线程池中执行任务用的
    private List<Runnable> threadspools = new ArrayList<Runnable>();
    // 2.为了统一管理这个池子,也需要用到threadGroup,用来统一管理这些thread
    // 比如tg.interrupt 统一关闭线程池等操作
    private ThreadGroup tg = new ThreadGroup("zll1");
    // 4.一个任务列表用来缓存任务thread列表
    private LinkedList<Runnable> threadcache = new LinkedList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init(size);
    }

    private void init(int size) {
        for (int i = 0; i < size; i++) {
            createThread(i);
        }
    }

    /**
     * 创建初识化的n个线程,并开启等待模式
     * 
     * @param i
     */
    private void createThread(int i) {
        Thread thread = new TaskThread(tg, "thread num:" + i);
        threadspools.add(thread);
        thread.start();
    }

    // 3.用来提交任务,同时执行任务
    public void submit(Runnable task) {
        synchronized (threadcache) {
            threadcache.addLast(task);
            threadcache.notifyAll();
        }
    }

    private enum ThreadState {
        FREE, RUNNING, BLOCK, DEAD
    }

    private class TaskThread extends Thread {

        public TaskThread(ThreadGroup tg, String name) {
            super(tg, name);
        }

        @Override
        public void run() {
            // 5.激活当前线程 ,首先需要wait ,且线程要永远执行
            START: while (true) {
                Runnable runtask;
                synchronized (threadcache) {
                    while (threadcache.isEmpty()) {
                        try {
                            System.out.println("线程" + Thread.currentThread().getName() + "进入等待");
                            threadcache.wait();
                        } catch (InterruptedException e) {
                            // 打断以后要重新启动
                            break START;
                        }
                    }
                    System.out.println("cache是否为空" + threadcache.isEmpty());
                    runtask = threadcache.removeFirst();
                }
                if (runtask != null) {
                    runtask.run();
                }
            }
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool stp = new SimpleThreadPool();
        IntStream.rangeClosed(0, 40).forEach((i) -> {
            stp.submit(() -> {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("当前线程 " + i + Thread.currentThread().getName());
            });
        });
    }
}