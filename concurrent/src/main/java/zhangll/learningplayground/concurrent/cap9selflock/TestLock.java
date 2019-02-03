package zhangll.learningplayground.concurrent.cap9selflock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class TestLock {
    public static void main(String[] args) {
        MyLock ml = new MyLock();
        Stream.of("T1", "T2", "T3", "T4").forEach((name) -> {

            new Thread(() -> {
                try {
                    // 1.此时的lock就是要装载一个lock
                    ml.lock();
                    Thread.sleep(5_000);
                    System.out.println(name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    ml.unlock();
                }
            }, name).start();
        });
        // 这是一个bug，所有需要确保谁持有当前线程才能该释放本次的锁 在锁中添加一个判断
        ml.unlock();
    }
}

class TestCollection {
    public static void main(String[] args) {
        Collection<String> cs = new ArrayList<String>();
        cs.add("a");
        cs.add("b");
        cs.add("c");
        cs.forEach((name) -> {
            Optional.of(name).ifPresent(System.out::println);
        });
        cs = Collections.unmodifiableCollection(cs);
        cs.add(":");
    }
}