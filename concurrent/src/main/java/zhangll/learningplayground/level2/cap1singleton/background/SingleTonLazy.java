package zhangll.learningplayground.level2.cap1singleton.background;

import java.util.Arrays;

public class SingleTonLazy {
    private static Object foo;

    private SingleTonLazy() {

    }

    // 切记一定要加上同步快,用来同步这个实例,否者会出现线程不安全
    // 即会有10%的概率出现启动加载两个不同对象,可以运行下方进行实例讲解
    private synchronized static Object getInstance() {

        if (foo == null) {
            foo = new Object();
        }
        return foo;
    }

    public static void main(String[] args) {
        // 也是线程安全的
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).stream().forEach(i -> new Thread(i + "") {
            @Override
            public void run() {
                System.out.println(
                        "[+" + Thread.currentThread().getName() + "+]" + SingleTonLazy.getInstance().hashCode());
            }
        }.start());
        ;
    }
}