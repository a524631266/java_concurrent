package zhangll.learningplayground.level2.cap5singlethreadedexecution;

import zhangll.learningplayground.level2.cap6readwritelock.ReadWriteLock;

public class Gate {
    private int count = 0;
    private String name;
    private String Address;
    private static ReadWriteLock LOCK = new ReadWriteLock();
    private long starttime = System.currentTimeMillis();

    // public synchronized void pass(String name, String Address) {
    // this.count++;
    // this.name = name;
    // this.Address = Address;
    // vertify();
    // System.out.println("释放锁" + (System.currentTimeMillis() - starttime));
    // }
    public void pass(String name, String Address) {
        try {
            LOCK.writelock();
            this.count++;
            this.name = name;
            this.Address = Address;
            vertify();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            LOCK.writeunlock();
            System.out.println("释放锁" + (System.currentTimeMillis() - starttime));
        }

    }

    private void vertify() {
        if (this.name.charAt(0) != this.Address.charAt(0)) {
            System.out.println("××××××××××Block×××××××" + toString());
        }
    }

    @Override
    public String toString() {
        return "[id:" + count + "],[name:" + name + "],[address" + Address + "]";
    }
}