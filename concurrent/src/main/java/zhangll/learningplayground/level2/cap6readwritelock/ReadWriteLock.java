package zhangll.learningplayground.level2.cap6readwritelock;

/**
 * 创建一个共享锁rw = new ReadWriteLock() 读锁 rw.readlock() 释放读锁 rw.readunlock() 写锁
 * rw.writelock() 释放写锁 rw.writeunlock()
 */
public class ReadWriteLock {
    // 核心要点是以两种方式，两种状态计数以保持读写的记录
    // 同时这四个变量是临界区变量，所以要加锁，同时锁本身也是临界资源
    private int readingcount = 0;
    private int readwaitcount = 0;
    private int writingcount = 0;
    private int writewaitcount = 0;

    public synchronized void readlock() throws InterruptedException {
        readwaitcount++;
        try {
            // 一旦有写操作就要再等
            while (writingcount > 0) {

                this.wait();
            }
            readingcount++;
        } finally {
            readwaitcount--;
        }
    }

    public synchronized void readunlock() {
        readingcount--;
        this.notifyAll();
    }

    public synchronized void writelock() throws InterruptedException {

        writewaitcount++;
        try {
            // 一旦有写操作就要再等
            while (writingcount > 0 || readingcount > 0) {

                this.wait();
            }
            writingcount++;
        } finally {
            writewaitcount--;
        }
    }

    public synchronized void writeunlock() {
        writingcount--;
        this.notifyAll();
    }

    public static Integer testTry(String a) {
        try {
            return 1;
        } finally {
            // 即使返回了1 仍然执行该方法
            System.out.println("111111111111");
        }
    }

    public static void main(String[] args) {
        Integer a = testTry("!");
        System.out.println("a:" + a);
    }
}