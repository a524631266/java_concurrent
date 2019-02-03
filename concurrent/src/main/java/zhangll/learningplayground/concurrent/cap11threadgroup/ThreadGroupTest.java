package zhangll.learningplayground.concurrent.cap11threadgroup;

import java.util.Arrays;

/**
 * A thread group represents a set of threads. In addition, a thread group can
 * also include other thread groups. The thread groups form a tree in which
 * every thread group except the initial thread group has a parent.
 * 
 * treamgroup对象可以管理线程 同时threadgroup用来存储一棵树,也
 * 
 * 有父亲组,同时父亲组也有儿子组
 * 
 * [1]这里非常重要的一个概念是main方法也是jvm通过反射机制开辟了一条主线程,main方法本身 也是一个拥有顶级threadgroup维护的
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        // 私有构造函数是给C做调用的,默认为main 也即是 jvm初始化的时候用的 看[1]
        ThreadGroup tg1 = new ThreadGroup("t1");
        // 设置守护意思为,其管理的线程都结束的时候 该group就会被gc回收
        tg1.setDaemon(true);
        Thread t1 = new Thread(tg1, "thread1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1_000);
                        System.out.println("[" + Thread.currentThread().getName() + "] " + "thread group 1 name is :"
                                + tg1.getName());
                        System.out.println("[" + Thread.currentThread().getName() + "] " + "thread group 1 激活数量"
                                + tg1.activeCount());
                        System.out.println("tg1 父节点的信息 :" + tg1.getParent().getMaxPriority());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        // tg1设置访问权限
        tg1.checkAccess();
        t1.start();
        // 添加一个ThreadGroup2 ,内嵌一个group ,用父亲组来创建一个子组
        ThreadGroup childtg = new ThreadGroup(tg1, "tg2");
        Thread t2 = new Thread(childtg, "thread2") {
            @Override
            public void run() {
                while (true) {
                    ThreadGroup currentTg = this.getThreadGroup();
                    try {
                        Thread.sleep(1_000);
                        System.out.println("[" + Thread.currentThread().getName() + "] " + "thread group 2 name is"
                                + childtg.getName());
                        System.out.println("[" + Thread.currentThread().getName() + "] " + "thread group 2 激活数量"
                                + childtg.activeCount());
                        // 可以获取父亲节点的一些信息,官方不太准确 ,
                        System.out.println("获取父亲节点有多少个可以激活的线程数 :" + currentTg.getParent().activeCount());
                        System.out.println("tg2 父节点的信息 :" + currentTg.getParent().getName());
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // api 1 枚举出 tg1中的所有线程
        Thread[] tds = new Thread[tg1.activeCount()];
        // 枚举出一系列线程 copy 操作
        tg1.enumerate(tds);
        Arrays.asList(tds).forEach(t -> {
            System.out.println("Get the thread name is : " + t.getName());
        });
        // 打断所有线程 孙子线程
        // 设置守护
        System.err.println("tg1 是否存活" + tg1.isDestroyed());
        tg1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.err.println("tg1 是否存活" + tg1.isDestroyed());

    }
}